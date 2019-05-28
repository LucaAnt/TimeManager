package com.project1.learning.pesky.timemanager.persistence;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.project1.learning.pesky.timemanager.model.Attivita;
import com.project1.learning.pesky.timemanager.model.AttivitaFavoriti;
import com.project1.learning.pesky.timemanager.model.Giornata;
import com.project1.learning.pesky.timemanager.model.Utility;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBHelperUser {
    private DBHelper dbHelper;
    public DBHelperUser(Context ctx)
    {
        dbHelper = new DBHelper(ctx);
    }

    public void addActtivitaPreferita(String nome,boolean preferito)
    {
        Cursor cursor = null;
        int pref =(preferito?1:0);
        String query = "INSERT INTO attivitaPreferiti (nome, preferito) VALUES ( ?, ?)";
        cursor = dbHelper.getWritableDatabase().rawQuery(query, new String[]{ nome, pref+""});

        Log.d("addActtivitaPreferita", cursor.getCount()+"");

    }

    public ArrayList<AttivitaFavoriti> loadAttivittaPreferite()
    {
        ArrayList<AttivitaFavoriti> listaFavoriti= new ArrayList<>();
        Cursor cursor = null;
        String sqlQuery = "SELECT * FROM attivitaPreferiti";
        cursor =  this.dbHelper.getReadableDatabase().rawQuery(sqlQuery, new String[]{});

        cursor.moveToFirst();
       // Log.d("loadAttivittaPreferite", cursor.getCount()+"");
        while(cursor.moveToNext())
            listaFavoriti.add(new AttivitaFavoriti(cursor.getString(1),(cursor.getInt(2)==0?false:true)));

        return listaFavoriti;
    }

    public void deleteAttivita()
    {
        String sqlQuery = "DROP TABLE attivitaPreferiti";
        this.dbHelper.getWritableDatabase().execSQL(sqlQuery);
    }

    public boolean storeDay(Giornata giornata)
    {
        Cursor cursor = null;

        //Scrive il giorno corrente sul DB
        //( _idGiorno INTEGER PRIMARY KEY AUTOINCREMENT, dataGiorno TEXT)
        String query = "INSERT INTO giorni (dataGiorno) VALUES (?)";
        cursor = dbHelper.getWritableDatabase().rawQuery(query, new String[]{giornata.getDataDiOggi().toString()});

        //Scrive le Attivita sul DB
        //attivita ( _idAttivita INTEGER PRIMARY KEY AUTOINCREMENT,_idGiorno INTEGER , _idNomeAttivita INTEGER, Descrizione TEXT, FOREIGN KEY(_idNomeAttivita) REFERENCES attivitaPreferiti(_idNomeAttivita), FOREIGN KEY(_idGiorno) REFERENCES giorni(_idGiorno))";
        for (Attivita a :giornata.getAttivita())
        {
            query = "INSERT INTO attivita (_idGiorno,_idNomeAttivita,Descrizione) VALUES (?,?,?)";
            int idGiorno=this.retriveDayKey(giornata.getDataDiOggi());
            int idNomeAttivita=this.retrivenomeAttivitaId(a.getNome());
            cursor = dbHelper.getWritableDatabase().rawQuery(query, new String[]{idGiorno+"",idNomeAttivita+"",a.getDescrizione()});


        }

        return true;
    }

    public Giornata retriveDay(Date dateToRecover)
    {
        String DateString = dateToRecover.toString();
        String toSearchStartString= DateString.substring(0,10);
        String toSearchEndString = DateString.substring(DateString.length()-4,DateString.length());
        String likeString = "%"+toSearchStartString+"%"+toSearchEndString+"%";
        String sqlQuery;
        Cursor cursor = null;


        sqlQuery = "SELECT dataGiorno FROM giorni WHERE dataGiorno LIKE (?)";
        cursor =  this.dbHelper.getReadableDatabase().rawQuery(sqlQuery, new String[]{likeString});
        cursor.moveToFirst();
        Giornata dayToReturn = new Giornata(Utility.StringtoDate(cursor.getString(0)));
        //Log.d("QUERYSTRING ",sqlQuery);
        //Log.d("CERCATO", likeString);
        //Log.d("LETTO", cursor.getString(0));
        return dayToReturn;
    }

    public int retriveDayKey(Date dateToRecover)
    {
        String DateString = dateToRecover.toString();
        String toSearchStartString= DateString.substring(0,10);
        String toSearchEndString = DateString.substring(DateString.length()-4,DateString.length());
        String likeString = "%"+toSearchStartString+"%"+toSearchEndString+"%";
        String sqlQuery;
        Cursor cursor = null;


        sqlQuery = "SELECT * FROM giorni WHERE dataGiorno LIKE (?)";
        cursor =  this.dbHelper.getReadableDatabase().rawQuery(sqlQuery, new String[]{likeString});
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public int retrivenomeAttivitaId(String nomeAttivita)
    {
        String sqlQuery = "SELECT * FROM attivitaPreferiti WHERE nome =(?)";
        Cursor cursor =  this.dbHelper.getReadableDatabase().rawQuery(sqlQuery, new String[]{nomeAttivita});
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    //public List<Attivita> retriveAttivita () {}

    /*
    public boolean isAuthenticate (String username, String password)
    {
        String sqlQuery = "SELECT COUNT(*) FROM users WHERE username = ? and password = ?";

        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = this.dbHelper.getReadableDatabase();
            cursor = db.rawQuery(sqlQuery, new String[]{ username, password });
            cursor.moveToFirst();

            if( cursor.getInt(0) == 1 ){
                return true;
            }
        } catch (Exception ex) {
            return false;
        } finally {
            if(cursor != null)
                cursor.close();
        }

        return false;
    }
    */
}
