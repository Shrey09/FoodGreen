package com.example.foodgreen;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class activity_login extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;

    private TextView create_acc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);
        create_acc = findViewById(R.id.textcreate);
        toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.new_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Food Green");
        toolbar.setTitleTextColor(Color.BLACK);

        create_acc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_login.this, activity_register.class);
                startActivity(intent);
            }

        });
    }

}
