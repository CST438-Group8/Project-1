package com.example.project1_triviagame.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stats")
data class StatsEntity(
    @PrimaryKey
    val id: Int = 1,
    val wins: Int = 0,
    val losses: Int = 0
)