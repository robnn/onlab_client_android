package kurovszky.robin.unicalendar.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import kurovszky.robin.unicalendar.R;
import kurovszky.robin.unicalendar.fragment.PreferencesFragment;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView title = (TextView) findViewById(R.id.toolbar_title);
        title.setText(R.string.settings);
        getFragmentManager().beginTransaction().replace(R.id.content_frame, new PreferencesFragment()).commit();

    }
}
