package com.gabrielsamojlo.keyboarddismisser.dismissinglayouts;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.gabrielsamojlo.keyboarddismisser.DismissingUtils;
import com.gabrielsamojlo.keyboarddismisser.KeyboardListener;

public class KeyboardDismissingRelativeLayout extends RelativeLayout implements KeyboardListener {

    private Activity mActivity;
    private boolean mIsKeyboardVisible;

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

    public void setActivity(Activity activity) {
        mActivity = activity;
        setupKeyboardListener();
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


    @Override
    public void onKeyboardVisibilityChanged(boolean isVisible) {
        mIsKeyboardVisible = isVisible;
    }
}