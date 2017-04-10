package kurovszky.robin.unicalendar;

import android.content.Intent;
import android.content.SharedPreferences;
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
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User myUser = new User();
                myUser.setUserName(userInput.getText().toString());
                myUser.setPassword(password.getText().toString());
                myUser.setId(9999L);
                myUser.setRealName(realName.getText().toString());
                String selected = instituteSpinner.getSelectedItem().toString();
                long instituteID = 0;
                for (Institute i : instituteList) {
                    if (i.getName().equals(selected))
                        instituteID = i.getId();
                }
                myUser.setInstituteId(instituteID);
                User registeredUser = ws.register(myUser);
                SharedPreferences userDetails = getSharedPreferences("userDetails", MODE_PRIVATE);
                SharedPreferences.Editor edit = userDetails.edit();
                edit.putString("userName", registeredUser.getUserName());
                edit.putString("password", registeredUser.getPassword());
                edit.putLong("id", registeredUser.getId());
                edit.putLong("instituteId", registeredUser.getInstituteId());
                edit.putString("realName", registeredUser.getRealName());
                edit.apply();
                Intent i = new Intent(getApplicationContext(), UnireqMainActivity.class);
                startActivity(i);
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
        instituteList = ws.getInstitutes();
        ArrayList<String> spinnerItems = new ArrayList<>();
        for (Institute i : instituteList) {
            spinnerItems.add(i.getName());
        }
        instituteSpinner.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, spinnerItems) {});
    }
}
