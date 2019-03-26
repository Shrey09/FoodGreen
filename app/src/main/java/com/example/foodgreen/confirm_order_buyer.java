package com.example.foodgreen;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class confirm_order_buyer extends AppCompatActivity {

    TextView name,contact,address;
    ImageView homeButton, sellButton;
    ImageView dishimage;
    Button buy;
    String image_name;
    String buyercontactNo,sellercontactNo;
    String buyermessage,sellermessage;
    TextView show_dish_name, show_cook_date, show_cook_time, show_expire_date, show_expire_time, show_dish_description,
        show_dish_price, show_dish_quantity, show_food_category;   // To show values in textview
    String parent_value;   // To store intent's passed data
    android.support.v7.widget.Toolbar toolbar;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =1 ;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    DatabaseReference root_ref = FirebaseDatabase.getInstance().getReference();
    DatabaseReference sell_data_ref = root_ref.child("sell_data_open");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_confim_buyer);
        toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.new_bar);
        setSupportActionBar(toolbar);
        name=(TextView)findViewById(R.id.sellername);
        contact=(TextView)findViewById(R.id.sellercontact);
        address=(TextView)findViewById(R.id.selleraddress);
        buy=(Button) findViewById(R.id.buybutton);
        dishimage=(ImageView)findViewById(R.id.orderimage);
        show_dish_name = findViewById(R.id.dishname);
        show_cook_date = findViewById(R.id.show_cook_date);
        show_cook_time = findViewById(R.id.show_cook_time);
        show_expire_date = findViewById(R.id.show_expire_date);
        show_expire_time = findViewById(R.id.show_expire_time);
        show_dish_description = findViewById(R.id.orderdescription);
        show_dish_price = findViewById(R.id.show_price);
        show_dish_quantity = findViewById(R.id.show_quantity);
        show_food_category = findViewById(R.id.food_category);

        Intent intent = getIntent();
        parent_value = intent.getStringExtra("parent_value");
        DatabaseReference get_order_data_ref = sell_data_ref.child(parent_value);
        get_order_data_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                show_dish_name.setText(dataSnapshot.child("data_dish_name").getValue(String.class));
                show_cook_date.setText(dataSnapshot.child("data_cook_date").getValue(String.class));
                show_cook_time.setText(dataSnapshot.child("data_cook_time").getValue(String.class));
                show_expire_date.setText(dataSnapshot.child("data_expire_date").getValue(String.class));
                show_expire_time.setText(dataSnapshot.child("data_expire_time").getValue(String.class));
                show_dish_description.setText(dataSnapshot.child("data_dish_description").getValue(String.class));
                show_dish_price.setText(dataSnapshot.child("data_dish_price").getValue(String.class));
                show_dish_quantity.setText(dataSnapshot.child("data_dish_quantity").getValue(String.class));
                show_food_category.setText(dataSnapshot.child("food_category").getValue(String.class));
                image_name = dataSnapshot.child("image_name").getValue(String.class);
                Picasso.get().load("http://foodgreen.000webhostapp.com/images/" + image_name).into(dishimage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        homeButton = findViewById(R.id.homebtn);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home_intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(home_intent);
            }
        });

        sellButton = findViewById(R.id.sellbtn);
        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sell_intent = new Intent(v.getContext(), SellView.class);
                startActivity(sell_intent);
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendConfirmationMessage();
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(buyercontactNo, null, buyermessage, null, null);
                smsManager.sendTextMessage(sellercontactNo, null, sellermessage, null, null);
                Toast.makeText(getApplicationContext(), "Order Confimed",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
    protected void sendConfirmationMessage(){
        buyercontactNo="9028099647";
        sellercontactNo=contact.getText().toString();
        buyermessage= "Your Order is confirmed and deatils of the seller are\n" +
                "Name : "+name.getText().toString()+"\n" +
                "Address :"+address.getText().toString()+"\n"+
                "Contact :"+sellercontactNo+"\n"+"Thanks for ordering";
        sellermessage="Your dish is sold and details of buyer are\n" +
                "Name : "+"Shrey"+"\n" +
                "Address :"+"qunipool towers"+"\n"+
                "Contact :"+buyercontactNo;
        Log.i("message",buyermessage);
        Log.i("message",sellermessage);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(ContextCompat.checkSelfPermission(this,
                            Manifest.permission.SEND_SMS)==PackageManager.PERMISSION_GRANTED){

                    }

                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                }
            }
        }

    }


}