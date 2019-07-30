package com.project1.learning.pesky.timemanager.model;
import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.TimeZone;

@Entity(foreignKeys = @ForeignKey(entity = Attivita.class,
        parentColumns = "id",
        childColumns = "attivita_id",
        onDelete = ForeignKey.CASCADE),
        tableName = "tranches_table")
public class Tranche {

    @PrimaryKey(autoGenerate = true)
    private int id;

    //Foreign Key
    @ColumnInfo(name = "attivita_id")
    private String attivita_id;

    private Date Start,End;

    @Ignore
    private Date Diff;

    //CONSTRUCTORS
    public Tranche(String parentAttivita) {
        Start = new Date();
        End = new Date();
        this.getDiffEndStart();
        setAttivita_id(parentAttivita);
    }

    public Tranche(Date Start,Date End,String attivita_id)
    {
        this.Start = Start;
        this.End = End;
        this.setDiff(getDiffEndStart());
        setAttivita_id(attivita_id);
    }


    // CUSTOM METHODS
    public void endTranche()
    {
        End = new Date();
    }

    public Date getDiffEndStart()
    {


        //Diff.setTime(End.getTime()-Start.getTime());

        Calendar calendarStart = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        Calendar calendarDiff = Calendar.getInstance();
        calendarStart.setTime(Start);
        calendarEnd.setTime(End);

        calendarDiff.setTimeInMillis(calendarEnd.getTimeInMillis() - calendarStart.getTimeInMillis());
        calendarDiff.setTimeZone(TimeZone.getDefault());
        Diff  = calendarDiff.getTime();

        return Diff;
    }

    protected static Tranche getDummyTranche(String AttivitaRef)
    {
        Tranche t = new Tranche("0000-00-00");
        Random rand = new Random();
        long randomLong = rand.nextLong()%60000;
        t.End = new Date(((new Date()).getTime()+randomLong)-t.Start.getTime());
        t.setAttivita_id(AttivitaRef);
        return t;
    }



    //GETTER & SETTERS & OVERRIDES
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStart(Date start) {
        Start = start;
    }

    public void setEnd(Date end) {
        End = end;
    }

    public Date getDiff() {
        return Diff;
    }

    public void setDiff(Date diff) {
        Diff = diff;
    }

    public Date getStart() {
        return Start;
    }

    public Date getEnd() {
        return End;
    }

    public String getAttivita_id() {
        return attivita_id;
    }

    public void setAttivita_id(String attivita_id) {
        this.attivita_id = attivita_id;
    }

    @Override
    public String toString() {
        return this.getStart()+"\n"+this.getEnd() + "\n" + this.getDiffEndStart() +"ID:" +getId()+ "Attivita ID:" + getAttivita_id();
    }
}
