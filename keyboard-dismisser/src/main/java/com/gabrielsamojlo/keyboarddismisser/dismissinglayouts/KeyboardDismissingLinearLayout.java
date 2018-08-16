package com.gabrielsamojlo.keyboarddismisser.dismissinglayouts;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.gabrielsamojlo.keyboarddismisser.DismissingUtils;
import com.gabrielsamojlo.keyboarddismisser.KeyboardListener;

public class KeyboardDismissingLinearLayout extends LinearLayout {

    private boolean mIsKeyboardVisible;
    private KeyboardListener mKeyboardListener;

    public KeyboardDismissingLinearLayout(Context context) {
        super(context);
    }

    public KeyboardDismissingLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyboardDismissingLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public KeyboardDismissingLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean wasDispatched = super.dispatchTouchEvent(ev);

        if (!wasDispatched && mIsKeyboardVisible) {
            DismissingUtils.hideKeyboard(this);
        }

        return wasDispatched;
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mKeyboardListener = new KeyboardListener() {
            @Override
            public void onKeyboardVisibilityChanged(boolean isVisible) {
                mIsKeyboardVisible = isVisible;
            }
        };

        DismissingUtils.setupKeyboardListener(this, mKeyboardListener);
    }

    @Override
    public void onDetachedFromWindow() {
        DismissingUtils.removeKeyboardListener(this, mKeyboardListener);
        mKeyboardListener = null;
        super.onDetachedFromWindow();
    }
}
