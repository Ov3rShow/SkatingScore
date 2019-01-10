package com.sartore.matteo.skatingscore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Debug;
import android.util.Log;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/*
 * Created by Matteo on 28/05/2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static final String DBNAME="SkatingScoreDb";
    private String query;
    private Context context;
    private SQLiteDatabase db;

    public DbHelper(Context context)
    {
        super(context, DBNAME, null, 1);
        db = this.getWritableDatabase();
        this.context = context;
        //onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try
        {
            query="CREATE TABLE IF NOT EXISTS Atleti (idA INTEGER PRIMARY KEY AUTOINCREMENT, Nome TEXT, Societa TEXT, Titolo TEXT)";
            db.execSQL(query);

            query="CREATE TABLE IF NOT EXISTS Competizioni (idC INTEGER PRIMARY KEY AUTOINCREMENT, Titolo TEXT, Data TEXT, Luogo TEXT, Tipo INTEGER)";
            db.execSQL(query);

            query="CREATE TABLE IF NOT EXISTS Partecipa (" +
                    "idC INTEGER NOT NULL," +
                    "idA INTEGER NOT NULL," +
                    "Ordine INTEGER," +
                    "PRIMARY KEY (idC, idA))";
            db.execSQL(query);

            query="CREATE TABLE IF NOT EXISTS Punteggi (" +
                    "idP INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idA INTEGER," +
                    "idC INTEGER," +
                    "A1 DOUBLE," +
                    "A2 DOUBLE," +
                    "A3 DOUBLE," +
                    "A4 DOUBLE," +
                    "A5 DOUBLE," +
                    "B1 DOUBLE," +
                    "B2 DOUBLE," +
                    "B3 DOUBLE," +
                    "B4 DOUBLE," +
                    "B5 DOUBLE)";
            db.execSQL(query);
        }
        catch (Exception ex)
        {
            //Toast.makeText(context, "Errore caricamento db"+ex.getMessage(), Toast.LENGTH_SHORT).show();
            //Log.d("Cazzo", ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {   }

    boolean saveScore (String idC, String idA, double A1, double A2, double A3, double A4, double A5, double B1, double B2, double B3, double B4, double B5)
    {
        ContentValues cv;
        SQLiteDatabase db = this.getWritableDatabase();
        int countrows;
        boolean result;
        try
        {
            query = "SELECT * FROM punteggi WHERE idC='"+idC+"' AND idA='"+idA+"'";
            Cursor cursor = db.rawQuery(query, null);
            countrows = cursor.getCount();
            cursor.close();
            if(countrows==0)
            {

                cv = new ContentValues();
                cv.put("A1", A1);
                cv.put("A2", A2);
                cv.put("A3", A3);
                cv.put("A4", A4);
                cv.put("A5", A5);
                cv.put("B1", B1);
                cv.put("B2", B2);
                cv.put("B3", B3);
                cv.put("B4", B4);
                cv.put("B5", B5);
                cv.put("idA", idA);
                cv.put("idC", idC);
                db.insert("Punteggi", null, cv);
            }
            else
            {
                cv = new ContentValues();
                cv.put("A1", A1);
                cv.put("A2", A2);
                cv.put("A3", A3);
                cv.put("A4", A4);
                cv.put("A5", A5);
                cv.put("B1", B1);
                cv.put("B2", B2);
                cv.put("B3", B3);
                cv.put("B4", B4);
                cv.put("B5", B5);
                cv.put("idA", idA);
                cv.put("idC", idC);
                db.update("Punteggi", cv, "idA="+idA+" AND idC="+idC,null);
            }
            result = true;
        }
        catch (Exception ex)
        {
            result = false;
        }
        return result;
    }

    String checkAthlete (String name)
    {
        String result = "";
        SQLiteDatabase db = this.getWritableDatabase();
        query = "SELECT idA FROM Atleti WHERE Nome = ?";
        Cursor cursor = db.rawQuery(query, new String[] {name.trim().toLowerCase()});
        try
        {
            cursor.moveToFirst();
            result = String.valueOf(cursor.getInt(0));
        }
        catch (Exception ex)
        {
            result = "";
        }

        cursor.close();
        return result;
    }

    boolean saveAthlete (String name, String society, String title, String order, String idC, String idA)
    {
        name = name.trim().toLowerCase();

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values;
        boolean result;
        try
        {
            if(idA.equals(""))
            {
                values = new ContentValues();
                values.put("Nome", name);
                values.put("Societa", society);
                values.put("Titolo", title);
                db.insert("Atleti", null, values);
                query = "SELECT idA FROM Atleti WHERE Nome = ?";
                Cursor cursor = db.rawQuery(query, new String[] {name});
                cursor.moveToFirst();
                int tempidA = cursor.getInt(0);
                cursor.close();


                values = new ContentValues();
                values.put("idC", idC);
                values.put("idA", tempidA);
                values.put("Ordine", order);
                db.insert("Partecipa", null, values);
            }
            else
            {
                values = new ContentValues();
                values.put("Nome", name);
                values.put("Societa", society);
                values.put("Titolo", title);
                db.update("Atleti", values, "idA="+idA, null);

                query = "SELECT * FROM Partecipa WHERE idA='"+idA+"' AND idC='"+idC+"'";
                Cursor cursor3 = db.rawQuery(query, null);
                if(cursor3.getCount()>0)
                {
                    values = new ContentValues();
                    values.put("idC", idC);
                    values.put("idA", idA);
                    values.put("Ordine", order);
                    db.update("Partecipa", values, "idA="+idA+" AND idC="+idC, null);
                }
                else
                {
                    values = new ContentValues();
                    values.put("idC", idC);
                    values.put("idA", idA);
                    values.put("Ordine", order);
                    db.insert("Partecipa", null, values);
                }
                cursor3.close();
            }
            result = true;
        }
        catch (Exception ex)
        {
            result = false;
        }
        return result;
    }

    boolean saveCompetition (String name, String place, String date, int type)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean result;
        try
        {
            ContentValues values = new ContentValues();
            values.put("Titolo" , name);
            values.put("Luogo" , place);
            values.put("Tipo" , type);
            values.put("Data" , date);
            db.insert("Competizioni", null, values);
            result = true;
        }
        catch(Exception ex)
        {
            result = false;
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    List<CompetitionClass> getCompetitionsList()
    {
        List<CompetitionClass> returnlist = new ArrayList<>();
        CompetitionClass competition;
        SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            query = "SELECT * FROM Competizioni";
            Cursor res = db.rawQuery(query, null);
            while(res.moveToNext())
            {
                int id = res.getInt(0);
                String title = res.getString(1);
                String date = res.getString(2);
                String place = res.getString(3);
                int type = res.getInt(4);
                competition = new CompetitionClass(id, title, place, date, type);
                returnlist.add(competition);
            }
            res.close();
        }
        catch (Exception ex)
        {
            Toast.makeText(context, "Errore recupero competizioni " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return returnlist;
    }

    List<AthleteClass> getAthletes(int idC)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        List<AthleteClass> lista = new ArrayList<>();
        AthleteClass athlete;
        try
        {
            query = "SELECT Nome, Societa, Ordine, Titolo, idC, Atleti.idA FROM Atleti INNER JOIN Partecipa ON Atleti.idA = Partecipa.idA WHERE Partecipa.idC='"+idC+"'";
            Cursor cursor = db.rawQuery(query, null);
            while(cursor.moveToNext())
            {
                String name = cursor.getString(0);
                String societa = cursor.getString(1);
                int order = cursor.getInt(2);
                String titolo = cursor.getString(3);
                athlete = new AthleteClass(name, societa, titolo, order);
                try
                {
                    query = "SELECT A1, A2, A3, A4, A5, B1, B2, B3, B4, B5 FROM Punteggi WHERE idC='"+cursor.getInt(4)+"' AND idA='"+cursor.getInt(5)+"'";
                    Cursor cursor2 = db.rawQuery(query, null);
                    cursor2.moveToFirst();
                    ScoreClass temp = new ScoreClass(Double.parseDouble(cursor2.getString(0)), Double.parseDouble(cursor2.getString(1)), Double.parseDouble(cursor2.getString(2)),
                            Double.parseDouble(cursor2.getString(3)), Double.parseDouble(cursor2.getString(4)), Double.parseDouble(cursor2.getString(5)),
                            Double.parseDouble(cursor2.getString(6)), Double.parseDouble(cursor2.getString(7)), Double.parseDouble(cursor2.getString(8)), Double.parseDouble(cursor2.getString(9)));
                    athlete.setScore(temp);
                    cursor2.close();
                }
                catch (Exception ex)
                {
                    Log.d("mylog", ex.getMessage());
                }
                lista.add(athlete);
            }
            cursor.close();
        }
        catch (Exception ex)
        {
            Toast.makeText(context, "Errore recupero atleti " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return lista;
    }

    boolean removeAthlete(int idA, int idC)
    {
        boolean result;
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            query = "DELETE FROM Partecipa WHERE idA='"+String.valueOf(idA)+"' AND idC='"+String.valueOf(idC)+"'";
            db.execSQL(query);
            result = true;
        }
        catch (Exception ex)
        {
            result = false;
        }
        return result;
    }

    boolean removeCompetition(int idC)
    {
        boolean result;
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            /*query = "DELETE FROM Competizioni WHERE idC='"+String.valueOf(idC)+"'";
            db.execSQL(query);
            query = "DELETE FROM Partecipa WHERE idC='"+String.valueOf(idC)+"'";
            db.execSQL(query);*/
            db.delete("Competizioni", "idC="+String.valueOf(idC), null);
            db.delete("Partecipa", "idC="+String.valueOf(idC), null);
            result = true;
        }
        catch (Exception ex)
        {
            result = false;
        }
        return result;
    }
}
