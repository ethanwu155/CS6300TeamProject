package edu.gatech.seclass.sdpvocabquiz.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Quiz.class,
            parentColumns = "id",
            childColumns = "quizId")})
public class Word {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String definition;
    public int quizId;

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getDefinition() {
        return definition;
    }

    public int getQuizId() {
        return quizId;
    }

    public Word(String name, String definition, int quizId) {
        this.name = name;
        this.definition = definition;
        this.quizId = quizId;
    }

}
