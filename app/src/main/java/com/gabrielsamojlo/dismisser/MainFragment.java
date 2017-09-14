package com.gabrielsamojlo.dismisser;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment implements View.OnClickListener {

    private Button mRelativeBtn;
    private Button mLinearBtn;
    private Button mCoordinatorBtn;
    private Button mConstraintBtn;

    private MainActivity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mActivity = ((MainActivity) getActivity());

        mRelativeBtn = (Button) view.findViewById(R.id.relative_btn);
        mLinearBtn = (Button) view.findViewById(R.id.linear_btn);
        mCoordinatorBtn = (Button) view.findViewById(R.id.coordinator_btn);
        mConstraintBtn = (Button) view.findViewById(R.id.constraint_btn);

        mRelativeBtn.setOnClickListener(this);
        mLinearBtn.setOnClickListener(this);
        mCoordinatorBtn.setOnClickListener(this);
        mConstraintBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;

        if (view == mRelativeBtn) {
            fragment = new RelativeLayoutFragment();
        } else if (view == mLinearBtn) {
            fragment = new LinearLayoutFragment();
        } else if (view == mCoordinatorBtn) {
            fragment = new CoordinatorLayoutFragment();
        } else if (view == mConstraintBtn) {
            fragment = new ConstraintLayoutFragment();
        }

        if (fragment != null) {
            mActivity.replaceFragment(fragment);
        }
    }
}
