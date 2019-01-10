package com.sartore.matteo.skatingscore;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class AppTourActivity extends AppIntro {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Aggiunta schermate
        addSlide(AppIntroFragment.newInstance(getString(R.string.AppTourString1_Title), "", getString(R.string.AppTourString1_Descr), "",
                getApplicationContext().getResources().getIdentifier("screenshot1d", "drawable", getPackageName()), Color.parseColor("#3F51B5")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.AppTourString2_Title), "", getString(R.string.AppTourString2_Descr), "",
                getApplicationContext().getResources().getIdentifier("screenshot2d", "drawable", getPackageName()), Color.parseColor("#3F51B5")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.AppTourString3_Title), "", getString(R.string.AppTourString3_Descr), "boh2",
                getApplicationContext().getResources().getIdentifier("screenshot3d", "drawable", getPackageName()), Color.parseColor("#3F51B5")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.AppTourString4_Title), "", getString(R.string.AppTourString4_Descr), "",
                getApplicationContext().getResources().getIdentifier("screenshot4d", "drawable", getPackageName()), Color.parseColor("#3F51B5")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.AppTourString5_Title), "", getString(R.string.AppTourString5_Descr), "",
                getApplicationContext().getResources().getIdentifier("screenshot5d", "drawable", getPackageName()), Color.parseColor("#3F51B5")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.AppTourString6_Title), "", getString(R.string.AppTourString6_Descr), "",
                getApplicationContext().getResources().getIdentifier("screenshot6d", "drawable", getPackageName()), Color.parseColor("#3F51B5")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.AppTourString7_Title), "", getString(R.string.AppTourString7_Descr), "",
                getApplicationContext().getResources().getIdentifier("screenshot7d", "drawable", getPackageName()), Color.parseColor("#3F51B5")));
        addSlide(AppIntroFragment.newInstance(getString(R.string.AppTourString8_Title), "boh1", getString(R.string.AppTourString8_Descr), "boh2",
                getApplicationContext().getResources().getIdentifier("screenshot8d", "drawable", getPackageName()), Color.parseColor("#3F51B5")));

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#303F9F"));
        setSeparatorColor(Color.parseColor("#FFFFFF"));

        // Hide Skip/Done button.
        showSkipButton(true);
        setProgressButtonEnabled(true);

        setSkipText(getString(R.string.str_skip));
        setDoneText(getString(R.string.str_understood));

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
        //setVibrate(false);
        //setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }

}
