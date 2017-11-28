package kurovszky.robin.unicalendar.fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import kurovszky.robin.unicalendar.R;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    AddReqElementFragment aq;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute, android.text.format.DateFormat.is24HourFormat(getActivity()));

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView time = (TextView) getActivity().findViewById(R.id.timeText);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        date.setHours(hourOfDay);
        date.setMinutes(minute);
        time.setText(format.format(date));
        aq.setTime(hourOfDay,minute);
    }
    public void setPickerReference(AddReqElementFragment a){
        aq = a;
    }
}
