package edu.gatech.seclass.sdpvocabquiz.database;

import java.util.ArrayList;
import java.util.Collections;

public class Question {

    public String word;
    public ArrayList<String> definitions = new ArrayList<>();
    private String correctDefinition;

    public Question(String word, ArrayList<String> definitions, String correctDefinition) {
        this.word = word;
        this.definitions.addAll(definitions);
        Collections.shuffle(this.definitions);
        this.correctDefinition = correctDefinition;
    }

    public boolean answerQuestion(String definition) {
        if (definition.equals(this.correctDefinition)) {
            return true;
        }
        return false;
    }

}
