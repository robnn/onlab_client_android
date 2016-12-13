package kurovszky.robin.unicalendar.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import kurovszky.robin.unicalendar.R;
import kurovszky.robin.unicalendar.fragment.adapter.AddAdapter;
import kurovszky.robin.unicalendar.model.Requirement;
import kurovszky.robin.unicalendar.view.CustomViewPager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddSubject.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddSubject#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddSubject extends Fragment {


    private OnFragmentInteractionListener mListener;


    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    List<Requirement> requirements;
    AddAdapter adapter;
    public AddSubject() {
        // Required empty public constructor
    }


    public static AddSubject newInstance() {
        AddSubject fragment = new AddSubject();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // specify an adapter (see also next example)


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_subject, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_adding);
        requirements = new ArrayList<>();
        TextView textView = (TextView) getActivity().findViewById(R.id.toolbar_title);
        textView.setText(R.string.addReqTitle);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this.getActivity()).color(R.color.divider).sizeResId(R.dimen.divider).marginResId(R.dimen.leftmargin, R.dimen.rightmargin).build());
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapter = new AddAdapter(requirements);
        // use a linear layout manager

        mRecyclerView.setAdapter(adapter);






            Button addReq = (Button) v.findViewById(R.id.addReqButton);
            addReq.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CustomViewPager pager = mListener.getPager();
                    pager.setCurrentItem(1);

                }
            });

        // Inflate the layout for this fragment
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        CustomViewPager getPager();
    }

    public void addRequirement(Requirement requirement) {

        int curSize = adapter.getItemCount();
        requirements.add(requirement);
        adapter.notifyItemInserted(curSize);
        //mRecyclerView.invalidate();
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }
}
