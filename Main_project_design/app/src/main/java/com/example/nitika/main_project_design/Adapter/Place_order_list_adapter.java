package com.example.nitika.main_project_design.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nitika.main_project_design.Activity.Activity_main;
import com.example.nitika.main_project_design.Activity.Cart_item;
import com.example.nitika.main_project_design.R;
import com.example.nitika.main_project_design.Utiles.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by NITIKA on 31-Mar-15.
 */
public class Place_order_list_adapter extends BaseAdapter{


    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
  public   int quantity,tt;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public Place_order_list_adapter(Context context,
                                    ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
        imageLoader = new ImageLoader(context);
    }



    public int getCount() {
        return data.size();
    }


    public Object getItem(int position) {
        return null;
    }


    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        // Declare Variables
        TextView name;

        TextView price;
        TextView quantity;

String total_product,quantity_order;
        int mul;
    //    Log.d("done", "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.place_order, parent, false);
        // Get the position
        resultp = data.get(position);

        // Locate the TextViews in listview_item.xml
        name = (TextView) itemView.findViewById(R.id.place_product_name);
        price = (TextView) itemView.findViewById(R.id.place_product_price);
        quantity = (TextView) itemView.findViewById(R.id.place_product_quantity);




        // Capture position and set results to the TextViews
        name.setText(resultp.get(Activity_main.TAG_NAME));
        price.setText(resultp.get(Activity_main.TAG_SELL_COST));
        quantity.setText(resultp.get(Activity_main.TAG_QUANTITY));
        total_product=resultp.get(Activity_main.TAG_TOTAL_PRODUCT);
        quantity_order=resultp.get(Activity_main.TAG_QUANTITY);
        Log.d("quantity-->",quantity_order);
        Log.d("total-->",total_product);
        int color = Color.red(255);
        int co =Color.argb(255, 255, 1, 1);
        if(Integer.parseInt(quantity_order)<=Integer.parseInt(total_product)) {
            Log.d(" palced ordre","");
        }
        else{
            Log.d("canot palced ordre","");
            itemView.setBackgroundColor(co);
        }

        return itemView;

    }
}
