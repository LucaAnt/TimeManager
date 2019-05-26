package com.project1.learning.pesky.timemanager.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Attivita {
    private String Nome="Attività di Defalut";
    private String Descrizione="Lore Ipsum Lore Ipsum Lore Ipsum";
    private Tranche currentTranche=null;
    private List<Tranche> tranches=null;
    public enum Status  {RUNNING,PAUSED,COMPLETED,};
    public Status status;
    private boolean runningTranche;
    public Attivita(String nome) {
        this.Nome = nome;
        status = Status.COMPLETED;
        tranches = new ArrayList<>();
        runningTranche = false;
    }

    public String getNome() {
        return Nome;
    }

    public String getDescrizione() {
        return Descrizione;
    }

    public boolean hasRunningTranche() {
        return runningTranche;
    }

    public void setDescrizione(String descrizione) {
        Descrizione = descrizione;
    }



    public Date getTempoTotale()
    {
        long totalTime=0;

        for(Tranche t:tranches)
        {
           totalTime +=  t.getDiffEndStart().getTime() ;
        }

        if (this.hasRunningTranche())
            totalTime+= (new Date()).getTime() - currentTranche.getStart().getTime();
        return new Date(totalTime);
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

    @Override
    public String toString()
    {
        String returnString=this.getNome();
        for (Tranche t: this.tranches)
        {
            returnString+="StartTranche :" + t.getStart().toString() + " EndTranche: " + t.getEnd() +"DiffTranche:"+t.getDiffEndStart();
        }

        return returnString;
    }
}
