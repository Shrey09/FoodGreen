package com.example.foodgreen;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SellView extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    ListView food;
    String[] dish={"first","Second","Third","Fourth","first","Second","Third","Fourth"};
    String [] price={"22","23","24","25","22","23","24","25"};
    Integer [] quantity={2,3,4,5,2,3,4,5};
    String [] location={"qunipool","spring","sexton","spring","qunipool","spring","sexton","spring"};
    Integer[] images={R.drawable.food,R.drawable.food2,R.drawable.food2,R.drawable.food,R.drawable.food2,R.drawable.food2,R.drawable.food,R.drawable.food2};
    ImageView homebutton, buybutton, neworderbutton;
    ImageButton filter;
    Spinner foodcategory;
    TextView pricetext;
    SeekBar pricebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sellview);
        toolbar=(android.support.v7.widget.Toolbar) findViewById(R.id.new_bar);
        setSupportActionBar(toolbar);
        food=(ListView) findViewById(R.id.foodlist);

        CustomListView customListView=new CustomListView();
        food.setAdapter(customListView);

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

                AlertDialog.Builder mBuilder=new AlertDialog.Builder(SellView.this);
                View mView =getLayoutInflater().inflate(R.layout.filterview,null);
                mBuilder.setTitle("Filter Dialog");

                foodcategory=(Spinner)mView.findViewById(R.id.foodcategory);
                pricebar=(SeekBar)mView.findViewById(R.id.pricebar);
                pricetext=(TextView) mView.findViewById(R.id.pricetext);

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

                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(!foodcategory.getSelectedItem().toString().equalsIgnoreCase("Select food Category"))
                        {
                            Toast.makeText(SellView.this,
                                    foodcategory.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });
                mBuilder.setNegativeButton("Clear", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                mBuilder.setView(mView);
                AlertDialog dialog=mBuilder.create();
                dialog.show();


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

            View view=getLayoutInflater().inflate(R.layout.sell_list_layout,null);

            ImageView mImageView=(ImageView)view.findViewById(R.id.imageview);
            TextView dishname=(TextView) view.findViewById(R.id.dishname);
            TextView  priceval=(TextView) view.findViewById(R.id.valprice);
            TextView  quantityval=(TextView) view.findViewById(R.id.valquantity);
            TextView  locationval=(TextView) view.findViewById(R.id.vallocation);

            mImageView.setImageResource(images[position]);
            dishname.setText(dish[position]);
            priceval.setText(price[position]);
            quantityval.setText(String.valueOf(quantity[position]));
            locationval.setText(location[position]);
            return view;
        }
    }
}
