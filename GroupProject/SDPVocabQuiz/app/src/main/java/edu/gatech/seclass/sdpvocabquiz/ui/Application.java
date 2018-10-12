package edu.gatech.seclass.sdpvocabquiz.ui;

import android.arch.lifecycle.LiveData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.gatech.seclass.sdpvocabquiz.R;
import edu.gatech.seclass.sdpvocabquiz.database.AppDatabase;
import edu.gatech.seclass.sdpvocabquiz.database.Quiz;
import edu.gatech.seclass.sdpvocabquiz.database.QuizEvent;
import edu.gatech.seclass.sdpvocabquiz.database.Student;

public class Application extends AppCompatActivity {

    public String currentUser = null;
    private AppDatabase db;

    private List<Student> studentList = new ArrayList<>();
    private List<Quiz> quizList = new ArrayList<>();
    private Map<String, List<QuizEvent>> quizHistory = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        String currentUser = extras.getString("currentUser");
        if (currentUser != null) {
            this.currentUser = currentUser;
        }

        this.db = AppDatabase.getInMemoryDatabase(getApplicationContext());
        showQuizListFragment();
    }


    private void showQuizListFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrameLayout, new QuizListFragment(), "QUIZ_LIST")
                .commit();

    }

    private void showAddQuizFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrameLayout, new AddQuizFragment(), "ADD_QUIZ")
                .commit();

    }

    private void showPracticeQuizFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrameLayout, new QuizPracticeFragment(), "ADD_QUIZ")
                .commit();

    }

    public void addQuiz(Quiz quiz) {
        if(db != null) {
            db.quizDao().addQuiz(quiz);
        }
        displayDBError();
    }

    public void removeQuiz(String quizName) {
        if(db != null) {
            db.quizDao().deleteQuiz(quizName);
        }
        displayDBError();

    }

    public List<Quiz> getQuizList() {
        if(db != null) {
            return db.quizDao().getAllQuizzes();
        }
        displayDBError();
        return null;
    }

    public Map<String, List<QuizEvent>> getQuizScoresByStudent(String username) {

        return null;
    }

    public double getFirstScore(String quizName, String username) {

        return 0;
    }

    public double getHighestScore(String quizName, String username) {

        return 0;
    }

    public void practiceQuiz(String quizName) {

    }

    public List<QuizEvent> viewQuizScores(String quizName) {

        return null;
    }



    public void displayDBError() {
        Toast.makeText(getApplicationContext(),
                "There was a problem with the DB", Toast.LENGTH_SHORT).show();
    }


}
