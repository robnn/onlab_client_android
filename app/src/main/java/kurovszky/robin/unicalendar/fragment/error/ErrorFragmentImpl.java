package kurovszky.robin.unicalendar.fragment.error;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import kurovszky.robin.unicalendar.R;


public class ErrorFragmentImpl extends DialogFragment implements ErrorFragment{

    private String errorText;

    public ErrorFragmentImpl() {}

    public static ErrorFragmentImpl newInstance(String errorText) {
        ErrorFragmentImpl fragment = new ErrorFragmentImpl();
        fragment.setErrorText(errorText);
        return fragment;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
       return new AlertDialog.Builder(getActivity()).setIcon(R.drawable.ic_error_black_24dp).setTitle("Error").setMessage(errorText).setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {

           }
       }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               getActivity().moveTaskToBack(true);
           }
       }).create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public String getErrorText() {
        return errorText;
    }

    @Override
    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }
}
