package com.sartore.matteo.skatingscore;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sartore.matteo.skatingscore.R;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ResultsFragment extends Fragment {

    ListView listView;
    ResultsListAdapter adapter;
    CreateRank createRank;
    TextView hinttxtView;
    List<RankClass> tempList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        try
        {
            view = inflater.inflate(R.layout.fragment_results, container, false);

            hinttxtView = view.findViewById(R.id.hint_results);

            if(IndexActivity.current_competition != null)
            {
                if(IndexActivity.current_competition.getAthletes().size() > 0)
                {
                    if(IndexActivity.current_competition.getAthletesWithScoreHigherThanZero().size() > 0)
                    {
                        hinttxtView.setVisibility(View.INVISIBLE);

                        createRank = new CreateRank(IndexActivity.current_competition.getAthletesWithScoreHigherThanZero());

                        listView = view.findViewById(R.id.list_results);

                        tempList = createRank.getRank();
                        Collections.sort(tempList, new Comparator<RankClass>() {
                            @Override
                            public int compare(RankClass c1, RankClass c2) {
                                return Double.compare(c1.getMajorityDecisions(), c2.getMajorityDecisions());
                            }
                        });
                        Collections.reverse(tempList);

                        adapter = new ResultsListAdapter(getActivity(), R.layout.resultslist_row_layout, tempList);
                        listView.setAdapter(adapter);
                    }
                    else
                    {
                        hinttxtView.setText("La classifica verrà visualizzata qui!");
                        hinttxtView.setVisibility(View.VISIBLE);
                    }
                }
                else
                {
                    hinttxtView.setText("Aggiungi un concorrente!");
                    hinttxtView.setVisibility(View.VISIBLE);
                }
            }
            else
            {
                hinttxtView.setText("Seleziona una gara!");
                hinttxtView.setVisibility(View.VISIBLE);
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(getActivity(), "Errore nel calcolo della classifica" + ex.toString(), Toast.LENGTH_LONG).show();
        }
        return view;
    }

    @Override
    public void onResume() {
        if(adapter!=null)
        {
            orderList(IndexActivity.selectedResultsOrder);
        }

        super.onResume();
    }

    public void orderList(final int orderListMode) {
        if(adapter!=null) {
            if (orderListMode == 0) {
                adapter.sort(new Comparator<RankClass>() {
                    @Override
                    public int compare(RankClass rank1, RankClass rank2) {
                        int retValue = 0;
                        if(rank1.getMajorityDecisions() > rank2.getMajorityDecisions())
                            retValue = -1;
                        else if (rank1.getMajorityDecisions() == rank2.getMajorityDecisions())
                            retValue = 0;
                        else if(rank1.getMajorityDecisions() < rank2.getMajorityDecisions())
                            retValue = 1;
                        return retValue;
                    }
                });
            } else if (orderListMode == 1) {
                adapter.sort(new Comparator<RankClass>() {
                    @Override
                    public int compare(RankClass rank1, RankClass rank2) {

                        int retValue = 0;
                        double punteggirank1 = rank1.getPunteggi().getA1() + rank1.getPunteggi().getB1();
                        double punteggirank2 = rank2.getPunteggi().getA1() + rank2.getPunteggi().getB1();

                        if(punteggirank1 > punteggirank2)
                            retValue = -1;
                        else if (punteggirank1 == punteggirank2)
                            retValue = 0;
                        else if(punteggirank1 < punteggirank2)
                            retValue = 1;
                        return retValue;
                    }
                });
            } else if (orderListMode == 2) {
                adapter.sort(new Comparator<RankClass>() {
                    @Override
                    public int compare(RankClass rank1, RankClass rank2) {

                        int retValue = 0;
                        double punteggirank1 = rank1.getPunteggi().getA2() + rank1.getPunteggi().getB2();
                        double punteggirank2 = rank2.getPunteggi().getA2() + rank2.getPunteggi().getB2();

                        if(punteggirank1 > punteggirank2)
                            retValue = -1;
                        else if (punteggirank1 == punteggirank2)
                            retValue = 0;
                        else if(punteggirank1 < punteggirank2)
                            retValue = 1;
                        return retValue;
                    }
                });
            } else if (orderListMode == 3) {
                adapter.sort(new Comparator<RankClass>() {
                    @Override
                    public int compare(RankClass rank1, RankClass rank2) {

                        int retValue = 0;
                        double punteggirank1 = rank1.getPunteggi().getA3() + rank1.getPunteggi().getB3();
                        double punteggirank2 = rank2.getPunteggi().getA3() + rank2.getPunteggi().getB3();

                        if(punteggirank1 > punteggirank2)
                            retValue = -1;
                        else if (punteggirank1 == punteggirank2)
                            retValue = 0;
                        else if(punteggirank1 < punteggirank2)
                            retValue = 1;
                        return retValue;
                    }
                });
            } else if (orderListMode == 4) {
                adapter.sort(new Comparator<RankClass>() {
                    @Override
                    public int compare(RankClass rank1, RankClass rank2) {

                        int retValue = 0;
                        double punteggirank1 = rank1.getPunteggi().getA4() + rank1.getPunteggi().getB4();
                        double punteggirank2 = rank2.getPunteggi().getA4() + rank2.getPunteggi().getB4();

                        if(punteggirank1 > punteggirank2)
                            retValue = -1;
                        else if (punteggirank1 == punteggirank2)
                            retValue = 0;
                        else if(punteggirank1 < punteggirank2)
                            retValue = 1;
                        return retValue;
                    }
                });
            }else if (orderListMode == 5) {
                adapter.sort(new Comparator<RankClass>() {
                    @Override
                    public int compare(RankClass rank1, RankClass rank2) {

                        int retValue = 0;
                        double punteggirank1 = rank1.getPunteggi().getA5() + rank1.getPunteggi().getB5();
                        double punteggirank2 = rank2.getPunteggi().getA5() + rank2.getPunteggi().getB5();

                        if(punteggirank1 > punteggirank2)
                            retValue = -1;
                        else if (punteggirank1 == punteggirank2)
                            retValue = 0;
                        else if(punteggirank1 < punteggirank2)
                            retValue = 1;
                        return retValue;
                    }
                });
            }
        }
    }
}
