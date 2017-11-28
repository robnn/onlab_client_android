package kurovszky.robin.unicalendar.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Calendar;

import kurovszky.robin.unicalendar.R;
import kurovszky.robin.unicalendar.model.Requirement;

public class AddReqElementFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Calendar calendar;
    public AddReqElementFragment() {
        // Required empty public constructor
    }

    public static AddReqElementFragment newInstance() {
        AddReqElementFragment fragment = new AddReqElementFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        calendar = Calendar.getInstance();
        View v = inflater.inflate(R.layout.fragment_add_req_element, container, false);
        Button datePicker = (Button) v.findViewById(R.id.datePickerButton);
        Button timePicker = (Button) v.findViewById(R.id.timePickerButton);

        final DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setPickerReference(this);
        datePicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                datePickerFragment.show(getActivity().getSupportFragmentManager(),"datepicker");
            }
        });
        final TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.setPickerReference(this);
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timePickerFragment.show(getActivity().getSupportFragmentManager(),"timepicker");
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
        void setReq(Requirement req);
    }

    public void setDate(int year, int month, int day){
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH,day);
    }
    public void setTime(int hour, int minute){
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        calendar.set(Calendar.MINUTE,minute);
    }
    public  void confirmInput(){
        Spinner typeSpinner = (Spinner) getActivity().findViewById(R.id.typeSpinner);
        Spinner hardinessSpinner = (Spinner) getActivity().findViewById(R.id.hardinessSpinner);
        Requirement req = new Requirement(typeSpinner.getSelectedItem().toString(),calendar,hardinessSpinner.getSelectedItemPosition());
        mListener.setReq(req);
    }
}
