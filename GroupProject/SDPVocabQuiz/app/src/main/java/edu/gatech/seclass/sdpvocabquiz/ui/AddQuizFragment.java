package edu.gatech.seclass.sdpvocabquiz.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.sdpvocabquiz.R;
import edu.gatech.seclass.sdpvocabquiz.database.Quiz;
import edu.gatech.seclass.sdpvocabquiz.database.Word;

public class AddQuizFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button addButton, addWordButton, addBadDefButton;
    ArrayList<Word> wordList = new ArrayList<>();
    ArrayList<String> wordListDisplay = new ArrayList<>();
    ArrayList<String> badDefinitionList = new ArrayList<>();
    LinearLayout newWordLayout, newDefLayout;
    TextInputLayout quizName, quizDescription;

    private OnQuizAddedListener mListener;

    public AddQuizFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_quiz, container, false);

        addButton = view.findViewById(R.id.addButton);
        addWordButton = view.findViewById(R.id.addWordButton);
        addBadDefButton = view.findViewById(R.id.addBadDefButton);
        newWordLayout = view.findViewById(R.id.wordsLayout);
        newDefLayout = view.findViewById(R.id.badDefsLayout);
        quizName = view.findViewById(R.id.textInputLayoutQuizName);
        quizDescription = view.findViewById(R.id.textInputLayoutQuizDescription);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = quizName.getEditText().getText().toString();
                String description = quizDescription.getEditText().getText().toString();
                //Quiz quiz = new Quiz(name, description, wordList, badDefinitionList);  //TODO: the DB needs to be updated
                Quiz quiz = new Quiz(name, description, badDefinitionList);
                if (mListener != null) {
                    mListener.onQuizAdded(quiz);
                }
            }
        });

        addWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.new_word_layout);
                final EditText newWordEditText = dialog.findViewById(R.id.newWord);
                final EditText definitionEditText = dialog.findViewById(R.id.definition);
                final Button cancelButton = dialog.findViewById(R.id.cancelButton);
                final Button addButton = dialog.findViewById(R.id.addButton);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String newWord = newWordEditText.getText().toString();
                        String definition = definitionEditText.getText().toString();
                        addNewWord(newWord, definition);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        addBadDefButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.new_definition_layout);
                final EditText definitionEditText = dialog.findViewById(R.id.definition);
                final Button cancelButton = dialog.findViewById(R.id.cancelButton);
                final Button addButton = dialog.findViewById(R.id.addButton);
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String definition = definitionEditText.getText().toString();
                        addNewDefinition(definition);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnQuizAddedListener) {
            mListener = (OnQuizAddedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnQuizAddedListener");
        }
    }

    public void addNewWord(String word, String definition) {
        wordList.add(new Word(word, definition));
        addTextView(word + ": " + definition, "new_word");
        Toast.makeText(getActivity(),
                "Number of Words " + String.valueOf(wordList.size()),
                Toast.LENGTH_SHORT).show();
    }

    public void addNewDefinition(String definition) {
        badDefinitionList.add(definition);
        addTextView(definition, "new_def");
        Toast.makeText(getActivity(),
                "Number of Defs" + String.valueOf(badDefinitionList.size()),
                Toast.LENGTH_SHORT).show();
    }

    public void addTextView(String text, String list) {
        TextView newTextView = new TextView(getActivity());
        newTextView.setText(text);
        newTextView.setLayoutParams((new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)));

        if(list.equals("new_word")) {
            newWordLayout.addView(newTextView);
        } else if (list.equals("new_def")) {
            newDefLayout.addView(newTextView);
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnQuizAddedListener {
        void onQuizAdded(Quiz quiz);
    }

}
