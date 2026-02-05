package com.example.project1_triviagame.database

import androidx.room.Entity
import androidx.room.PrimaryKey

// users tables
@Entity(tableName = "users")
data class User(

    // auto-generated primary key
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    //username
    val username: String,

    // password
    val password: String,

    // admin
    val isAdmin: Boolean = false
)