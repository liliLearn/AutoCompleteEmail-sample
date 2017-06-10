package liliLearn.yeah.net.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MultiAutoCompleteTextView;

public class CleanableMultiAutoCompleteTextView extends MultiAutoCompleteTextView {
    private Drawable mRightDrawable;

    public CleanableMultiAutoCompleteTextView(Context context) {
        super(context);
        init();
    }

    public CleanableMultiAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CleanableMultiAutoCompleteTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        // getCompoundDrawables:
        // Returns drawables for the left, top, right, and bottom borders.
        Drawable[] drawables = this.getCompoundDrawables();
        // get right drawable in layout.xml that is android:drawableRight
        mRightDrawable = drawables[2];
        setOnFocusChangeListener(new FocusChangeListenerImpl());
        addTextChangedListener(new TextWatcherImpl());
        setClearDrawableVisible(false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                boolean isClean = (event.getX() > (getWidth() - getTotalPaddingRight()))
                        && (event.getX() < (getWidth() - getPaddingRight()));
                if (isClean) {
                    setText("");
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private class FocusChangeListenerImpl implements OnFocusChangeListener {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                boolean isVisible = !TextUtils.isEmpty(getText());
                setClearDrawableVisible(isVisible);
            } else {
                setClearDrawableVisible(false);
            }
        }
    }

    private class TextWatcherImpl implements TextWatcher {
        @Override
        public void afterTextChanged(Editable s) {
            boolean isVisible = !TextUtils.isEmpty(getText());
            setClearDrawableVisible(isVisible);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // no-op
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            // no-op
        }

    }

    public void setClearDrawableVisible(boolean isVisible) {
        Drawable rightDrawable = isVisible ? mRightDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0],
                getCompoundDrawables()[1], rightDrawable,
                getCompoundDrawables()[3]);
    }
}
