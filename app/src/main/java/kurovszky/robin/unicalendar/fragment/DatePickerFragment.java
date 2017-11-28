package kurovszky.robin.unicalendar.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import kurovszky.robin.unicalendar.R;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    AddReqElementFragment aq;
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
        TextView date = (TextView) getActivity().findViewById(R.id.dateText);
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        Date dateD = new Date();
        dateD.setYear(year-1900);
        dateD.setMonth(month);
        dateD.setDate(dayOfMonth);
        date.setText(format.format(dateD));

        aq.setDate(year,month,dayOfMonth);
    }
    public void setPickerReference(AddReqElementFragment a){
        aq = a;
    }
}
