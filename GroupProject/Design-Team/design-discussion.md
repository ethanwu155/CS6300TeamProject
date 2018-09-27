### Design 1 (bevans60)
* +Many methods that allow for more granularity
* -Architecture not entirely flushed out
* -Doesn't seem like there's a logical way for a Student to add/delete quizzes

### Design 2 (crobins8)
* +Simplistic design which makes it easy to extend as needed, and easy to understand
* +Utility classes for Date object
* -Unclear how QuizStatistic objects are stored
* -Ambiguous necessarily how a Student might take quizzes not created by that Student

### Design 3 (ewu41)
* +Very modular, which would allow for more flexibility should anything need to change later
* +Includes the "has a" and "is a" relationships, as well as specific number constraints on those relationships (e.g. 1 to many)
* -A bit complicated, and makes it difficult to read -- a little over-constrained
* -Some items are represented as objects, but they relate more to functionality (e.g. Remove, Add)

### Design 4 (rdymock3)
* +Actually used an enumeration for seniority level
* +QuizEvent object makes it very straightforward to take a Quiz, and also stores statistics for that Quiz
* -"Has a" relationships are not included

### Team Design

>The quiz score statistics for a student S should list all quizzes, whether they were played by S or not, and including the quizzes created by S.

Comments:  I changed the name of one of the Application methods to getQuizScoresByStudent and changed it to return a Map instead of a list.  This would be the same structure as the quizHistory, but filtered to only include the Student’s Quiz Events.  Quizzes the Student has not yet played will be contained in the Map, their value being an empty array. 

>The quizzes not played by S can be displayed in any order (after the ones played).

Comments:  We could solve this by making the Map a TreeMap and then sorting the Map by number of QuizEvents.  This way, all the quizzes not played would be at the end.  


>For quizzes not played by S, only the names of the first three students to score 100% on the quiz should be displayed.

Comments:  Since each quiz object holds a Set of Student objects that obtained the three first 100% scores, this is already addressed.  


>The names displayed (and used to sort) in the statistics for the first three students to score 100% on the quiz can be either their usernames or their real names.

Comments:Since each quiz object holds a Set of Student objects that obtained the three first 100% scores, this is already addressed.  


>Every word in a quiz should be shown once and only once.

Comments:  This logic can handled in the getNextQuestion method in the QuizEvent class.  I changed the method generateQuestion in the Quiz class to allow for passing a Word to allow for this.   


>Incorrect definitions, conversely, may repeat.

Comments:  This assumption is already built in and is handled in the dynamic creation of new questions in the generateQuestion method.  


>General clarification: all relevant data (scores, statistics, quizzes, student logins) should persist between uses of the application.

Comments: Since this is an android device, we may want to persist the data in an SQLite database.  This can be accomplished using Room, which is an abstraction layer over an SQLite database.  This can be easily implemented using the classes we have defined. 


### Summary
