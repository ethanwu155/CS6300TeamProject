package edu.gatech.seclass.sdpvocabquiz.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import edu.gatech.seclass.sdpvocabquiz.R;
import edu.gatech.seclass.sdpvocabquiz.database.Question;
import edu.gatech.seclass.sdpvocabquiz.database.QuizEvent;
import edu.gatech.seclass.sdpvocabquiz.database.QuizWithWords;

public class QuizPracticeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Application app;

    private QuizEvent quizEvent;
    private QuizWithWords quizWithWords;
    private OnQuizCompletedListener mListener;
    private Button confirmButton;
    SelectableCardView a1, a2, a3, a4;
    TextView textViewA1, textViewA2, textViewA3, textViewA4, word;
    private Question mCurrentQuestion;
    private boolean quizCompleted = false;

    public QuizPracticeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        app = (Application) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quiz_practice, container, false);

        confirmButton = view.findViewById(R.id.confirmButton);
        a1 = view.findViewById(R.id.card_view_answer_1);
        a2 = view.findViewById(R.id.card_view_answer_2);
        a3 = view.findViewById(R.id.card_view_answer_3);
        a4 = view.findViewById(R.id.card_view_answer_4);
        textViewA1 = view.findViewById(R.id.info_text_answer_1);
        textViewA2 = view.findViewById(R.id.info_text_answer_2);
        textViewA3 = view.findViewById(R.id.info_text_answer_3);
        textViewA4 = view.findViewById(R.id.info_text_answer_4);
        word = view.findViewById(R.id.word);

        View.OnClickListener answerListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()) {
                    case R.id.card_view_answer_1:
                        selectAnswer(1);
                        break;
                    case R.id.card_view_answer_2:
                        selectAnswer(2);
                        break;
                    case R.id.card_view_answer_3:
                        selectAnswer(3);
                        break;
                    case R.id.card_view_answer_4:
                        selectAnswer(4);
                        break;
                    case R.id.confirmButton:
                        submitAnswer();
                        break;
                }
            }
        };

        a1.setOnClickListener(answerListener);
        a2.setOnClickListener(answerListener);
        a3.setOnClickListener(answerListener);
        a4.setOnClickListener(answerListener);
        confirmButton.setOnClickListener(answerListener);

        initialize();

        return view;
    }

    private void initialize() {
        quizEvent = app.getCurrentQuizEvent();
        if (quizEvent.getNumQuestions() == 0) {
            if (mListener != null) {
                mListener.onQuizLoadError();
            }
        } else {
            onQuestionCompleted();
        }
    }

    private void updateUI() {
        if(mCurrentQuestion == null) {
            //app.displayDBError();
            return;
        }
        word.setText(mCurrentQuestion.word);
        ArrayList<String> definitions = mCurrentQuestion.definitions;
        textViewA1.setText(definitions.get(0));
        textViewA2.setText(definitions.get(1));
        textViewA3.setText(definitions.get(2));
        textViewA4.setText(definitions.get(3));
    }

    private void submitAnswer() {
        int answerIndex = 0;
        if(a1.isChecked()) {
            answerIndex = 0;
        } else if (a2.isChecked()) {
            answerIndex = 1;
        } else if (a3.isChecked()) {
            answerIndex = 2;

        } else if (a4.isChecked()) {
            answerIndex = 3;
        }

        boolean isCorrect = quizEvent.gradeQuestion(mCurrentQuestion.getDefinitions().get(answerIndex));
        displayResult(isCorrect);
    }

    private void displayResult(boolean isCorrect) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String message = String.format("%d out of %d correct", quizEvent.getTotalCorrect(), quizEvent.getNumQuestions());
        builder.setMessage(message);

        if(isCorrect) {
            builder.setTitle("Correct!");
        } else {
            builder.setTitle("Incorrect");
        }

        builder.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                onQuestionCompleted();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.show();

    }

    private void onQuestionCompleted() {
        mCurrentQuestion = quizEvent.getNextQuestion();
        if(mCurrentQuestion == null) {
            if (mListener != null) {
                mListener.onQuizCompleted();
            }
        }
        a1.setChecked(false);
        a2.setChecked(false);
        a3.setChecked(false);
        a4.setChecked(false);
        updateUI();
    }

    private void selectAnswer(int answer) {
        a1.setChecked(answer == 1);
        a2.setChecked(answer == 2);
        a3.setChecked(answer == 3);
        a4.setChecked(answer == 4);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnQuizCompletedListener) {
            mListener = (OnQuizCompletedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnQuizCompletedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnQuizCompletedListener {
        void onQuizCompleted();
        void onQuizLoadError();
    }
}
