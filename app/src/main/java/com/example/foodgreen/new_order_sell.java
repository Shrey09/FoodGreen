package com.example.foodgreen;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class new_order_sell extends AppCompatActivity implements View.OnClickListener{

    Button btn_cooked_date, btn_cooked_time, btn_expire_date,btn_expire_time;
    EditText sell_cook_date, sell_cook_time, sell_expire_date, sell_expire_time;
    int ExpireHour,ExpireMinute,btnCookedYear,btnCookedDay,btnCookedMonth,btnExpireYear,btnExpireMonth,btnExpireDay,CookHour,CookMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__sell);


        btn_cooked_date = findViewById(R.id.btn_cooked_date);
        btn_cooked_time = findViewById(R.id.btn_cooked_time);
        btn_expire_date = findViewById(R.id.btn_expire_date);
        btn_expire_time =  findViewById(R.id.btn_expire_time);

        sell_cook_date = findViewById(R.id.sell_cook_date);
        sell_cook_time = findViewById(R.id.sell_cook_time);
        sell_expire_date = findViewById(R.id.sell_expire_date);
        sell_expire_time  =  findViewById(R.id.sell_expire_time);

        btn_cooked_date.setOnClickListener(this);
        btn_cooked_time.setOnClickListener(this);
        btn_expire_date.setOnClickListener(this);
        btn_expire_time.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v == btn_cooked_date){
            final Calendar c = Calendar.getInstance();
            btnCookedYear = c.get(Calendar.YEAR);
            btnCookedMonth = c.get(Calendar.MONTH);
            btnCookedDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            sell_cook_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            Toast.makeText(new_order_sell.this,dayOfMonth + "-" + (monthOfYear + 1) + "-" + year,Toast.LENGTH_LONG).show();
                        }
                    }, btnCookedYear, btnCookedMonth, btnCookedDay);
            datePickerDialog.show();
        }

        if(v == btn_cooked_time){
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            CookHour = c.get(Calendar.HOUR_OF_DAY);
            CookMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            sell_cook_time.setText(hourOfDay + ":" + minute);
                        }
                    }, CookHour, CookMinute, false);
            timePickerDialog.show();


        }
        if (v == btn_expire_date){
            final Calendar c = Calendar.getInstance();
            btnExpireYear = c.get(Calendar.YEAR);
            btnExpireMonth = c.get(Calendar.MONTH);
            btnExpireDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            sell_expire_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            Toast.makeText(new_order_sell.this,dayOfMonth + "-" + (monthOfYear + 1) + "-" + year,Toast.LENGTH_LONG).show();
                        }
                    }, btnCookedYear, btnCookedMonth, btnCookedDay);
            datePickerDialog.show();

        }
        if(v == btn_expire_time){
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            ExpireHour = c.get(Calendar.HOUR_OF_DAY);
            ExpireMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            sell_expire_time.setText(hourOfDay + ":" + minute);
                        }
                    }, CookHour, CookMinute, false);
            timePickerDialog.show();


        }
    }
}
