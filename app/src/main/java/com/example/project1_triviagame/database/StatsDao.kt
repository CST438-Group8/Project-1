package com.example.project1_triviagame.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface StatsDao {

    @Query("SELECT * FROM stats WHERE id = 1 LIMIT 1")
    suspend fun getStats(): StatsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(stats: StatsEntity)

    @Update
    suspend fun update(stats: StatsEntity)

    @Query("UPDATE stats SET wins = wins + 1 WHERE id = 1")
    suspend fun incrementWins()

    @Query("UPDATE stats SET losses = losses + 1 WHERE id = 1")
    suspend fun incrementLosses()
}