package com.sartore.matteo.skatingscore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matteo on 20/06/2017.
 */

public class RankClass {

    private String name, societa, titolo;
    private List<Double> vittorie;
    private ScoreClass punteggi;
    //double n_vittorie;

    public RankClass (String name)
    {
        this.name = name;
        vittorie = new ArrayList<>();
    }

    public RankClass()
    {
        vittorie = new ArrayList<>();
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void addVictory(double v)
    {
        vittorie.add(v);
    }

    public String getSocieta() {
        return societa;
    }

    public void setSocieta(String societa) {
        this.societa = societa;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public ScoreClass getPunteggi() {
        return punteggi;
    }

    public void setPunteggi(ScoreClass punteggi) {
        this.punteggi = punteggi;
    }

    public double getMajorityDecisions()
    {
        double valuetoReturn=0;
        for (int i = 0; i < vittorie.size(); i++)
        {
            if(vittorie.get(i)>2.5)
            {
                valuetoReturn++;
            }
            else if(valuetoReturn==2.5)
            {
                valuetoReturn+=0.5;
            }
        }
        return valuetoReturn;
    }
    public String victoriesToString()
    {
        String returnString="";
        for(int i = 0; i < vittorie.size(); i++)
        {
            returnString+=vittorie.get(i)+"/";
        }
        return returnString;
    }
}