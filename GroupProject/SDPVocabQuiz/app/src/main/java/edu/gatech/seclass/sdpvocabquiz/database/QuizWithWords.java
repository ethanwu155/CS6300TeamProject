package edu.gatech.seclass.sdpvocabquiz.database;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class QuizWithWords {
    @Embedded
    public Quiz quiz;

    public Quiz getQuiz() {
        return quiz;
    }

    public List<Word> getWords() {
        return words;
    }

    @Relation(parentColumn = "id", entityColumn = "quizId", entity = Word.class)
    public List<Word> words;
}
