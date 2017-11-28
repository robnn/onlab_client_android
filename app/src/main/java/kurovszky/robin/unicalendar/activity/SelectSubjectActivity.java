package kurovszky.robin.unicalendar.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import kurovszky.robin.unicalendar.R;
import kurovszky.robin.unicalendar.exception.BaseException;
import kurovszky.robin.unicalendar.fragment.error.ErrorFragmentImpl;
import kurovszky.robin.unicalendar.web_service.WebService;
import kurovszky.robin.unicalendar.web_service.error.ErrorObject;
import kurovszky.robin.unicalendar.web_service.model.Institute;
import kurovszky.robin.unicalendar.web_service.model.Subject;
import kurovszky.robin.unicalendar.web_service.model.User;
import kurovszky.robin.unicalendar.web_service.tools.StaticTools;
import kurovszky.robin.unicalendar.web_service.type.ErrorCode;
import kurovszky.robin.unicalendar.web_service.type.Transaction;

public class SelectSubjectActivity extends AppCompatActivity {
    Spinner subjectSpinner;
    Button selectSubject;
    Button addNewSubject;
    WebService webService;
    ErrorFragmentImpl errorFragment;
    final ArrayList<String> spinnerItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_subject);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        subjectSpinner = (Spinner) findViewById(R.id.subject_spinner);
        selectSubject = (Button) findViewById(R.id.submit_input_subject_pick);
        addNewSubject = (Button) findViewById(R.id.subject_create);
        User user = StaticTools.loadUserFromPrefs(getApplicationContext());

        AsyncInitWebService asyncInitWebService = new AsyncInitWebService(this);
        asyncInitWebService.execute(user);

        AsyncGetSubjects asyncGetSubjects = new AsyncGetSubjects(this);
        asyncGetSubjects.execute(user);
        selectSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddRequirementActivity.class);
                i.putExtra("subjectName", subjectSpinner.getSelectedItem().toString());
                startActivityForResult(i, MainActivity.REQUEST_CODE);
            }
        });
        addNewSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddRequirementActivity.class);
                startActivityForResult(i, MainActivity.REQUEST_CODE);
            }
        });

    }

    private class AsyncInitWebService extends AsyncTask<User, Void, ErrorObject> {

        ProgressDialog dialog;
        SelectSubjectActivity activity;

        public AsyncInitWebService(SelectSubjectActivity activity) {
            this.activity = activity;
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected ErrorObject doInBackground(User... users) {
            try {
                webService = StaticTools.initWebService(getApplicationContext(), users[0], Transaction.GET_SUBJECTS);
            } catch (BaseException e) {
                return new ErrorObject(e.getErrorObject().getErrorCode(), getApplication());
            }
            return new ErrorObject(ErrorCode.NO_ERROR, getApplication());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Initializing connection...");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(ErrorObject errorObject) {
            super.onPostExecute(errorObject);
            ErrorCode errorCode = errorObject.getErrorCode();
            String message = errorObject.getMessage();
            dialog.dismiss();
            if (errorCode == ErrorCode.NO_ERROR)
                return;
            errorFragment = ErrorFragmentImpl.newInstance(message);
            errorFragment.show(activity.getSupportFragmentManager(), "DIALOG");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MainActivity.REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                this.setResult(RESULT_OK);
                finish();
            }
        }
    }

    private class AsyncGetSubjects extends AsyncTask<User, Void, ErrorObject> {
        ProgressDialog dialog;
        SelectSubjectActivity activity;

        public AsyncGetSubjects(SelectSubjectActivity context) {
            activity = context;
            dialog = new ProgressDialog(context);
        }

        @Override
        protected ErrorObject doInBackground(User... users) {
            Institute i = null;
            try {
                i = webService.getInstituteById(users[0].getInstituteId());
                List<Subject> subjects = webService.getSubjectsByInstitute(i);
                for (Subject s : subjects) {
                    spinnerItems.add(s.getName());
                }
            } catch (BaseException e) {
                return new ErrorObject(ErrorCode.SERVER_DOWN, activity);
            }


            return new ErrorObject(ErrorCode.NO_ERROR, activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Getting subject list");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(ErrorObject errorObject) {
            super.onPostExecute(errorObject);
            ErrorCode errorCode = errorObject.getErrorCode();
            String message = errorObject.getMessage();
            dialog.dismiss();
            if (errorCode == ErrorCode.NO_ERROR) {
                subjectSpinner.setAdapter(new ArrayAdapter<String>(activity, R.layout.support_simple_spinner_dropdown_item, spinnerItems) {
                });
                return;
            }
            errorFragment = ErrorFragmentImpl.newInstance(message);
            errorFragment.show(activity.getSupportFragmentManager(), "DIALOG");

        }
    }
}
