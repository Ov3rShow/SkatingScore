package com.sartore.matteo.skatingscore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sartore.matteo.skatingscore.R;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class AthletesFragment extends Fragment implements View.OnClickListener {

    FloatingActionButton fab;
    TextView hint;
    List<AthleteClass> athletes = new ArrayList<>();
    DbHelper db;
    ListView listView;
    AthletesListAdapter adapter;
    public static boolean ath_modified = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_athletes, container, false);
        fab = view.findViewById(R.id.fab_athletes);
        listView = view.findViewById(R.id.listview_athletes);
        hint = view.findViewById(R.id.hint_athletes);
        fab.setOnClickListener(this);
        db = new DbHelper(getActivity());

        try {
            if (IndexActivity.current_competition != null) {

                showAthletes();

                listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent myIntent = new Intent(getActivity(), NewAthleteActivity.class);
                        myIntent.putExtra("name", IndexActivity.current_competition.getAthlete(position).getName());
                        myIntent.putExtra("society", IndexActivity.current_competition.getAthlete(position).getSociety());
                        myIntent.putExtra("title", IndexActivity.current_competition.getAthlete(position).getTitle());
                        myIntent.putExtra("order", String.valueOf(IndexActivity.current_competition.getAthlete(position).getPosition()));
                        getActivity().startActivity(myIntent);
                    }
                });

                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        createAndShowAlertDialog(IndexActivity.current_competition.getAthlete(position).getName());
                        return true;
                    }
                });
            } else {
                hint.setVisibility(View.VISIBLE);
                hint.setText(getString(R.string.str_selectACompetition));
            }
        } catch (Exception ex) {
            Toast.makeText(getActivity(), getString(R.string.str_groupError) + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        if (IndexActivity.current_competition != null) {
            Intent myIntent = new Intent(getActivity(), NewAthleteActivity.class);
            startActivity(myIntent);
        } else {
            Toast.makeText(getActivity(), getString(R.string.str_selectACompetition), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        if (IndexActivity.current_competition != null) {
            showAthletes();
        }
        super.onResume();
    }

    void showAthletes()
    {
        if(IndexActivity.current_competition != null)
        {
            if (ath_modified || IndexActivity.current_competition.getAthletes() == null) {
                IndexActivity.current_competition.setAthletes(db.getAthletes(IndexActivity.current_competition.getId()));
                ath_modified = false;
            }
            athletes = IndexActivity.current_competition.getAthletes();
        }
        if (athletes.size() == 0) {
            hint.setVisibility(View.VISIBLE);
            hint.setText(getString(R.string.str_newCompetitor));
                    /*if(IndexActivity.current_competition.getId() == -1) OBSOLETO, disattivata il fab quando nessuna gara era selezionata
                    {
                        fab.setEnabled(false);
                    }*/
        } else {
            hint.setVisibility(View.INVISIBLE);
                    /*if(!fab.isEnabled()) OBSOLETO, attivava il fab quando una gara era selezionata
                    {
                        fab.setEnabled(true);
                    }*/
            adapter = new AthletesListAdapter(getActivity(), R.layout.athleteslist_row_layout, athletes);
            listView.setAdapter(adapter);
        }
    }

    private void createAndShowAlertDialog(final String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.str_doYouWantToRemove) +" " +  name + " " + getString(R.string.str_fromThisCompetition));
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (db.removeAthlete(Integer.parseInt(db.checkAthlete(name)), IndexActivity.current_competition.getId())) {
                    Toast.makeText(getActivity(), getString(R.string.str_groupRemoved), Toast.LENGTH_LONG).show();
                    ath_modified = true;
                    showAthletes();
                }
                IndexActivity act = (IndexActivity) getActivity();
                act.UpdateVisuals();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //TODO
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void orderList(final int orderListMode) {
        if (orderListMode == 0) {
            adapter.sort(new Comparator<AthleteClass>() {
                @Override
                public int compare(AthleteClass ath1, AthleteClass ath2) {
                    return ath1.getName().toLowerCase().compareTo(ath2.getName().toLowerCase());
                }
            });
        } else if (orderListMode == 1) {
            adapter.sort(new Comparator<AthleteClass>() {
                @Override
                public int compare(AthleteClass ath1, AthleteClass ath2) {
                    return ath1.getSociety().toLowerCase().compareTo(ath2.getSociety().toLowerCase());
                }
            });
        } else if (orderListMode == 2) {
            adapter.sort(new Comparator<AthleteClass>() {
                @Override
                public int compare(AthleteClass ath1, AthleteClass ath2) {
                    return ath1.getTitle().toLowerCase().compareTo(ath2.getTitle().toLowerCase());
                }
            });
        } else if (orderListMode == 3) {
            adapter.sort(new Comparator<AthleteClass>() {
                @Override
                public int compare(AthleteClass ath1, AthleteClass ath2) {

                    int retValue = 0;

                    if (ath1.getPosition() > ath2.getPosition())
                        retValue = 1;
                    else if (ath1.getPosition() == ath2.getPosition())
                        retValue = 0;
                    else if (ath1.getPosition() < ath2.getPosition())
                        retValue = -1;

                    return retValue;
                }
            });
        }
    }
}