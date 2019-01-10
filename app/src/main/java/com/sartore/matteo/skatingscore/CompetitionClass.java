package com.sartore.matteo.skatingscore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Matteo on 30/05/2017.
 */

public class CompetitionClass {
    private List<AthleteClass> Athletes;
    private String Title;
    private String Place;
    private String Date = null;
    private int Type;
    private int id = -1;

    public CompetitionClass(int id, String Title, String Place, String Date, int type)
    {
        this.id = id;
        this.Title = Title;
        this.Place = Place;
        this.Date = Date;
        this.Type = type;
        Athletes = new ArrayList<>();
    }

    public CompetitionClass () { }

    public CompetitionClass(String Title, int Type)
    {
        this.Title = Title;
        this.Type = Type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public void AddAthlete (AthleteClass Athlete)
    {
        Athletes.add(Athlete);
    }

    public List<AthleteClass> getAthletes() {
        return Athletes;
    }

    public AthleteClass getAthlete(int index)
    {
        return Athletes.get(index);
    }

    public void setAthletes(List<AthleteClass> athletes) {
        Athletes = athletes;
    }

    public List<AthleteClass> getAthletesWithScoreHigherThanZero ()
    {
        List<AthleteClass> tempList = new ArrayList<>();
        for(AthleteClass item : Athletes)
        {
            if(item.getScore().getTotal() > 0)
            {
                tempList.add(item);
            }
        }
        return tempList;
    }
}
