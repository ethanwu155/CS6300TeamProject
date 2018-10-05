# Design Document
**Author**: 6300Fall18Team69

## 1 Design Considerations

### 1.1 Assumptions
* This app does not maintain cross session state. For example if user completes adding Quiz or exits before finishing process, than process will start from the beginning. 
* Time and Date is provided by a utility class
* All data for Quiz application is stored locally on the phone. 

### 1.2 Constraints
* User must have smart phone

### 1.3 System Environment
* _Platform_: Android SDK
* _Operating Systems_: Android
* _User Environment_: Phone Application

## 2 Architecture Design

### 2.1 Component Diagram
![alt text](./pics/Component%20Diagram.svg)

* The requirements for this project did not require a particularly complex solution.  Thus, in the planned architecure, the majority of functionality is to be implemented within a single main class.
* Nevertheless, there are at least two other distinguishable "components":
  1. The Application maintains a collection of Quiz objects, and when a user elects to practice one of them, the Application will call upon that Quiz n times to generate a question with randomly selected wrong answers.
  2. As created Quizzes and QuizEvents must persist after the application is closed and after the device is powered off (and because the project specification specifies this is to be a non-networked solution), we must store these somewhere on disk.  We will do this with an sqLite databse.

### 2.2 Deployment Diagram
* Due to the lack of network requirements and all the data being stored locally, the application would be deployed on a single Android device and a deployment diagram would not be necessary.

## 3 Low Level Design

### 3.1 Class Diagram

![alt text](../Design-Team/Group%20Project%20D1%20-%20Updated.jpg)

### 3.2 Other Diagrams

## 4 User Interface Design

![alt text](./pics/CS6300%20UI%20Design%20.png)
