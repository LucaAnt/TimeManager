package com.project1.learning.pesky.timemanager.persistencev2.daos;

import androidx.room.*;
import com.project1.learning.pesky.timemanager.model.*;
import java.util.List;

@Dao
public interface AttivitaFavoriteDao
{
    @Query("SELECT * FROM attivitafavoriti")
    List<AttivitaFavoriti> loadAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<AttivitaFavoriti> attivitaFavorite);

    /*
    @Delete
    void delete(User user);
    */
}
