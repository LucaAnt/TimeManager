package com.project1.learning.pesky.timemanager.persistencev2;

import androidx.room.*;
import com.project1.learning.pesky.timemanager.model.Attivita;
import com.project1.learning.pesky.timemanager.model.AttivitaFavoriti;
import com.project1.learning.pesky.timemanager.model.Giornata;
import com.project1.learning.pesky.timemanager.model.Tranche;
import com.project1.learning.pesky.timemanager.persistencev2.daos.*;
import com.project1.learning.pesky.timemanager.persistencev2.typeconverters.TmTypeConverters;

@Database(entities = {AttivitaFavoriti.class, Giornata.class, Attivita.class, Tranche.class},version=1)
@TypeConverters({TmTypeConverters.class})
public abstract class TmRoomDb extends RoomDatabase
{
    public abstract AttivitaFavoriteDao AttivitaFavoriteDao();
    public abstract GiornataDao GiornataDao();
    public abstract AttivitaDao AttivitaDao();
    public abstract TranchesDao TranchesDao();
}


