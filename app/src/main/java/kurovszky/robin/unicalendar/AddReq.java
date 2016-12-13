package kurovszky.robin.unicalendar;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;

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


        addSubject = new AddSubject();





    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter = new MyAdapter(getSupportFragmentManager());

        pager = (CustomViewPager)findViewById(R.id.add_pager);
        pager.setPagingEnabled(false);
        pager.setAdapter(adapter);
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


        @Override
        public int getCount() {
            return FRAGMENT_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0) {

                return addSubject;
            }

            if(position == 1) {
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
    }

    String readSubjectName(){
        EditText et = (EditText) findViewById(R.id.subjectNameText);
        return et.getText().toString();
    }
    void finishAddActivity(){
        for(Requirement requirement: addSubject.getRequirements()){
            requirement.setSubject(readSubjectName());
            requirement.save();
        }
        this.setResult(RESULT_OK);
        finish();
    }
    public CustomViewPager getPager() {
        return pager;
    }
}
