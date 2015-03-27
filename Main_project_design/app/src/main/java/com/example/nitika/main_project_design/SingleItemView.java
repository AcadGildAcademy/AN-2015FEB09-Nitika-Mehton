package com.example.nitika.main_project_design;

/**
 * Created by NITIKA on 19-Mar-15.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleItemView extends Activity{
    // Declare Variables
    String name;
    String id;
    String description;
    String image_path;
    String selling_price;
    String cost_price;
    String brand;
    String status;

    String position;
    ImageLoader imageLoader = new ImageLoader(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.singleitemview);

        Intent i = getIntent();
        // Get the result of name
        name = i.getStringExtra("name");
        // Get the result of id
        id = i.getStringExtra("id");
        // Get the result of description
        description = i.getStringExtra("description");
        // Get the result of image
        image_path = i.getStringExtra("image_path");
        //Get the result of selling price
        selling_price =i.getStringExtra("selling_price");
        //Get the result of cost price
        cost_price =i.getStringExtra("cost_price");
        //Get the result of cost price
        brand =i.getStringExtra("brand");

        // Locate the TextViews in singleitemview.xml
        TextView txtname = (TextView) findViewById(R.id.id_name_value);
        TextView txtcost_price = (TextView) findViewById(R.id.id_cost_price_value);
        TextView txtselling_price = (TextView) findViewById(R.id.id_selling_price_value);
        TextView txtbrand=(TextView)findViewById(R.id.id_brand_value);
        TextView txtdescription=(TextView)findViewById(R.id.id_description_value);
        // Locate the ImageView in singleitemview.xml
        ImageView imgflag = (ImageView) findViewById(R.id.image);

        // Set results to the TextViews
        txtname.setText(name);
        txtcost_price.setText(cost_price);
        txtselling_price.setText(selling_price);
        txtbrand.setText(brand);
        txtdescription.setText(description);

        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
        imageLoader.DisplayImage(image_path, imgflag);
    }
}
