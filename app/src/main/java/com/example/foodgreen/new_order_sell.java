package com.example.foodgreen;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class new_order_sell extends AppCompatActivity implements View.OnClickListener{

    android.support.v7.widget.Toolbar toolbar;
    Button btn_cooked_date, btn_cooked_time, btn_expire_date,btn_expire_time;
    EditText sell_cook_date, sell_cook_time, sell_expire_date, sell_expire_time;
    EditText dish_name, dish_quantity, dish_description;
    EditText dish_price;
    Button submit_order;
    int ExpireHour,ExpireMinute,btnCookedYear,btnCookedDay,btnCookedMonth,btnExpireYear,btnExpireMonth,btnExpireDay,CookHour,CookMinute;
    String data_dish_name, data_price, data_quantity, data_description, data_cook_date, data_cook_time, data_expire_date, data_expire_time;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private boolean isUserAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__sell);
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.new_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("FoodGreen");
        toolbar.setTitleTextColor(Color.BLACK);

        dish_name = findViewById(R.id.enter_dishname);
        dish_quantity = findViewById(R.id.enter_quantity);
        dish_description = findViewById(R.id.enter_description);
        dish_price = findViewById(R.id.enter_price);
        submit_order = findViewById(R.id.submit_button);

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

        submit_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit_data();
            }
        });
    }

    @Override
    public void onClick(View v) {

        if(v == btn_cooked_date){
            final Calendar c = Calendar.getInstance();
            btnCookedYear = c.get(Calendar.YEAR);
            btnCookedMonth = c.get(Calendar.MONTH);
            btnCookedDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
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
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
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


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
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
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
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

    public void submit_data(){
        data_dish_name = dish_name.getText().toString();
        data_price = dish_price.getText().toString();
        data_quantity = dish_quantity.getText().toString();
        data_description = dish_description.getText().toString();
        data_cook_time = sell_cook_time.getText().toString();
        data_cook_date = sell_cook_date.getText().toString();
        data_expire_time = sell_expire_time.getText().toString();
        data_expire_date = sell_expire_date.getText().toString();
        model_new_sell_order model = new model_new_sell_order(data_dish_name, data_price, data_quantity, data_description, data_cook_time, data_cook_date, data_expire_time, data_expire_date);
        //Log.i("flag 1", "flag 1");
        FirebaseUser userId = mAuth.getCurrentUser();
        //Log.e("user ID", "userId :: " + userId);
        //Log.i("flag 2", "flag 2");
        databaseReference.child("sell_data_open").push().setValue(model);
        //Log.i("flag 3", "flag 3");
        Toast.makeText(getApplicationContext(), "Order submitted", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(new_order_sell.this, MainActivity.class);
        startActivity(i);
    }
}
