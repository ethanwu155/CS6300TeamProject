# User Case Model
**Author** : 6300Fall18Team69

## 1. Use Case Diagram

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/master/GroupProject/Docs/pics/SDP%20Team%2069%20Use%20Case%20Diagram.png)

## 2. Use Case Description

User Login
- Requirements : Must allow the user to login into Vocabulary Quiz Application
- Pre-conditions :
  * User must be a Returning User with valid username already created
  * User must have launched the application
  * The app must be able to log user into account upon successful input of valid username
- Post-conditions : User is logged into account successfully
- Scenarios : 
  * User launches Vocabulary Quiz Application
  * One of the following then occurs :
  + Login into Account Successfully for Returning User
    * User inputs correct credentials
    * App validates credentials and logs into account
  + Login into Account Unsuccessfully for Returning User
    * User inputs incorrect credentials
    * App outputs error message
    * User given another opportunity to input correct credentials
  + Login attempt by New User without Registration
    * New User attempts to input credentials
    * Credentials don't exist so App outputs error message

User Registration
- Requirements : Must allow NEW user to register an account for Vocabulary Quiz Application
- Pre-conditions :
  * User must be a New User
  * User must have launched the application
  * The app must create an account that user can log into upon account creation
      
- Post-conditions : User creates an account successfully
- Scenarios :

  * User launches Vocabulary Quiz Application and clicks on "Register"
  * One of the following then occurs :
  + User inputs Registration information successfully
    * User inputs correct information (username, major, seniority, email)
    * App creates an account and returns to home page for User login
  + User inputs Registration information incorrectly
    * User inputs registration information (username, major, seniority, email)
    * App outputs error message indicating where there is error
    * User corrects mistakes
    * App creates an account and returns to home page for User Login

Adding Quiz
- Requirements : Must allow user to add Quiz
- Pre-conditions :
  * User must be logged into account
  * User must have launched the application
- Post-conditions : User will create a Quiz
- Scenarios :
  * User hits the "Add Quiz" button
  * User will input Quiz "Name"
  * User will input Quiz "Description"
  * User will input N words (N is between 1 and 10) together with definitions
  * User will input 3*N incorrect defintions
  * When words and defintions are inputted completely, Application will inform User that Quiz was successfully created. Quiz Name will show up on User's Quiz List
      
Remove Quiz
- Requirements : Must allow user to remove Quiz from the Quiz List
- Pre-conditions :
  * User must be logged into account
  * User must have launched the application
  * Quiz to be removed must be in User's Quiz List
- Post-conditions : User will remove Quiz from Quiz List
- Scenarios :
  * User selects Quiz by chosing name of Quiz from list of created Quizzes
  * User hits the "Remove Quiz" button
  * Application will verify if User actually wants Quiz removed including information about statistics being removed as well.
  * User verifies that they want Quiz Removed. App informs user that Quiz is removed.
  * Selected Quiz will no longer be on Quiz List.
      
Practice Quiz
- Requirements : Must allow user to practice Quizzes from Quiz List
- Pre-conditions :
  * User must be logged into account
  * User must have launched the application
  * Quizzes created by user or other students must exist
  * Quizzes be able to record users statistics
- Post-conditions : User will be able to select a Quiz, practice the Quiz, after every question display percentage of words correctly defined.
- Scenarios :
  * User selects Quiz from List of Quizzes
  * User hits the "Practice Quiz" button
  * Quiz assessment will come up displaying random word with 4 defintions
  * User will select answer and app will inform user with "correct" or "incorrect" depending on selected answer
  * After every word, application will display percentage of words correctly defined
  * After practice User will be taken back to Quiz List
       
View Quiz Statistics
- Requirements : Must allow user to view statistics of a particular Quiz
- Pre-conditions : 
  * User must be logged into account
  * User must have launched the application
  * Application must be able to record all users quiz statistics
- Post-conditions : 
  * User will be able to view statistics for quizzes
    * User's first score with date and time 
    * Highest User score with datea and time
    * Names of first students to achieve 100% on the quiz
- Scenarios :
  * User selectes "View Quiz Statistics"
  * User selects name of Quiz they want to view statistics for
  * User looks at results
  * User returns to "Quiz List" page 
      
