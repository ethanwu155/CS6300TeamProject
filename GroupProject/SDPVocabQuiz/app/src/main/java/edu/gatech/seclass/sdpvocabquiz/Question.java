package edu.gatech.seclass.sdpvocabquiz;

import java.util.ArrayList;

public class Question {

    ArrayList<Word> wordList;
    ArrayList<String> incorrectDefinitions;

    public Question(ArrayList wordList, ArrayList incorrectDefinitions) {
        this.wordList = wordList;
        this.incorrectDefinitions = incorrectDefinitions;
    }

}
