package kurovszky.robin.unicalendar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import kurovszky.robin.unicalendar.web_service.RestWebServiceImpl;
import kurovszky.robin.unicalendar.web_service.WebService;
import kurovszky.robin.unicalendar.web_service.model.Institute;
import kurovszky.robin.unicalendar.web_service.model.User;
import kurovszky.robin.unicalendar.web_service.tools.StaticTools;

public class LoginActivity extends AppCompatActivity {

    EditText userInput;
    EditText password;
    TextView instituteText;
    Button button;
    Button registerStart;
    User user;
    List<Institute> instituteList;
    WebService webService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        User u = StaticTools.loadUserFromPrefs(getApplicationContext());



        if(u.getUserName()!=null){
            Intent i = new Intent(getApplicationContext(),UnireqMainActivity.class);
            startActivity(i);
        }


        {
            userInput = (EditText) findViewById(R.id.user_text_input);
            password = (EditText) findViewById(R.id.password_text_input);
            instituteText = (TextView) findViewById(R.id.institute_text);
            button = (Button) findViewById(R.id.submit_input);
            final LoginActivity _this = this;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user = new User();
                    user.setUserName(userInput.getText().toString());
                    user.setPassword(password.getText().toString());
                    webService = StaticTools.initWebService(getApplicationContext(), user);
                    AsyncGetIdByName asyncGetIdByName = new AsyncGetIdByName(_this);
                    asyncGetIdByName.execute(user);
                }
            });
            registerStart = (Button) findViewById(R.id.register_start);
            registerStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
                    startActivity(i);
                }
            });
        }
    }
    private class AsyncGetIdByName extends AsyncTask<User, Void, Long> {
        ProgressDialog dialog;
        LoginActivity activity;
        public AsyncGetIdByName(LoginActivity context) {
            activity = context;
            dialog = new ProgressDialog(context);
        }

        @Override
        protected Long doInBackground(User... users) {
            Long id_ = webService.getIdByName(users[0]);
            Long instituteId_ = webService.getInstituteIdByName(users[0]);
            SharedPreferences userDetails = getSharedPreferences("userDetails", MODE_PRIVATE);
            SharedPreferences.Editor edit = userDetails.edit();
            edit.putLong("id", id_);
            edit.putLong("instituteId", instituteId_);
            edit.putString("userName", user.getUserName());
            edit.putString("password", user.getPassword());
            edit.apply();
            return id_;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Getting user id and institute id");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(Long aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            Intent i = new Intent(getApplicationContext(),UnireqMainActivity.class);
            startActivity(i);
        }
    }
}
