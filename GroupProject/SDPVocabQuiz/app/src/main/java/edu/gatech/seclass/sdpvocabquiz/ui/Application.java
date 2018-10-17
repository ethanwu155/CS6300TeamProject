package edu.gatech.seclass.sdpvocabquiz.ui;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.gatech.seclass.sdpvocabquiz.R;
import edu.gatech.seclass.sdpvocabquiz.database.AppDatabase;
import edu.gatech.seclass.sdpvocabquiz.database.Quiz;
import edu.gatech.seclass.sdpvocabquiz.database.QuizEvent;
import edu.gatech.seclass.sdpvocabquiz.database.QuizWithWords;
import edu.gatech.seclass.sdpvocabquiz.database.Student;
import edu.gatech.seclass.sdpvocabquiz.database.Word;

public class Application extends AppCompatActivity implements
        AddQuizFragment.OnQuizAddedListener,
        QuizListFragment.OnQuizSelectedListener,
        QuizPracticeFragment.OnQuizCompletedListener {

    public String currentUser = null;
    public int currentUserID = -1;
    private AppDatabase db;

    private List<Student> studentList = new ArrayList<>();
    private List<Quiz> quizList = new ArrayList<>();  //TODO: replace this when DB is working
    private Map<String, List<QuizEvent>> quizHistory = new HashMap<>();
    public Quiz currentQuiz;
    public QuizEvent currentQuizEvent;

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

        this.db = AppDatabase.getDatabaseInstance(getApplicationContext());
        List<Student> studentList = db.studentDao().getStudentByUsername(currentUser);
        if(studentList.size() > 0) {
            Student student = studentList.get(0);
            currentUserID = student.getId();
        }
        showQuizListFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_add:
                showAddQuizFragment();
                return false;
            default:
                break;
        }

        return false;
    }

    public QuizEvent getCurrentQuizEvent() {
        return currentQuizEvent;
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

    private void showPracticeQuizFragment(Quiz quiz) {
        currentQuiz = quiz;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentFrameLayout, new QuizPracticeFragment(), "ADD_QUIZ")
                .commit();

    }

    public int addQuiz(Quiz quiz) {
        showQuizListFragment();

        if(db != null) {
            int quizId = (int) db.quizDao().addQuiz(quiz);
            quiz.setId(quizId);
            this.quizList.add(quiz);
            return quizId;
        }

        displayDBError();
        return -1;
    }

    public void removeQuiz(Quiz quiz) {
        if(db != null) {
            db.quizDao().deleteQuiz(quiz);
        } else {
            displayDBError();
        }

    }

    public List<Quiz> getQuizList() {

        //TODO: DB is not working yet, just use the List<Quiz>
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


    @Override
    public void onQuizAdded(Quiz quiz, List<Word> wordList) {
        int quizID = addQuiz(quiz);
        for (Word word : wordList) {
            word.setQuizId(quizID);
            db.wordDao().addWord(word);
        }
        showQuizListFragment();
    }

    @Override
    public void onQuizSelected(Quiz quiz) {
        currentQuizEvent = new QuizEvent(currentUser, quiz.getName(), getApplicationContext());
        showPracticeQuizFragment(quiz);
    }

    @Override
    public void onQuizCompleted() {
        Toast.makeText(getApplicationContext(), "Quiz Completed and Logged in DB", Toast.LENGTH_SHORT).show();
        showQuizListFragment();
    }

    @Override
    public void onQuizLoadError() {
        Toast.makeText(getApplicationContext(), "The Quiz has no defined words, can't practice!", Toast.LENGTH_SHORT).show();
        showQuizListFragment();
    }
}
