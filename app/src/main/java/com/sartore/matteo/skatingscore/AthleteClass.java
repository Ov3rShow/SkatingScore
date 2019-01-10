package com.sartore.matteo.skatingscore;

import android.renderscript.Script;

/**
 * Created by Matteo on 30/05/2017.
 */

public class AthleteClass {
    private String Name;
    private String Society;
    private String Title;
    int position;
    ScoreClass Score;

    public AthleteClass(String Name, String Society, String title, int position)
    {
        this.Name = Name;
        this.Society = Society;
        this.position = position;
        this.Title = title;
        Score = new ScoreClass();
    }

    public AthleteClass(String Name)
    {
        this.Name = Name;
        this.Society = "";
        this.Title = "";
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSociety() {
        return Society;
    }

    public void setSociety(String society) {
        Society = society;
    }

    public ScoreClass getScore() {
        return Score;
    }

    public void setScore(ScoreClass score) {
        Score = score;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
