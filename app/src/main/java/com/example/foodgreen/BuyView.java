package com.example.foodgreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class BuyView extends AppCompatActivity {

    ListView food;
    String[] dish={"first","Second","Third"};
    String [] price={"22","23","24"};
    Integer [] quantity={2,3,4};
    String [] location={"qunipool","spring","sexton"};
    Integer[] images={R.drawable.food,R.drawable.food2,R.drawable.food3};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyview);
        food=(ListView) findViewById(R.id.foodlist);

        CustomListView customListView=new CustomListView();
        food.setAdapter(customListView);

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

            mImageView.setImageResource(images[position]);
            dishname.setText(dish[position]);
            priceval.setText(price[position]);
            quantityval.setText(String.valueOf(quantity[position]));
            locationval.setText(location[position]);
            return view;
        }
    }
}