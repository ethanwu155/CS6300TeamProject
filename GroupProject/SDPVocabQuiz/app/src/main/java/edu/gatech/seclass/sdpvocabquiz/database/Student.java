package edu.gatech.seclass.sdpvocabquiz.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

@Entity(indices = {@Index(value = {"username"}, unique = true)})
public class Student {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String fullName;
    public String username;
    public String major;

    @TypeConverters(SeniorityLevelConverter.class)
    public SeniorityLevel seniorityLevel;
    public String emailAddress;

    public Student(String username, String email, String major, SeniorityLevel level) {
        this("", username, major, level, email);
    }

    public Student(String fullName, String username, String major, SeniorityLevel seniorityLevel, String emailAddress) {
        this.fullName = fullName;
        this.username = username;
        this.major = major;
        this.seniorityLevel = seniorityLevel;
        this.emailAddress = emailAddress;
    }

    public int getId() {
        return id;
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

        private String name;

        private SeniorityLevel(String name){
            this.name = name;
        }

        @Override public String toString(){
            return name;
        }
    }

}
