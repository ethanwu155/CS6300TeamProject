package edu.gatech.seclass.sdpvocabquiz;

import android.content.Context;

import edu.gatech.seclass.sdpvocabquiz.database.AppDatabase;
import edu.gatech.seclass.sdpvocabquiz.database.Student;

public class Application {

    private String currentUser = null;
    private AppDatabase db;

    public Application(Context context) {
        this.db = AppDatabase.getInMemoryDatabase(context);
    }

    public void login(String username) {
        this.currentUser = username;
    }

    public void logout() {
        this.currentUser = null;
    }

    public void registerNewStudent(String fullName, String username, String major, Student.SeniorityLevel seniorityLevel, String email) {
        Student student = new Student(fullName, username, major, seniorityLevel, email);
        db.studentDao().registerNewStudent(student);
        this.currentUser = username;
    }

}
