# Class Diagram - Accompanying Information

This document contains supporting explanations for how the design depicted in my class diagram fulfills the design requirements set forth in the project specification.

1) When starting the application, a user can choose whether to (1) log in as a specific student or (2) register as a new student.
    * The QuizPrep main class would contain a GUI element to input any necessary information, and either logging into an existing account or creating a new one would be accomplished via the "loginUser()" or "registerUser()" functions in AccountManager.
    * The "registerUser()" function requires as input the 4 fields specified in the specification.
    * Upon registration, a new "Student" object is immediately created and added to the list maintained by "AccountManager".
    * No password is required.

2) The application allows students to (1) add a quiz, (2) remove a quiz they created, (3)
practice quizzes, and (4) view the list of quiz score statistics.
    * The "QuizManager", which maintains the directory of created quizzes, makes functions available to the main GUI class to create, delete, and take quizzes quizzes (both for practice and for real).  The "ScoreViewer" class makes available functions for pulling past scores, and exists as a safety layer between the main class and the "ScoreKeeper", which actually holds the recorded scores.

3) To add a quiz, a student must enter the following quiz information: Unique name, Short description, List of N words and definitions, List of N*3 incorrect definitions.
    * The "createQuiz()" function requires as input the 4 values listed in the specification: a unique name, a short description, a matched list of N words and definitions, and a list of 3*N false definitions.

4) To remove a quiz, students must select it from the list of the quizzes they created.
Removing a quiz must also remove the score statistics associated with that quiz.
    * When the user wants to delete a quiz, the GUI class would call the "getQuizTitlesByUser()" function which returns only those quizzes which the currently logged in user created.  After the user selected which they want to delete, the selected title is passed back to the QuizManager's "delQuiz()" function, which when called also has the ScoreKeeper erase all recorded scores for that quiz.

5) To practice a quiz, students must select it from the list of quizzes created by other
students.
    * Alternative to "getQuizTitlesByUser()", the "getQuizTitlesNotByUser()" function is used here.

6) When a student is practicing a quiz, the application must do the following: [omitted]
    * A fair bit of the specifics required of the administration of a practice quiz are GUI details and thus far not all covered here in the class diagram, but they did necessitate the inclusion of the two seperate main class functions, "administerPracticeQuiz()" and "administerRealQuiz()".

7) The list of quiz score statistics for a student must list all quizzes, ordered based on when
they were last played by the student (most recent first). Clicking on a quiz must display
(1) the student’s first score and when it was achieved (date and time), (2) the student’s
highest score and when it was achieved (date and time), and (3) the names of the first
three students to score 100% on the quiz, ordered alphabetically.
    * The main class will make use of ScoreViewer for each of these requirements.  Note that to facilitate all these requirements, scores are to be recorded in ScoreKeeper a 2d array with columns: username, quizTitle, date, time, score.

8) The user interface must be intuitive and responsive.
    * This is a purely GUI-based requirement, and thus was not required to be covered in the class diagram.

9) The performance of the game should be such that students do not experience any
considerable lag between their actions and the response of the application.
    * This is an runtime/efficiency requirement, and thus falls outside the scope of the class diagram, as it deals with the specifics of implementation.
