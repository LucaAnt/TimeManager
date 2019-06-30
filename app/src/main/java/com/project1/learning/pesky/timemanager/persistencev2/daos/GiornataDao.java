package com.project1.learning.pesky.timemanager.persistencev2.daos;

import androidx.room.*;
import com.project1.learning.pesky.timemanager.model.*;

@Dao
public interface GiornataDao
{


    @Query("SELECT * FROM giornate_table WHERE id=:dayString")
    Giornata loadGiornata(String dayString);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Giornata giornata);

}
