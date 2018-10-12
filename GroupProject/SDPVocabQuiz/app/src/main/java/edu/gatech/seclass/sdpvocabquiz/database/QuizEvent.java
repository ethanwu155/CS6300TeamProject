package edu.gatech.seclass.sdpvocabquiz.database;

import java.util.ArrayList;
import java.util.Collections;
import android.content.Context;

public class QuizEvent {
    private Student student;
    private QuizWithWords quizWithWords;
    private ArrayList<Question> questions = new ArrayList<>();
    private int totalCorrect = 0;
    private Question currentQuestion;
    private int questionIndex = 0;

    private AppDatabase db;

    public QuizEvent(String studentName, String quizName, Context context) {
        this.db = AppDatabase.getDatabaseInstance(context);
        this.student = this.db.studentDao().getStudentByUsername(studentName).get(0);
        this.quizWithWords = this.db.quizDao().getQuizWithWordsByName(quizName).get(0);

        this.generateQuestions();
    }

    private void generateQuestions() {
        Collections.shuffle(this.quizWithWords.words);
        Collections.shuffle(this.quizWithWords.quiz.incorrectDefinitions);

        int index = 0;
        for (Word word : this.quizWithWords.words) {
            ArrayList<String> defs = new ArrayList<>();
            defs.add(word.definition);

            defs.addAll(this.quizWithWords.quiz.incorrectDefinitions.subList(index, index+3));
            index += 3;

            Question question = new Question(word.name, defs, word.definition);
            this.questions.add(question);
        }
        Collections.shuffle(this.questions);
    }

    public Question getNextQuestion() {
        if (this.questionIndex < this.questions.size()) {
            this.currentQuestion = this.questions.get(this.questionIndex++);
            return this.currentQuestion;
        }
        return null;
    }

    public void gradeQuestion(String definition) {
        boolean correct = this.currentQuestion.answerQuestion(definition);
        if (correct) {
            this.totalCorrect++;
        }
    }

    public QuizScore gradeQuiz() {
        float finalScore = ((float) this.totalCorrect) / ((float) this.questions.size());
        QuizScore quizScore = new QuizScore(this.student.id, this.quizWithWords.quiz.id, finalScore);
        this.db.quizScoreDao().addQuizScore(quizScore);
        return quizScore;
    }

}
