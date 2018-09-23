## Design Information

1. When starting the application, a user can choose whether to (1) log in as a specific student or (2) register as a new student.

    This requirement is satisfied by the User Class, which contains a login() method and registerNewStudent() method, both of which return a Student, which is a subclass of User. 
    
  * a. To register as a new student, the user must provide the following student information:
    * i. A unique username
    * ii. A major
    * iii. A seniority level (i.e., freshman, sophomore, junior, senior, or grad)
    * iv. An email address
    
    These requirements are satisfied by the Student Class, and the 4 attributes, username, major, seniorityLevel, and email, that are contained within the Student class. 
    
  * b. The newly added student is immediately created in the system.
  
    This is not represented in my design, as it relates to storage, and is implied. 
    
  * c. For simplicity, there is no password creation/authentication; that is, selecting or entering a student username is sufficient to log in as that student.
  
  This is satisfied by the listStudents() method, which returns a list of Students to choose from, as well as a login() method, which only takes as input a username to login as. 
  
  * d. Also for simplicity, student and quiz information is local to a device.
  
  This is not represented in my design, as it relates to storage. 
  
2. The application allows students to (1) add a quiz, (2) remove a quiz they created, (3) practice quizzes, and (4) view the list of quiz score statistics.

These requirements are satisfied by the addQuiz(), removeQuiz(), getCreatedQuizzes(), practiceQuiz(), and getQuizScoreStats() functions that the Student has access to. These methods will respectively create a Quiz object given the Quiz attributes (see below), remove a Quiz given its name, practice a Quiz given its name, and get the QuizStatistic objects (see below) given a Quiz name.

3. To add a quiz, a student must enter the following quiz information:
  * a. Unique name
  * b. Short description
  * c. List of N words, where N is between 1 and 10,  together with their definitions 
  * d. List of N * 3 incorrect definitions, not tied to any particular word, where N is the number of words in the quiz.
  
  These requirements are satisfied by the Quiz object which contains a name and description. It also contains 1-10 Word objects, each of which contains a Definition object, and N*3 Definition objects that are not tied to any particular word. There are N Definition objects that relate directly to Words for a Quiz. 
  
4. To remove a quiz, students must select it from the list of the quizzes they created. Removing a quiz must also remove the score statistics associated with that quiz.

This requirement is satisfied by the getCreatedQuizzes() method that shows the quizzes they created, and the removeQuiz() method that the Student has, and the underlying business logic for the function will also remove any score stats related to that quiz. 

5. To practice a quiz, students must select it from the list of quizzes created by other students.

This requirement is satisfied by the getNonCreatedQuizzes() method, and the practiceQuiz() method. The student will select the quiz to take from the first method, which will display a list of Quizzes created by other Students, and then initiate a "practice session" with the other. Once the practice session has been completed, a QuizStatistic object will be created, which is represented with the dotted line branching off of the practiceQuiz() method, because there is a many-many relationship between Students and QuizStatistics, and the QuizStatistics are also related to Quizzes.

6. When a student is practicing a quiz, the application must do the following:
  * a. Until all words in the quiz have been used in the current practice session: 
  
    The practiceQuiz() method will iterate through the entire list of Word objects, selecting a different Word at random every time (below). 
  
    * i. Display a random word in the quiz word list.
  
    This requirement is satisfied by the displayRandomWord() method, which will display a random Word associated with the Quiz.
    
    * ii. Display four definitions, including the correct definition for that word (the other three definitions must be randomly selected from the union of (1) the set of definitions for the other words in the quiz and (2) the set of incorrect definitions for the quiz. 
    
    This requirement is satisfied by the displayFourDefinitions() method, which will choose the Definition associated with the Word, as well as 3 other random Definitions from the list of incorrect Definitions not associated with any Word object, but associated with the Quiz. 
    
    * iii. Let the student select a definition and display “correct” (resp., “incorrect”) if the definition is correct (resp., incorrect).
    
    This requirement is satisfied by the checkAnswer() method, which takes in a Word and a Definition and checks whether the Definition is correct for the given Word. 
    
  * b. After every word in the quiz has been used, the student will be shown the percentage of words they correctly defined, and this information will be saved in the quiz score statistics for that quiz and student.
  
  This requirement is satisfied via the practiceQuiz() method, which, upon a Student completing a quiz practice session, will display the newly saved QuizStatistic associated with that practice session. The QuizStatistic class contains a score field as well as a date that the Quiz was taken. It is related to the Student as each Student has multiple QuizStatistics associated with it, and it is also related to the Quiz because the QuizStatistic relates to a score on a specific Quiz.
  
7. The list of quiz score statistics for a student must list all quizzes, ordered based on when they were last played by the student (most recent first). Clicking on a quiz must display (1) the student’s first score and when it was achieved (date and time), (2) the student’s highest score and when it was achieved (date and time), and (3) the names of the first three students to score 100% on the quiz, ordered alphabetically.

These requirements are satisfied by the QuizStatistic objects that are saved after every practice session. This is represented in the UML via the dotted line to the QuizStatistic object branching off of the practiceQuiz() method line between the Student and the Quiz. This is also satisfied by the getFirstScore(), which iterates through all of the QuizStatistics for a Student and a given Quiz and finds the first one by the date, getHighScore() which iterates through all QuizStatistics for a Student and given Quiz and finds the one with the highest score, and getStudentsWithHundred() methods, which calls the getHighScore() method for each Student and sees which Students scored a 100.

8. The user interface must be intuitive and responsive.

This is not represented in my design, as it will be handled entirely within the GUI implementation.

9. The performance of the game should be such that students do not experience any considerable lag between their actions and the response of the application.

This is not represented in my design, as it will be handled entirely within the GUI implementation.
