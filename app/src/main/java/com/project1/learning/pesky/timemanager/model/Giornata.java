package com.project1.learning.pesky.timemanager.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Giornata
{
    private List<Attivita> attivita;
    private Date dataDiOggi;
    private Attivita currentAttivita = null;

    public Giornata() {
        this.attivita = new ArrayList<Attivita>();
        dataDiOggi = new Date();
    }

    public Date getDataDiOggi() {
        return dataDiOggi;
    }

    public Attivita getCurrentAttivita() {
        return currentAttivita;
    }

    public List<Attivita> getAttivita() {
        return attivita;
    }

    public Date getTotalTime()
    {
        long totalTime =0;
        for (Attivita a: this.getAttivita())
        {
            totalTime+=a.getTempoTotale().getTime();
        }
        return new Date(totalTime);
    }

    public void newAttivita(String nomeAttivita)
    {
        boolean attivitaEsistente=false;
        for (Attivita a :this.attivita)
        {
            if (a.getNome() == nomeAttivita)
            {
                attivitaEsistente = true;;
                refreshOldAttivita(nomeAttivita);
            }
        }


        if ((!attivitaEsistente))
        {
                this.currentAttivita = new Attivita(nomeAttivita);
                attivita.add(currentAttivita);
        }

    }

    public void refreshOldAttivita(String nomeAttivita)
    {
        for (Attivita a :this.attivita)
        {
            if (a.getNome() == nomeAttivita)
            {
                this.currentAttivita = a;
            }

        }
    }

}
