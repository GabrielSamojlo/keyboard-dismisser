package com.gabrielsamojlo.keyboarddismisser.dismissinglayouts;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.gabrielsamojlo.keyboarddismisser.DismissingUtils;
import com.gabrielsamojlo.keyboarddismisser.KeyboardListener;

public class KeyboardDismissingRelativeLayout extends RelativeLayout {

    private boolean mIsKeyboardVisible;
    private KeyboardListener mKeyboardListener;

    public KeyboardDismissingRelativeLayout(Context context) {
        super(context);
    }

    public KeyboardDismissingRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public KeyboardDismissingRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public KeyboardDismissingRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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