package edu.gatech.seclass.sdpvocabquiz;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Student {
    @PrimaryKey
    public int id;

    public String fullName;
    public String username;
    public String major;
    public SeniorityLevel seniorityLevel;
    public String emailAddress;

    public Student(String fullName, String username, String major, SeniorityLevel seniorityLevel, String emailAddress) {
        this.fullName = fullName;
        this.username = username;
        this.major = major;
        this.seniorityLevel = seniorityLevel;
        this.emailAddress = emailAddress;
    }

}
