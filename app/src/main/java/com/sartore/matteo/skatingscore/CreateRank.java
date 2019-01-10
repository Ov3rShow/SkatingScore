package com.sartore.matteo.skatingscore;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matteo on 20/06/2017.
 */

public class CreateRank {
    private List<AthleteClass> lista_atleti;
    private double iAB1, iAB2, iAB3, iAB4, iAB5;
    private double xAB1, xAB2, xAB3, xAB4, xAB5;

    public CreateRank(List<AthleteClass> athletesClassList)
    {
        this.lista_atleti = athletesClassList;
    }

    public List<RankClass> getRank()
    {
        List<RankClass> returnList = new ArrayList<>();
        RankClass item;
        double valuetosum=0;
        for(int i = 0; i < lista_atleti.size(); i++)
        {
            item = new RankClass();
            item.setName(lista_atleti.get(i).getName());
            item.setSocieta(lista_atleti.get(i).getSociety());
            item.setTitolo(lista_atleti.get(i).getTitle());
            item.setPunteggi(lista_atleti.get(i).getScore());

            iAB1 = lista_atleti.get(i).getScore().getA1()+lista_atleti.get(i).getScore().getB1();
            iAB2 = lista_atleti.get(i).getScore().getA2()+lista_atleti.get(i).getScore().getB2();
            iAB3 = lista_atleti.get(i).getScore().getA3()+lista_atleti.get(i).getScore().getB3();
            iAB4 = lista_atleti.get(i).getScore().getA4()+lista_atleti.get(i).getScore().getB4();
            iAB5 = lista_atleti.get(i).getScore().getA5()+lista_atleti.get(i).getScore().getB5();

            for(int x = 0; x < lista_atleti.size(); x++)
            {
                if(i!=x)
                {
                    xAB1 = lista_atleti.get(x).getScore().getA1()+lista_atleti.get(x).getScore().getB1();
                    xAB2 = lista_atleti.get(x).getScore().getA2()+lista_atleti.get(x).getScore().getB2();
                    xAB3 = lista_atleti.get(x).getScore().getA3()+lista_atleti.get(x).getScore().getB3();
                    xAB4 = lista_atleti.get(x).getScore().getA4()+lista_atleti.get(x).getScore().getB4();
                    xAB5 = lista_atleti.get(x).getScore().getA5()+lista_atleti.get(x).getScore().getB5();

                    if(IndexActivity.current_competition.getType()==5)
                    {
                        if(iAB1>xAB1)
                        {
                            valuetosum++;
                        }
                        else if(iAB1==xAB1)
                        {
                            valuetosum+=0.5;
                        }

                        if(iAB2>xAB2)
                        {
                            valuetosum++;
                        }
                        else if(iAB2==xAB2)
                        {
                            valuetosum+=0.5;
                        }

                        if(iAB3>xAB3)
                        {
                            valuetosum++;
                        }
                        else if(iAB3==xAB3)
                        {
                            valuetosum+=0.5;
                        }

                        if(iAB4>xAB4)
                        {
                            valuetosum++;
                        }
                        else if(iAB4==xAB4)
                        {
                            valuetosum+=0.5;
                        }

                        if(iAB5>xAB5)
                        {
                            valuetosum++;
                        }
                        else if(iAB5==xAB5)
                        {
                            valuetosum+=0.5;
                        }

                    }
                    else if (IndexActivity.current_competition.getType()==3)
                    {
                        if(iAB1>xAB1)
                        {
                            valuetosum++;
                        }
                        else if(iAB1==xAB1)
                        {
                            valuetosum+=0.5;
                        }

                        if(iAB2>xAB2)
                        {
                            valuetosum++;
                        }
                        else if(iAB2==xAB2)
                        {
                            valuetosum+=0.5;
                        }

                        if(iAB3>xAB3)
                        {
                            valuetosum++;
                        }
                        else if(iAB3==xAB3)
                        {
                            valuetosum+=0.5;
                        }
                    }
                    item.addVictory(valuetosum);
                    valuetosum=0;
                }
            }
            returnList.add(item);
            Log.d("mylog", String.valueOf(i));
        }
        return returnList;
    }
}
