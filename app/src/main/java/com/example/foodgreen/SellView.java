package com.example.foodgreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SellView extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    ListView food;
    String[] dish={"first","Second","Third","Fourth","first","Second","Third","Fourth"};
    String [] price={"22","23","24","25","22","23","24","25"};
    Integer [] quantity={2,3,4,5,2,3,4,5};
    String [] location={"qunipool","spring","sexton","spring","qunipool","spring","sexton","spring"};
    Integer[] images={R.drawable.food,R.drawable.food2,R.drawable.food2,R.drawable.food,R.drawable.food2,R.drawable.food2,R.drawable.food,R.drawable.food2};
    ImageView homebutton, buybutton;

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

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
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
