package kurovszky.robin.unicalendar.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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

public class RegisterActivity extends AppCompatActivity {
    EditText userInput;
    EditText password;
    EditText realName;
    Spinner instituteSpinner;
    List<Institute> instituteList;
    Button submitButton;
    Button addInstituteButton;
    WebService webService;
    String selected;
    ArrayList<String> spinnerItems;
    ErrorFragmentImpl errorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        AsyncInitWebService asyncInitWebService = new AsyncInitWebService(this);
        asyncInitWebService.execute(new User());

        ((TextView) findViewById(R.id.toolbar_title)).setText(R.string.title_activity_register);
        instituteSpinner = (Spinner) findViewById(R.id.institute_spinner);
        userInput = (EditText) findViewById(R.id.user_text_input_register);
        password = (EditText) findViewById(R.id.password_text_input_register);
        realName = (EditText) findViewById(R.id.real_name_text_input_register);
        submitButton = (Button) findViewById(R.id.submit_input_register);
        addInstituteButton = (Button) findViewById(R.id.institute_create_register);
        final RegisterActivity this_ = this;
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User myUser = new User();
                myUser.setUserName(userInput.getText().toString());
                myUser.setPassword(password.getText().toString());
                myUser.setId(9999L);
                myUser.setRealName(realName.getText().toString());
                selected = instituteSpinner.getSelectedItem() != null ? instituteSpinner.getSelectedItem().toString() : null;
                AsyncRegisterUser asyncRegisterUser = new AsyncRegisterUser(this_);
                asyncRegisterUser.execute(myUser);

            }
        });
        addInstituteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddInstituteActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        AsyncGetInstitutes asyncGetInstitutes = new AsyncGetInstitutes(this);
        asyncGetInstitutes.execute();

    }

    private class AsyncInitWebService extends AsyncTask<User, Void, ErrorObject> {

        ProgressDialog dialog;
        RegisterActivity activity;

        public AsyncInitWebService(RegisterActivity activity) {
            this.activity = activity;
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected ErrorObject doInBackground(User... users) {
            try {
                webService = StaticTools.initWebService(getApplicationContext(), users[0], Transaction.REGISTER);
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

    private class AsyncRegisterUser extends AsyncTask<User, Void, ErrorObject> {
        ProgressDialog dialog;
        RegisterActivity activity;

        public AsyncRegisterUser(RegisterActivity context) {
            activity = context;
            dialog = new ProgressDialog(context);
        }

        @Override
        protected ErrorObject doInBackground(User... users) {
            long instituteID = 0;
            if (instituteList == null || instituteList.isEmpty() || selected == null)
                return new ErrorObject(getApplication());
            for (Institute i : instituteList) {
                if (i.getName().equals(selected)) {
                    instituteID = i.getId();
                    break;
                }
            }
            users[0].setInstituteId(instituteID);
            if (users[0].getUserName().isEmpty() || users[0].getPassword().isEmpty() || users[0].getRealName().isEmpty() || users[0].getInstituteId() == null) {
                return new ErrorObject(ErrorCode.ALL_FIELDS_MUST_BE_FILLED, getApplication());
            }
            try {
                Long userIdForValidation = webService.getIdByName(users[0]);
            } catch (BaseException e) { //using exception catching to determine the user is in the database
                User registeredUser = null;
                try {
                    registeredUser = webService.register(users[0]);
                } catch (BaseException e1) {
                    return new ErrorObject(ErrorCode.SERVER_DOWN, getApplication());
                }
                SharedPreferences userDetails = getSharedPreferences("userDetails", MODE_PRIVATE);
                SharedPreferences.Editor edit = userDetails.edit();
                edit.putString("userName", registeredUser.getUserName());
                edit.putString("password", registeredUser.getPassword());
                edit.putLong("id", registeredUser.getId());
                edit.putLong("instituteId", registeredUser.getInstituteId());
                edit.putString("realName", registeredUser.getRealName());
                edit.apply();
                return new ErrorObject(ErrorCode.NO_ERROR, getApplication());
            }

            return new ErrorObject(ErrorCode.USERNAME_EXISTS, getApplication());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Registering new user...");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(ErrorObject returnedValue) {
            super.onPostExecute(returnedValue);
            ErrorCode errorCode = returnedValue.getErrorCode();
            String message = returnedValue.getMessage();
            dialog.dismiss();
            if (errorCode == ErrorCode.NO_ERROR) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                return;
            }
            errorFragment = ErrorFragmentImpl.newInstance(message);
            errorFragment.show(activity.getSupportFragmentManager(), "DIALOG");
        }
    }

    private class AsyncGetInstitutes extends AsyncTask<Void, Void, ErrorObject> {
        ProgressDialog dialog;
        RegisterActivity activity;

        public AsyncGetInstitutes(RegisterActivity context) {
            activity = context;
            dialog = new ProgressDialog(context);
        }

        @Override
        protected ErrorObject doInBackground(Void... voids) {
            spinnerItems = new ArrayList<>();
            try {
                instituteList = webService.getInstitutes();
                for (Institute i : instituteList) {
                    spinnerItems.add(i.getName());
                }
            } catch (BaseException e) {
                return new ErrorObject(ErrorCode.SERVER_DOWN, activity);
            }

            return new ErrorObject(ErrorCode.NO_ERROR, activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Getting institute list");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(ErrorObject errorObject) {
            super.onPostExecute(errorObject);
            ErrorCode errorCode = errorObject.getErrorCode();
            String message = errorObject.getMessage();
            dialog.dismiss();
            if (errorCode == ErrorCode.NO_ERROR) {
                instituteSpinner.setAdapter(new ArrayAdapter<String>(activity, R.layout.support_simple_spinner_dropdown_item, spinnerItems) {
                });
                return;
            }
            errorFragment = ErrorFragmentImpl.newInstance(message);
            errorFragment.show(activity.getSupportFragmentManager(), "DIALOG");

        }
    }
}
