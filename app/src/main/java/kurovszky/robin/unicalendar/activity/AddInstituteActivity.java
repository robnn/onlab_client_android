package kurovszky.robin.unicalendar.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kurovszky.robin.unicalendar.R;
import kurovszky.robin.unicalendar.exception.BaseException;
import kurovszky.robin.unicalendar.fragment.error.ErrorFragmentImpl;
import kurovszky.robin.unicalendar.web_service.WebService;
import kurovszky.robin.unicalendar.web_service.error.ErrorObject;
import kurovszky.robin.unicalendar.web_service.model.Institute;
import kurovszky.robin.unicalendar.web_service.model.User;
import kurovszky.robin.unicalendar.web_service.tools.StaticTools;
import kurovszky.robin.unicalendar.web_service.type.ErrorCode;
import kurovszky.robin.unicalendar.web_service.type.Transaction;

public class AddInstituteActivity extends AppCompatActivity {
    EditText instituteName;
    Button submit;

    User u;
    WebService webService;
    ErrorFragmentImpl errorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        u = StaticTools.loadUserFromPrefs(getApplicationContext());
        AsyncInitWebService asyncInitWebService = new AsyncInitWebService(this);
        asyncInitWebService.execute(u);
        setContentView(R.layout.activity_add_institute);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        instituteName = (EditText) findViewById(R.id.institute_text_input);
        submit = (Button) findViewById(R.id.submit_input_institute);
        final AddInstituteActivity _this = this;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = instituteName.getText().toString();
                Long id = 99999L;
                Institute i = new Institute(id, name);

                AsyncAddInstitute asyncAddInstitute = new AsyncAddInstitute(_this);

                asyncAddInstitute.execute(i);

            }
        });
    }

    private class AsyncInitWebService extends AsyncTask<User, Void, ErrorObject> {

        ProgressDialog dialog;
        AddInstituteActivity activity;

        public AsyncInitWebService(AddInstituteActivity activity) {
            this.activity = activity;
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected ErrorObject doInBackground(User... users) {
            try {
                webService = StaticTools.initWebService(getApplicationContext(), users[0], Transaction.ADD_INSTITUTE);
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

    private class AsyncAddInstitute extends AsyncTask<Institute, Void, ErrorObject> {
        ProgressDialog dialog;
        AddInstituteActivity activity;

        public AsyncAddInstitute(AddInstituteActivity context) {
            activity = context;
            dialog = new ProgressDialog(context);
        }

        @Override
        protected ErrorObject doInBackground(Institute... institutes) {
            if (institutes[0].getName().isEmpty())
                return new ErrorObject(ErrorCode.INSTITUTE_NAME_EMPTY, activity);
            try {
                webService.addInstitute(institutes[0]);
            } catch (BaseException e) {
                return new ErrorObject(ErrorCode.SERVER_DOWN, activity);
            }
            return new ErrorObject(ErrorCode.NO_ERROR, activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Uploading institute");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(ErrorObject errorObject) {
            super.onPostExecute(errorObject);
            ErrorCode errorCode = errorObject.getErrorCode();
            String message = errorObject.getMessage();
            dialog.dismiss();
            if (errorCode == ErrorCode.NO_ERROR) {
                activity.finish();
                return;
            }
            errorFragment = ErrorFragmentImpl.newInstance(message);
            errorFragment.show(activity.getSupportFragmentManager(), "DIALOG");

        }
    }

}
