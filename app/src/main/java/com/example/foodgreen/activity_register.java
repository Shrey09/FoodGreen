package com.example.foodgreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class activity_register extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.new_bar);
        setSupportActionBar(toolbar);
    }
}
