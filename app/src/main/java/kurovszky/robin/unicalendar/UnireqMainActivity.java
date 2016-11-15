package kurovszky.robin.unicalendar;


import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import kurovszky.robin.unicalendar.fragment.CalendarFragment;
import kurovszky.robin.unicalendar.fragment.UpcomingFragment;

public class UnireqMainActivity extends AppCompatActivity implements CalendarFragment.OnFragmentInteractionListener, UpcomingFragment.OnFragmentInteractionListener {
    /*for debug only, use view instead*/
    public static final int FRAGMENT_COUNT= 2;
    MyAdapter adapter;
    ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unireq_main);
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);


        //fragments
        /*FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        UpcomingFragment upcomingFragment = new UpcomingFragment();
        CalendarFragment calendarFragment = new CalendarFragment();
        fragmentTransaction.add(R.id.fragment_space,upcomingFragment);
        fragmentTransaction.add(R.id.fragment_space,calendarFragment);
        fragmentTransaction.commit();*/

        adapter = new MyAdapter(getSupportFragmentManager());

        pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);


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

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    public static class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return FRAGMENT_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0)
                return UpcomingFragment.newInstance();
            if(position == 1)
                return CalendarFragment.newInstance();
            return null;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return "tab";
        }
    }
}
