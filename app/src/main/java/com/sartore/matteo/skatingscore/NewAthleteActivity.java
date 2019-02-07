package com.sartore.matteo.skatingscore;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewAthleteActivity extends AppCompatActivity {

    FloatingActionButton fab;
    DbHelper db;
    EditText edittxtname;
    EditText edittxtsociety;
    EditText edittxtorder;
    EditText edittxttitle;
    TextView hinttxtview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_athlete);

        getSupportActionBar().setElevation(0);

        fab = findViewById(R.id.newath_btn1);
        edittxtname = findViewById(R.id.newath_edittxt1);
        edittxtsociety = findViewById(R.id.newath_edittxt2);
        edittxtorder = findViewById(R.id.newath_edittxt3);
        edittxttitle = findViewById(R.id.newath_edittxt4);
        hinttxtview = findViewById(R.id.newath_lbltitle);
        db = new DbHelper(getApplicationContext());

        try
        {
            String nametoUpper = getIntent().getStringExtra("name").substring(0,1).toUpperCase()+getIntent().getStringExtra("name").substring(1, getIntent().getStringExtra("name").length());
            edittxtname.setText(nametoUpper);
            edittxtsociety.setText(getIntent().getStringExtra("society"));
            edittxtorder.setText(getIntent().getStringExtra("order"));
            edittxttitle.setText(getIntent().getStringExtra("title"));
            if(!edittxtname.getText().toString().trim().equals(""))
            {
                hinttxtview.setText(getString(R.string.str_editCompetitor));
            }
        }
        catch (Exception ex)
        {   }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldname = getIntent().getStringExtra("name");
                String newname = edittxtname.getText().toString();
                String society = edittxtsociety.getText().toString();
                String order = edittxtorder.getText().toString();
                String title = edittxttitle.getText().toString();
                if(!newname.trim().equals(""))
                {
                    try
                    {
                        if(db.saveAthlete(newname, society, title, order, String.valueOf(IndexActivity.current_competition.getId()), db.checkAthlete(oldname)))
                        {
                            Toast.makeText(getApplication(), getString(R.string.str_competitorAdded), Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(getApplication(), getString(R.string.str_OopsAnErrorOccurred), Toast.LENGTH_LONG).show();
                        }
                    }
                    catch (Exception ex)
                    {
                        if(db.saveAthlete(newname, society, title, order, String.valueOf(IndexActivity.current_competition.getId()), db.checkAthlete(newname)))
                        {
                            Toast.makeText(getApplication(), getString(R.string.str_competitorAdded), Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(getApplication(), getString(R.string.str_OopsAnErrorOccurred), Toast.LENGTH_LONG).show();
                        }
                    }
                    AthletesFragment.ath_modified = true;
                }
                else
                {
                    Toast.makeText(getApplication(), getString(R.string.str_insertCompetitorName), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
