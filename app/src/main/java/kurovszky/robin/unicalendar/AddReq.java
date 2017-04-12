package kurovszky.robin.unicalendar;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import kurovszky.robin.unicalendar.fragment.AddReqElement;
import kurovszky.robin.unicalendar.fragment.AddSubject;
import kurovszky.robin.unicalendar.model.Requirement;
import kurovszky.robin.unicalendar.view.CustomViewPager;
import kurovszky.robin.unicalendar.web_service.GrpcWebServiceImpl;
import kurovszky.robin.unicalendar.web_service.RestWebServiceImpl;
import kurovszky.robin.unicalendar.web_service.WebService;
import kurovszky.robin.unicalendar.web_service.model.Institute;
import kurovszky.robin.unicalendar.web_service.model.Subject;
import kurovszky.robin.unicalendar.web_service.model.User;
import kurovszky.robin.unicalendar.web_service.tools.StaticTools;

public class AddReq extends AppCompatActivity implements AddReqElement.OnFragmentInteractionListener, AddSubject.OnFragmentInteractionListener {
    private static final int FRAGMENT_COUNT = 2;
    AddSubject addSubject;
    MyAdapter adapter;
    CustomViewPager pager;
    Menu menu;
    FragmentManager fm = getSupportFragmentManager();
    WebService webService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_req);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        addSubject = new AddSubject();
        adapter = new MyAdapter(getSupportFragmentManager());
        adapter.setActivityCompat(this);
        pager = (CustomViewPager)findViewById(R.id.add_pager);
        pager.setPagingEnabled(false);
        pager.setAdapter(adapter);




    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void setReq(Requirement req) {
        addSubject.addRequirement(req);
    }
    public class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }
        AddReqElement aq;
        AppCompatActivity activityCompat;

        @Override
        public int getCount() {
            return FRAGMENT_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0) {
                ((TextView)activityCompat.findViewById(R.id.toolbar_title)).setText(R.string.add_subject);
                return addSubject;
            }

            if(position == 1) {
                ((TextView)activityCompat.findViewById(R.id.toolbar_title)).setText(R.string.add_requirement);
                aq = new AddReqElement();
                return aq;
            }
            return null;
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);
            if(menu != null) {
                MenuItem confirm = menu.findItem(R.id.confirm);
                confirm.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int cur = pager.getCurrentItem();
                        switch (cur) {
                            case 0:
                                finishAddActivity();
                                break;
                            case 1:
                                aq.confirmInput();
                                pager.setCurrentItem(0);
                        }
                        return false;
                    }
                });
            }
        }

        public void setActivityCompat(AppCompatActivity activityCompat) {
            this.activityCompat = activityCompat;
        }
    }

    String readSubjectName(){
        EditText et = (EditText) findViewById(R.id.subjectNameText);
        return et.getText().toString();
    }
    void finishAddActivity(){
        for(Requirement requirement: addSubject.getRequirements()){
            requirement.setSubject(readSubjectName());
            if(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("notification", false)) {
                String days =null;
                switch (requirement.getHardiness()) {
                    case 0:
                        days = PreferenceManager.getDefaultSharedPreferences(this).getString("very_easy_days","1");
                        break;
                    case 1:
                        days = PreferenceManager.getDefaultSharedPreferences(this).getString("easy_days","3");
                        break;
                    case 2:
                        days = PreferenceManager.getDefaultSharedPreferences(this).getString("moderate_days","5");
                        break;
                    case 3:
                        days = PreferenceManager.getDefaultSharedPreferences(this).getString("hard_days","7");
                        break;
                    case 4:
                        days = PreferenceManager.getDefaultSharedPreferences(this).getString("very_hard_days","9");
                        break;
                }
                int daysBefore = Integer.parseInt(days);
                long before = TimeUnit.DAYS.toMillis(daysBefore);
                Alarm alarm = new Alarm();
                long time = requirement.getTimeInDate().getTime()-before;
                Date date = new Date(time);
                Long timeToNotify = PreferenceManager.getDefaultSharedPreferences(this).getLong("notification_time", 0);

                Date timeToNotifyDate = new Date(timeToNotify);

                date.setHours(timeToNotifyDate.getHours());
                date.setMinutes(timeToNotifyDate.getMinutes());
                date.setSeconds(0);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
                String s = simpleDateFormat.format(date);
                alarm.setAlarm(this, date.getTime(), requirement);
            }
            requirement.save();

        }
        EditText subjectName = (EditText)findViewById(R.id.subjectNameText);
        if(subjectName.isEnabled()){
            User u = StaticTools.loadUserFromPrefs(getApplicationContext());
            StaticTools.protocol protocol = StaticTools.loadProtocolFromPrefs(getApplicationContext());

            webService = StaticTools.initWebService(getApplicationContext(), u);
            Subject subject = new Subject();
            subject.setId(99999L);
            subject.setInstituteId(u.getInstituteId());
            subject.setName(subjectName.getText().toString());
            AsyncAddSubject asyncAddSubject = new AsyncAddSubject(this);
            asyncAddSubject.execute(subject);
        }
        else {
            this.setResult(RESULT_OK);
            finish();
        }

    }
    public CustomViewPager getPager() {
        return pager;
    }
    public void startSubjectActivity(View view) {

    }
    private class AsyncAddSubject extends AsyncTask<Subject, Void, Void> {
        ProgressDialog dialog;
        AddReq activity;
        public AsyncAddSubject(AddReq context) {
            activity = context;
            dialog = new ProgressDialog(context);
        }

        @Override
        protected Void doInBackground(Subject... subjects) {
            webService.addSubject(subjects[0]);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Adding subject");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            activity.setResult(RESULT_OK);
            activity.finish();
        }
    }
}
