package com.project1.learning.pesky.timemanager.model;

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

    public  Giornata(Date data)
    {
        this.attivita = new ArrayList<Attivita>();
        dataDiOggi = data;
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
            totalTime+=a.getTempoTotaleAttivita().getTime();
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
                attivitaEsistente = true;
            }
        }


        if ((!attivitaEsistente))
        {
                this.currentAttivita = new Attivita(nomeAttivita);
                attivita.add(currentAttivita);
        }

    }


    public void refreshTotalTime()
    {
        for (Attivita a :this.attivita)
        {
            a.UpdateTempoTotale();

        }
    }

    public String allAttivitaStatus()
    {
        String retString="";
        for (Attivita a :this.getAttivita())
        {
            retString+= a.getNome() +" STATUS:" + a.status.toString()+"\n";
        }

        return
                 retString;
    }

    public boolean hasRuningAttivita()
    {
        for (Attivita a :this.getAttivita())
        {
            if( a.hasRunningTranche())
                return true;
        }

        return false;
    }

    public Attivita getCurrentRunningActivity()
    {
        for (Attivita a :this.getAttivita())
        {
            if( a.hasRunningTranche())
                return a;
        }
        return null;
    }

    public void completeAllActivity()
    {
        for (Attivita a :this.getAttivita())
        {
            a.status= Attivita.Status.COMPLETED;
        }
    }

}
