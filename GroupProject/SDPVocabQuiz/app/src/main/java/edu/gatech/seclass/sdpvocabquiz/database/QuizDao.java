package edu.gatech.seclass.sdpvocabquiz.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface QuizDao {

    @Query("SELECT * FROM Quiz")
    LiveData<List<QuizWithWords>> loadAllQuizzesWithWords();

    @Query("SELECT * FROM Quiz WHERE Quiz.id = :id")
    List<QuizWithWords> loadQuizWithWordsById(int id);

    @Query("SELECT * FROM Quiz WHERE Quiz.name LIKE :name")
    List<QuizWithWords> loadQuizWithWordsByName(String name);

    @Query("SELECT * FROM Quiz WHERE Quiz.studentId = :studentId")
    LiveData<List<Quiz>> getQuizzesByStudentId(int studentId);

    @Query("SELECT * FROM Quiz WHERE Quiz.studentId != :studentId")
    LiveData<List<Quiz>> getQuizzesNotByStudentId(int studentId);

    @Insert
    void addQuiz(Quiz quiz);

    @Delete
    void deleteQuiz(Quiz quiz);

}
