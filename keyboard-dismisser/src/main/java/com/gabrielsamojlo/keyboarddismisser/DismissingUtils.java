package com.gabrielsamojlo.keyboarddismisser;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

public class DismissingUtils {

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);

        if (view == null) {
            return;
        }

        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void setupKeyboardListener(Activity activity, final KeyboardListener keyboardListener) {
        FrameLayout content = (FrameLayout) activity.findViewById(android.R.id.content);
        final View contentView = content.getChildAt(0);

        final View view = (contentView != null) ? contentView : content;

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Rect r = new Rect();
                view.getWindowVisibleDisplayFrame(r);
                int screenHeight = view.getRootView().getHeight();
                int keypadHeight = screenHeight - r.bottom;

                if (keyboardListener != null) {
                    keyboardListener.onKeyboardVisibilityChanged(keypadHeight > screenHeight * 0.15);
                }
            }
        });
    }

}
