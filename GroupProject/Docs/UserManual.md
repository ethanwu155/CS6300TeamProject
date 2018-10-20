# User Manual - CS6300Team69

## Contents

### 1. [Introduction](#introduction)

### 2 [Startup](#startup)

### 3 [Quiz](#quiz)

#### 3.1 [Add Quiz](#addquiz)

#### 3.2 [Remove Quiz](#remove)

#### 3.3 [Practice Quiz](#practice)

### 4. [Statistic](#statistic)

<hr>

## <a name = "introduction"></a> Introduction

This is Team 69's Group Project for Georgia Tech CS6300: Software Development Process. For this project, we created a Vocabulary Quiz Android Application. This application is designed with the following features outlined in this User Manual: Login, Registration, Add Quiz, Remove Quiz, Practice Quiz and View Quiz Statistics. 

Any feedback on this project is much appreciated. Thank you for your time and enjoy!

## <a name = "startup"></a> Startup

When you start up the application you will be met with the following Login Screen. If you have an account, simply enter your username and press the `Login` Button.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/LoginScreen.PNG)

New users must register for an account. Simply do this by clicking the `Register` Button. This will bring you to the Registration Page.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/RegisterScreen.PNG)

The registration process involves inserting the following information in the text fields: username, major and email. Additionally new users must select their seniority level from the dropdown. 

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/RegistrationContent.PNG)

Complete the Registration process by selecting `Register`, and the application will take the user to the user home page.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/HomeScreen.PNG)

## <a name = "quiz"></a> Quiz

### <a name = "addquiz"></a> Add Quiz

Users are able to do the following: Add Quizzes, Remove Quizzes, and Practice Quizzes.

Users create Quizzes by pressing the `+` Button in the top right hand corner of the page. Once a user clicks this, they will be taken to the Add Quiz Screen.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/AddQuizScreen.PNG)

Enter Quiz Name and Quiz Description into the text fields:

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/QuizNameDescription.PNG)

Users can add words to their quiz by pressing the `Add Word` Button.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/AddWordScreen.PNG)

Input the Word and its corresponding Definition into Text fields. When done adding a Word, a User can press the `Add` Button.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/AddWordContent.PNG)

Incorrect Defintions must be added as well. This is done by pressing the `Add Bad Defintion` button.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/BadDefinition.PNG)

3*N (where N is the number of Words in quiz) incorrect definitions must be inputted. The Application will inform the user if not enough bad definitions have been inputted.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/3NBadDefinition.PNG)

Complete Quiz creation process by pressing the `Save` Button.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/SaveQuiz.PNG)

The User's Home Screen will be updated with the newly added Quiz.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/UpdatedHomeScreen.PNG)

### <a name = "remove"></a> Remove Quiz

Users can remove Quizzes they have created by pressing the Trashcan icon to the right of the `+` icon, in the top right hand corner of the screen. Doing so will erase all statistics related to the Quiz as well.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/DeleteQuiz.PNG)

### <a name = "practice"></a> Practice Quiz

Users can practice Quizzes by simply clicking on the quiz name that shows up on their Home Screen and selecting the `Practice Quiz` option.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/PracticeViewStatsOptions.PNG)

Users practice quiz in multiple choice style (display word and 4 definitions). Answers can be submitted by pressing the `Confirm` button. 

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/PracticeQuiz.PNG)

After every answered question, the application will inform the User of their current quiz performance up to that point.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/PracticeQuizStatus.PNG)

When the Quiz is complete, the application will show the final quiz score.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/PracticeQuizFinalScore.PNG)

## <a name = "statistic"></a> Statistic

Users can view statistics for individual quizzes. They can do this by clicking on the quiz name on their Home Screen and selecting 'Show Statistics` option.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/PracticeViewStatsOptions.PNG)

The Application will display Quiz Statistic Summary including: User's First Score with timestamp, User's High Score with timestamp, and the First three Users to score a 100% on the Quiz.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/QuizScoreView.PNG)
