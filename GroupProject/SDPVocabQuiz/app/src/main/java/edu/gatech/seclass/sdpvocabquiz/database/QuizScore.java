package edu.gatech.seclass.sdpvocabquiz.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

@Entity(foreignKeys = {
        @ForeignKey(entity = Student.class,
            parentColumns = "id",
            childColumns = "studentId"),
        @ForeignKey(entity = Quiz.class,
            parentColumns = "id",
            childColumns = "quizId")})
public class QuizScore {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public float finalScore;

    @TypeConverters(DateConverter.class)
    public Date timestamp;

    public int studentId;
    public int quizId;

    public QuizScore(int studentId, int quizId, float finalScore) {
        this.finalScore = finalScore;
        this.timestamp = new Date();
        this.studentId = studentId;
        this.quizId = quizId;
    }

}
