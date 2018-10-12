package edu.gatech.seclass.sdpvocabquiz.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {Quiz.class, QuizScore.class, Student.class, Word.class}, version = 1)
@TypeConverters({ListConverter.class, DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract QuizDao quizDao();
    public abstract QuizScoreDao quizScoreDao();
    public abstract StudentDao studentDao();
    public abstract WordDao wordDao();

    public static AppDatabase getDatabaseInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "sdpvocabquizdb")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
