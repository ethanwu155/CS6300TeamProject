package edu.gatech.seclass.sdpvocabquiz;

public class Student {

    String fullName;
    String username;
    String major;
    SeniorityLevel seniorityLevel;
    String emailAddress;

    public Student(String fullName, String username, String major, SeniorityLevel seniorityLevel, String emailAddress) {
        this.fullName = fullName;
        this.username = username;
        this.major = major;
        this.seniorityLevel = seniorityLevel;
        this.emailAddress = emailAddress;
    }

}
