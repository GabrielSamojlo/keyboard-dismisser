package com.gabrielsamojlo.keyboarddismisser;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.gabrielsamojlo.keyboarddismisser.dismissinglayouts.KeyboardDismissingCoordinatorLayout;
import com.gabrielsamojlo.keyboarddismisser.dismissinglayouts.KeyboardDismissingLinearLayout;
import com.gabrielsamojlo.keyboarddismisser.dismissinglayouts.KeyboardDismissingRelativeLayout;

public class KeyboardDismisser {

    private static String[] sSupportedLayouts = new String[] {"LinearLayout", "RelativeLayout", "CoordinatorLayout"};

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
        for (String name : sSupportedLayouts) {
            if (viewGroupClassName.equals(name)) {
                className = name;
            }
        }

        ViewGroup generatedLayout = viewGroup;

        switch (className) {
            case "LinearLayout":
                generatedLayout = new KeyboardDismissingLinearLayout(activity);
                ((LinearLayout) generatedLayout).setOrientation(((LinearLayout) viewGroup).getOrientation());
                ((KeyboardDismissingLinearLayout) generatedLayout).setActivity(activity);
                break;
            case "RelativeLayout":
                generatedLayout = new KeyboardDismissingRelativeLayout(activity);
                ((KeyboardDismissingRelativeLayout) generatedLayout).setActivity(activity);
                break;
            case "CoordinatorLayout":
                generatedLayout = new KeyboardDismissingCoordinatorLayout(activity);
                ((KeyboardDismissingCoordinatorLayout) generatedLayout).setActivity(activity);
        }

        if (className.isEmpty()) {
            return;
        }

        generatedLayout.setLayoutParams(viewGroup.getLayoutParams());

        while (viewGroup.getChildCount() != 0) {
            View child = viewGroup.getChildAt(0);

            viewGroup.removeViewAt(0);
            generatedLayout.addView(child);
        }

        viewGroup.removeAllViews();
        viewGroup.addView(generatedLayout, 0);
    }
}