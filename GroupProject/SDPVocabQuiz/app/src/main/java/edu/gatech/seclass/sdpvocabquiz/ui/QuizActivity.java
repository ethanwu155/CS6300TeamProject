package edu.gatech.seclass.sdpvocabquiz.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import edu.gatech.seclass.sdpvocabquiz.R;

public class QuizActivity extends AppCompatActivity {

    private String currentUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

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

}
