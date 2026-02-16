package com.example.project1_triviagame

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.project1_triviagame.database.AppDatabase
import com.example.project1_triviagame.database.User
import com.example.project1_triviagame.database.UserDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented tests for Login and Signup database operations.
 * Tests the UserDao methods that LoginActivity and SignupActivity rely on.
 */
@RunWith(AndroidJUnit4::class)
class LoginSignupDatabaseTest {

    private lateinit var database: AppDatabase
    private lateinit var userDao: UserDao

    @Before
    fun setup() {
        // Create an in-memory database for testing (destroyed after each test)
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        userDao = database.userDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    // ==================== SIGNUP TESTS ====================

    @Test
    fun signup_insertsUserIntoDatabase() = runBlocking {
        val user = User(username = "testuser", password = "password123")
        userDao.insert(user)

        val result = userDao.getUserByUsername("testuser")
        assertNotNull("User should exist after signup", result)
        assertEquals("testuser", result!!.username)
        assertEquals("password123", result.password)
    }

    @Test
    fun signup_duplicateUsernameCanBeDetected() = runBlocking {
        val user1 = User(username = "duplicate", password = "pass1")
        userDao.insert(user1)

        val existing = userDao.getUserByUsername("duplicate")
        assertNotNull("Existing user should be found", existing)
    }

    @Test
    fun signup_newUsernameIsAvailable() = runBlocking {
        val existing = userDao.getUserByUsername("brandnewuser")
        assertNull("Non-existent user should return null", existing)
    }

    @Test
    fun signup_multipleUsersCanBeCreated() = runBlocking {
        userDao.insert(User(username = "user1", password = "pass1"))
        userDao.insert(User(username = "user2", password = "pass2"))
        userDao.insert(User(username = "user3", password = "pass3"))

        assertNotNull(userDao.getUserByUsername("user1"))
        assertNotNull(userDao.getUserByUsername("user2"))
        assertNotNull(userDao.getUserByUsername("user3"))
    }

    @Test
    fun signup_defaultIsAdminIsFalse() = runBlocking {
        userDao.insert(User(username = "regularuser", password = "pass"))

        val user = userDao.getUserByUsername("regularuser")
        assertNotNull(user)
        assertFalse("New user should not be admin by default", user!!.isAdmin)
    }

    // ==================== LOGIN TESTS ====================

    @Test
    fun login_withCorrectCredentials_returnsUser() = runBlocking {
        userDao.insert(User(username = "loginuser", password = "correctpass"))

        val result = userDao.login("loginuser", "correctpass")
        assertNotNull("Login with correct credentials should return user", result)
        assertEquals("loginuser", result!!.username)
    }

    @Test
    fun login_withWrongPassword_returnsNull() = runBlocking {
        userDao.insert(User(username = "loginuser", password = "correctpass"))

        val result = userDao.login("loginuser", "wrongpass")
        assertNull("Login with wrong password should return null", result)
    }

    @Test
    fun login_withNonExistentUsername_returnsNull() = runBlocking {
        val result = userDao.login("nouser", "anypass")
        assertNull("Login with non-existent username should return null", result)
    }

    @Test
    fun login_isCaseSensitiveForUsername() = runBlocking {
        userDao.insert(User(username = "TestUser", password = "pass"))

        val result = userDao.login("testuser", "pass")
        assertNull("Login should be case-sensitive for username", result)
    }

    @Test
    fun login_isCaseSensitiveForPassword() = runBlocking {
        userDao.insert(User(username = "user", password = "MyPass"))

        val result = userDao.login("user", "mypass")
        assertNull("Login should be case-sensitive for password", result)
    }

    @Test
    fun login_withEmptyUsername_returnsNull() = runBlocking {
        userDao.insert(User(username = "user", password = "pass"))

        val result = userDao.login("", "pass")
        assertNull("Login with empty username should return null", result)
    }

    @Test
    fun login_withEmptyPassword_returnsNull() = runBlocking {
        userDao.insert(User(username = "user", password = "pass"))

        val result = userDao.login("user", "")
        assertNull("Login with empty password should return null", result)
    }

    @Test
    fun login_afterSignup_fullFlow() = runBlocking {
        // Simulate full signup -> login flow
        val username = "newplayer"
        val password = "securepass"

        // Step 1: Check username is available (signup check)
        assertNull("Username should be available", userDao.getUserByUsername(username))

        // Step 2: Insert user (signup)
        userDao.insert(User(username = username, password = password))

        // Step 3: Verify user exists (signup confirmation)
        assertNotNull("User should exist after signup", userDao.getUserByUsername(username))

        // Step 4: Login with those credentials
        val loggedIn = userDao.login(username, password)
        assertNotNull("Login should succeed after signup", loggedIn)
        assertEquals(username, loggedIn!!.username)
    }
}
