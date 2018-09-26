### Assignment #5 Design Information

>When starting the application, a user can choose whether to (1) log in as a specific student or (2) register as a new student.

The application will have login() and register() methods to handle the user inputs to the GUI.  

>To register as a new student, the user must provide the following student information: unique username, major, seniority level (i.e., freshman, sophomore, junior, senior, or grad), email address.

The Student class will store each of these attributes.  The application will have a list of registered students.  the register() method will create a new Student object.  

>The newly added student is immediately created in the system.

The register() method will create a new Student object and add it to the list in the application.

>For simplicity, there is no password creation/authentication; that is, selecting or entering a student username is sufficient to log in as that student.

The Student class does not have a password attribute.

>Also for simplicity, student and quiz information is local to a device.

Quiz and student information are stored as lists within the application.

>The application allows students to (1) add a quiz, (2) remove a quiz they created, (3) practice quizzes, and (4) view the list of quiz score statistics.

Within the application class are methods addQuiz(), removeQuiz(). practiceQuiz(), and viewQuizScores().

>To add a quiz, a student must enter the following quiz information: Unique name, Short description, List of N words, where N is between 1 and 10, together with their definitions, List of N * 3 incorrect definitions, not tied to any particular word, where N is the number of words in the quiz.

The application includes an addQuiz() method which takes a Quiz as an argument.  How the GUI receives this input from the student is not represented in this diagram, but the separate items needed from the student would be used to build the Quiz object which is then passed to the method. An integer is automatically assigned to the new quiz which represents a unique ID.  

>To remove a quiz, students must select it from the list of the quizzes they created. Removing a quiz must also remove the score statistics associated with that quiz.

There is a removeQuiz() method in the application that takes an integer as an argument.  This integer is the ID attribute from the quiz object that is to be removed.  The removeQuiz method would remove the quiz from the quizList attribute as well as the quizHistory map.  This insures that the quiz will not show up in the statistics.  

>To practice a quiz, students must select it from the list of quizzes created by other students.

There is a practiceQuiz() method which takes an integer as an input which represents the ID attribute of the quiz.  One of the results of this method will be the creation of a QuizEvent.  The QuizEvent object handles the quiz related methods.  This object has attributes that store the student taking the quiz, the quiz taken, the timestamp of when it was taken, and a list of QuestionEvents.  The QuestionEvent class stores the question asked as well as the result; whether it was answered correctly or incorrectly.  This list of QuestionEvents is what is used to calculate the current score.  A list of QuizEvents could then in turn aggregate a list of QuestionEvents to get an aggregate score, either by quiz or by student, or overall.  

>Until all words in the quiz have been used in the current practice session: Display a random word in the quiz word list.  Display four definitions, including the correct definition for that word (the other three definitions must be randomly selected from the union of (1) the set of definitions for the other words in the quiz and (2) the set of incorrect definitions for the quiz. 

The Quiz class has a static method generateQuestion which will take a random Word from the list of Words and three random incorrect definitions without replacement from the list of incorrect definitions and definitions from other Words in the Quiz.  The Word class contains a name attribute and a correct definition attribute.  


>Let the student select a definition and display “correct” (resp., “incorrect”) if the definition is correct (resp., incorrect).

The QuizEvent class has a submitQuestionResponse() method which takes an integer as an argument.  This integer represents the index of the answer submitted to the question.  The method determines if the response was correct and stores that result in the QuestionEvent.  This method also will display to the user the result of the question.

>After every word in the quiz has been used, the student will be shown the percentage of words they correctly defined, and this information will be saved in the quiz score statistics for that quiz and student.

The QuizEvent class has a method that is called after every answer is submitted called isQuizComplete().  This method determines whether all the words have been used.  If the quiz is complete it calls getCurrentScore() to get and display the current score.  The QuizEvent is already stored in the application, which is how the application can fetch statistics later.  

>The list of quiz score statistics for a student must list all quizzes, ordered based on when they were last played by the student (most recent first). Clicking on a quiz must display (1) the student’s first score and when it was achieved (date and time), (2) the student’s highest score and when it was achieved (date and time), and (3) the names of the first three students to score 100% on the quiz, ordered alphabetically.

When getting the quiz statistics, the quizHistory Map is used.  The timestamp attribute in the QuizEvent allows sorting by time.  This also allows the application to getch the student's first score on each quiz, as you can get the quiz history by Quiz and then filter the list by student.  This list can also be searched to find the student's high score, as that is stored in the attribute finalScore of the QuizEvent.  The timestamp of that high score is also stored in that QuizEvent.  The names of the first three students to get a perfect score are stored as a Set in the Quiz object, which can easily be sorted alphabetically. 

>The user interface must be intuitive and responsive.
The performance of the game should be such that students do not experience any considerable lag between their actions and the response of the application.

This is not represented in my design, as it will be handled entirely within the GUI implementation.

