package com.sartore.matteo.skatingscore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CompetitionsFragment extends Fragment implements View.OnClickListener{

    FloatingActionButton fab;
    ListView listView;
    TextView hint;
    DbHelper db;
    public static List<CompetitionClass> list;
    public static CompetitionListAdapter adapter;
    public static int selectedCompetition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_competitions, container, false);
        fab = view.findViewById(R.id.fab_competitions);
        listView = view.findViewById(R.id.listview_competitions);
        hint = view.findViewById(R.id.hint_competitions);
        fab.setOnClickListener(this);

        populateListView();

        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IndexActivity.current_competition = new CompetitionClass();
                IndexActivity.current_competition.setTitle(list.get(position).getTitle());
                IndexActivity.current_competition.setDate(list.get(position).getDate());
                IndexActivity.current_competition.setPlace(list.get(position).getPlace());
                IndexActivity.current_competition.setId(list.get(position).getId());
                IndexActivity.current_competition.setType(list.get(position).getType());
                IndexActivity.current_competition.setAthletes(db.getAthletes(list.get(position).getId()));
                //view.setBackgroundColor(255255255);
                IndexActivity.current_competition_index = position;
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), getString(R.string.str_competitionSelected), Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                createAndShowAlertDialog(list.get(position).getId());
                return true;
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        Intent myIntent = new Intent(getActivity(), NewCompetitionActivity.class);
        //getActivity().startActivity(myIntent);
        startActivityForResult(myIntent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //aggiorna la lista delle competizioni quando la form di aggiunta viene chiusa
        if(requestCode == 0)
        {
            populateListView();
        }
    }

    void populateListView()
    {
        db = new DbHelper(getActivity());
        list = db.getCompetitionsList();

        if(list.size()==0)
        {
            hint.setVisibility(View.VISIBLE);
        }
        else
        {
            hint.setVisibility(View.INVISIBLE);
            adapter = new CompetitionListAdapter(getActivity(), R.layout.competitionlist_row_layout, list);
            listView.setAdapter(adapter);
        }
    }

    private void createAndShowAlertDialog(final int idC) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.str_RemoveCompetition));
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if(db.removeCompetition(idC))
                {
                    Toast.makeText(getActivity(), getString(R.string.str_CompetitionRemoved), Toast.LENGTH_LONG).show();
                    //IndexActivity.current_competition.setId(-1); OBSOLETO, serviva per la disattivazione del fab nel fragment dei concorrenti
                    IndexActivity.current_competition = null;
                    IndexActivity.current_competition_index = -1;
                    AthletesFragment.ath_modified = true;
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

    /*public void orderList(final int orderListMode)
    {
        if(orderListMode == 0)
        {
            adapter.sort(new Comparator<CompetitionClass>() {
                @Override
                public int compare(CompetitionClass competitionClass, CompetitionClass t1) {
                    return competitionClass.getTitle().compareTo(t1.getTitle());
                }
            });
        }
        else if(orderListMode == 1)
        {
            adapter.sort(new Comparator<CompetitionClass>() {
                @Override
                public int compare(CompetitionClass competitionClass, CompetitionClass t1) {
                    DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ITALIAN);
                    Date date1 = null, date2 = null;
                    try
                    {
                        date1 = format.parse(competitionClass.getDate());
                        date2 = format.parse(t1.getDate());
                    }
                    catch (Exception ex) {}
                    return date1.compareTo(date2);
                }
            });
        }
        else if(orderListMode == 2)
        {
            adapter.sort(new Comparator<CompetitionClass>() {
                @Override
                public int compare(CompetitionClass competitionClass, CompetitionClass t1) {
                    return competitionClass.getPlace().compareTo(t1.getPlace());
                }
            });
        }

        adapter.notifyDataSetChanged();
    }*/
}