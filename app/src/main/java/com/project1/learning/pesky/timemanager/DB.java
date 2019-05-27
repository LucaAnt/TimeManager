package com.project1.learning.pesky.timemanager;

import com.project1.learning.pesky.timemanager.model.AttivitaFavoriti;
import com.project1.learning.pesky.timemanager.model.Giornata;

import java.util.ArrayList;

public class DB
{

    public static ArrayList<AttivitaFavoriti> listaAttivita;
    public static Giornata giornataCorrente;


    public DB()
    {

        //Instanzia Attivita di prova
        listaAttivita = new ArrayList<>();
        listaAttivita.add(new AttivitaFavoriti("Pausa caffe", true));
        listaAttivita.add(new AttivitaFavoriti("Meeting", false));
        listaAttivita.add(new AttivitaFavoriti("Pausa pranzo", false));
        listaAttivita.add(new AttivitaFavoriti("Assistenza", false));
        listaAttivita.add(new AttivitaFavoriti("Sviluppo app", true));
        listaAttivita.add(new AttivitaFavoriti("Sviluppo sito", false));

        //Istanzia la giornata corrente di prova
        giornataCorrente = new Giornata();
    }
}
