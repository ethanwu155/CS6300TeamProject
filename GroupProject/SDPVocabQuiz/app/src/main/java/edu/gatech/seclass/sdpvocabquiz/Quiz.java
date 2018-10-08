package edu.gatech.seclass.sdpvocabquiz;
import java.util.ArrayList;
import java.util.HashSet;

public class Quiz {

    String name;
    String description;
    ArrayList<Word> wordList;
    ArrayList<String> incorrectDefinitions;
    ArrayList<QuizEvent> quizHistory;
    HashSet<Student> firstThree;

    public Quiz(String name, String description, ArrayList wordList, ArrayList incorrectDefinitions) {
        this.name = name;
        this.description = description;
        this.wordList = wordList;
        this.incorrectDefinitions = incorrectDefinitions;
        this.quizHistory = new ArrayList<>();
        this.firstThree = new HashSet<>();
    }

}
