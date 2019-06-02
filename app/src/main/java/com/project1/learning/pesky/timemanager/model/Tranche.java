package com.project1.learning.pesky.timemanager.model;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;
import java.util.Random;

@Entity
public class Tranche {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private Date Start,End,Diff;
    public Tranche() {
        Start = new Date();
    }

    public Tranche(Date start,Date end)
    {
        Start = start;
        this.End = end;
    }
    public void endTranche()
    {
        End = new Date();
    }


    public Date getStart() {
        return Start;
    }

    public Date getEnd() {
        return End;
    }

    public Date getDiffEndStart()
    {
        if(Diff!=null)
            return Diff;
        else if(End==null)
            return null;
        Diff = new Date(End.getTime()-Start.getTime());
        return Diff;
    }

    protected static Tranche getDummyTranche()
    {
        Tranche t = new Tranche();
        Random rand = new Random();
        long randomLong = rand.nextLong()%60000;
        t.End = new Date(((new Date()).getTime()+randomLong)-t.Start.getTime());
        return t;
    }

    @Override
    public String toString() {
        return this.getStart()+"\n"+this.getEnd() + "\n" + this.getDiffEndStart();
    }
}
