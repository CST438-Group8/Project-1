# Project 1 [Hangman Trivia]

## Table of Contents
1. [Overview](#overview)
2. [Product Spec](#productspec)
3. [Documentation](#documentation)


## Overview
### Description
*Hangman Trivia* is an app that takes a twist on hangman for users who enjoy a challenging their knowledge on categories of trivia. Testing your knowledge while keeping your hangman alive!

### API used
[Open Trivia DB](https://opentdb.com/api_config.php)

### Triva Categories
* **Music**
* **Film**
* **Geography**
* **Sports**
* **Computers**
* **Video Games**

## Product Spec
* **Title Screen**
  * User is introduced into app
* **Login Screen**
  * User can access *Hangman Trivia* using their username and password
* **Sign Up Screen**
  * User can register for *Hangman Trivia* using their username and password
* **Landing Screen**
  * User can access different categories of trivia + stats of wins/losses from user
* **Difficulty Screen**
  * User can select different difficulties for trivia
* **Game Screen**
  * User plays trivia questions based off of their chosen difficulty and category

## Documentation
### 1/28
* **Inital Commit**
  * Inital commit made and project created in android studio - by Rbherjaoui
* **Inital UI**
  * UI for title, login, and sign up screen - JordLagura
* **Storage Space**
  * Data storage to store users games and progression - RishabhPatel04
* **Trivia Logic**
  * Game Activity UI, progress bar, hangman UI, game logic - noegutierrez1
 
### 2/4
* **Improved UI + Difficulty Screen**
  * Improved UI in Difficulty Screen and overall app UI, implemented difficulty function/UI - Rbherjaoui
* **Main Screen**
  * User friendly UI for main screen - RishabhPatel04
* **User Database + Login/Signup Logic**
  * Users can now add register and login and be added to the database - JordanLagura
* **Improved Landing + Added Logic**
  * Fixed and updated issues with database and theme scrolling logic - noegutierrez1
 
### 2/6
* **XML to Comp. UI**
  * XML files into Composable UI and logic flow from landing page to difficulty page - RishabhPatel04

### 2/9
* **API added + Database issues + User Authentication**
  * API implemented, databas fixed, user authentication added - JordanLagura

### 2/11
* **Stats Tracking + Refactor Database Logic**
  * Tracks wins/losses, improved database stability, fixdd login issues - RishabhPatel04
* **Room-Based authentication + Tests**
  * Improved/changed database setup and added unit tests - noegutierrez1
* **Viewmodel Game Logic**
  * Updated view model for game logic and updated difficulty screen - Rbherjaoui
 
### 2/12
* **Game Runnign**
  * Test run with computer-based trivia questions - Rbherjaoui
* **Added StatsDaoTest + TriviaAPITest**
  * Tests added for Stats and API - JordanLagura
* **Improved Database Logic + UI**
  * Improved navigation and tracking stats of users - RishabhPatel04

### 2/15
* **UI/Database Improvement + Bug Fixes**
  * Fixed naviagation bugs, stats persitence, integrated scrolling categories, and tests - JordanLagura
* **Hangman Drawing Logic**
  * Hangman now increments with number of losses from user - RishabhPatel04
* **Overall Improvement In App**
  * Major fixes/implementation to stability, naviagation, themes, and UI - noegutierrez1

### Features We Didn't Get To Implement
* **Smaller Hangman Within Game**
  * Originally our plan was to create a form of hangman inception, where the user would have a main hangman that can have up to 6 body parts before losing the game. There would be 6 levels of trivia for the 6 body parts of the main hangman, each level would have their own hangman that a user can get up to 6 questions wrong before losing that level of trivia. If they were to get 6 questions wrong, then that would be a body part added to the main hangman.
* **Reward System**
  * We wanted to give incentive to continue playing the game, wanting the user to feel a sense of achievement for each level they succeded in. We had planned to give users the option to customize their main hangman with diferent styles of cosmetics from the different categories/genres of trivia we offer on our app. You win a level of trivia, you get a cosmetic based on that genre, and it would be like that for easy, medium, hard, and the different categories.
