package com.project1.learning.pesky.timemanager.persistencev2;

import android.content.Context;

import androidx.room.Room;

public class TmDatabaseAccessor
{
    private static TmRoomDb TmDatabaseInstance;
    private static final String TM_DB_NAME = "TmRoomDb";
    private TmDatabaseAccessor() {}
    public static TmRoomDb getInstance(Context context) {
        if (TmDatabaseInstance == null)
        {
            // Create or open a new SQLite database, and return it as
            // a Room Database instance.

            TmDatabaseInstance = Room.databaseBuilder(context,TmRoomDb.class,TM_DB_NAME).allowMainThreadQueries().build();
        }
        return TmDatabaseInstance;
    }
}
