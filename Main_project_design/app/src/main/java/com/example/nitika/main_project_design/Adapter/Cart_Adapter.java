package com.example.nitika.main_project_design.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nitika.main_project_design.Activity.Activity_main;
import com.example.nitika.main_project_design.Activity.Cart_SingleItemView;
import com.example.nitika.main_project_design.Activity.Cart_item;
import com.example.nitika.main_project_design.Utiles.ImageLoader;
import com.example.nitika.main_project_design.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by NITIKA on 31-Mar-15.
 */
public class Cart_Adapter  extends BaseAdapter{


    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
  public   int quantity,tt;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public Cart_Adapter(Context context,
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
      TextView total_custom_ui;
        TextView cart_quantity;
        ImageView image_path;
String total_product;
        int mul;
        Log.d("done", "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.cart_list_custom, parent, false);
        // Get the position
        resultp = data.get(position);

        // Locate the TextViews in listview_item.xml
        name = (TextView) itemView.findViewById(R.id.cart_id_name_value);
        cost_price = (TextView) itemView.findViewById(R.id.cart_id_cost_price_value);
        selling_price = (TextView) itemView.findViewById(R.id.cart_id_selling_price_value);

        total_custom_ui=(TextView)itemView.findViewById(R.id.cart_total_id_value);//to show total
        cart_quantity=(TextView)itemView.findViewById(R.id.cart_quantity_value);//quantity
        // Locate the ImageView in listview_item.xml
        image_path = (ImageView) itemView.findViewById(R.id.cart_image);
        stock=(TextView)itemView.findViewById(R.id.id_stock);



        // Capture position and set results to the TextViews
        name.setText(resultp.get(Activity_main.TAG_NAME));
        cost_price.setText(resultp.get(Activity_main.TAG_COST_PRICE));
        selling_price.setText(resultp.get(Activity_main.TAG_SELL_COST));
        cart_quantity.setText(resultp.get(Activity_main.TAG_QUANTITY));
        total_custom_ui.setText(resultp.get(Activity_main.TAG_TOTAL));
        total_product=resultp.get(Activity_main.TAG_TOTAL_PRODUCT);
        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
        imageLoader.DisplayImage(resultp.get(Activity_main.TAG_IMAGE_PATH), image_path);
        String ss="Out Of Stock";
        if(total_product.equals("0"))
        {
            stock.setText(ss);
        }

        // Capture ListView item click
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position

                resultp = data.get(position);
                Intent intent = new Intent(context, Cart_SingleItemView.class);
                // Pass all data
                intent.putExtra("id", resultp.get(Activity_main.TAG_ID));
                intent.putExtra("name", resultp.get(Activity_main.TAG_NAME));
                intent.putExtra("description",resultp.get(Activity_main.TAG_DESCRIPTION));
                intent.putExtra("image_path", resultp.get(Activity_main.TAG_IMAGE_PATH));
                intent.putExtra("cost_price", resultp.get(Activity_main.TAG_COST_PRICE));
                intent.putExtra("selling_price",resultp.get(Activity_main.TAG_SELL_COST));
                intent.putExtra("brand",resultp.get(Activity_main.TAG_BRAND));
                 intent.putExtra("quantity",resultp.get(Activity_main.TAG_QUANTITY));
                    intent.putExtra("total",resultp.get(Activity_main.TAG_TOTAL));
                intent.putExtra("total_product",resultp.get(Activity_main.TAG_TOTAL_PRODUCT));
                // Start SingleItemView Class

                context.startActivity(intent);

            }
        });
        return itemView;

    }
}
