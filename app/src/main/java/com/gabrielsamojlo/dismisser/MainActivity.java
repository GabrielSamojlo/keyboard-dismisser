package com.gabrielsamojlo.dismisser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gabrielsamojlo.keyboarddismisser.KeyboardDismisser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        KeyboardDismisser.useWith(this);
    }
}
