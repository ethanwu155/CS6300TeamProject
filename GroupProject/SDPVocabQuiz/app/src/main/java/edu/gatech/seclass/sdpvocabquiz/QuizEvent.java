package edu.gatech.seclass.sdpvocabquiz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class QuizEvent {
    public Student student;
    public Quiz quiz;
    public Date timestamp;
    public ArrayList<QuestionEvent> questions = new ArrayList<>();
    public Double score;

    public Map<String, String> dictionary;

    public QuizEvent(Student student, Quiz quiz, Date timestamp, ArrayList questions) {
        this.student = student;
        this.quiz = quiz;
        this.timestamp = timestamp;
        this.questions.addAll(questions);
        this.score = 0.0;
    }

    //getting Quiz
    public Quiz getQuiz() {
        return quiz;
    }

    //setting Quiz
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    //getting score
    public double getScore() {
        return score;
    }

    public double calculateScore() {
        if (true /*Question is answered correctly??*/) {
            score++;
        }
        return score;
    }

    /*method for choosing question with correct answer and randomly selected
    incorrect definitions.

    Utilized Map and also randomly selected incorrect definition from existing correct definitions
    not list of incorrect-definitions. (Need to fix)
     */

    public void chooseWords() {
        List<String>words = new ArrayList<>(dictionary.keySet());
        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        String theWord = words.get(randomIndex);
        String theDef = dictionary.get(theWord);


        //pick 3 other defintions at random (from existing library of words/definitions)
        List<String>def = new ArrayList<>(dictionary.values());
        def.remove(theDef);
        Collections.shuffle(def);
        def = def.subList(0, 3);
        def.add(theDef);
        Collections.shuffle(def);
    }
}
