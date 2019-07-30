package com.project1.learning.pesky.timemanager.model;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Utility
{

    public  static Date  StringtoDate(String s)
    {
        try {
            return  new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy", Locale.getDefault()).parse(s);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String getStringData(Date d)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        return calendar.get(Calendar.DAY_OF_MONTH)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR);
    }

    public static String getFormattedString(Date d)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.setTimeZone(TimeZone.getTimeZone("\"Europe//Berlin\""));
        return String.format("%02d:%02d:%02d", calendar.get(Calendar.HOUR_OF_DAY ),calendar.get(Calendar.MINUTE),calendar.get(Calendar.SECOND));

    }
}
