package com.project1.learning.pesky.timemanager.persistencev2.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.project1.learning.pesky.timemanager.model.Attivita;

import java.util.List;

@Dao
public interface AttivitaDao
{
    @Query("SELECT * FROM attivita_table  WHERE giornata_id = :giornataForeignKey")
    List<Attivita> loadAllAttivitaByDay(String giornataForeignKey);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Attivita> attivita);
}
