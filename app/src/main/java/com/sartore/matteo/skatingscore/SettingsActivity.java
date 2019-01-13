package com.sartore.matteo.skatingscore;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    Switch NightModeSwitch;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        NightModeSwitch = findViewById(R.id.Switch_NightMode);

        NightModeSwitch.setEnabled(false);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        LoadPreferences();

        NightModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    preferences.edit().putBoolean("NightMode", true).apply();
                }
                else
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    preferences.edit().putBoolean("NightMode", false).apply();
                }
                recreate();
            }
        });
    }

    private void LoadPreferences()
    {
        boolean wantNightMode = preferences.getBoolean("NightMode", false);
        if(wantNightMode)
        {
            NightModeSwitch.setChecked(true);
        }
    }
}
