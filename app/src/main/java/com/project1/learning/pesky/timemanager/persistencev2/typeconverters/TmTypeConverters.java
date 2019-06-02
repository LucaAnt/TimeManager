package com.project1.learning.pesky.timemanager.persistencev2.typeconverters;

import androidx.room.TypeConverter;
import java.util.Date;


public class TmTypeConverters
{
    @TypeConverter
    public static Date dateFromTimestamp(Long value)
    {
        return value == null ? null : new Date(value);
    }
    @TypeConverter
    public static Long dateToTimestamp(Date date)
    {
        return date == null ? null : date.getTime();
    }

}
