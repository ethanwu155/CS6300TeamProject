package edu.gatech.seclass.sdpvocabquiz;

import android.database.sqlite.SQLiteConstraintException;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import java.util.Random;

import edu.gatech.seclass.sdpvocabquiz.database.AppDatabase;
import edu.gatech.seclass.sdpvocabquiz.database.Student;

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
            Log.d("MainActivityTests-Info", "Registered user: \"" + username+"\"");
            return true;
        } catch (SQLiteConstraintException e) {
            Log.d("MainActivityTests-Info", "User \"" + username+"\" was already registered");
            return false;
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
