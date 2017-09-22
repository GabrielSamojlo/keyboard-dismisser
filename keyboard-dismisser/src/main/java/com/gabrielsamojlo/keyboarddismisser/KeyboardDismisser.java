package com.gabrielsamojlo.keyboarddismisser;

import android.app.Activity;

import android.support.constraint.ConstraintLayout;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.gabrielsamojlo.keyboarddismisser.dismissinglayouts.KeyboardDismissingConstraintLayout;
import com.gabrielsamojlo.keyboarddismisser.dismissinglayouts.KeyboardDismissingCoordinatorLayout;
import com.gabrielsamojlo.keyboarddismisser.dismissinglayouts.KeyboardDismissingLinearLayout;
import com.gabrielsamojlo.keyboarddismisser.dismissinglayouts.KeyboardDismissingRelativeLayout;

public class KeyboardDismisser {

    private static String[] sSupportedClasses = new String[] {
            "LinearLayout",
            "RelativeLayout",
            "CoordinatorLayout",
            "ConstraintLayout"
    };

    public static void useWith(Fragment fragment) {
        ViewGroup viewGroup = (ViewGroup) fragment.getView();

        swapMainLayoutWithDismissingLayout(viewGroup, fragment.getActivity());
    }

    public static void useWith(Activity activity) {
        FrameLayout content = (FrameLayout) activity.findViewById(android.R.id.content);
        ViewGroup viewGroup = (ViewGroup) content.getChildAt(0);

        swapMainLayoutWithDismissingLayout(viewGroup, activity);
    }

    private static void swapMainLayoutWithDismissingLayout(ViewGroup viewGroup, Activity activity) {
        if (viewGroup == null) {
            return;
        }

        String className = "";

        String viewGroupClassName = viewGroup.getClass().getSimpleName();
        for (String name : sSupportedClasses) {
            if (viewGroupClassName.equals(name)) {
                className = name;
            }
        }

        ViewGroup generatedLayout = viewGroup;

        switch (className) {
            case "LinearLayout":
                generatedLayout = new KeyboardDismissingLinearLayout(activity);
                ((KeyboardDismissingLinearLayout) generatedLayout).setActivity(activity);
                break;
            case "RelativeLayout":
                generatedLayout = new KeyboardDismissingRelativeLayout(activity);
                ((KeyboardDismissingRelativeLayout) generatedLayout).setActivity(activity);
                break;
            case "CoordinatorLayout":
                generatedLayout = new KeyboardDismissingCoordinatorLayout(activity);
                ((KeyboardDismissingCoordinatorLayout) generatedLayout).setActivity(activity);
                break;
            case "ConstraintLayout":
                generatedLayout = new KeyboardDismissingConstraintLayout(activity);
                ((KeyboardDismissingConstraintLayout) generatedLayout).setActivity(activity);
        }

        if (className.isEmpty()) {
            return;
        }

        if (viewGroup.getLayoutParams() != null) {
            generatedLayout.setLayoutParams(viewGroup.getLayoutParams());
        }

        if (generatedLayout instanceof KeyboardDismissingConstraintLayout) {
            int widthOfOriginalLayout = viewGroup.getLayoutParams().width;
            int heightOfOriginalLayout = viewGroup.getLayoutParams().height;

            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(widthOfOriginalLayout, heightOfOriginalLayout);
            layoutParams.validate();

            generatedLayout.setLayoutParams(layoutParams);
        }

        while (viewGroup.getChildCount() != 0) {
            View child = viewGroup.getChildAt(0);

            viewGroup.removeViewAt(0);
            generatedLayout.addView(child);
        }

        viewGroup.removeAllViews();
        viewGroup.addView(generatedLayout, 0);
    }
}