package kurovszky.robin.unicalendar;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.Voice;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kurovszky.robin.unicalendar.web_service.GrpcWebServiceImpl;
import kurovszky.robin.unicalendar.web_service.RestWebServiceImpl;
import kurovszky.robin.unicalendar.web_service.WebService;
import kurovszky.robin.unicalendar.web_service.model.Institute;
import kurovszky.robin.unicalendar.web_service.model.User;
import kurovszky.robin.unicalendar.web_service.tools.StaticTools;

public class AddInstitute extends AppCompatActivity {
    EditText instituteName;
    Button submit;

    User u ;
    WebService ws;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        u= StaticTools.loadUserFromPrefs(getApplicationContext());
        ws = StaticTools.initWebService(getApplicationContext(),u);
        setContentView(R.layout.activity_add_institute);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        instituteName = (EditText)findViewById(R.id.institute_text_input);
        submit = (Button) findViewById(R.id.submit_input_institute);
        final AddInstitute _this = this;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = instituteName.getText().toString();
                Long id = 99999L;
                Institute i = new Institute(id,name);
                AsyncAddInstitute asyncAddInstitute = new AsyncAddInstitute(_this);

                asyncAddInstitute.execute(i);

            }
        });
    }
    private class AsyncAddInstitute extends AsyncTask<Institute, Void, Void>{
        ProgressDialog dialog;
        AddInstitute activity;
        public AsyncAddInstitute(AddInstitute context) {
            activity = context;
            dialog = new ProgressDialog(context);
        }

        @Override
        protected Void doInBackground(Institute... institutes) {
            ws.addInstitute(institutes[0]);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Uploading institute");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            activity.finish();
        }
    }

}
