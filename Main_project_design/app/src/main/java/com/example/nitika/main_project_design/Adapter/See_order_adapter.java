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
import com.example.nitika.main_project_design.Activity.See_my_order;
import com.example.nitika.main_project_design.R;
import com.example.nitika.main_project_design.Utiles.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by NITIKA on 31-Mar-15.
 */
public class See_order_adapter extends BaseAdapter{


    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
  public   int quantity,tt;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public See_order_adapter(Context context,
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
        TextView description;
        TextView date_order;
        TextView selling_price;
        TextView brand;
      TextView total_custom_ui;
        TextView cart_quantity;
        ImageView image_path;

        int mul;
        Log.d("done", "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.see_order_custom_ui, parent, false);
        // Get the position
        resultp = data.get(position);

        // Locate the TextViews in listview_item.xml
        name = (TextView) itemView.findViewById(R.id.order_id_name_value);
       date_order = (TextView) itemView.findViewById(R.id.order_id_date_value);
        selling_price = (TextView) itemView.findViewById(R.id.order_id_selling_price_value);

        total_custom_ui=(TextView)itemView.findViewById(R.id.order_total_id_value);//to show total
        cart_quantity=(TextView)itemView.findViewById(R.id.order_quantity_value);//quantity
        // Locate the ImageView in listview_item.xml
        image_path = (ImageView) itemView.findViewById(R.id.cart_image);



        // Capture position and set results to the TextViews
        name.setText(resultp.get(Activity_main.TAG_NAME));
        date_order.setText(resultp.get(Activity_main.TAG_DATE_ORDER));
        selling_price.setText(resultp.get(Activity_main.TAG_SELL_COST));
        cart_quantity.setText(resultp.get(Activity_main.TAG_QUANTITY));
        total_custom_ui.setText(resultp.get(Activity_main.TAG_TOTAL));
        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
        imageLoader.DisplayImage(resultp.get(Activity_main.TAG_IMAGE_PATH), image_path);

        // Capture ListView item click
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position

             /*   resultp = data.get(position);
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
                // Start SingleItemView Class
                context.startActivity(intent);
*/
            }
        });
        return itemView;

    }
}
