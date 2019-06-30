package com.project1.learning.pesky.timemanager;

import android.content.Context;
import android.util.Log;

import com.project1.learning.pesky.timemanager.model.Attivita;
import com.project1.learning.pesky.timemanager.model.AttivitaFavoriti;
import com.project1.learning.pesky.timemanager.model.Giornata;
import com.project1.learning.pesky.timemanager.model.Tranche;
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
        /*
        this.mainContext = context;

        TmRoomDb tmdb = TmDatabaseAccessor.getInstance(mainContext);
        //Test scritturalettura attivita preferite room
        ArrayList<AttivitaFavoriti> tmp;
        tmp = new ArrayList<>();
        tmp.add(new AttivitaFavoriti("Riunione con Piva",false));
        tmp.add(new AttivitaFavoriti("Riunione con Pascolutti",false));
        tmp.add(new AttivitaFavoriti("Riunione con Antonio",false));
        tmdb.AttivitaFavoriteDao().insertAll(tmp);
        listaAttivita = tmdb.AttivitaFavoriteDao().loadAll();

        //Test Giornata e sotto classi aggregate SAVE
        Giornata tmpGiornata = new Giornata();

        tmpGiornata.newAttivita("TEST");
        tmpGiornata.newAttivita("TEST2");

        tmdb.GiornataDao().insert(tmpGiornata);

        tmdb.AttivitaDao().insertAll(tmpGiornata.getAttivita());

        for (Attivita a: tmpGiornata.getAttivita() )
        {
            a.addDummyTranches();
            tmdb.TranchesDao().insertAll(a.getTranches());
        }




        //Test Giornata e sotto classi aggregate LOAD

        Giornata recovered = tmdb.GiornataDao().loadGiornata(tmpGiornata.getId());
        Log.d("GIORNATA ID", tmpGiornata.getId());

        List<Attivita> recoveredAtt = tmdb.AttivitaDao().loadAllAttivitaByDay(tmpGiornata.getId());




        Log.d("GIORNATA", recovered.getId());

        Log.d("ATTIVITA", recoveredAtt.toString());

        List<Tranche> recoveredTranches = tmdb.TranchesDao().loadTranchesByActivity(recoveredAtt.get(0).getId());

        Log.d("TRANCHES ATT 1", recoveredTranches.toString());

        recoveredTranches = tmdb.TranchesDao().loadTranchesByActivity(recoveredAtt.get(1).getId());

        Log.d("TRANCHES ATT 2", recoveredTranches.toString());

        /*

         tmdb.GiornataDao().insert(tmpGiornata);

        Date adesso = new Date();
        giornataCorrente = tmdb.GiornataDao().load(adesso.getDay(),adesso.getMonth(),adesso.getYear());
        Log.d("DB", "Giornata inserita e caricata:"+giornataCorrente.getDay());
        */

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