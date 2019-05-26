package com.project1.learning.pesky.timemanager.model;


import java.util.Date;
import java.util.Random;

public class Tranche {
    private Date Start,End,Diff;
    public Tranche() {
        Start = new Date();
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
        return new Date(End.getTime()-Start.getTime());
    }

    protected static Tranche getDummyTranche()
    {
        Tranche t = new Tranche();
        Random rand = new Random();
        long randomLong = rand.nextLong()%60000;
        t.End = new Date(((new Date()).getTime()+randomLong)-t.Start.getTime());
        return t;
    }
}