package kurovszky.robin.unicalendar.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import kurovszky.robin.unicalendar.R;

/**
 * Created by robin on 2016. 11. 27..
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    AddReqElement aq;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        EditText date = (EditText) getActivity().findViewById(R.id.dateText);
        date.setText(Integer.toString(year) + "."+Integer.toString(month)+ "." + Integer.toString(dayOfMonth));
        aq.setDate(year,month,dayOfMonth);
    }
    public void setPickerReference(AddReqElement a){
        aq = a;
    }
}
