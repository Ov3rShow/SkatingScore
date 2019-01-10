package com.sartore.matteo.skatingscore;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SetScoresActivity extends AppCompatActivity {

    TextView nameath;
    EditText A1, A2, A3, A4, A5, B1, B2, B3, B4, B5;
    FloatingActionButton fab;
    DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_scores);

        fab = findViewById(R.id.setscores_fab);
        nameath = findViewById(R.id.setscores_lblath);
        A1 = findViewById(R.id.setscores_A1);
        A2 = findViewById(R.id.setscores_A2);
        A3 = findViewById(R.id.setscores_A3);
        A4 = findViewById(R.id.setscores_A4);
        A5 = findViewById(R.id.setscores_A5);
        B1 = findViewById(R.id.setscores_B1);
        B2 = findViewById(R.id.setscores_B2);
        B3 = findViewById(R.id.setscores_B3);
        B4 = findViewById(R.id.setscores_B4);
        B5 = findViewById(R.id.setscores_B5);

        String athname = getIntent().getStringExtra("name").substring(0, 1).toUpperCase().concat(getIntent().getStringExtra("name").substring(1));
        nameath.setText(athname);
        db = new DbHelper(this);

        if(IndexActivity.current_competition.getType()==3)
        {
            B4.setEnabled(false);
            B4.setFocusable(false);
            B5.setEnabled(false);
            B5.setFocusable(false);
            A4.setEnabled(false);
            A4.setFocusable(false);
            A5.setEnabled(false);
            A5.setFocusable(false);
            A3.setNextFocusDownId(R.id.setscores_B1);
            if(Double.parseDouble(getIntent().getStringExtra("A1"))!=0 && Double.parseDouble(getIntent().getStringExtra("A2"))!=0 && Double.parseDouble(getIntent().getStringExtra("A3"))!=0
                    && Double.parseDouble(getIntent().getStringExtra("B1"))!=0 && Double.parseDouble(getIntent().getStringExtra("B2"))!=0 && Double.parseDouble(getIntent().getStringExtra("B3"))!=0)
            {
                A1.setText(String.valueOf(Double.parseDouble(getIntent().getStringExtra("A1"))*10).substring(0, getIntent().getStringExtra("A1").indexOf(".")+1).replace(".", ""));
                A2.setText(String.valueOf(Double.parseDouble(getIntent().getStringExtra("A2"))*10).substring(0, getIntent().getStringExtra("A2").indexOf(".")+1).replace(".", ""));
                A3.setText(String.valueOf(Double.parseDouble(getIntent().getStringExtra("A3"))*10).substring(0, getIntent().getStringExtra("A3").indexOf(".")+1).replace(".", ""));
                B1.setText(String.valueOf(Double.parseDouble(getIntent().getStringExtra("B1"))*10).substring(0, getIntent().getStringExtra("B1").indexOf(".")+1).replace(".", ""));
                B2.setText(String.valueOf(Double.parseDouble(getIntent().getStringExtra("B2"))*10).substring(0, getIntent().getStringExtra("B2").indexOf(".")+1).replace(".", ""));
                B3.setText(String.valueOf(Double.parseDouble(getIntent().getStringExtra("B3"))*10).substring(0, getIntent().getStringExtra("B3").indexOf(".")+1).replace(".", ""));
            }
        }
        else if(IndexActivity.current_competition.getType()==5)
        {
            if(Double.parseDouble(getIntent().getStringExtra("A1"))!=0 && Double.parseDouble(getIntent().getStringExtra("A2"))!=0 && Double.parseDouble(getIntent().getStringExtra("A3"))!=0
                    && Double.parseDouble(getIntent().getStringExtra("B1"))!=0 && Double.parseDouble(getIntent().getStringExtra("B2"))!=0 && Double.parseDouble(getIntent().getStringExtra("B3"))!=0
                    && Double.parseDouble(getIntent().getStringExtra("A4"))!=0 && Double.parseDouble(getIntent().getStringExtra("A5"))!=0 && Double.parseDouble(getIntent().getStringExtra("B4"))!=0 && Double.parseDouble(getIntent().getStringExtra("B5"))!=0)
            {
                A1.setText(String.valueOf(Double.parseDouble(getIntent().getStringExtra("A1"))*10).substring(0, getIntent().getStringExtra("A1").indexOf(".")+1).replace(".", ""));
                A2.setText(String.valueOf(Double.parseDouble(getIntent().getStringExtra("A2"))*10).substring(0, getIntent().getStringExtra("A2").indexOf(".")+1).replace(".", ""));
                A3.setText(String.valueOf(Double.parseDouble(getIntent().getStringExtra("A3"))*10).substring(0, getIntent().getStringExtra("A3").indexOf(".")+1).replace(".", ""));
                A4.setText(String.valueOf(Double.parseDouble(getIntent().getStringExtra("A4"))*10).substring(0, getIntent().getStringExtra("A4").indexOf(".")+1).replace(".", ""));
                A5.setText(String.valueOf(Double.parseDouble(getIntent().getStringExtra("A5"))*10).substring(0, getIntent().getStringExtra("A5").indexOf(".")+1).replace(".", ""));
                B1.setText(String.valueOf(Double.parseDouble(getIntent().getStringExtra("B1"))*10).substring(0, getIntent().getStringExtra("B1").indexOf(".")+1).replace(".", ""));
                B2.setText(String.valueOf(Double.parseDouble(getIntent().getStringExtra("B2"))*10).substring(0, getIntent().getStringExtra("B2").indexOf(".")+1).replace(".", ""));
                B3.setText(String.valueOf(Double.parseDouble(getIntent().getStringExtra("B3"))*10).substring(0, getIntent().getStringExtra("B3").indexOf(".")+1).replace(".", ""));
                B4.setText(String.valueOf(Double.parseDouble(getIntent().getStringExtra("B4"))*10).substring(0, getIntent().getStringExtra("B4").indexOf(".")+1).replace(".", ""));
                B5.setText(String.valueOf(Double.parseDouble(getIntent().getStringExtra("B5"))*10).substring(0, getIntent().getStringExtra("B5").indexOf(".")+1).replace(".", ""));
            }
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Controllo valori
                //normalizeValues(IndexActivity.current_competition.getType());

                A1.clearFocus();
                A2.clearFocus();
                A3.clearFocus();
                A4.clearFocus();
                A5.clearFocus();
                B1.clearFocus();
                B2.clearFocus();
                B3.clearFocus();
                B4.clearFocus();
                B5.clearFocus();

                try
                {
                    if(IndexActivity.current_competition.getType()==5)
                    {
                        if(db.saveScore(getIntent().getStringExtra("idC"), db.checkAthlete(getIntent().getStringExtra("name")),
                                Double.parseDouble(A1.getText().toString())/10, Double.parseDouble(A2.getText().toString())/10, Double.parseDouble(A3.getText().toString())/10,
                                Double.parseDouble(A4.getText().toString())/10, Double.parseDouble(A5.getText().toString())/10,
                                Double.parseDouble(B1.getText().toString())/10, Double.parseDouble(B2.getText().toString())/10, Double.parseDouble(B3.getText().toString())/10,
                                Double.parseDouble(B4.getText().toString())/10, Double.parseDouble(B5.getText().toString())/10))
                        {
                            Toast.makeText(getApplicationContext(), getString(R.string.str_scoresSaved), Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                    else if (IndexActivity.current_competition.getType()==3)
                    {
                        if(db.saveScore(getIntent().getStringExtra("idC"), db.checkAthlete(getIntent().getStringExtra("name")),
                                Double.parseDouble(A1.getText().toString())/10, Double.parseDouble(A2.getText().toString())/10, Double.parseDouble(A3.getText().toString())/10, 0, 0,
                                Double.parseDouble(B1.getText().toString())/10, Double.parseDouble(B2.getText().toString())/10, Double.parseDouble(B3.getText().toString())/10, 0 ,0))
                        {
                            Toast.makeText(getApplicationContext(), getString(R.string.str_scoresSaved), Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                    ScoresFragment.score_modified = true;
                }
                catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(), getString(R.string.str_scoresNotSaved), Toast.LENGTH_LONG).show();
                }
            }
        });

        A1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    checkInputValue(A1);
                }
            }
        });

        A2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    checkInputValue(A2);
                }
            }
        });

        A3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    checkInputValue(A3);
                }
            }
        });

        A4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    checkInputValue(A4);
                }
            }
        });

        A5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    checkInputValue(A5);
                }
            }
        });

        B1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    checkInputValue(B1);
                }
            }
        });

        B2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    checkInputValue(B2);
                }
            }
        });

        B3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    checkInputValue(B3);
                }
            }
        });

        B4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    checkInputValue(B4);
                }
            }
        });

        B5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b)
                {
                    checkInputValue(B5);
                }
            }
        });
    }

    //Normalizzazione valori
    void normalizeValues(int competitionType)
    {
        if(Integer.parseInt(A1.getText().toString()) == 10)
        {
            A1.setText("100");
        }
        if(Integer.parseInt(A2.getText().toString()) == 10)
        {
            A2.setText("100");
        }
        if(Integer.parseInt(A3.getText().toString()) == 10)
        {
            A3.setText("100");
        }
        if(Integer.parseInt(B1.getText().toString()) == 10)
        {
            B1.setText("100");
        }
        if(Integer.parseInt(B2.getText().toString()) == 10)
        {
            B2.setText("100");
        }
        if(Integer.parseInt(B3.getText().toString()) == 10)
        {
            B3.setText("100");
        }
        if(competitionType == 5)
        {
            if(Integer.parseInt(A4.getText().toString()) == 10)
            {
                A4.setText("100");
            }
            if(Integer.parseInt(A5.getText().toString()) == 10)
            {
                A5.setText("100");
            }
            if(Integer.parseInt(B4.getText().toString()) == 10)
            {
                B4.setText("100");
            }
            if(Integer.parseInt(B5.getText().toString()) == 10)
            {
                B5.setText("100");
            }
        }
    }

    void checkInputValue(EditText parameter)
    {
        if(!parameter.getText().toString().trim().equals(""))
        {
            if(parameter.getText().toString().length()==1)
                parameter.setText(parameter.getText().toString().concat("0"));
            if(parameter.getText().toString().equals("10"))
                parameter.setText(parameter.getText().toString().concat("0"));

            if(!(Double.parseDouble(parameter.getText().toString()) >= 20 && Double.parseDouble(parameter.getText().toString()) <= 100))
            {
                Toast.makeText(getApplicationContext(), getString(R.string.str_invalidScore), Toast.LENGTH_LONG).show();
                parameter.setText("");
            }
        }
    }
}
