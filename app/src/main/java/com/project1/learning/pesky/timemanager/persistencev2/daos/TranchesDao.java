package com.project1.learning.pesky.timemanager.persistencev2.daos;

import androidx.room.*;

import com.project1.learning.pesky.timemanager.model.Tranche;

import java.util.List;

@Dao
public interface TranchesDao
{
    @Query("SELECT *  FROM tranches_table WHERE attivita_id = :attivitaForeignKey")
    List<Tranche> loadTranchesByActivity(String attivitaForeignKey);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Tranche> tranches);

}
