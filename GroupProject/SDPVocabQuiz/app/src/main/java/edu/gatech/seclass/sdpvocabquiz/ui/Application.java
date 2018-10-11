package edu.gatech.seclass.sdpvocabquiz.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.gatech.seclass.sdpvocabquiz.R;
import edu.gatech.seclass.sdpvocabquiz.database.AppDatabase;
import edu.gatech.seclass.sdpvocabquiz.database.Student;

public class Application extends AppCompatActivity implements
        LoginFragment.OnLoginListener,
        RegisterFragment.OnRegisterListener {

    private String currentUser = null;
    private AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showLoginFragment();
    }

    private void showLoginFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrameLayout, new LoginFragment(), "LOGIN")
                .commit();

    }

    private void showRegistrationFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrameLayout, new RegisterFragment(), "REGISTRATION")
                .commit();

    }
    public Application() {

    }
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

    @Override
    public void onLogin(String username) {
        login(username);
    }

    @Override
    public void onRegisterRequested() {
        showRegistrationFragment();
    }

    @Override
    public void onRegistered(Uri uri) {

    }
}
