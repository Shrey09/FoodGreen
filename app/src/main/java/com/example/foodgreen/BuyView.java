package com.example.foodgreen;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BuyView extends AppCompatActivity {

    ListView food;
    ArrayList<String> dish_name_array = new ArrayList<String>();
    ArrayList<String> price_array = new ArrayList<String>();
    ArrayList<String> quantity_array = new ArrayList<String>();
    ArrayList<String> location_array = new ArrayList<String>();
    ArrayList<String> images_array = new ArrayList<String>();
    ArrayList<String> key_array = new ArrayList<String>();   // To store parent key values
    String[] dish, price, quantity, location, images;

    android.support.v7.widget.Toolbar toolbar;
    ImageView homeButton, sellButton, neworderbutton, filter;
    Spinner foodcategory;
    TextView pricetext;
    SeekBar pricebar;
    Button okButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyview);
        food=(ListView) findViewById(R.id.foodlist);
        toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.new_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("FoodGreen");

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
        neworderbutton = findViewById(R.id.addbtn);
        neworderbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_order_button = new Intent(v.getContext(), Activity_buy.class);
                startActivity(new_order_button);
            }
        });

        filter = findViewById(R.id.filterbtn);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog mBuilder = new AlertDialog.Builder(BuyView.this).create();
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
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(BuyView.this
                        ,android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.foodmenu));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                foodcategory.setAdapter(adapter);

                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!foodcategory.getSelectedItem().toString().equalsIgnoreCase("Select food Category"))
                        {
                            Toast.makeText(BuyView.this,
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



        FirebaseDatabase firebaseDatabase;
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        DatabaseReference root_ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference sell_data_open_ref = root_ref.child("sell_data_open");

        sell_data_open_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("Test", "INSIDE sell data");
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    dish_name_array.add(ds.child("data_dish_name").getValue(String.class));
                    price_array.add(ds.child("data_dish_price").getValue(String.class));
                    quantity_array.add(ds.child("data_dish_quantity").getValue(String.class));
                    images_array.add(ds.child("image_name").getValue(String.class));
                    location_array.add("Dalhousie University");
                    key_array.add(ds.getKey().toString());
                }

                int count;
                dish = new String[dish_name_array.size()];
                price = new String[dish_name_array.size()];
                quantity = new String[dish_name_array.size()];
                location = new String[dish_name_array.size()];
                images = new String[dish_name_array.size()];

                Log.i("Count: ", Integer.toString(dish_name_array.size()));
                for (count=0; count < dish_name_array.size(); count++){
                    dish[count] = dish_name_array.get(count);
                    price[count] = price_array.get(count);
                    quantity[count] = quantity_array.get(count);
                    location[count] = location_array.get(count);
                    images[count] = images_array.get(count);
                }
                CustomListView customListView=new CustomListView();
                food.setAdapter(customListView);
                customListView.notifyDataSetChanged();

                // onclick event of item in listview

                food.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent confirm_buy = new Intent(view.getContext(), confirm_order_buyer.class);
                        confirm_buy.putExtra("parent_value", key_array.get(position));
                        startActivity(confirm_buy);

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void openDialog(){

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
    class CustomListView extends BaseAdapter{

        @Override
        public int getCount() {
            return images.length;
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

            View view=getLayoutInflater().inflate(R.layout.buy_list_layout,null);
            
            ImageView mImageView=(ImageView)view.findViewById(R.id.imageview);
            TextView  dishname=(TextView) view.findViewById(R.id.dishname);
            TextView  priceval=(TextView) view.findViewById(R.id.valprice);
            TextView  quantityval=(TextView) view.findViewById(R.id.valquantity);
            TextView  locationval=(TextView) view.findViewById(R.id.vallocation);

            Picasso.get().load("http://foodgreen.000webhostapp.com/images/" + images[position]).into(mImageView);
            dishname.setText(dish[position]);
            priceval.setText(price[position]);
            quantityval.setText(String.valueOf(quantity[position]));
            locationval.setText(location[position]);
            return view;
        }
    }
}