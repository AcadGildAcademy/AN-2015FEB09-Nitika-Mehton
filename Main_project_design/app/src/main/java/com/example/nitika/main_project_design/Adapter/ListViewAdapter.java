package com.example.nitika.main_project_design.Adapter;

/**
 * Created by NITIKA on 19-Mar-15.
 */


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nitika.main_project_design.Activity.Activity_main;
import com.example.nitika.main_project_design.R;
import com.example.nitika.main_project_design.Activity.SingleItemView;
import com.example.nitika.main_project_design.Utiles.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewAdapter extends BaseAdapter{
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public ListViewAdapter(Context context,
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
        TextView id;
        TextView stock;
        TextView cost_price;
        TextView selling_price;
        TextView brand;

        ImageView image_path;


        Log.d("done","<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_ui, parent, false);
        // Get the position
        resultp = data.get(position);

        // Locate the TextViews in listview_item.xml
        name = (TextView) itemView.findViewById(R.id.id_name_value);
        cost_price = (TextView) itemView.findViewById(R.id.id_cost_price_value);
        selling_price = (TextView) itemView.findViewById(R.id.id_selling_price_value);
stock=(TextView)itemView.findViewById(R.id.stock);

        // Locate the ImageView in listview_item.xml
        image_path = (ImageView) itemView.findViewById(R.id.image);

        // Capture position and set results to the TextViews
        name.setText(resultp.get(Activity_main.TAG_NAME));
        cost_price.setText(resultp.get(Activity_main.TAG_COST_PRICE));
        selling_price.setText(resultp.get(Activity_main.TAG_SELL_COST));
         String tt=resultp.get(Activity_main.TAG_TOTAL_PRODUCT);
        String ss="Out Of Stock";
      if(tt.equals(("0")))
      {
         Log.d("total",tt);
          stock.setText(ss);

      }
        else
      {
          Log.d("total>>",tt);

      }

        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
        imageLoader.DisplayImage(resultp.get(Activity_main.TAG_IMAGE_PATH), image_path);

        // Capture ListView item click
        itemView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position

                resultp = data.get(position);
                Intent intent = new Intent(context, SingleItemView.class);
                // Pass all data
                intent.putExtra("id", resultp.get(Activity_main.TAG_ID));
                intent.putExtra("name", resultp.get(Activity_main.TAG_NAME));
                intent.putExtra("description",resultp.get(Activity_main.TAG_DESCRIPTION));
                intent.putExtra("image_path", resultp.get(Activity_main.TAG_IMAGE_PATH));
                intent.putExtra("cost_price", resultp.get(Activity_main.TAG_COST_PRICE));
                intent.putExtra("selling_price",resultp.get(Activity_main.TAG_SELL_COST));
                intent.putExtra("brand",resultp.get(Activity_main.TAG_BRAND));
                intent.putExtra("total_product",resultp.get(Activity_main.TAG_TOTAL_PRODUCT));


                // Start SingleItemView Class
                context.startActivity(intent);

            }
        });
        return itemView;

    }
}
