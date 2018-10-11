package edu.gatech.seclass.sdpvocabquiz.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Quiz.class,
            parentColumns = "id",
            childColumns = "quizId")})
public class Word {
    @PrimaryKey
    public int id;

    public String name;
    public String definition;
    public int quizId;

    public Word(String name, String definition) {
        this.name = name;
        this.definition = definition;
    }

}
