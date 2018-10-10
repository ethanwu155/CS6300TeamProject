package edu.gatech.seclass.sdpvocabquiz;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

@Entity
public class Student {
    @PrimaryKey
    public int id;

    public String fullName;
    public String username;
    public String major;

    @TypeConverters(SeniorityLevelConverter.class)
    public SeniorityLevel seniorityLevel;
    public String emailAddress;

    public Student(String fullName, String username, String major, SeniorityLevel seniorityLevel, String emailAddress) {
        this.fullName = fullName;
        this.username = username;
        this.major = major;
        this.seniorityLevel = seniorityLevel;
        this.emailAddress = emailAddress;
    }

    public enum SeniorityLevel {
        FRESHMAN(0),
        SOPHOMORE(1),
        JUNIOR(2),
        SENIOR(3);

        public int value;

        SeniorityLevel(int value) {
            this.value = value;
        }
    }

}
