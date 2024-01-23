package com.example.loveengine.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.loveengine.classes.data.NovellData
import com.example.loveengine.classes.data.SaveData
import com.example.loveengine.classes.data.SavedEnvironment
import kotlinx.coroutines.flow.Flow

@Dao
interface NovellDataDao {

    @Query("SELECT * FROM Novell_data WHERE id = :id")
    fun getData(id: Int): Flow<NovellData>

    @Query("SELECT * FROM Novell_data WHERE id >= :startId AND id <= :endId")
    fun getSectionFromFirstTable(startId: Int, endId: Int): Flow<List<NovellData>>

    @Query("SELECT * FROM Novell_data")
    fun getAllDataFromTableFirstTable(): Flow<List<NovellData>>

    @Query("SELECT * FROM Save_data WHERE id = 1")
    fun getSave(): Flow<List<SaveData>>

    @Update(entity = SaveData::class)
    suspend fun updateSave(saveData: SaveData)

    @Query("SELECT * FROM Saved_environment WHERE id = 1 LIMIT 1")
    fun getEnvironment(): Flow<SavedEnvironment>

    @Update(entity = SavedEnvironment::class)
    suspend fun updateEnvironment(environment: SavedEnvironment)
}