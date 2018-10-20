# Test Plan

**Author**: 6300Fall18Team69

## 1 Testing Strategy

### 1.1 Overall strategy

We will try to automate as many tests as possible, because it is almost always easier to automate tests than to run them manually. However, there will be some tests that we will not be able to automate, because there is a UI portion. 

##### Unit Tests
There will be unit tests written for each class individually, and ideally each method within those classes as well so that all of the functionality throughout the application will be tested individually. As each member of the team writes some code, they should write a unit test for that code to ensure that it works as expected; each member of the team will be responsible for writing unit tests for the code that they write. These tests will also be useful because after fixing or updating other pieces of code, these unit tests should still pass, assuming that the core functionality will not have changed. These tests will not be overly complicated, as they should only be testing a few things at a time. These tests should be run automatically on a schedule, such as nightly.

##### Integration Tests
As it stands, we have 7 classes that all relate to each other in different ways. The integration tests should focus on testing out different parts of the application that directly relate to one another, but not more than 2-3 components at one time. For example, it should test out interaction and functionality between the Application and the Student, the Application and the QuizEvent, the Application and the Quiz, the QuizEvent and the QuestionEvent, etc., essentially testing each 2-3 components that directly relate to each other. These tests will be written by one or more members of the team, and will be more complex than the unit tests. They should be run automatically on a schedule, such as nightly.

##### System Tests
These tests are harder to automate as they need to check the full end-to-end functionality of the system, and they will most likely be the most complex of any of the test suites. These system tests should check functionality from a user logging or registering as a new student in via the application, to creating a quiz, and finally practicing a quiz and viewing statistics. These tests will also be written by one or more of the team members. They could be written at any time in the development process, although probably towards the end just in case there are any changes. These tests will take longer to run than the unit or integration tests, but should still be run nightly and automatically. Some of these tests will also probably have to be run manually (or potentially with Selenium or another testing library) because the UI component needs to be tested as well. One of the developers on our testing team will assist in this effort of running the manual tests every so often, most likely before any release and after a major fix or feature enhancement. 

##### Regression Tests
Regression tests do not need to be in their own suite, but rather after writing and running the integration and system tests and fixing any bugs found from those tests or from somewhere else, the unit tests in particular should be run again (this is the regression testing) to make sure that these "fixes" did not break something somewhere else in the code. Because the unit tests should be testing the functionality in each class individually, it is crucial that these tests don't break. As mentioned above, that's why it's important to do nightly builds and continuous integration using a tool Jenkins or Travis CI. 

### 1.2 Test Selection

The unit tests will all be white-box since they're testing individual functionality at a very modular level, and it makes more sense to white-box test. Integration tests should also mostly be white-box tested because they're testing that various components work together. The system tests should be a mixture of white and black box testing. Because they are testing the entire system end-to-end, they should be as black box as possible, because the tester shouldn't need to know a whole lot about the underlying code since it's testing the end-to-end functionality. 

### 1.3 Adequacy Criterion

We will go with a coverage tool to make sure that we get an acceptable level of code coverage in the tests. 100% code coverage would of course be the most ideal, but anything over 90% will also be acceptable. 

### 1.4 Bug Tracking

Bug reports and enhancement requests can be easily tracked through our GitHub Issues page. If this application was converted to a truly open-source project, users would be able to easily report bugs or request enhancements through the Issues page. As developers, we can comment, close, or even open bugs/feature requests. 

### 1.5 Technology

We will mainly be using JUnit, and potentially use Selenium as well for testing.

## 2 Test Cases

| Purpose | Steps | Expected | Actual | P/F
| ------- | :---- | :------- | :----- | :--
| Test register new student | register as a new student and verify the fields are correct | verify correct fields | succeeds | P |
| Test login | login as an existing student and verify everything is correct | verify correct fields | succeeds | P |
| Test add quiz | create a quiz | verify that the fields in the quiz are correct upon creation | succeeds | P | 
| Test remove quiz | remove a quiz by name | verify that the quiz is removed | suceeds | P |
| Test get quiz scores by student | create quizzes for a student, view them and verify fields are correct | verify correct fields| n/a | n/a |
| Test get first score | login, create a quiz, practice quiz, get score | verify that score is correct | n/a | n/a |
| Test get highest score | login, create a quiz, practice quiz with varying results, get highest score | verify highest score is correct | n/a | n/a |
| Test practice quiz with score | login, create a quiz, practice quiz and get score | verify score is correct | n/a | n/a |
| Test quiz generate question | login, create a quiz, test generate word function | verify word is valid | n/a | n/a |
| Test QuizEvent is get next question | login, create a quiz and quizevent, call getNextQuestion() method | verify it returns a question | n/a | n/a |
| Test get current score | login, create a quiz and quizevent, answer all questions with varying correctness | verify score is correct | n/a | n/a |
| Test submit question response | login, create a quiz and quizevent, submit a question response | verify that question was graded | n/a | n/a |
| Test get question result | login, create a quiz and quizevent, submit question response and call getQuestionResult() | verify that question was graded correctly | n/a | n/a |
| Test add word | login, create a quiz and quizevent, call Question addWord() method | verify word is in Question wordList | verified | P |
| Test add incorrect definition | login, create a quiz and quizevent, call Question addIncorrectDefinition() method | verify incorrect definition is in incorrectDefinitions list | verified | P |
| Test system multiple users practicing quizzes | create a few users, have each user create a few quizzes, each user practices a few quizzes, get quiz scores by student | verify scores are correct | n/a | n/a |
