package com.sartore.matteo.skatingscore;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sartore.matteo.skatingscore.R;

import java.util.List;

public class ScoresFragment extends Fragment{

    ListView list;
    ScoresListAdapter adapter;
    public static boolean score_modified = false;
    DbHelper db;
    List athletes;
    TextView hint;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scores, container, false);

        hint = view.findViewById(R.id.hint_scores);
        list = view.findViewById(R.id.score_listview);
        db = new DbHelper(getActivity());

        if(IndexActivity.current_competition != null)
        {
            ViewScores();

            list.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent myIntent = new Intent(getActivity(), SetScoresActivity.class);
                    myIntent.putExtra("name", IndexActivity.current_competition.getAthlete(position).getName());
                    myIntent.putExtra("idC", String.valueOf(IndexActivity.current_competition.getId()));
                    myIntent.putExtra("A1", String.valueOf(IndexActivity.current_competition.getAthlete(position).getScore().getA1()));
                    myIntent.putExtra("A2", String.valueOf(IndexActivity.current_competition.getAthlete(position).getScore().getA2()));
                    myIntent.putExtra("A3", String.valueOf(IndexActivity.current_competition.getAthlete(position).getScore().getA3()));
                    myIntent.putExtra("A4", String.valueOf(IndexActivity.current_competition.getAthlete(position).getScore().getA4()));
                    myIntent.putExtra("A5", String.valueOf(IndexActivity.current_competition.getAthlete(position).getScore().getA5()));
                    myIntent.putExtra("B1", String.valueOf(IndexActivity.current_competition.getAthlete(position).getScore().getB1()));
                    myIntent.putExtra("B2", String.valueOf(IndexActivity.current_competition.getAthlete(position).getScore().getB2()));
                    myIntent.putExtra("B3", String.valueOf(IndexActivity.current_competition.getAthlete(position).getScore().getB3()));
                    myIntent.putExtra("B4", String.valueOf(IndexActivity.current_competition.getAthlete(position).getScore().getB4()));
                    myIntent.putExtra("B5", String.valueOf(IndexActivity.current_competition.getAthlete(position).getScore().getB5()));
                    startActivityForResult(myIntent, 0);
                }
            });
        }
        else
        {
            hint.setVisibility(View.VISIBLE);
            hint.setText(getString(R.string.str_selectACompetition));
        }

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == 0)
        {
            ViewScores();
        }
    }

    void ViewScores()
    {
        if(AthletesFragment.ath_modified || IndexActivity.current_competition.getAthletes() == null || score_modified)
        {
            IndexActivity.current_competition.setAthletes(db.getAthletes(IndexActivity.current_competition.getId()));
            score_modified = false;
        }
        athletes = IndexActivity.current_competition.getAthletes();
        if(athletes.size() == 0)
        {
            hint.setVisibility(View.VISIBLE);
            hint.setText(getString(R.string.str_insertACompetitor));
        }
        else
        {
            hint.setVisibility(View.INVISIBLE);
            adapter = new ScoresListAdapter(getActivity(), R.layout.scoreslist_row_layout, athletes);
            list.setAdapter(adapter);
        }
    }
}