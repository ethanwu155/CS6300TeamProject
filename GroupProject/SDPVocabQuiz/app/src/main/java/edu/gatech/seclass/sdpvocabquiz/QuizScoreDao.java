package edu.gatech.seclass.sdpvocabquiz;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

public interface QuizScoreDao {

    @Query("SELECT * FROM QuizScore")
    List<QuizScore> getAllQuizScores();

    @Insert
    void addQuizScore(QuizScore quizScore);

    @Delete
    void deleteQuizScore(QuizScore quizScore);

}
