package kurovszky.robin.unicalendar.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.common.base.MoreObjects;
import com.mustafaferhan.MFCalendarView;
import com.mustafaferhan.onMFCalendarViewListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import kurovszky.robin.unicalendar.R;
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

        mfCalendarView = (MFCalendarView) calendarFragment.findViewById(R.id.mFCalendarView);
        requirements = Requirement.listAll(Requirement.class);
        for(Requirement r : requirements){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            eventDays.add(dateFormat.format(r.getTimeInDate()));
        }
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
            public void onDateChanged(String date) {
                //codes
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
