package com.project1.learning.pesky.timemanager.model;

import android.util.Log;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Attivita {
    @PrimaryKey(autoGenerate = true)
    public long id;
    private String Nome="Attività di Defalut";
    private String Descrizione="Lore Ipsum Lore Ipsum Lore Ipsum";
    private List<Tranche> tranches=null;
    Date tempoTotaleAttivita;

    @Ignore
    private Tranche currentTranche=null;
    public enum Status  {RUNNING,PAUSED,COMPLETED,};
    @Ignore
    public Status status;
    @Ignore
    private boolean runningTranche;


    public Attivita(String nome) {
        this.Nome = nome;
        status = Status.COMPLETED;
        tranches = new ArrayList<>();
        runningTranche = false;
        tempoTotaleAttivita = new Date(0);
    }

    public String getNome() {
        return Nome;
    }

    public Date getTempoTotaleAttivita() {
        this.UpdateTempoTotale();
        return tempoTotaleAttivita;
    }

    public String getDescrizione() {
        return Descrizione;
    }

    public boolean hasRunningTranche() {
        return runningTranche;
    }

    public List<Tranche> getTranches() {
        return this.tranches;
    }


    public void setDescrizione(String descrizione) {
        Descrizione = descrizione;
    }

    public boolean setTranche(Date start,Date end,int trancheId)
    {
        if(tranches.get(trancheId)!=null)
        {
            tranches.set(trancheId,new Tranche(start,end));
            return  true;
        }
        return false;
    }

    public void UpdateTempoTotale()
    {
        long totalTime=0;

        for(Tranche t:tranches)
        {
           totalTime +=  t.getDiffEndStart().getTime() ;
        }

        if (this.hasRunningTranche())
            totalTime+= (new Date()).getTime() - currentTranche.getStart().getTime();

        tempoTotaleAttivita = new Date(totalTime);
    }

    public void startAttivita()
    {
        currentTranche = new Tranche();
        runningTranche = true;
    }

    public void pauseAttivita()
    {
        currentTranche.endTranche();
        tranches.add(currentTranche);
        runningTranche = false;
    }

    public void addDummyTranches()
    {
        tranches.add(Tranche.getDummyTranche());
        tranches.add(Tranche.getDummyTranche());
        tranches.add(Tranche.getDummyTranche());
        tranches.add(Tranche.getDummyTranche());
        this.status = Status.COMPLETED;
    }

    public void addManualTranche()
    {
        this.startAttivita();
        this.pauseAttivita();
    }


    @Override
    public String toString()
    {
        String returnString=this.getNome()+"\nSTATUS:"+this.status.toString()+"\n";
        for (Tranche t: this.tranches)
        {
            returnString+="StartTranche :" + t.getStart().toString() + " EndTranche: " + t.getEnd() +"DiffTranche:"+t.getDiffEndStart();
        }

        return returnString;
    }
}
