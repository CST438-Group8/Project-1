# Project 1 - *Hangman Trivia*

## Table of Contents
1. [Overview](#overview)
2. [Introduction](#introduction)
3. [Product Spec](#productspec)
4. [Documentation](#documentation)
5. [Conclusion](#conclusion)


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

## Introduction
* **How was communication managed?**
  * Discord was our main form of communication.
* **How many stories/issues were initially considered?**
  * We had considered 5 user stories and about 15 issues.
* **How many stories/issues were completed?**
  * We completed 20 issues and 6 user stories.
 
 ### Team Members
* **Anthony Jordan Lagura - [JordanLagura](https://github.com/JordanLagura)**
  * [Pull Requests](https://github.com/CST438-Group8/Project-1/pulls?q=is%3Apr+is%3Aclosed+author%3AJordanLagura)
  * [Issues](https://github.com/CST438-Group8/Project-1/issues?q=is%3Aissue%20state%3Aclosed%20assignee%3AJordanLagura)
  * What was your role / which stories did you work on
    * I was responsible for a lot of the backend coding, databases, and working with the API. I implemented the UI for the title, login, and sign up. I worked around a lot with the categories and API appearing on the main screen for users to select their trivia.
  * What was the biggest challenge?
    * The biggest challenge was for me pulling the API information onto the game. 
  * Why was it a challenge?
    * The reason was I had several ideas of how to implement it, but had some trouble excuting it. The process itself wasn't difficult, but it was definetly tedious making sure each individual file worked in tangent or flowed with each other correctly. Sometimes the app could run but some features wouldn't appear from the API, or even worse, the app would crash trying to pull API data.
  * How was the challenge addressed?
    * I eventually figured it out, I even had some help from my group memebers when they implemented certain features making it more clear of what direction I had to head towards when implementing the API.
  * Favorite / most interesting part of this project
    * Working with composable UI was a somewhat newer feature of Android Studio for me, so seeing how it works was interesting to me.
  * If you could do it over, what would you change?
    * Definetly better planning on my part, working with a group or no group, I work by going by whatever is more convient for me to code at the time. However, this way of planning means some featuees get left out or forgotten. As for the project itself, I'd want to add more features for the users to mess around with, like achievements or customizing their hangman.
  * What is the most valuable thing you learned?
    * Working with Kotlin, while it is very similar to Java, there are noticable differences that gave some insight into how it works with softwares like Android Studio.
* **Noe Gutierrez - [noegutierrez1](https://github.com/noegutierrez1)**
  * [Pull Requests](https://github.com/CST438-Group8/Project-1/pulls?q=is%3Apr+is%3Aclosed+author%3Anoegutierrez1)
  * [Issues](https://github.com/CST438-Group8/Project-1/issues?q=is%3Aissue%20state%3Aclosed%20assignee%3Anoegutierrez1)
  * What was your role / which stories did you work on
    * ...
  * What was the biggest challenge?
    * ...
  * Why was it a challenge?
    * ... 
  * How was the challenge addressed?
    * ...
  * Favorite / most interesting part of this project
    * ...
  * If you could do it over, what would you change?
    * ...
  * What is the most valuable thing you learned?
    * ...
* **Raphael Hadi Berjaoui - [Rberjaoui](https://github.com/Rberjaoui)**
  * [Pull Requests](https://github.com/CST438-Group8/Project-1/pulls?q=is%3Apr+is%3Aclosed+author%3ARberjaoui)
  * [Issues](https://github.com/CST438-Group8/Project-1/issues?q=is%3Aissue%20state%3Aclosed%20assignee%3ARberjaoui)
  * What was your role / which stories did you work on
    * ...
  * What was the biggest challenge?
    * ...
  * Why was it a challenge?
    * ... 
  * How was the challenge addressed?
    * ...
  * Favorite / most interesting part of this project
    * ...
  * If you could do it over, what would you change?
    * ...
  * What is the most valuable thing you learned?
    * ...
* **Rishabh Patel - [RishabhPatel04](https://github.com/RishabhPatel04)**
  * [Pull Requests](https://github.com/CST438-Group8/Project-1/pulls?q=is%3Apr+is%3Aclosed+author%3ARishabhPatel04)
  * [Issues](https://github.com/CST438-Group8/Project-1/issues?q=is%3Aissue%20state%3Aclosed%20assignee%3ARishabhPatel04)
   * What was your role / which stories did you work on
    * ...
  * What was the biggest challenge?
    * ...
  * Why was it a challenge?
    * ... 
  * How was the challenge addressed?
    * ...
  * Favorite / most interesting part of this project
    * ...
  * If you could do it over, what would you change?
    * ...
  * What is the most valuable thing you learned?
    * ...

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
 
## Conclusion
* **How successful were we?**
  * Overall, with the short time frame, we as a group got 90% of the features we wanted to implement. With our first time working as a group together and working with kotlin, we consider our project very succesful in our eyes.
* **What was our largest victory**
  * The app runs with ZERO bugs! Of course, that should be any app's bare minimum. However, our project for a day had some major gradle issues when we were pulling from main and nothing was working for a while. After we had gotten it fixed, everything after was smooth sailing till the end of the project.
* **Final assessment?**
  * With the time constraint, our first time working with kotlin, and exploring new featues of Android Studio, our app came out better than we had planned and expected. We're very proud of the app we've created and vey happy to be able to present it.
 
