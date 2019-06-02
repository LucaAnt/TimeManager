package com.project1.learning.pesky.timemanager.persistencev2;

import androidx.room.*;

import com.project1.learning.pesky.timemanager.model.AttivitaFavoriti;
import com.project1.learning.pesky.timemanager.persistencev2.daos.AttivitaFavoriteDao;
import com.project1.learning.pesky.timemanager.persistencev2.typeconverters.TmTypeConverters;

@Database(entities = {AttivitaFavoriti.class},version=1)
@TypeConverters({TmTypeConverters.class})
public abstract class TmRoomDb extends RoomDatabase
{
    public abstract AttivitaFavoriteDao AttivitaFavoriteDao();
}


