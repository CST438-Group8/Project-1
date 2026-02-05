package com.example.project1_triviagame.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.project1_triviagame.database.entities.User

@Dao
interface UserDao{
    // insert new user
    @Insert
    suspend fun insert(user: User)

    // login match
    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    suspend fun login(username: String, password: String): User?

    // sign up if user already exists
    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getUserByUsername(username: String): User?
}