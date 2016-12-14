package kurovszky.robin.unicalendar.fragment;

import android.os.Bundle;

import kurovszky.robin.unicalendar.R;

public class PreferencesFragment extends android.preference.PreferenceFragment {
    @Override
    public void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}