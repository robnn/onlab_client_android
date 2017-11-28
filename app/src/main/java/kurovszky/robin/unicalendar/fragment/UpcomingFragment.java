package kurovszky.robin.unicalendar.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import kurovszky.robin.unicalendar.R;
import kurovszky.robin.unicalendar.fragment.adapter.UpcomingAdapter;
import kurovszky.robin.unicalendar.model.Requirement;


public class UpcomingFragment extends Fragment {

    public RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    List<Requirement> requirements;
    List<Requirement> upcoming;
    public UpcomingAdapter adapter;
    static UpcomingFragment upcomingFragment;

    public UpcomingFragment() {
        // Required empty public constructor
    }

    public static UpcomingFragment getInstance() {
        if (upcomingFragment == null)
            upcomingFragment = new UpcomingFragment();

        return upcomingFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View upcomingView = inflater.inflate(R.layout.fragment_upcoming, container, false);

        mRecyclerView = (RecyclerView) upcomingView.findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapter = new UpcomingAdapter(upcoming);
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this.getActivity()).color(R.color.divider).sizeResId(R.dimen.divider).marginResId(R.dimen.leftmargin, R.dimen.rightmargin).build());
        adapter.setContext(getActivity());


        return upcomingView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public List<Requirement> setSubjects(List<Requirement> requirements) {

        this.requirements = new ArrayList<>();
        this.upcoming = new ArrayList<>();
        this.requirements = requirements;
        for (Requirement r : requirements) {
            Date current = Calendar.getInstance().getTime();
            if (r.getTimeInDate().getTime() >= current.getTime()) {
                upcoming.add(r);
            }


        }
        Collections.sort(upcoming);
        return upcoming;
    }

}
