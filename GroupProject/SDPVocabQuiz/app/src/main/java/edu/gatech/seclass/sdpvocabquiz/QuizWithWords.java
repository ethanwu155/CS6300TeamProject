package edu.gatech.seclass.sdpvocabquiz;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class QuizWithWords {
    @Embedded
    public Quiz quiz;

    @Relation(parentColumn = "id", entityColumn = "quizId", entity = Word.class)
    public List<Word> words;
}
