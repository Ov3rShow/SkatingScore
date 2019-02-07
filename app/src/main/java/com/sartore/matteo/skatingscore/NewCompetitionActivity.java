package com.sartore.matteo.skatingscore;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.R.attr.startYear;

public class NewCompetitionActivity extends AppCompatActivity {

    public DbHelper db;
    EditText txtplace;
    EditText txttitle;
    RadioButton radio3;
    RadioButton radio5;
    Button btndate;
    TextView txtdate;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_competition);

        getSupportActionBar().setElevation(0);

        txttitle = findViewById(R.id.newcomp_edittxt1);
        txtplace = findViewById(R.id.newcomp_edittxt2);
        radio3 = findViewById(R.id.newcomp_radio1);
        radio5 = findViewById(R.id.newcomp_radio2);
        btndate = findViewById(R.id.newcomp_btn2);
        txtdate = findViewById(R.id.newcomp_txtview2);
        fab = findViewById(R.id.newcomp_btn1);

        db = new DbHelper(this);

        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                //System.out.println("the selected " + mDay);
                DatePickerDialog dialog = new DatePickerDialog(NewCompetitionActivity.this, new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        Date date = new Date();
                        DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy", Locale.ITALY);
                        txtdate.setText(dateFormat.format(newDate.getTime()));
                    }

                }, mYear, mMonth, mDay);
                dialog.show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ngiudes = 0;
                if(radio3.isChecked())
                {
                    ngiudes = 3;
                }
                else if (radio5.isChecked())
                {
                    ngiudes = 5;
                }
                String title = String.valueOf(txttitle.getText());
                String place = String.valueOf(txtplace.getText());
                String date = String.valueOf(txtdate.getText());
                if(title.trim().compareTo("") != 0 && (radio3.isChecked() || radio5.isChecked()))
                {
                    if(db.saveCompetition(title, place, date, ngiudes))
                    {
                        Toast.makeText(getApplication(), getString(R.string.str_competitionSaved), Toast.LENGTH_LONG).show();
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplication(), getString(R.string.str_competitionNotSaved), Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplication(), getString(R.string.str_insertRequiredCompetitionFields), Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}



