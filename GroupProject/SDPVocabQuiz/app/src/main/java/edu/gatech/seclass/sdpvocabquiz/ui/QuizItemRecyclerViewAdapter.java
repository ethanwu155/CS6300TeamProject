package edu.gatech.seclass.sdpvocabquiz.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.gatech.seclass.sdpvocabquiz.R;
import edu.gatech.seclass.sdpvocabquiz.database.Quiz;
import edu.gatech.seclass.sdpvocabquiz.ui.QuizListFragment.OnQuizSelectedListener;

import java.util.List;

public class QuizItemRecyclerViewAdapter extends RecyclerView.Adapter<QuizItemRecyclerViewAdapter.ViewHolder> {

    private final List<Quiz> mQuizzes;
    private final OnQuizSelectedListener mListener;

    public QuizItemRecyclerViewAdapter(List<Quiz> items, QuizListFragment.OnQuizSelectedListener listener) {
        mQuizzes = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_quiz_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mQuizzes.get(position);
        holder.mNameView.setText(mQuizzes.get(position).getName());
        holder.mDescriptionView.setText(mQuizzes.get(position).getDescription());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onQuizSelected(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mQuizzes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNameView;
        public final TextView mDescriptionView;
        public Quiz mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView =  view.findViewById(R.id.name);
            mDescriptionView = view.findViewById(R.id.description);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDescriptionView.getText() + "'";
        }
    }
}
