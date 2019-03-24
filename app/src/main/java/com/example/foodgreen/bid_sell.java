package com.example.foodgreen;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class bid_sell extends AppCompatActivity {
    TextView name,contact,address;
    ImageView homebutton, buybutton;
    EditText bidvalue;
    Button bid;
    String buyercontactNo,sellercontactNo;
    String buyermessage,sellermessage;
    android.support.v7.widget.Toolbar toolbar;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sell_bid);
        name=(TextView)findViewById(R.id.buyerrname);
        contact=(TextView)findViewById(R.id.buyerrcontact);
        address=(TextView)findViewById(R.id.buyeraddress);
        bidvalue=(EditText)findViewById(R.id.etbid);
        bid=(Button) findViewById(R.id.bidbutton);

        homebutton = findViewById(R.id.homebtn);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home_button = new Intent(v.getContext(), MainActivity.class);
                startActivity(home_button);
            }
        });

        buybutton = findViewById(R.id.buybtn);
        buybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buy_button = new Intent(v.getContext(), BuyView.class);
                startActivity(buy_button);
            }
        });

        bid.setOnClickListener(new View.OnClickListener() {
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

