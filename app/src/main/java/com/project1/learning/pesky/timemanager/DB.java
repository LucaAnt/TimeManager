package com.project1.learning.pesky.timemanager;

import android.content.Context;
import com.project1.learning.pesky.timemanager.model.AttivitaFavoriti;
import com.project1.learning.pesky.timemanager.model.Giornata;
import com.project1.learning.pesky.timemanager.persistencev2.TmDatabaseAccessor;
import com.project1.learning.pesky.timemanager.persistencev2.TmRoomDb;

import java.util.ArrayList;
import java.util.List;

public class DB
{

    public static List<AttivitaFavoriti> listaAttivita;

    public static Giornata giornataCorrente;
    Context mainContext;



    public DB(Context context)
    {

        this.mainContext = context;
        giornataCorrente = new Giornata();
        TmRoomDb tmdb = TmDatabaseAccessor.getInstance(mainContext);
        //Test scritturalettura attivita preferite room
        ArrayList<AttivitaFavoriti> tmp;
        tmp = new ArrayList<>();
        tmp.add(new AttivitaFavoriti("Riunione con Piva",false));
        tmp.add(new AttivitaFavoriti("Riunione con Pascolutti",false));
        tmp.add(new AttivitaFavoriti("Riunione con Antonio",false));
        tmdb.AttivitaFavoriteDao().insertAll(tmp);
        listaAttivita = tmdb.AttivitaFavoriteDao().loadAll();




    }
}




//VECCHI TEST PERSISTENZA CON SQLLite
//Test scritturalettura tranches room

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