package edu.gatech.seclass.sdpvocabquiz;

import android.database.sqlite.SQLiteConstraintException;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.gatech.seclass.sdpvocabquiz.database.AppDatabase;
import edu.gatech.seclass.sdpvocabquiz.database.Quiz;
import edu.gatech.seclass.sdpvocabquiz.database.Student;
import edu.gatech.seclass.sdpvocabquiz.database.Word;

class TestUtils {
    private AppDatabase db;

    public TestUtils() {
        db = AppDatabase.getDatabaseInstance(InstrumentationRegistry.getInstrumentation().getTargetContext());
    }

    public boolean registerUser(String username) {
        try {
            db.studentDao().registerNewStudent(new Student(
                    username, username+"@gatech.edu", "Some Major", Student.SeniorityLevel.FRESHMAN
            ));
            Log.d("TestUtils-Info", "Registered user: \"" + username+"\"");
            return true;
        } catch (SQLiteConstraintException e) {
            Log.d("TestUtils-Info", "User \"" + username+"\" was already registered");
            return false;
        }
    }

    public boolean addBaselineQuiz(String userName, String quizName) {
        return addBaselineQuiz(getUserID(userName), quizName);
    }

    public boolean addBaselineQuiz(int userID, String quizName) {
        ArrayList<Word> wordList = new ArrayList<>();
        wordList.add(new Word("Word1", "Def1", -1));

        ArrayList<String> badDefList = new ArrayList<>();
        badDefList.add("BadDef1");
        badDefList.add("BadDef2");
        badDefList.add("BadDef3");

        Quiz quiz = new Quiz(quizName, "A Descrip", badDefList, userID);
        int quizId = (int) db.quizDao().addQuiz(quiz);
        quiz.setId(quizId);
        for (Word word : wordList) {
            word.setQuizId(quizId);
            db.wordDao().addWord(word);
        }

        try {
            Log.d("TestUtils-Info", "Added quiz: \""+quizName+"\"");
            return true;
        } catch (SQLiteConstraintException e) {
            Log.d("TestUtils-Info", "Quiz Name \""+quizName+"\" was already taken");
            return false;
        }
    }

    public int getUserID(String userName) {
        List<Student> students = db.studentDao().getStudentByUsername(userName);
        if(students.size() != 1) {
            return -1;
        } else {
            return students.get(0).id;
        }
    }

    public static String randAlphanumeric(int length) {
        // Reference on randomized strings: https://www.baeldung.com/java-random-string
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = 97 + (int)
                    (random.nextFloat() * (122 - 97 + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
