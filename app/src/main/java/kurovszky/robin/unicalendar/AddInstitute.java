package kurovszky.robin.unicalendar;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kurovszky.robin.unicalendar.web_service.RestWebServiceImpl;
import kurovszky.robin.unicalendar.web_service.WebService;
import kurovszky.robin.unicalendar.web_service.model.Institute;
import kurovszky.robin.unicalendar.web_service.model.User;
import kurovszky.robin.unicalendar.web_service.tools.StaticTools;

public class AddInstitute extends AppCompatActivity {
    EditText instituteName;
    Button submit;

    User u ;
    WebService ws = new RestWebServiceImpl(u);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_institute);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        u  = StaticTools.loadUserFromPrefs(getApplicationContext());
        instituteName = (EditText)findViewById(R.id.institute_text_input);
        submit = (Button) findViewById(R.id.submit_input_institute);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = instituteName.getText().toString();
                Long id = 99999L;
                Institute i = new Institute(id,name);
                ws.addInstitute(i);
                finish();
            }
        });
    }

}
