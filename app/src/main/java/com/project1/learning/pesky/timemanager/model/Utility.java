package com.project1.learning.pesky.timemanager.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility
{

    public  static Date  StringtoDate(String s)
    {
        try {
            return  new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy").parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getFormattedString(Date d)
    {

        return String.format("%02d:%02d:%02d", d.getHours(),d.getMinutes(),d.getSeconds());
    }
}
