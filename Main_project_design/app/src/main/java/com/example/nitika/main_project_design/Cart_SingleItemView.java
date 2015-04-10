package com.example.nitika.main_project_design;

/**
 * Created by NITIKA on 19-Mar-15.
 */
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cart_SingleItemView extends Activity{
    // Declare Variables
    private ProgressDialog pDialog;
    UserSessionLogin session;
    String name;
    String id;
    String description;
    String image_path;
    String selling_price;
    String cost_price;
    String brand;
    String status;
    Button add_to_cart;
    String total;
    String quantity;
    int get_post;
    private static String url = "http://bishasha.com/json/whdeal_RemoveFromCart.php";
    private static String update_url = "http://bishasha.com/json/whdeal_UpDateCartQuantity.php";
    ImageLoader imageLoader = new ImageLoader(this);
    private static final String TAG_SUCCESS = "success";
    Button total_btn;
   EditText txtquantity;
    TextView cart_tot_value;
    int total_seeling_cost;
    String text;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.cart_singleitemview);
        add_to_cart =(Button)findViewById(R.id.cart_id_button_add_cart);
      total_btn=(Button)findViewById(R.id.cart_total_btn);
        txtquantity=(EditText)findViewById(R.id.cart_quantity_value_singleView);//for quanity
        cart_tot_value=(TextView)findViewById(R.id.cart_total_value);
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
         quantity=i.getStringExtra("quantity");
       total=i.getStringExtra("total");
        // Locate the TextViews in singleitemview.xml
        TextView txtname = (TextView) findViewById(R.id.cart_id_name_value);
        TextView txtcost_price = (TextView) findViewById(R.id.cart_id_cost_price_value);
        TextView txtselling_price = (TextView) findViewById(R.id.cart_id_selling_price_value);
        TextView txtbrand=(TextView)findViewById(R.id.cart_id_brand_value);
        TextView txtdescription=(TextView)findViewById(R.id.cart_id_description_value);

        // Locate the ImageView in singleitemview.xml
        ImageView imgflag = (ImageView) findViewById(R.id.cart_image);
      Toast.makeText(getApplication(),id,Toast.LENGTH_LONG).show();
        // Set results to the TextViews
        txtname.setText(name);
        txtcost_price.setText(cost_price);
        txtselling_price.setText(selling_price);
        txtbrand.setText(brand);
        txtdescription.setText(description);
        txtquantity.setText(quantity);
        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
        imageLoader.DisplayImage(image_path, imgflag);

        String ss=txtquantity.getText().toString();



        text = txtselling_price.getText().toString();
        //add_to_cart code
        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserSessionLogin session;

                session = new UserSessionLogin(getApplicationContext());
                HashMap<String, String> user = session.getUserDetails();
                // get name

                get_post = 1;
                new GetContacts().execute();


            }
        });
        //update quantity
       total_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               get_post=2;

                if(text.matches("\\d+")) //check if only digits. Could also be text.matches("[0-9]+")
                {
                    String ss=txtquantity.getText().toString();
                    total_seeling_cost = Integer.parseInt(text)*Integer.parseInt(ss);
                    cart_tot_value.setText(Integer.toString(total_seeling_cost).toString());
                    Log.d("$$$$", "" + cart_tot_value.toString() + "q-->" + Integer.toString(total_seeling_cost).toString());
                }
                else
                {
                    Log.d("not a valid number","");
                }
                new GetContacts().execute();
                String ss=txtquantity.getText().toString();

                Toast.makeText(getApplicationContext(),ss,Toast.LENGTH_SHORT).show();
Toast.makeText(getApplicationContext(),"update",Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * Async task class to get json by making HTTP call
     * */

    private class GetContacts extends AsyncTask<Void,Integer, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Cart_SingleItemView.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            int success;
            UserSessionLogin session;
if(get_post==1)
{
    Log.d("get_post==1", "starting");
            session = new UserSessionLogin(getApplicationContext());
            HashMap<String, String> user = session.getUserDetails();
            String user_email= (user.get(UserSessionLogin.KEY_EMAIL_SESSION)).toString();
            String product_id = id.toString();

                 try {
                     // Building Parameters
                     List params = new ArrayList();
                     params.add(new BasicNameValuePair("user_email", user_email));
                     params.add(new BasicNameValuePair("product_id", product_id));

                     ServiceHandler sh = new ServiceHandler();

                     // getting product details by making HTTP request
                     String jsonStr = sh.makeServiceCall(url, 1, params);//1 for GET

                JSONObject json = new JSONObject(jsonStr);
                // check your log for json response
                Log.d("store to cart attempt", json.toString());
                // json success tag
                success = json.getInt(TAG_SUCCESS);
                Log.d("success",""+success);
                publishProgress(success);
            }catch(JSONException e){
                e.printStackTrace();
            }

} else if(get_post==2)
{
    Log.d("get_post==2", "starting");
    session = new UserSessionLogin(getApplicationContext());
    HashMap<String, String> user = session.getUserDetails();
    String user_email= (user.get(UserSessionLogin.KEY_EMAIL_SESSION)).toString();
    String product_id = id.toString();
    String quan=  txtquantity.getText().toString();
String tt=Integer.toString(total_seeling_cost).toString();
    Log.d("tt-->",tt);
    try {
        // Building Parameters
        List params = new ArrayList();
        params.add(new BasicNameValuePair("user_email", user_email));
        params.add(new BasicNameValuePair("product_id",product_id));
        params.add(new BasicNameValuePair("quantity",quan));
        params.add(new BasicNameValuePair("total",tt));
        ServiceHandler sh = new ServiceHandler();
        Log.d("request!", "starting");
        // getting product details by making HTTP request
        String jsonStr = sh.makeServiceCall(update_url, 2, params);//1 for GET 2 for post
        JSONObject json = new JSONObject(jsonStr);
        // check your log for json response
        Log.d("update attempt", json.toString());
        // json success tag
        success = json.getInt(TAG_SUCCESS);
        Log.d("success",""+success);
        publishProgress(success);
    }catch(JSONException e){
        e.printStackTrace();
    }

}
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
        }


        //Handling updation to your UI
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int  success=values[0];
            if (success == 1) {
                if(get_post==1) {
                    Log.d("remove from cart !", " Success");
                    Toast.makeText(getApplicationContext(), "Product is removed to cart", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Cart_SingleItemView.this, Cart_item.class);//temp
                    finish();
                    startActivity(i);
                }
                else if(get_post==2)
                {

                    String ss=txtquantity.getText().toString();


                    if(text.matches("\\d+")) //check if only digits. Could also be text.matches("[0-9]+")
                    {

                        total_seeling_cost = Integer.parseInt(text)*Integer.parseInt(ss);
                        cart_tot_value.setText(Integer.toString(total_seeling_cost).toString());
                        Log.d("$$$$", "" + cart_tot_value + "pp-->" + text);
                    }
                    else
                    {
                        //System.out.println("not a valid number");
                    }
                     Toast.makeText(getApplicationContext(), "Product Quantity is Updated", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Cart_SingleItemView.this, Cart_item.class);//temp
                     finish();
                     startActivity(i);

                }
            }
            else {
                Log.d("Remove from cart  Failure!"," fail");
                Toast.makeText(getApplicationContext(),"Slow Internet",Toast.LENGTH_LONG).show();
                // return json.getString(TAG_MESSAGE);
            }
        }
    }


}
