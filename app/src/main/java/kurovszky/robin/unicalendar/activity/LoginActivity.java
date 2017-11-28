package kurovszky.robin.unicalendar.activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import kurovszky.robin.unicalendar.R;
import kurovszky.robin.unicalendar.exception.BaseException;
import kurovszky.robin.unicalendar.fragment.error.ErrorFragmentImpl;
import kurovszky.robin.unicalendar.web_service.WebService;
import kurovszky.robin.unicalendar.web_service.error.ErrorObject;
import kurovszky.robin.unicalendar.web_service.model.User;
import kurovszky.robin.unicalendar.web_service.tools.StaticTools;
import kurovszky.robin.unicalendar.web_service.type.ErrorCode;
import kurovszky.robin.unicalendar.web_service.type.Transaction;

public class LoginActivity extends AppCompatActivity {

    EditText userInput;
    EditText password;
    Button button;
    Button registerStart;
    User user;
    WebService webService;
    ErrorFragmentImpl errorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        User u = StaticTools.loadUserFromPrefs(getApplicationContext());
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        ((TextView) findViewById(R.id.toolbar_title)).setText(R.string.title_activity_login);
        if (u.getUserName() != null) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
        userInput = (EditText) findViewById(R.id.user_text_input);
        password = (EditText) findViewById(R.id.password_text_input);
        button = (Button) findViewById(R.id.submit_input);
        final LoginActivity _this = this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new User();
                user.setUserName(userInput.getText().toString());
                user.setPassword(password.getText().toString());
                AsyncInitWebService asyncInitWebService = new AsyncInitWebService(_this);
                try {
                    ErrorObject errorObject = asyncInitWebService.execute(user).get();
                    if (!errorObject.isFailed()) {
                        AsyncGetIdByName asyncGetIdByName = new AsyncGetIdByName(_this);
                        asyncGetIdByName.execute(user);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
        registerStart = (Button) findViewById(R.id.register_start);
        registerStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private class AsyncInitWebService extends AsyncTask<User, Void, ErrorObject> {

        ProgressDialog dialog;
        LoginActivity activity;

        public AsyncInitWebService(LoginActivity activity) {
            this.activity = activity;
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected ErrorObject doInBackground(User... users) {
            try {
                webService = StaticTools.initWebService(getApplicationContext(), users[0], Transaction.LOGIN);
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

    private class AsyncGetIdByName extends AsyncTask<User, Void, ErrorObject> {
        ProgressDialog dialog;
        LoginActivity activity;

        public AsyncGetIdByName(LoginActivity context) {
            activity = context;
            dialog = new ProgressDialog(context);
        }

        @Override
        protected ErrorObject doInBackground(User... users) {
            Long id_ = null;
            try {
                id_ = webService.getIdByName(users[0]);
            } catch (BaseException e) {
                return new ErrorObject(e.getErrorObject().getErrorCode(), activity);
            }

            Long instituteId_;
            try {
                instituteId_ = webService.getInstituteIdByName(users[0]);
            } catch (BaseException ex) {
                return new ErrorObject(ex.getErrorObject().getErrorCode(), activity);
            }
            SharedPreferences userDetails = getSharedPreferences("userDetails", MODE_PRIVATE);
            SharedPreferences.Editor edit = userDetails.edit();
            edit.putLong("id", id_);
            edit.putLong("instituteId", instituteId_);
            edit.putString("userName", user.getUserName());
            edit.putString("password", user.getPassword());
            edit.apply();
            return new ErrorObject(ErrorCode.NO_ERROR, activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Getting user id and institute id");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(ErrorObject errorObject) {
            super.onPostExecute(errorObject);
            ErrorCode errorCode = errorObject.getErrorCode();
            String message = errorObject.getMessage();
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

}
