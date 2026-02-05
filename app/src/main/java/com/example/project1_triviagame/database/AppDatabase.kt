package com.example.project1_triviagame.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object{
        private var INSTANCE: AppDatabase? = null

        // singleton database instance
        fun getDatabase(context: Context): AppDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
            }
            return INSTANCE!!
        }
    }
}