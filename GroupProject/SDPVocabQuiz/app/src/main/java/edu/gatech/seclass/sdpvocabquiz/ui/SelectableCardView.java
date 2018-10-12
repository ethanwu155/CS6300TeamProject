package edu.gatech.seclass.sdpvocabquiz.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Checkable;

import edu.gatech.seclass.sdpvocabquiz.R;

public class SelectableCardView extends CardView implements Checkable {

    private static final int[] CHECKED_STATE_SET = {
            android.R.attr.state_checked,
    };

    private boolean isChecked;

    public SelectableCardView(Context context) {
        super(context);
        init(null);
    }

    public SelectableCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SelectableCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        //LayoutInflater.from(getContext()).inflate(R.layout.checkable_card_view, this, true);

        setClickable(true);
        setChecked(false);

        setCardBackgroundColor(
                ContextCompat.getColorStateList(
                        getContext(),
                        R.color.selector_card_view_colors
                )
        );

    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }

    @Override
    public boolean performClick() {
        toggle();
        return super.performClick();
    }

    @Override
    public void setChecked(boolean checked) {
        this.isChecked = checked;
        refreshDrawableState();
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        setChecked(!this.isChecked);
        refreshDrawableState();
    }
}
