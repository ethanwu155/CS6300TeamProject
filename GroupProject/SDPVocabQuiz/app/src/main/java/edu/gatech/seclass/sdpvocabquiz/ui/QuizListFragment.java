package edu.gatech.seclass.sdpvocabquiz.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.gatech.seclass.sdpvocabquiz.R;
import edu.gatech.seclass.sdpvocabquiz.database.Quiz;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnQuizSelectedListener}
 * interface.
 */
public class QuizListFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnQuizSelectedListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public QuizListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static QuizListFragment newInstance(int columnCount) {
        QuizListFragment fragment = new QuizListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.quiz_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz_list, container, false);

        String currentUser = ((Application)getActivity()).currentUser;
        ((TextView)view.findViewById(R.id.welcomeTextView)).setText("Welcome " + currentUser + "!");

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        Context context = view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        List<Quiz> quizList = ((Application)(getActivity())).getOrderedQuizList();
        recyclerView.setAdapter(new QuizItemRecyclerViewAdapter(quizList, mListener));

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnQuizSelectedListener) {
            mListener = (OnQuizSelectedListener) context;
        } else {
            //throw new RuntimeException(context.toString()
            //        + " must implement OnQuizSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnQuizSelectedListener {
        void onQuizSelected(Quiz quiz);
    }
}
