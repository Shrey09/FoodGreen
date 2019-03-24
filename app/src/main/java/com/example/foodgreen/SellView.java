package com.example.foodgreen;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SellView extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    ListView food;

    // create arraylist to store dynamically
    ArrayList<String> dish_name_array = new ArrayList<String>();
    ArrayList<String> location_array = new ArrayList<String>();
    ArrayList<String> quantity_array = new ArrayList<String>();
    ArrayList<String> time_array = new ArrayList<String>();
    ArrayList<String> date_array = new ArrayList<String>();
    ArrayList<String> key_array = new ArrayList<String>();   // to store parent value. It will be passed with Intent
    String[] dish, quantity, location, time, date;

    //String[] dish={"first","Second","Third","Fourth","first","Second","Third","Fourth"};
    //String [] price={"22","23","24","25","22","23","24","25"};
    //Integer [] quantity={2,3,4,5,2,3,4,5};
    //String [] location={"qunipool","spring","sexton","spring","qunipool","spring","sexton","spring"};
    //Integer[] images={R.drawable.food,R.drawable.food2,R.drawable.food2,R.drawable.food,R.drawable.food2,R.drawable.food2,R.drawable.food,R.drawable.food2};
    ImageView homebutton, buybutton, neworderbutton, filter;
    Spinner foodcategory;
    TextView pricetext;
    SeekBar pricebar;
    Button okButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sellview);
        toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.new_bar);
        setSupportActionBar(toolbar);
        food=(ListView) findViewById(R.id.foodlist);

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

        neworderbutton = findViewById(R.id.addbtn);
        neworderbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_order_button = new Intent(v.getContext(), new_order_sell.class);
                startActivity(new_order_button);
            }
        });

        filter=findViewById(R.id.filterbtn);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog mBuilder=new AlertDialog.Builder(SellView.this).create();
                View mView =getLayoutInflater().inflate(R.layout.filterview,null);
                mBuilder.setTitle("Filter");

                foodcategory=(Spinner)mView.findViewById(R.id.foodcategory);
                pricebar=(SeekBar)mView.findViewById(R.id.pricebar);
                pricetext=(TextView) mView.findViewById(R.id.pricetext);
                okButton = (Button) mView.findViewById(R.id.okButton);
                cancelButton = (Button) mView.findViewById(R.id.cancelButton);

                pricetext.setText(String.valueOf(pricebar.getProgress()));
                pricebar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        pricetext.setText(String.valueOf(pricebar.getProgress()));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(SellView.this
                        ,android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.foodmenu));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                foodcategory.setAdapter(adapter);

                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!foodcategory.getSelectedItem().toString().equalsIgnoreCase("Select food Category"))
                        {
                            Toast.makeText(SellView.this,
                                    foodcategory.getSelectedItem().toString() + " selected",Toast.LENGTH_SHORT).show();
                            mBuilder.dismiss();
                        }
                        else {
                            mBuilder.dismiss();
                        }
                    }
                });

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBuilder.dismiss();
                    }
                });

                mBuilder.setView(mView);
                mBuilder.show();
            }
        });

        // fetching data part
        FirebaseDatabase firebaseDatabase;
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference root_ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference buy_data_open_ref = root_ref.child("buy_data_open");

        buy_data_open_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    dish_name_array.add(ds.child("data_dish_name").getValue(String.class));
                    quantity_array.add(ds.child("data_quantity").getValue(String.class));
                    time_array.add(ds.child("data_expected_time").getValue(String.class));
                    date_array.add(ds.child("data_expected_date").getValue(String.class));
                    location_array.add("Dalhousie University");
                    key_array.add(ds.getKey().toString());
                    //Log.i("Key value: ", ds.getKey().toString());   // To get parent value
                }

                int count;
                dish = new String[dish_name_array.size()];
                quantity = new String[dish_name_array.size()];
                location = new String[dish_name_array.size()];
                time = new String[dish_name_array.size()];
                date = new String[dish_name_array.size()];
                for (count=0; count < dish_name_array.size(); count++){
                    dish[count] = dish_name_array.get(count);
                    time[count] = time_array.get(count);
                    date[count] = date_array.get(count);
                    quantity[count] = quantity_array.get(count);
                    location[count] = location_array.get(count);
                }

                CustomListView customListView=new CustomListView();
                food.setAdapter(customListView);
                customListView.notifyDataSetChanged();
                // onclick event of item in listview

                food.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent confirm_bid = new Intent(view.getContext(), bid_sell.class);
                        confirm_bid.putExtra("parent_value", key_array.get(position));
                        startActivity(confirm_bid);

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent login_intent = new Intent(this, activity_login.class);
        startActivity(login_intent);
        return super.onOptionsItemSelected(item);
    }
    class CustomListView extends BaseAdapter {

        @Override
        public int getCount() {
            return dish.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view=getLayoutInflater().inflate(R.layout.sell_list_layout,null);

            TextView dishname=(TextView) view.findViewById(R.id.dishname);
            TextView quantityval=(TextView) view.findViewById(R.id.valquantity);
            TextView locationval=(TextView) view.findViewById(R.id.vallocation);
            TextView timeval = (TextView) view.findViewById(R.id.valexpectedtime);
            TextView dateval = (TextView) view.findViewById(R.id.valexpecteddate);

            //mImageView.setImageResource(images[position]);
            dishname.setText(dish[position]);
            quantityval.setText(String.valueOf(quantity[position]));
            locationval.setText(location[position]);
            timeval.setText(time[position]);
            dateval.setText(date[position]);
            return view;
        }
    }
}
