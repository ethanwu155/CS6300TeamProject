package edu.gatech.seclass.sdpvocabquiz;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface QuizDao {

    @Query("SELECT * FROM Quiz")
    List<QuizWithWords> loadQuizzesWithWords();

    @Query("SELECT * FROM Quiz WHERE id = :id")
    QuizWithWords loadQuizWithWords(int id);

    @Insert
    void addQuiz(Quiz quiz);

    @Delete
    void deleteQuiz(Quiz quiz);

}
