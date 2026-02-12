package com.example.project1_triviagame.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StatsDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var statsDao: StatsDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        statsDao = db.statsDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun getStats_initiallyNull() = runBlocking {
        val stats = statsDao.getStats()
        assertNull(stats)
    }

    @Test
    fun upsert_insertsStatsCorrectly() = runBlocking {
        val stats = StatsEntity(wins = 2, losses = 3)
        statsDao.upsert(stats)

        val result = statsDao.getStats()

        assertNotNull(result)
        assertEquals(2, result?.wins)
        assertEquals(3, result?.losses)
    }

    @Test
    fun incrementWins_increasesWinsByOne() = runBlocking {
        statsDao.upsert(StatsEntity(wins = 0, losses = 0))

        statsDao.incrementWins()

        val result = statsDao.getStats()
        assertEquals(1, result?.wins)
        assertEquals(0, result?.losses)
    }

    @Test
    fun incrementLosses_increasesLossesByOne() = runBlocking {
        statsDao.upsert(StatsEntity(wins = 0, losses = 0))

        statsDao.incrementLosses()

        val result = statsDao.getStats()
        assertEquals(0, result?.wins)
        assertEquals(1, result?.losses)
    }

    @Test
    fun update_changesValuesCorrectly() = runBlocking {
        statsDao.upsert(StatsEntity(wins = 1, losses = 1))

        val updated = StatsEntity(wins = 5, losses = 4)
        statsDao.update(updated)

        val result = statsDao.getStats()

        assertEquals(5, result?.wins)
        assertEquals(4, result?.losses)
    }
}
