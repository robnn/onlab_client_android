package kurovszky.robin.unicalendar.fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

import kurovszky.robin.unicalendar.R;

/**
 * Created by robin on 2016. 11. 27..
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    AddReqElement aq;
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
        EditText time = (EditText) getActivity().findViewById(R.id.timeText);
        time.setText(Integer.toString(hourOfDay)+":"+Integer.toString(minute));
        aq.setTime(hourOfDay,minute);
    }
    public void setPickerReference(AddReqElement a){
        aq = a;
    }
}
