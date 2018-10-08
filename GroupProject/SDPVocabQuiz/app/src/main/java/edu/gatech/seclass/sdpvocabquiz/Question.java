package edu.gatech.seclass.sdpvocabquiz;

import java.util.ArrayList;

public class Question {
    public ArrayList<Word> wordList = new ArrayList<>();
    public ArrayList<String> incorrectDefinitions = new ArrayList<>();

    public Question(ArrayList wordList, ArrayList incorrectDefinitions) {
        this.wordList.addAll(wordList);
        this.incorrectDefinitions.addAll(incorrectDefinitions);
    }

}
