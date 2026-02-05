package com.example.project1_triviagame

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class MainMenuScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun mainMenu_displaysCoreElements() {
        composeTestRule.onNodeWithText("Play").assertIsDisplayed()
        composeTestRule.onNodeWithText("Unlocks & Levels").assertIsDisplayed()
        composeTestRule.onNodeWithText("Wins: 0    Losses: 0").assertIsDisplayed()
    }
}
