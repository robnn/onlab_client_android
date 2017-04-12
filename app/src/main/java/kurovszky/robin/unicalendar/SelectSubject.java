package kurovszky.robin.unicalendar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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
    WebService webService;
    final ArrayList<String> spinnerItems = new ArrayList<>();
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
        webService = StaticTools.initWebService(getApplicationContext(), user);
        AsyncGetSubjects asyncGetSubjects = new AsyncGetSubjects(this);
        asyncGetSubjects.execute(user);
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
    private class AsyncGetSubjects extends AsyncTask<User, Void, Void> {
        ProgressDialog dialog;
        SelectSubject activity;
        public AsyncGetSubjects(SelectSubject context) {
            activity = context;
            dialog = new ProgressDialog(context);
        }

        @Override
        protected Void doInBackground(User... users) {
            Institute i = webService.getInstituteById(users[0].getInstituteId());
            List<Subject> subjects = webService.getSubjectsByInstitute(i);
            for (Subject s : subjects) {
                spinnerItems.add(s.getName());
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Getting subject list");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            subjectSpinner.setAdapter(new ArrayAdapter<String>(activity, R.layout.support_simple_spinner_dropdown_item, spinnerItems) {});
            dialog.dismiss();
        }
    }
}
