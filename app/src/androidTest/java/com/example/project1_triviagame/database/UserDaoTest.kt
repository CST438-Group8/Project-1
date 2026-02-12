package com.example.project1_triviagame.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var userDao: UserDao

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        )
            .allowMainThreadQueries() // OK for tests only
            .build()

        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertUser_thenLoginReturnsUser() = runBlocking {
        userDao.insert(User(username = "testuser", password = "password123"))

        val loggedIn = userDao.login("testuser", "password123")

        Assert.assertNotNull(loggedIn)
        Assert.assertEquals("testuser", loggedIn?.username)
    }

    @Test
    fun loginWrongPassword_returnsNull() = runBlocking {
        userDao.insert(User(username = "testuser", password = "password123"))

        val loggedIn = userDao.login("testuser", "wrong")

        Assert.assertNull(loggedIn)
    }

    @Test
    fun getUserByUsername_returnsUserIfExists() = runBlocking {
        userDao.insert(User(username = "alice", password = "pw"))

        val existing = userDao.getUserByUsername("alice")

        Assert.assertNotNull(existing)
        Assert.assertEquals("alice", existing?.username)
    }
}