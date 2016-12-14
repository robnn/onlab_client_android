package kurovszky.robin.unicalendar.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.CircularArray;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

import kurovszky.robin.unicalendar.R;
import kurovszky.robin.unicalendar.model.Requirement;
import kurovszky.robin.unicalendar.view.CustomViewPager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddReqElement.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddReqElement#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddReqElement extends Fragment {

    private OnFragmentInteractionListener mListener;
    private TextView dateText;
    private TextView timeText;
    private Button datePicker;
    private Button timePicker;
    private Calendar calendar;
    private Button confirm;
    public AddReqElement() {
        // Required empty public constructor
    }

    public static AddReqElement newInstance() {
        AddReqElement fragment = new AddReqElement();
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
        dateText = (TextView) v.findViewById(R.id.dateText);
        timeText = (TextView) v.findViewById(R.id.timeText);
        datePicker = (Button) v.findViewById(R.id.datePickerButton);
        timePicker = (Button) v.findViewById(R.id.timePickerButton);

        //TextView textView = (TextView)getActivity().findViewById(R.id.toolbar_title);
        //textView.setText(R.string.add_a_requ);

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
        void setReq(Requirement req);
        CustomViewPager getPager();
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
