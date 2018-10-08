package edu.gatech.seclass.sdpvocabquiz;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(foreignKeys = {
        @ForeignKey(entity = Student.class,
            parentColumns = "id",
            childColumns = "studentId"),
        @ForeignKey(entity = Quiz.class,
            parentColumns = "id",
            childColumns = "quizId")})
public class QuizScore {

    @PrimaryKey
    public int id;

    public double finalScore;
    public Date timestamp;

    public int studentId;
    public int quizId;

}
