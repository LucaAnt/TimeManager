package com.project1.learning.pesky.timemanager.model;

import androidx.annotation.NonNull;
import androidx.room.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity(tableName = "giornate_table")
public class Giornata
{
    @PrimaryKey
    @NonNull
    private String id;

    @Ignore
    private List<Attivita> attivita;

    private Date dataDiOggi;


    public Giornata() {
        this.attivita = new ArrayList<Attivita>();
        dataDiOggi = new Date();
        this.id = getTodayStringId();
    }

    public  Giornata(Date data)
    {
        this.attivita = new ArrayList<Attivita>();
        dataDiOggi = data;
        this.id = getTodayStringId();
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
            if (a.getNome().equals(nomeAttivita))
            {
                attivitaEsistente = true;
            }
        }


        if ((!attivitaEsistente))
        {
                //this.currentAttivita = new Attivita(nomeAttivita);
                attivita.add(new Attivita(nomeAttivita,getId()));
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


    //GETTER & SETTERS & OVERRIDES


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Attivita> getAttivita() {
        return attivita;
    }

    public Date getDataDiOggi() {
        return dataDiOggi;
    }

    public void setAttivita(List<Attivita> attivita) {
        this.attivita = attivita;
    }

    public void setDataDiOggi(Date dataDiOggi) {
        this.dataDiOggi = dataDiOggi;
    }



    private String getTodayStringId()
    {
        return Utility.getStringData(getDataDiOggi());
    }
}
