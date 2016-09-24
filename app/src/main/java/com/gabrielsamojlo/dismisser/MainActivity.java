package com.gabrielsamojlo.dismisser;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gabrielsamojlo.keyboarddismisser.KeyboardDismisser;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        KeyboardDismisser.useWith(this);
        addFragment(new MainFragment());
    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.content_frame, fragment).commit();
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).addToBackStack(null).commit();
    }

}
