package com.project1.learning.pesky.timemanager.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{
    public static final String DBNAME="TimeManagerDB";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        String queryCreateTableGiorni = "CREATE TABLE IF NOT EXISTS giorni ( _idGiorno INTEGER PRIMARY KEY AUTOINCREMENT, dataGiorno TEXT)";
        db.execSQL(queryCreateTableGiorni);
        String queryCreateTableAttivita = "CREATE TABLE IF NOT EXISTS attivita ( _idAttivita INTEGER PRIMARY KEY AUTOINCREMENT,_idGiorno INTEGER , _idNomeAttivita INTEGER, Descrizione TEXT, FOREIGN KEY(_idNomeAttivita) REFERENCES attivitaPreferiti(_idNomeAttivita), FOREIGN KEY(_idGiorno) REFERENCES giorni(_idGiorno))";
        db.execSQL(queryCreateTableAttivita);
        String queryCreateTableTranches = "CREATE TABLE IF NOT EXISTS tranches ( _idTranches INTEGER PRIMARY KEY AUTOINCREMENT,_idAttivita INTEGER, StartT TEXT,EndT TEXT,DiffT TEXT, FOREIGN KEY(_idAttivita) REFERENCES attivita(_idAttivita))";
        db.execSQL(queryCreateTableTranches);
        String queryCreateTableListaAttivitaPreferiti = "CREATE TABLE IF NOT EXISTS attivitaPreferiti ( _idNomeAttivita INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, preferito INTEGER)";
        db.execSQL(queryCreateTableListaAttivitaPreferiti);

        /*
        q = "INSERT INTO users (username, password) VALUES ('mirco', 'mirco')";
        db.execSQL(q);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}