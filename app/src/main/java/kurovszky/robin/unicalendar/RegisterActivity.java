package kurovszky.robin.unicalendar;

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

import java.util.ArrayList;
import java.util.List;

import kurovszky.robin.unicalendar.web_service.RestWebServiceImpl;
import kurovszky.robin.unicalendar.web_service.WebService;
import kurovszky.robin.unicalendar.web_service.model.Institute;
import kurovszky.robin.unicalendar.web_service.model.User;
import kurovszky.robin.unicalendar.web_service.tools.StaticTools;

public class RegisterActivity extends AppCompatActivity {
    EditText userInput;
    EditText password;
    EditText realName;
    Spinner instituteSpinner;
    List<Institute> instituteList;
    Button submitButton;
    Button addInstituteButton;
    WebService ws;
    String selected;
    ArrayList<String> spinnerItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ws = StaticTools.initWebService(getApplicationContext(), null);

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
                selected = instituteSpinner.getSelectedItem().toString();
                AsyncRegisterUser asyncRegisterUser = new AsyncRegisterUser(this_);
                asyncRegisterUser.execute(myUser);

            }
        });
        addInstituteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AddInstitute.class);
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
    private class AsyncRegisterUser extends AsyncTask<User, Void, Void> {
        ProgressDialog dialog;
        RegisterActivity activity;
        public AsyncRegisterUser(RegisterActivity context) {
            activity = context;
            dialog = new ProgressDialog(context);
        }

        @Override
        protected Void doInBackground(User... users) {
            long instituteID = 0;
            for (Institute i : instituteList) {
                if (i.getName().equals(selected))
                    instituteID = i.getId();
            }
            users[0].setInstituteId(instituteID);
            User registeredUser = ws.register(users[0]);
            SharedPreferences userDetails = getSharedPreferences("userDetails", MODE_PRIVATE);
            SharedPreferences.Editor edit = userDetails.edit();
            edit.putString("userName", registeredUser.getUserName());
            edit.putString("password", registeredUser.getPassword());
            edit.putLong("id", registeredUser.getId());
            edit.putLong("instituteId", registeredUser.getInstituteId());
            edit.putString("realName", registeredUser.getRealName());
            edit.apply();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Registering new user...");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            Intent i = new Intent(getApplicationContext(), UnireqMainActivity.class);
            startActivity(i);
        }
    }
    private class AsyncGetInstitutes extends AsyncTask<Void, Void, Void> {
        ProgressDialog dialog;
        RegisterActivity activity;
        public AsyncGetInstitutes(RegisterActivity context) {
            activity = context;
            dialog = new ProgressDialog(context);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            spinnerItems = new ArrayList<>();
            instituteList = ws.getInstitutes();
            for (Institute i : instituteList) {
                spinnerItems.add(i.getName());
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Getting institute list");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            instituteSpinner.setAdapter(new ArrayAdapter<String>(activity, R.layout.support_simple_spinner_dropdown_item, spinnerItems) {});
            dialog.dismiss();
        }
    }
}
