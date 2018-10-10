package edu.gatech.seclass.sdpvocabquiz;

import java.util.ArrayList;

public class Question {

    public ArrayList<Word> wordList = new ArrayList<>();
    public ArrayList<String> incorrectDefinitions = new ArrayList<>();

    public Word name;
    public Word definition;
    String[] choices = new String[3];

    public Question(ArrayList wordList, ArrayList incorrectDefinitions) {
        this.wordList.addAll(wordList);
        this.incorrectDefinitions.addAll(incorrectDefinitions);
    }

    //constructor to describe question on quiz
    public Question(Word name, String[] choices, Word definition) {
        this.name = name;
        this.definition = definition;
        this.choices[0] = choices[0];
        this.choices[1] = choices[1];
        this.choices[2] = choices[2];
        this.choices[3] = choices[3];
        this.definition = definition;
    }

    /*
    Getter and Setter Methods for Question
     */
    public Word getName() {
        return name;
    }

    public Word getDefinition() {
        return definition;
    }

    public void setDefinition(Word definition) {
        this.definition = definition;
    }

    public void setChoices(int i, String choices) {
        this.choices[i] = choices;
    }

    public void setQuestion(Word name) {
        this.name = name;
    }
}
