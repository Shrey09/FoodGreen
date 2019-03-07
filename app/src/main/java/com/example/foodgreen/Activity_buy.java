package com.example.foodgreen;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Activity_buy extends AppCompatActivity implements View.OnClickListener {
    android.support.v7.widget.Toolbar toolbar;
    Button btn_expected_date, btn_expected_time;
    EditText buy_expected_date, buy_expected_time;
    int expectedHour,expectedMinute,btnexpectedYear,btnExpectedMonth,btnExpectedDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.new_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("FoodGreen");
        toolbar.setTitleTextColor(Color.BLACK);


        btn_expected_date = findViewById(R.id.btn_expected_date);
        btn_expected_time =  findViewById(R.id.btn_expected_time);
        buy_expected_date = findViewById(R.id.buy_expected_date);
        buy_expected_time =  findViewById(R.id.buy_expected_time);

        btn_expected_date.setOnClickListener(this);
        btn_expected_time.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == btn_expected_date){
            final Calendar c = Calendar.getInstance();
            btnexpectedYear = c.get(Calendar.YEAR);
            btnExpectedMonth = c.get(Calendar.MONTH);
            btnExpectedDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            buy_expected_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            Toast.makeText(Activity_buy.this,dayOfMonth + "-" + (monthOfYear + 1) + "-" + year,Toast.LENGTH_LONG).show();
                        }
                    }, btnexpectedYear, btnExpectedMonth, btnExpectedDay);
            datePickerDialog.show();

        }
        if(v == btn_expected_time){
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            expectedHour = c.get(Calendar.HOUR_OF_DAY);
            expectedMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            buy_expected_time.setText(hourOfDay + ":" + minute);
                        }
                    }, expectedHour, expectedMinute, false);
            timePickerDialog.show();


        }
    }
}
