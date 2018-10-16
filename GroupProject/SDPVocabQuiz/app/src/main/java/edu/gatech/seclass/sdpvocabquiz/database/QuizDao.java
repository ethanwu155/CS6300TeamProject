package edu.gatech.seclass.sdpvocabquiz.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface QuizDao {

    @Query("SELECT * FROM Quiz")
    List<Quiz> getAllQuizzes();

    @Query("SELECT * FROM Quiz WHERE Quiz.name LIKE :name")
    List<Quiz> getQuizzesByName(String name);

    @Query("SELECT * FROM Quiz " +
            "INNER JOIN Word ON Word.quizId = Quiz.id")
    List<QuizWithWords> getAllQuizzesWithWords();

    @Query("SELECT * FROM Quiz " +
            "INNER JOIN Word ON Word.quizId = Quiz.id " +
            "WHERE Quiz.id = :id")
    List<QuizWithWords> getQuizWithWordsById(int id);

    @Query("SELECT * FROM Quiz " +
            "INNER JOIN Word ON Word.quizId = Quiz.id " +
            "WHERE Quiz.name LIKE :name")
    List<QuizWithWords> getQuizWithWordsByName(String name);

    @Query("SELECT * FROM Quiz WHERE Quiz.studentId = :studentId")
    List<Quiz> getQuizzesByStudentId(int studentId);

    @Query("SELECT * FROM Quiz WHERE Quiz.studentId != :studentId")
    List<Quiz> getQuizzesNotByStudentId(int studentId);

    @Insert
    void addQuiz(Quiz quiz);

    @Delete
    void deleteQuiz(Quiz quiz);

}
