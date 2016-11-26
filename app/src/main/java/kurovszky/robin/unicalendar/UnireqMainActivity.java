package kurovszky.robin.unicalendar;



import android.net.Uri;

import android.content.Intent;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kurovszky.robin.unicalendar.fragment.CalendarFragment;
import kurovszky.robin.unicalendar.fragment.UpcomingFragment;
import kurovszky.robin.unicalendar.model.Requirement;
import kurovszky.robin.unicalendar.model.Subject;

public class UnireqMainActivity extends AppCompatActivity implements CalendarFragment.OnFragmentInteractionListener, UpcomingFragment.OnFragmentInteractionListener {

    public static final int FRAGMENT_COUNT= 2;
    MyAdapter adapter;
    ViewPager pager;
    List<Subject> subjects;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unireq_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        adapter = new MyAdapter(getSupportFragmentManager());

        pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);

        //this is a workaround, tabstrip not showing without it
        PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.tabStrip);
        ((ViewPager.LayoutParams) pagerTabStrip.getLayoutParams()).isDecor = true;

        ImageView mainButton = (ImageView)findViewById(R.id.main_toolbar_button);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Should open a navigation drawer", Toast.LENGTH_LONG).show();
            }
        });

        subjects = new ArrayList<>();
        Subject test = new Subject("teszt");
        test.addReq(new Requirement("házi", Calendar.getInstance(), 5));

        subjects.add(test);
        Subject test2 = new Subject("teszt2");
        test2.addReq(new Requirement("ZH", Calendar.getInstance(), 1));

        subjects.add(test2);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_subject:
                Intent intent = new Intent(this, AddReq.class);
                startActivity(intent);
                break;
            case R.id.settings:
                //TODO
                Toast.makeText(this,"Will implement this", Toast.LENGTH_LONG).show();
                break;
            case R.id.info:
                //TODO
                Toast.makeText(this,"Will implement this", Toast.LENGTH_LONG).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    public class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }



        @Override
        public int getCount() {
            return FRAGMENT_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0) {
                UpcomingFragment a = UpcomingFragment.newInstance();
                a.setSubjects(subjects);
                return a;
            }

            if(position == 1)
                return CalendarFragment.newInstance();
            return null;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return getString(R.string.upcoming);
                case 1:
                    return getString(R.string.calendar);
                default:
                    return getString(R.string.bad_string);
            }
        }
    }
}
