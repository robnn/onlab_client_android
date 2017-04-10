package kurovszky.robin.unicalendar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

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
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user = new User();
                    user.setUserName(userInput.getText().toString());
                    user.setPassword(password.getText().toString());
                    webService = StaticTools.initWebService(getApplicationContext(), user);
                    Long id_  = webService.getIdByName(user);
                    Long instituteId_ = webService.getInstituteIdByName(user);
                    SharedPreferences userDetails = getSharedPreferences("userDetails", MODE_PRIVATE);
                    SharedPreferences.Editor edit = userDetails.edit();
                    edit.putLong("id", id_);
                    edit.putLong("instituteId", instituteId_);
                    edit.putString("userName", user.getUserName());
                    edit.putString("password", user.getPassword());
                    edit.apply();
                    Intent i = new Intent(getApplicationContext(),UnireqMainActivity.class);
                    startActivity(i);

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


}
