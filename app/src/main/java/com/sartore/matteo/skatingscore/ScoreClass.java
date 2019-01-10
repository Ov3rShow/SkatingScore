package com.sartore.matteo.skatingscore;

/**
 * Created by Matteo on 30/05/2017.
 */

public class ScoreClass {
    private double A1, A2, A3, A4, A5, B1, B2, B3, B4, B5;


    public ScoreClass (double A1, double A2, double A3, double A4, double A5, double B1, double B2, double B3, double B4, double B5)
    {
        this.A1 = A1;
        this.A2 = A2;
        this.A3 = A3;
        this.A4 = A4;
        this.A5 = A5;
        this.B1 = B1;
        this.B2 = B2;
        this.B3 = B3;
        this.B4 = B4;
        this.B5 = B5;
    }

    public ScoreClass()
    {

    }

    public double getA1() {
        return A1;
    }

    public void setA1(double a1) {
        A1 = a1;
    }

    public double getA2() {
        return A2;
    }

    public void setA2(double a2) {
        A2 = a2;
    }

    public double getA3() {
        return A3;
    }

    public void setA3(double a3) {
        A3 = a3;
    }

    public double getA4() {
        return A4;
    }

    public void setA4(double a4) {
        A4 = a4;
    }

    public double getA5() {
        return A5;
    }

    public void setA5(double a5) {
        A5 = a5;
    }

    public double getB1() {
        return B1;
    }

    public void setB1(double b1) {
        B1 = b1;
    }

    public double getB2() {
        return B2;
    }

    public void setB2(double b2) {
        B2 = b2;
    }

    public double getB3() {
        return B3;
    }

    public void setB3(double b3) {
        B3 = b3;
    }

    public double getB4() {
        return B4;
    }

    public void setB4(double b4) {
        B4 = b4;
    }

    public double getB5() {
        return B5;
    }

    public void setB5(double b5) {
        B5 = b5;
    }

    public String ToString ()
    {
        String ret = "";
        if(IndexActivity.current_competition.getType()==3)
        {
            ret = A1+"/"+A2+"/"+A3+"/"+B1+"/"+B2+"/"+B3;
        }
        else if(IndexActivity.current_competition.getType()==5)
        {
            ret = A1+"/"+A2+"/"+A3+"/"+A4+"/"+A5+"/"+B1+"/"+B2+"/"+B3+"/"+B4+"/"+B5;
        }
        return ret;
    }

    public double getTotal()
    {
        return A1+A2+A3+A4+A5+B1+B2+B3+B4+B5;
    }
}
