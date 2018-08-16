package com.gabrielsamojlo.keyboarddismisser;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

public class DismissingUtils {

    public static void hideKeyboard(Activity activity) {
        if (activity == null) {
            return;
        }
        View view = activity.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);

        if (view == null || imm == null) {
            return;
        }

        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void hideKeyboard(View view) {
        hideKeyboard(getActivity(view));
    }

    public static void setupKeyboardListener(Activity activity, final KeyboardListener keyboardListener) {
        View view = getContentView(activity);

        if (view == null) {
            return;
        }

        keyboardListener.setView(view);

        view.getViewTreeObserver().addOnGlobalLayoutListener(keyboardListener);
    }

    public static void setupKeyboardListener(View view, final KeyboardListener keyboardListener) {
        setupKeyboardListener(getActivity(view), keyboardListener);
    }

    @SuppressWarnings("deprecation")
    public static void removeKeyboardListener(Activity activity, KeyboardListener keyboardListener) {
        View view = getContentView(activity);

        if (view == null) {
            return;
        }

        if (Build.VERSION.SDK_INT < 16) {
            view.getViewTreeObserver().removeGlobalOnLayoutListener(keyboardListener);
        } else {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(keyboardListener);
        }
    }

    public static void removeKeyboardListener(View view, KeyboardListener keyboardListener) {
        removeKeyboardListener(getActivity(view), keyboardListener);
    }

    private static View getContentView(Activity activity) {
        if (activity == null) {
            return null;
        }
        FrameLayout content = (FrameLayout) activity.findViewById(android.R.id.content);
        View contentView = content.getChildAt(0);

        return (contentView != null) ? contentView : content;
    }

    private static Activity getActivity(View view) {
        if (view == null) {
            return null;
        }
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

}
