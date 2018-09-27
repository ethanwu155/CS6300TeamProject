### Design 1 (bevans60)
* + Many methods that allow for more granularity
* - Architecture not entirely flushed out
* - Doesn't seem like there's a logical way for a Student to add/delete quizzes

### Design 2 (crobins8)
* + Simplistic design which makes it easy to extend as needed, and easy to understand
* + Utility classes for Date object
* - Unclear how QuizStatistic objects are stored
* - Ambiguous necessarily how a Student might take quizzes not created by that Student

### Design 3 (ewu41)
* + Very modular, which would allow for more flexibility should anything need to change later
* + Includes the "has a" and "is a" relationships, as well as specific number constraints on those relationships (e.g. 1 to many)
* - A bit complicated, and makes it difficult to read -- a little over-constrained
* - Some items are represented as objects, but they relate more to functionality (e.g. Remove, Add)

### Design 4 (rdymock3)
* + Actually used an enumeration for seniority level
* + QuizEvent object makes it very straightforward to take a Quiz, and also stores statistics for that Quiz
* - "Has a" relationships are not included

### Team Design

### Summary
