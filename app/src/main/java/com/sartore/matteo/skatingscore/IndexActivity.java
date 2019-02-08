package com.sartore.matteo.skatingscore;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class IndexActivity extends AppCompatActivity {

    Fragment fragment;
    public static CompetitionClass current_competition;
    public static int current_competition_index = -1;
    public static ArrayAdapter<String> sortingOptions;
    BottomNavigationView navigation;
    public static int currentShownFragment;
    public static int selectedCompetitionOrder = -1, selectedAthletesOrder = -1, selectedScoresOrder = -1, selectedResultsOrder = 0;
    MenuItem sortBtn;
    SharedPreferences preferences;
    MenuItem btn_settings, btn_help;
    boolean isAppLaunching = true;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_bar, menu);

        sortBtn = menu.findItem(R.id.btn_ordina);

        if(preferences.getBoolean("NightMode", false))
        {
            btn_settings = menu.findItem(R.id.action_settings);
            btn_help = menu.findItem(R.id.action_help);

            btn_settings.setIcon(R.drawable.ic_settings_white_24dp);
            btn_help.setIcon(R.drawable.ic_help_white_24dp);
        }

        if(currentShownFragment == 0)
            sortBtn.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_help:
            {
                Intent myIntent = new Intent(IndexActivity.this, AppTourActivity.class);
                IndexActivity.this.startActivity(myIntent);
                break;
            }
            case R.id.action_settings:
            {
                Intent myIntent = new Intent(IndexActivity.this, SettingsActivity.class);
                //IndexActivity.this.startActivityForResult(myIntent, 0);
                IndexActivity.this.startActivity(myIntent);
                break;
            }
            case R.id.btn_ordina:
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(IndexActivity.this);
                /*builder.setTitle(getString(R.string.str_chooseSortingRule)).setAdapter(sortingOptions, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedCompetitionOrder = which;
                    }
                });*/

                builder.setTitle(getString(R.string.str_chooseSortingRule)).setAdapter(sortingOptions, null);

                //Se il fragment visualizzato è quello delle gare
                if(currentShownFragment == 0)
                {
                    //NON IMPLEMENTATO

                }
                else if (currentShownFragment == 1)
                {
                    builder.setSingleChoiceItems(sortingOptions, selectedAthletesOrder,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    ((AthletesFragment)fragment).orderList(arg1);
                                    selectedAthletesOrder = arg1;
                                }
                            });
                }
                else if (currentShownFragment == 2)
                {
                    builder.setSingleChoiceItems(sortingOptions, selectedScoresOrder,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    //NON IMPLEMENTATO
                                }
                            });
                }
                else if (currentShownFragment == 3)
                {
                    builder.setSingleChoiceItems(sortingOptions, selectedResultsOrder,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                    ((ResultsFragment)fragment).orderList(arg1);
                                    selectedResultsOrder = arg1;
                                }
                            });
                }

                builder.show();
            }
            default:
            {   }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        //showAlertDialog();

        //this.setTitle("SkatingScore - Home");

        //NEW 02-02-2019 FIX DB
        DbHelper dbHelper = new DbHelper(this);
        dbHelper.fixDatabase();

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(preferences.getBoolean("NightMode", false))
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        if(isAppLaunching)
        {
            isAppLaunching = false;

            navigation = findViewById(R.id.navigationtab);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

            ChangeFragment(0);
            ChangeSortOptions(0);

            //Se è il primo avvio visualizzo un messaggio di benvenuto
            if(CheckIfIsFirstRun())
            {
                AlertDialog alertDialog = new AlertDialog.Builder(IndexActivity.this).create();
                alertDialog.setTitle("Benvenuto/a!");
                alertDialog.setMessage("Sembra che l'applicazione sia appena stata installata (o aggiornata)!\nHai bisogno di visualizzare una breve guida per iniziare a scoprire SkatingScore?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Certo!",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent myIntent = new Intent(IndexActivity.this, AppTourActivity.class);
                                IndexActivity.this.startActivity(myIntent);
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No, grazie",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_events:
                    ChangeFragment(0);
                    currentShownFragment = 0;
                    ChangeSortOptions(0);
                    return true;
                case R.id.navigation_athletes:
                    ChangeFragment(1);
                    currentShownFragment = 1;
                    ChangeSortOptions(1);
                    return true;
                case R.id.navigation_scores:
                    ChangeFragment(2);
                    currentShownFragment = 2;
                    ChangeSortOptions(2);
                    return true;
                case R.id.navigation_results:
                    ChangeFragment(3);
                    currentShownFragment = 3;
                    ChangeSortOptions(3);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onResume() {
        UpdateVisuals();
        super.onResume();
    }

    public void UpdateVisuals()
    {
        //DbHelper db = new DbHelper(this);

        Fragment myFragment = getFragmentManager().findFragmentByTag("fragment_tag_fragment0");
        if (myFragment != null && myFragment.isVisible()) {
            final android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(myFragment);
            ft.attach(myFragment);
            ft.commit();
        }
        myFragment = getFragmentManager().findFragmentByTag("fragment_tag_fragment1");
        if (myFragment != null && myFragment.isVisible()) {
            final android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(myFragment);
            ft.attach(myFragment);
            ft.commit();
        }
        myFragment = getFragmentManager().findFragmentByTag("fragment_tag_fragment2");
        if (myFragment != null && myFragment.isVisible()) {
            final android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(myFragment);
            ft.attach(myFragment);
            ft.commit();
        }
        /*myFragment = getFragmentManager().findFragmentByTag("fragment_tag_fragment3");
        if (myFragment != null && myFragment.isVisible()) {
            final android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(myFragment);
            ft.attach(myFragment);
            ft.commit();
        }*/
    }

    public void ChangeFragment(int number)
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(number == 0)
        {
            this.setTitle(getString(R.string.str_titleHome));
            fragment = new CompetitionsFragment();
            ft.replace(R.id.container, fragment, "fragment_tag_fragment0");
            ft.commit();

            if(sortBtn != null && sortBtn.isVisible())
                sortBtn.setVisible(false);
        }
        else if(number == 1)
        {
            this.setTitle(getString(R.string.str_titleGroups));
            fragment = new AthletesFragment();
            ft.replace(R.id.container, fragment, "fragment_tag_fragment1");
            ft.commit();

            if(!sortBtn.isVisible())
                sortBtn.setVisible(true);
        }
        else if(number == 2)
        {
            this.setTitle(getString(R.string.str_titleScores));
            fragment = new ScoresFragment();
            ft.replace(R.id.container, fragment, "fragment_tag_fragment2");
            ft.commit();

            if(!sortBtn.isVisible())
                sortBtn.setVisible(true);
        }
        else if(number == 3)
        {
            this.setTitle(getString(R.string.str_titleResults));
            fragment = new ResultsFragment();
            ft.replace(R.id.container, fragment, "fragment_tag_fragment3");
            ft.commit();

            if(!sortBtn.isVisible())
                sortBtn.setVisible(true);
        }
    }

    public boolean CheckIfIsFirstRun()
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstRun = preferences.getBoolean("FirstRun", true);

        if(isFirstRun)
        {
            preferences.edit().putBoolean("FirstRun", false).apply();
        }

        return isFirstRun;
    }

    public void ChangeSortOptions(int number)
    {
        sortingOptions = new ArrayAdapter<>(this, android.R.layout.select_dialog_singlechoice);
        sortingOptions.clear();

        if(number == 0)
        {
            sortingOptions.add(getString(R.string.str_ByName));
            sortingOptions.add(getString(R.string.str_ByDate));
            sortingOptions.add(getString(R.string.str_ByPlace));
        }
        else if (number == 1)
        {
            sortingOptions.add(getString(R.string.str_ByName));
            sortingOptions.add(getString(R.string.str_BySociety));
            sortingOptions.add(getString(R.string.str_ByTitle));
            sortingOptions.add(getString(R.string.str_ByCompetitionOrder));
        }
        else if (number == 2)
        {
            sortingOptions.add(getString(R.string.str_ByName));
            sortingOptions.add(getString(R.string.str_BySociety));
            sortingOptions.add(getString(R.string.str_ByTitle));
            sortingOptions.add(getString(R.string.str_ByCompetitionOrder));
        }
        else if (number == 3)
        {
            sortingOptions.add(getString(R.string.str_Ranking));
            sortingOptions.add(getString(R.string.str_Judge) + " 1");
            sortingOptions.add(getString(R.string.str_Judge) + " 2");
            sortingOptions.add(getString(R.string.str_Judge) + " 3");
            sortingOptions.add(getString(R.string.str_Judge) + " 4");
            sortingOptions.add(getString(R.string.str_Judge) + " 5");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0)
        {
            recreate();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}