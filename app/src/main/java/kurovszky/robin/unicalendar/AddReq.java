package kurovszky.robin.unicalendar;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
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
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import kurovszky.robin.unicalendar.fragment.AddReqElement;
import kurovszky.robin.unicalendar.fragment.AddSubject;
import kurovszky.robin.unicalendar.model.Requirement;
import kurovszky.robin.unicalendar.view.CustomViewPager;

public class AddReq extends AppCompatActivity implements AddReqElement.OnFragmentInteractionListener, AddSubject.OnFragmentInteractionListener {
    private static final int FRAGMENT_COUNT = 2;
    AddSubject addSubject;
    MyAdapter adapter;
    CustomViewPager pager;
    Menu menu;
    FragmentManager fm = getSupportFragmentManager();

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
        this.setResult(RESULT_OK);
        finish();
    }
    public CustomViewPager getPager() {
        return pager;
    }
    public void startSubjectActivity(View view) {

    }
}
