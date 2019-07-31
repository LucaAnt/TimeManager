package com.project1.learning.pesky.timemanager.model;

import androidx.annotation.NonNull;
import androidx.room.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(foreignKeys = @ForeignKey(entity = Giornata.class,
        parentColumns = "id",
        childColumns = "giornata_id",
        onDelete = ForeignKey.CASCADE),
        tableName = "attivita_table")
public class Attivita {
    @PrimaryKey
    @NonNull
    private String id;

    //Foreign Key
    @ColumnInfo(name = "giornata_id")
    private String giornata_id;

    @Ignore
    private List<Tranche> tranches=null;

    private String Nome="Attività di Defalut";

    private String Descrizione="Lore Ipsum Lore Ipsum Lore Ipsum";

    Date tempoTotaleAttivita;

    @Ignore
    private Tranche currentTranche=null;

    public enum Status  {RUNNING,PAUSED,COMPLETED,};
    @Ignore
    public Status status;
    @Ignore
    private boolean runningTranche;

    //Constructor called by Room
    public Attivita(@NonNull String id, String giornata_id, String Nome, String Descrizione, Date tempoTotaleAttivita) {
        this.id = id;
        this.giornata_id = giornata_id;
        this.Nome = Nome;
        this.Descrizione = Descrizione;
        this.tempoTotaleAttivita = tempoTotaleAttivita;
        status = Status.COMPLETED;
        tranches = new ArrayList<>();
        runningTranche = false;
    }

    public Attivita(String nome, String ParentGiornata) {
        this.Nome = nome;
        status = Status.COMPLETED;
        tranches = new ArrayList<>();
        runningTranche = false;
        tempoTotaleAttivita = new Date();
        setId(ParentGiornata+"-"+nome);
        setGiornata_id(ParentGiornata);
    }


    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public Date getTempoTotaleAttivita() {
        this.UpdateTempoTotale();
        return tempoTotaleAttivita;
    }

    public boolean setTranche(Date start,Date end,int trancheId)
    {
        if(tranches.get(trancheId)!=null)
        {
            tranches.set(trancheId,new Tranche(start,end,getId()));
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
        currentTranche = new Tranche(getId());
        runningTranche = true;
    }

    public void pauseAttivita()
    {
        currentTranche.endTranche();
        tranches.add(currentTranche);
        runningTranche = false;
    }


    public void addManualTranche()
    {
        this.startAttivita();
        this.pauseAttivita();
    }

    //GETTER & SETTERS


    public String getDescrizione() {
        return Descrizione;
    }

    public boolean hasRunningTranche() {
        return runningTranche;
    }

    public List<Tranche> getTranches() {
        return this.tranches;
    }

    public String getGiornata_id() {
        return giornata_id;
    }

    public void setGiornata_id(String giornata_id) {
        this.giornata_id = giornata_id;
    }

    public void setDescrizione(String descrizione) {
        Descrizione = descrizione;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTranches(List<Tranche> tranches) {
        this.tranches = tranches;
    }

    public void setTempoTotaleAttivita(Date tempoTotaleAttivita) {
        this.tempoTotaleAttivita = tempoTotaleAttivita;
    }

    public Tranche getCurrentTranche() {
        return currentTranche;
    }

    public void setCurrentTranche(Tranche currentTranche) {
        this.currentTranche = currentTranche;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isRunningTranche() {
        return runningTranche;
    }

    public void setRunningTranche(boolean runningTranche) {
        this.runningTranche = runningTranche;
    }

    @Override
    public String toString()
    {
        String returnString=this.getNome()+"\nSTATUS:"+this.status.toString()+"\n";
        for (Tranche t: this.tranches)
        {
            returnString+="StartTranche :" + t.getStart().toString() + " EndTranche: " + t.getEnd() +"DiffTranche:"+t.getDiffEndStart();
        }
        returnString+= "Apartiene alla giornata :"+ getGiornata_id();

        return returnString;
    }
}
