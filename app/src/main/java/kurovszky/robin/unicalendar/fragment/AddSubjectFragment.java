package kurovszky.robin.unicalendar.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import kurovszky.robin.unicalendar.R;
import kurovszky.robin.unicalendar.fragment.adapter.AddAdapter;
import kurovszky.robin.unicalendar.model.Requirement;
import kurovszky.robin.unicalendar.view.CustomViewPager;

public class AddSubjectFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    List<Requirement> requirements;
    public AddAdapter adapter;

    public AddSubjectFragment() {
        // Required empty public constructor
    }


    public static AddSubjectFragment newInstance() {
        AddSubjectFragment fragment = new AddSubjectFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_subject, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_adding);
        requirements = new ArrayList<>();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this.getActivity()).color(R.color.divider).sizeResId(R.dimen.divider).marginResId(R.dimen.leftmargin, R.dimen.rightmargin).build());
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapter = new AddAdapter(requirements);

        mRecyclerView.setAdapter(adapter);
        adapter.setContext(getActivity());

        EditText nameText = (EditText) v.findViewById(R.id.subjectNameText);
        Intent starterIntent = getActivity().getIntent();
        if (starterIntent.hasExtra("subjectName")) {
            String subjectName = starterIntent.getStringExtra("subjectName");

            nameText.setText(subjectName);
            nameText.setEnabled(false);
        }
        Button addReq = (Button) v.findViewById(R.id.addReqButton);
        addReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomViewPager pager = mListener.getPager();
                pager.setCurrentItem(1);
            }
        });
        return v;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        CustomViewPager getPager();
    }

    public void addRequirement(Requirement requirement) {
        int curSize = adapter.getItemCount();
        requirements.add(requirement);
        adapter.notifyItemInserted(curSize);
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }
}
