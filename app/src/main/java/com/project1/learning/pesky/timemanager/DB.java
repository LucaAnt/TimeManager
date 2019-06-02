package com.project1.learning.pesky.timemanager;

import android.content.Context;
import android.util.Log;
import com.project1.learning.pesky.timemanager.model.AttivitaFavoriti;
import com.project1.learning.pesky.timemanager.model.Giornata;
import com.project1.learning.pesky.timemanager.model.Utility;
import com.project1.learning.pesky.timemanager.persistence.DBHelperUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

public class DB
{

    public static ArrayList<AttivitaFavoriti> listaAttivita;
    public static Giornata giornataCorrente;
    Context mainContext;
    DBHelperUser dbHelperUser;



    public DB(Context context)
    {

        this.mainContext = context;
        giornataCorrente = new Giornata();
        listaAttivita = new ArrayList<>();
        listaAttivita.add(new AttivitaFavoriti("Riunione con Piva",false));
        listaAttivita.add(new AttivitaFavoriti("Riunione con Pascolutti",false));

        //DBBinding
        //dbHelperUser = new DBHelperUser(mainContext);
/*
        dbHelperUser.addActtivitaPreferita("Pausa caffe", true);
        dbHelperUser.addActtivitaPreferita("Pausa pranzo", true);
        dbHelperUser.addActtivitaPreferita("Meeting", false);
        dbHelperUser.addActtivitaPreferita("Assistenza", false);
        dbHelperUser.addActtivitaPreferita("Sviluppo app", false);
        dbHelperUser.addActtivitaPreferita("Sviluppo sito", false);
        dbHelperUser.addActtivitaPreferita("Riunione con Piva", false);
*/
        //listaAttivita = dbHelperUser.loadAttivittaPreferite();

        //Giornata gTmp = new Giornata();
        //dbHelperUser.storeDay(gTmp);

        //giornataCorrente = dbHelperUser.retriveDay(gTmp.getDataDiOggi());

        //Log.d("GIORNO INSERITO", gTmp.getDataDiOggi().toString());
        //Log.d("GIORNO CARICATO", giornataCorrente.getDataDiOggi().toString());
        //dbHelperUser.retriveDay();


    }
}
