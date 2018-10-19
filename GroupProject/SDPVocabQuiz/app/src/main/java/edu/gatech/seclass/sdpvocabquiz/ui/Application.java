package edu.gatech.seclass.sdpvocabquiz.ui;

import android.content.DialogInterface;
import android.media.AsyncPlayer;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import edu.gatech.seclass.sdpvocabquiz.R;
import edu.gatech.seclass.sdpvocabquiz.database.AppDatabase;
import edu.gatech.seclass.sdpvocabquiz.database.Quiz;
import edu.gatech.seclass.sdpvocabquiz.database.QuizEvent;
import edu.gatech.seclass.sdpvocabquiz.database.QuizScore;
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

    private List<Quiz> quizList = new ArrayList<>();  //TODO: replace this when DB is working
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
            case R.id.menu_delete:
                showDeleteQuizDialog();
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
                .replace(R.id.fragmentFrameLayout, new QuizPracticeFragment(), "PRACTICE_QUIZ")
                .commit();

    }

    public int addQuiz(Quiz quiz) {
        if(db != null) {
            List<Quiz> quizList = db.quizDao().getQuizzesByName(quiz.getName());
            if(quizList.size()>0) {

                Toast.makeText(getApplicationContext(),
                        "Quiz name already exists",
                        Toast.LENGTH_SHORT).show();
                return -1;
            }
            int quizId = (int) db.quizDao().addQuiz(quiz);
            quiz.setId(quizId);
            this.quizList.add(quiz);

            showQuizListFragment();
            return quizId;
        }

        displayDBError();
        return -1;
    }

    private void showDeleteQuizDialog() {
        if(db != null) {
            final List<Quiz> quizzes = db.quizDao().getQuizzesByStudentId(currentUserID);

            if (quizzes.size() == 0) {
                Toast.makeText(getApplicationContext(), currentUser + " has no quizzes to delete", Toast.LENGTH_SHORT).show();
                return;
            }
            String[] quizTitles = new String[quizzes.size()];
            for (int i = 0; i < quizzes.size(); i++) {
                quizTitles[i] = quizzes.get(i).getName();
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(Application.this);
            builder.setTitle("Select Quiz to Delete")
                    .setItems(quizTitles, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            removeQuiz(quizzes.get(which));
                            showQuizListFragment();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        } else {
            displayDBError();
        }
    }

    public void removeQuiz(Quiz quiz) {
        if(db != null) {
            db.quizDao().deleteQuiz(quiz);
        } else {
            displayDBError();
        }

    }

    public List<Quiz> getOrderedQuizList() {
        List<Quiz> quizList = new ArrayList<>();
        List<String> quizNames = db.quizScoreDao().getQuizzesByStudent(currentUser);
        for (String name : quizNames) {
            List<Quiz> quizzesByName = db.quizDao().getQuizzesByName(name);
            if(quizzesByName.size()>0) {
                quizList.add(quizzesByName.get(0));
            }
        }

        List<Quiz> fullQuizList = getQuizList();
        for (Quiz quiz : fullQuizList) {
            if(!quizNames.contains(quiz.getName())) {
                quizList.add(quiz);
            }
        }
        return quizList;
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


    @Override
    public void onQuizAdded(Quiz quiz, List<Word> wordList) {
        int quizID = addQuiz(quiz);
        if (quizID == -1) {
            return;
        }

        for (Word word : wordList) {
            word.setQuizId(quizID);
            db.wordDao().addWord(word);
        }
        showQuizListFragment();
    }

    @Override
    public void onQuizAddCancelled() {
        showQuizListFragment();
    }

    private void practiceQuiz(Quiz quiz) {
        currentQuizEvent = new QuizEvent(currentUser, quiz.getName(), getApplicationContext());
        showPracticeQuizFragment(quiz);
    }

    @Override
    public void onQuizSelected(Quiz quiz) {
        showQuizActions(quiz);
    }

    @Override
    public void onQuizCompleted(QuizScore score) {
        Toast.makeText(getApplicationContext(), "Quiz Completed and Logged in DB", Toast.LENGTH_SHORT).show();
        showQuizListFragment();
        showStatisticsDialog(score);
    }

    @Override
    public void onQuizLoadError() {
        Toast.makeText(getApplicationContext(), "The Quiz has no defined words, can't practice!", Toast.LENGTH_SHORT).show();
        showQuizListFragment();
    }

    private void showStatisticsDialog(QuizScore score) {
        /**
         * After every word in the quiz has been used, the student will be shown the percentage of
         *  words they correctly defined, and this information will be saved in the quiz score statistics for that quiz and student."
         */
        //

        AlertDialog.Builder builder = new AlertDialog.Builder(Application.this);
        builder.setTitle("Quiz Completed!")
                .setMessage(score.toString());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void showQuizActions(final Quiz quiz) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Application.this);
        builder.setItems(R.array.QuizActions, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which) {
                            case 0:
                                practiceQuiz(quiz);
                                break;
                            case 1:
                                showQuizStatistics(quiz);
                                break;
                        }
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showQuizStatistics(Quiz quiz) {
        /**
         *  Clicking on a quiz must display
         *  (1) the student’s first score and when it was achieved (date and time),
         *  (2) the student’s highest score and when it was achieved (date and time), and
         *  (3) the names of the first three students to score 100% on the quiz, ordered alphabetically.
         */


        //TODO:  NEED TIMESTAMP FOR FIRST SCORE
        List<QuizScore> firstScoreList = db.quizScoreDao().getFirstScoreByStudentQuiz(currentUser, quiz.getName());
        String firstScore = firstScoreList.size() == 0 ? "" : "First Score: " + String.valueOf(firstScoreList.get(0).toString());


        List<QuizScore> highScoreList = db.quizScoreDao().getHighestScoreByStudentQuiz(currentUser, quiz.getName());
        String highestScore = highScoreList.size() == 0 ? "" : "Highest Score: " + String.valueOf(highScoreList.get(0).toString());

        List<String> hundredsList = db.quizScoreDao().getFirstThreeStudentHundredsByQuizName(quiz.getName());
        String firstHundreds = "First Perfect Scores: " + TextUtils.join(", ", hundredsList);


        AlertDialog.Builder builder = new AlertDialog.Builder(Application.this);
        builder.setTitle("STATISTICS")
        .setMessage(firstScore + "\n" + highestScore + "\n" + firstHundreds);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


    @Override
    public void onBackPressed() {
        Fragment quizList = getSupportFragmentManager().findFragmentByTag("QUIZ_LIST");
        Fragment addQuiz = getSupportFragmentManager().findFragmentByTag("ADD_QUIZ");
        Fragment practiceQuiz = getSupportFragmentManager().findFragmentByTag("PRACTICE_QUIZ");
        if (quizList != null) {
            super.onBackPressed();
        } else if (addQuiz != null) {
            //press cancel button
            onQuizAddCancelled();

        } else {
            //cancel out of quiz
            showQuizListFragment();
        }
    }
}
