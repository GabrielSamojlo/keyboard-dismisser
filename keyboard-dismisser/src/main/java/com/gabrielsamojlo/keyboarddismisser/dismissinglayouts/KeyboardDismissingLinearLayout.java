package com.gabrielsamojlo.keyboarddismisser.dismissinglayouts;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.gabrielsamojlo.keyboarddismisser.DismissingUtils;
import com.gabrielsamojlo.keyboarddismisser.KeyboardListener;

public class KeyboardDismissingLinearLayout extends LinearLayout implements KeyboardListener {

    private Activity mActivity;
    private boolean mIsKeyboardVisible;

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
            DismissingUtils.hideKeyboard(mActivity);
        }

        return super.dispatchTouchEvent(ev);
    }

    private void setupKeyboardListener() {
        DismissingUtils.setupKeyboardListener(mActivity, this);
    }

    public void setActivity(Activity activity) {
        mActivity = activity;
        setupKeyboardListener();
    }

    @Override
    public void onKeyboardVisibilityChanged(boolean isVisible) {
        mIsKeyboardVisible = isVisible;
    }
}
