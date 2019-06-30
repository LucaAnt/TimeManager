package com.project1.learning.pesky.timemanager.persistencev2;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.project1.learning.pesky.timemanager.model.Attivita;
import com.project1.learning.pesky.timemanager.model.AttivitaFavoriti;
import com.project1.learning.pesky.timemanager.model.Giornata;
import com.project1.learning.pesky.timemanager.model.Tranche;

import java.util.ArrayList;
import java.util.List;

public class TmDatabaseAccessor
{
    private static TmRoomDb TmDatabaseInstance;
    public static  TmDatabaseAccessor tmDatabaseAccessor;
    private static final String TM_DB_NAME = "TmRoomDb";
    private TmDatabaseAccessor() {}
    public static TmDatabaseAccessor getInstance(Context context) {
        if (TmDatabaseInstance == null)
        {
            TmDatabaseInstance = Room.databaseBuilder(context,TmRoomDb.class,TM_DB_NAME).allowMainThreadQueries().build();
        }

        if(tmDatabaseAccessor == null)
        {
            tmDatabaseAccessor  = new TmDatabaseAccessor();
        }

        return tmDatabaseAccessor;
    }

    public void saveAllActivities(List<AttivitaFavoriti> toSaveActivityList)
    {
        TmDatabaseInstance.AttivitaFavoriteDao().insertAll(toSaveActivityList);

    }

    public List<AttivitaFavoriti> loadAllActivities()
    {
        return TmDatabaseInstance.AttivitaFavoriteDao().loadAll();
    }

    public void saveDay(Giornata toSaveDay)
    {
        TmDatabaseInstance.GiornataDao().insert(toSaveDay);

        TmDatabaseInstance.AttivitaDao().insertAll(toSaveDay.getAttivita());

        for (Attivita a: toSaveDay.getAttivita() )
            TmDatabaseInstance.TranchesDao().insertAll(a.getTranches());

    }

    public Giornata loadDay(String toLoadDayString)
    {
        Giornata recoveredDay = TmDatabaseInstance.GiornataDao().loadGiornata(toLoadDayString);
        recoveredDay.setAttivita(TmDatabaseInstance.AttivitaDao().loadAllAttivitaByDay(toLoadDayString));

        for (Attivita attivita: recoveredDay.getAttivita())
        {
            attivita.setTranches(TmDatabaseInstance.TranchesDao().loadTranchesByActivity(attivita.getId()));
        }


        return recoveredDay;
    }
}
