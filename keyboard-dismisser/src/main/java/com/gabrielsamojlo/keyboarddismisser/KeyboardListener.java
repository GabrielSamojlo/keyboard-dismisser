package com.gabrielsamojlo.keyboarddismisser;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

public abstract class KeyboardListener implements ViewTreeObserver.OnGlobalLayoutListener {
    private View mView;

    public abstract void onKeyboardVisibilityChanged(boolean isVisible);

    @Override
    public void onGlobalLayout() {
        if (mView == null) {
            return;
        }
        Rect r = new Rect();
        mView.getWindowVisibleDisplayFrame(r);
        int screenHeight = mView.getRootView().getHeight();
        int keypadHeight = screenHeight - r.bottom;

        onKeyboardVisibilityChanged(keypadHeight > screenHeight * 0.15);
    }

    public View getView() {
        return mView;
    }

    public void setView(View mView) {
        this.mView = mView;
    }
}
