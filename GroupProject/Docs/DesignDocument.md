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
* _Database Library_: Android Room Persistence Library

## 2 Architecture Design

### 2.1 Component Diagram
![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/Updated%20Component%20Diagram.png)

* We have the following components: Student, Quiz, Word, Statistic, and Persistance. Dotted/Solid lines connecting Application (UI) to Student component indicate use. So Application uses Student component as well as Quiz Component. Student component uses Quiz Component as this is a Vocabulary Quiz application. Additionally Quizzes component uses Words Component as well as Statistics.
* As with UML 2 structure, we have a second level representation. We have a line connecting to a lollipop socket attached to the Persistence component. The Persistence component provides persistence services. In turn, the Persistence component relies on the local Quiz Database to provide low level database services. 

### 2.2 Deployment Diagram

* Due to the lack of network requirements and all the data being stored locally, the application would be deployed on a single Android device and a deployment diagram would not be necessary.

## 3 Low Level Design

### 3.1 Class Diagram

![alt text](https://github.gatech.edu/gt-omscs-se-2018fall/6300Fall18Team69/blob/development/GroupProject/Docs/pics/SDPVocab%20Class%20Diagram%20v2.png)

### 3.2 Other Diagrams

## 4 User Interface Design

![alt text](./pics/CS6300%20UI%20Design%20.png)
