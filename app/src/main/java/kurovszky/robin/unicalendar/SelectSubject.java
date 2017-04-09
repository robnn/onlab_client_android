package kurovszky.robin.unicalendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import kurovszky.robin.unicalendar.web_service.RestWebServiceImpl;
import kurovszky.robin.unicalendar.web_service.WebService;
import kurovszky.robin.unicalendar.web_service.model.Institute;
import kurovszky.robin.unicalendar.web_service.model.Subject;
import kurovszky.robin.unicalendar.web_service.model.User;
import kurovszky.robin.unicalendar.web_service.tools.StaticTools;

import static kurovszky.robin.unicalendar.UnireqMainActivity.REQUEST_CODE;

public class SelectSubject extends AppCompatActivity {
    Spinner subjectSpinner;
    Button selectSubject;
    Button addNewSubject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_subject);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        subjectSpinner = (Spinner) findViewById(R.id.subject_spinner);
        selectSubject = (Button) findViewById(R.id.submit_input_subject_pick);
        addNewSubject = (Button) findViewById(R.id.subject_create);
        User  user = StaticTools.loadUserFromPrefs(getApplicationContext());
        WebService webService = new RestWebServiceImpl(user);
        Institute i = webService.getInstituteById(user.getInstituteId());
        List<Subject> subjects = webService.getSubjectsByInstitute(i);
        final ArrayList<String> spinnerItems = new ArrayList<>();
        for (Subject s : subjects) {
            spinnerItems.add(s.getName());
        }
        subjectSpinner.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, spinnerItems) {});
        selectSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddReq.class);
                i.putExtra("subjectName",subjectSpinner.getSelectedItem().toString());
                startActivityForResult(i, REQUEST_CODE);
            }
        });
        addNewSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddReq.class);
                startActivityForResult(i, REQUEST_CODE);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                this.setResult(RESULT_OK);
                finish();
            }
        }
    }
}
