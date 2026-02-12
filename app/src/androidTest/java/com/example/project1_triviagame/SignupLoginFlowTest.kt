package com.example.project1_triviagame

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.*

import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignupLoginFlowTest {

    @get:Rule
    val rule = ActivityScenarioRule(LoginActivity::class.java)

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun teardown() {
        Intents.release()
    }

    @Test
    fun signupThenLogin_navigatesToLanding() {
        // Go to Signup
        onView(withId(R.id.signUpText)).perform(click())

        // Use a unique username so the test can run multiple times
        val username = "user_" + System.currentTimeMillis()
        val password = "pass123"

        // Fill Signup form
        onView(withId(R.id.usernameInput)).perform(typeText(username), closeSoftKeyboard())
        onView(withId(R.id.passwordInput)).perform(typeText(password), closeSoftKeyboard())
        onView(withId(R.id.confirmPasswordInput)).perform(typeText(password), closeSoftKeyboard())
        onView(withId(R.id.signUpButton)).perform(click())

        // Back on LoginActivity (SignupActivity calls finish())
        onView(withId(R.id.usernameInput)).perform(replaceText(username), closeSoftKeyboard())
        onView(withId(R.id.passwordInput)).perform(replaceText(password), closeSoftKeyboard())
        onView(withId(R.id.loginButton)).perform(click())

        // Verify LandingActivity opened
        Intents.intended(hasComponent(LandingActivity::class.java.name))
    }
}