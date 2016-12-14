package kurovszky.robin.unicalendar.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.common.base.MoreObjects;
import com.mustafaferhan.MFCalendarView;
import com.mustafaferhan.onMFCalendarViewListener;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import kurovszky.robin.unicalendar.R;
import kurovszky.robin.unicalendar.fragment.adapter.UpcomingAdapter;
import kurovszky.robin.unicalendar.model.Requirement;


public class CalendarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String TAG = "Upcoming";
    static CalendarFragment calendarFragment;
    ArrayList<String> eventDays = new ArrayList<>();
    List<Requirement> requirements = new ArrayList<>();
    public MFCalendarView mfCalendarView = null;
    public RecyclerView recyclerView = null;
    public UpcomingAdapter adapter;
    public RecyclerView.LayoutManager mLayoutManager;
    public CalendarFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static CalendarFragment getInstance() {
       if(calendarFragment == null){
           calendarFragment = new CalendarFragment();
       }
        return calendarFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View calendarFragment = inflater.inflate(R.layout.fragment_calendar, container, false);
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        mfCalendarView = (MFCalendarView) calendarFragment.findViewById(R.id.mFCalendarView);
        final String dateString = mfCalendarView.getInitialDate();
        Date date= null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        requirements = Requirement.listAll(Requirement.class);
        recyclerView = (RecyclerView) calendarFragment.findViewById(R.id.calendarRecyclerView);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this.getActivity()).color(R.color.divider).sizeResId(R.dimen.divider).marginResId(R.dimen.leftmargin, R.dimen.rightmargin).build());

        List<Requirement> dailyReq = new ArrayList<>();

        for(Requirement r : requirements){

            eventDays.add(dateFormat.format(r.getTimeInDate()));
        }
        for(Requirement r : requirements){
            if(r.getTimeInDate().getYear() == date.getYear()&& r.getTimeInDate().getMonth() == date.getMonth()&& r.getTimeInDate().getDay() == date.getDay()){
                dailyReq.add(r);
            }
        }
        adapter = new UpcomingAdapter(dailyReq);
        adapter.setContext(getActivity());
        recyclerView.setAdapter(adapter);
        mfCalendarView.setEvents(eventDays);
        mfCalendarView.setOnCalendarViewListener(new onMFCalendarViewListener() {

            @Override
            public void onDisplayedMonthChanged(int month, int year, String monthStr) {
                requirements = Requirement.listAll(Requirement.class);
                for(Requirement r : requirements){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    eventDays.add(dateFormat.format(r.getTimeInDate()));
                }
                mfCalendarView.setEvents(eventDays);

            }

            @Override
            public void onDateChanged(String dateString) {
                Date date1 = null;
                try {
                    date1 = dateFormat.parse(dateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                List<Requirement> daily = new ArrayList<Requirement>();
                for(Requirement r : requirements){
                    if(r.getTimeInDate().getYear() == date1.getYear()&& r.getTimeInDate().getMonth() == date1.getMonth()&& r.getTimeInDate().getDay() == date1.getDay()){
                        daily.add(r);
                    }
                }
                adapter = new UpcomingAdapter(daily);
                adapter.setContext(getActivity());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        return calendarFragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setRequirements(List<Requirement> requirements) {
        this.requirements = requirements;
    }
}
