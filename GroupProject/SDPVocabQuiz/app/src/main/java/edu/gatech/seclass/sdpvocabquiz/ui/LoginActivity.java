package edu.gatech.seclass.sdpvocabquiz.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import edu.gatech.seclass.sdpvocabquiz.R;
import edu.gatech.seclass.sdpvocabquiz.database.AppDatabase;
import edu.gatech.seclass.sdpvocabquiz.database.Student;

public class LoginActivity extends AppCompatActivity implements
        LoginFragment.OnLoginListener,
        RegisterFragment.OnRegisterListener {

    private String currentUser = null;
    private AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.db = AppDatabase.getDatabaseInstance(getApplicationContext());

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
    public LoginActivity() {
    }
    public LoginActivity(Context context) {
        this.db = AppDatabase.getDatabaseInstance(context);
    }

    public void login(String username) {
        if(db == null) {
            displayLoginFailed();
            return;
        }
        List<Student> studentList = db.studentDao().getStudentByUsername(username);
        if (studentList.size() > 0) {
            this.currentUser = username;
            loadQuizActivity();
        } else {
            displayLoginFailed();
        }

    }

    private void loadQuizActivity() {
        Intent i = new Intent(this, Application.class);
        Bundle b = new Bundle();
        b.putString("currentUser", currentUser);
        i.putExtras(b);
        startActivity(i);
    }

    public void logout() {
        this.currentUser = null;
    }

    public void registerNewStudent(Student student) {
        db.studentDao().registerNewStudent(student);
        this.currentUser = student.username;
        loadQuizActivity();
    }

    public void displayLoginFailed() {
        Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
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
    public void onRegistered(Student student) {
        registerNewStudent(student);
    }
}
