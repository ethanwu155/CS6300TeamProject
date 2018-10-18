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

This is Team 69 Group Project for Georgia Tech CS6300 Software Development Process. For this project we created a Vocabulary Quiz Android Application. This application is designed with the following features outlined in this User Manual: Login, Registration, Add Quiz, Remove Quiz, Practice Quiz and View Quiz Statistics. 

Any feedback on this project is much appreciated. Thank you for your time and enjoy!

## <a name = "startup"></a> Startup

When you start up the application you will be met with the following Login Screen. If you have an account, simply enter username and press the `Login` Button.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/LoginScreen.PNG)

New user's must register for an account. Simply do this by clicking the `Register` Button. This will bring you to the Registration Page.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/RegisterScreen.PNG)

Registration process involves inserting the following information in the text fields: username, major and email. Additionally new users must select their seniority from the spinner object. 

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/RegistrationContent.PNG)

Complete the Registration process by selecting `Register` and application will take user back user home page.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/HomeScreen.PNG)

## <a name = "quiz"></a> Quiz

### <a name = "addquiz"></a> Add Quiz

User's will have be able to do the following: Add Quizzes, Remove Quizzes, Practice Quizzes.

User's create Quizzes by pressing the `+` Button. User's will be taken to the Add Quiz Screen.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/AddQuizScreen.PNG)

Quiz Name and Quiz Description will be entered into the text fields:

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/QuizNameDescription.PNG)

User can add words to their quiz by pressing the `Add Word` Button.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/AddWordScreen.PNG)

Word and Definition will be inputted into Text fields. When done User can press `Add` Button.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/AddWordContent.PNG)

Incorrect Defintions must be added as well. This is done by pressing `Add Bad Defintion`.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/BadDefinition.PNG)

3*N (where N is the number of Words in quiz) must be inputted. Application will inform user if not enough bad definitions are inputted.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/3NBadDefinition.PNG)

Complete Quiz addition process by pressing `Save` Button.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/SaveQuiz.PNG)

User's Home Screen will be updated with added Quiz.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/UpdatedHomeScreen.PNG)

### <a name = "remove"></a> Remove Quiz

User's can remove Quizzes they have created by pressing the Trashcan icon right of the +. Doing so will erase all statistics relating to the quiz.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/DeleteQuiz.PNG)

### <a name = "practice"></a> Practice Quiz

User's can practice Quizzes by simple clicking on the quiz name that shows up on their Home Screen and selecting `Practice Quiz` option.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/PracticeViewStatsOptions.PNG)

User's be practice quiz in multiple choice style (display word and 4 definitions). Answer's can be submitted by pressing the `Confirm` button. 

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/PracticeQuiz.PNG)

After every answered question, application will inform User of current quiz performance.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/PracticeQuizStatus.PNG)

When Quiz is complete, application will show final quiz score.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/PracticeQuizFinalScore.PNG)

## <a name = "statistic"></a> Statistic

User's can view statistics for individual quizzes. They can do this by clicking on the quiz name on their Home Screen and selecting 'Show Statistics` option.

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/UserManual/PracticeViewStatsOptions.PNG)

Application will display Quiz Statistic Summary including: First Score, High Score, and First Three User perfect score.

