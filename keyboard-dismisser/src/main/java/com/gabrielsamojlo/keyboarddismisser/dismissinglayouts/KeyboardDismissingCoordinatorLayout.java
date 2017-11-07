package com.gabrielsamojlo.keyboarddismisser.dismissinglayouts;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.gabrielsamojlo.keyboarddismisser.DismissingUtils;
import com.gabrielsamojlo.keyboarddismisser.KeyboardListener;

public class KeyboardDismissingCoordinatorLayout extends CoordinatorLayout implements KeyboardListener {

    private Activity mActivity;
    private boolean mIsKeyboardVisible;

    public KeyboardDismissingCoordinatorLayout(Context context) {
        super(context);
    }

    public KeyboardDismissingCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyboardDismissingCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean wasDispatched = super.dispatchTouchEvent(ev);

        if (!wasDispatched && mIsKeyboardVisible) {
            DismissingUtils.hideKeyboard(mActivity);
        }

        return wasDispatched;
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
