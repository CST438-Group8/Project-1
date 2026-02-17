# Project 1 - *Hangman Trivia*

## Table of Contents
1. [Overview](#overview)
2. [Product Spec](#productspec)
3. [Documentation](#documentation)


## Overview
### Description
*Hangman Trivia* is an app that takes a twist on hangman for users who enjoy a challenging their knowledge on categories of trivia. Testing your knowledge on easy, medium, or hard with a different categories of trivia while keeping your hangman alive!

### API Used In *Hangman Trivia*
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
* **Inital Commit - [#18](https://github.com/CST438-Group8/Project-1/pull/18)**
  * Inital commit made and project created in android studio - by Rbherjaoui
* **Inital UI - [#20](https://github.com/CST438-Group8/Project-1/pull/20)**
  * UI for title, login, and sign up screen - JordanLagura
* **Storage Space - [#21](https://github.com/CST438-Group8/Project-1/pull/21)**
  * Data storage to store users games and progression - RishabhPatel04
* **Trivia Logic - [#22](https://github.com/CST438-Group8/Project-1/pull/22)**
  * Game Activity UI, progress bar, hangman UI, game logic - noegutierrez1
 
### 2/4
* **Improved UI + Difficulty Screen - [#23](https://github.com/CST438-Group8/Project-1/pull/23)**
  * Improved UI in Difficulty Screen and overall app UI, implemented difficulty function/UI - Rbherjaoui
* **Main Screen - [#24](https://github.com/CST438-Group8/Project-1/pull/24)**
  * User friendly UI for main screen - RishabhPatel04
* **User Database + Login/Signup Logic - [#25](https://github.com/CST438-Group8/Project-1/pull/25)**
  * Users can now add register and login and be added to the database - JordanLagura
* **Improved Landing + Added Logic - [#26](https://github.com/CST438-Group8/Project-1/pull/26)**
  * Fixed and updated issues with database and theme scrolling logic - noegutierrez1
 
### 2/6
* **XML to Comp. UI - [#28](https://github.com/CST438-Group8/Project-1/pull/28)**
  * XML files into Composable UI and logic flow from landing page to difficulty page - RishabhPatel04

### 2/9
* **API added + Database issues + User Authentication - [#29](https://github.com/CST438-Group8/Project-1/pull/29)**
  * API implemented, databas fixed, user authentication added - JordanLagura

### 2/11
* **Stats Tracking + Refactor Database Logic - [#31](https://github.com/CST438-Group8/Project-1/pull/31)**
  * Tracks wins/losses, improved database stability, fixdd login issues - RishabhPatel04
* **Room-Based authentication + Tests - [#32](https://github.com/CST438-Group8/Project-1/pull/32)**
  * Improved/changed database setup and added unit tests - noegutierrez1
* **Viewmodel Game Logic - [#34](https://github.com/CST438-Group8/Project-1/pull/34)**
  * Updated view model for game logic and updated difficulty screen - Rbherjaoui
 
### 2/12
* **Game Running - [#35](https://github.com/CST438-Group8/Project-1/pull/35)**
  * Test run with computer-based trivia questions - Rbherjaoui
* **Added StatsDaoTest + TriviaAPITest - [#36](https://github.com/CST438-Group8/Project-1/pull/36)**
  * Tests added for Stats and API - JordanLagura
* **Improved Database Logic + UI - [#37](https://github.com/CST438-Group8/Project-1/pull/37)**
  * Improved navigation and tracking stats of users - RishabhPatel04

### 2/15
* **UI/Database Improvement + Bug Fixes - [#38](https://github.com/CST438-Group8/Project-1/pull/38)**
  * Fixed naviagation bugs, stats persitence, integrated scrolling categories, and tests - JordanLagura
* **Hangman Drawing Logic - [#39](https://github.com/CST438-Group8/Project-1/pull/39)**
  * Hangman now increments with number of losses from user - RishabhPatel04
* **Overall Improvement In App - [#40](https://github.com/CST438-Group8/Project-1/pull/40)**
  * Major fixes/implementation to stability, naviagation, themes, and UI - noegutierrez1

### Features We Didn't Get To Implement
* **Smaller Hangman Within Game**
  * Originally our plan was to create a form of hangman inception, where the user would have a main hangman that can have up to 6 body parts before losing the game. There would be 6 levels of trivia for the 6 body parts of the main hangman, each level would have their own hangman that a user can get up to 6 questions wrong before losing that level of trivia. If they were to get 6 questions wrong, then that would be a body part added to the main hangman.
* **Reward System**
  * We wanted to give incentive to continue playing the game, wanting the user to feel a sense of achievement for each level they succeded in. We had planned to give users the option to customize their main hangman with diferent styles of cosmetics from the different categories/genres of trivia we offer on our app. You win a level of trivia, you get a cosmetic based on that genre, and it would be like that for easy, medium, hard, and the different categories.
