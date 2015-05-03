package com.example.nitika.main_project_design.Activity;

/**
 * Created by NITIKA on 19-Mar-15.
 */
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nitika.main_project_design.R;
import com.example.nitika.main_project_design.Utiles.UserSessionLogin;
import com.example.nitika.main_project_design.Utiles.ImageLoader;
import com.example.nitika.main_project_design.Utiles.ServiceHandler;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SingleItemView extends Activity
{
    // Declare Variables
    private ProgressDialog pDialog;

    String name;
    String id;
    String description;
    String image_path;
    String selling_price;
    String cost_price;
    String brand;
    String total_pro;
    Button add_to_cart,buy_now;
    String position;
    EditText qq;
    TextView total_qq;
    private static String url = "http://bishasha.com/json/whdeal_AddToCart.php";
    ImageLoader imageLoader = new ImageLoader(this);
    private static final String TAG_SUCCESS = "success";
    int total_s;
  String  text ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.singleitemview);
        add_to_cart =(Button)findViewById(R.id.id_button_add_cart);
        buy_now=(Button)findViewById(R.id.id_button_buy_now);
        total_qq=(TextView)findViewById(R.id.id_total_qq);
        qq=(EditText)findViewById(R.id.id_qq);
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
        total_pro=i.getStringExtra("total_product");
        // Locate the TextViews in singleitemview.xml
        final TextView txtname = (TextView) findViewById(R.id.id_name_value);
        TextView txtcost_price = (TextView) findViewById(R.id.id_cost_price_value);
        TextView txtselling_price = (TextView) findViewById(R.id.id_selling_price_value);
        TextView txtbrand=(TextView)findViewById(R.id.id_brand_value);
        TextView txtdescription=(TextView)findViewById(R.id.id_description_value);
        TextView stock=(TextView)findViewById(R.id.id_stock);
        // Locate the ImageView in singleitemview.xml
        final ImageView imgflag = (ImageView) findViewById(R.id.image);



        // Set results to the TextViews
        txtname.setText(name);
        txtcost_price.setText(cost_price);
        txtselling_price.setText(selling_price);
        txtbrand.setText(brand);
        txtdescription.setText(description);

        // Capture position and set results to the ImageView
        // Passes flag images URL into ImageLoader.class
        imageLoader.DisplayImage(image_path, imgflag);


        String ss="Out Of Stock";
        if(total_pro.equals(("0")))
        {
            Log.d("total","0");
            stock.setText(ss);
            buy_now.setEnabled(false);

        }

imgflag.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(SingleItemView.this, image.class);
        intent.putExtra("image", image_path);
        startActivity(intent);

    }
});
        text = txtselling_price.getText().toString();
        //add_to_cart code
        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                UserSessionLogin session;

                session = new UserSessionLogin(getApplicationContext());
                HashMap<String, String> user = session.getUserDetails();
                // get name
                if( session.isUserLoggedIn()) {
                    String user_str = user.get(UserSessionLogin.KEY_EMAIL_SESSION);
                  //  Toast.makeText(getApplicationContext(),user_str,Toast.LENGTH_LONG).show();

                    new GetContacts().execute();

                }
               else
                {
                    Toast.makeText(getApplicationContext(),"Please Login First",Toast.LENGTH_LONG).show();
                }

                }
            });

        buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
int check_quantity= Integer.parseInt(qq.getText().toString());

                if (check_quantity<1)
                {
                    Toast.makeText(getApplicationContext(),"Enter valid quantity",Toast.LENGTH_LONG).show();
                }
                else {
                    UserSessionLogin session;

                    session = new UserSessionLogin(getApplicationContext());
                    HashMap<String, String> user = session.getUserDetails();
                    // get name
                    if (session.isUserLoggedIn()) {
                        String user_str = user.get(UserSessionLogin.KEY_EMAIL_SESSION);
                        //  Toast.makeText(getApplicationContext(),user_str,Toast.LENGTH_LONG).show();
                        Log.d("userlogin", "" + user_str);
                        if (text.matches("\\d+")) //check if only digits. Could also be text.matches("[0-9]+")
                        {
                            String ss = qq.getText().toString();
                            total_s = Integer.parseInt(text) * Integer.parseInt(ss);
                            total_qq.setText(Integer.toString(total_s).toString());
                            Log.d("$$$$", "" + total_qq.toString() + "q-->" + Integer.toString(total_s).toString());


                        } else {
                            Toast.makeText(getApplicationContext(), "connect to internet", Toast.LENGTH_LONG).show();
                            Log.d("not a valid number", "");
                        }
                        String ss = qq.getText().toString();
                        String uu = total_qq.getText().toString();
                        String nn = txtname.getText().toString();

                        Log.d("uu", "" + uu);
                        Intent intent = new Intent(SingleItemView.this, DEMOcart_User_Profile_single_buy.class);
                        intent.putExtra("product_name", nn);
                        intent.putExtra("quantity", ss);
                        intent.putExtra("grand_total", uu);
                        intent.putExtra("user_email", user_str);
                        intent.putExtra("product_id", id);
                        intent.putExtra("total_product", total_pro);
                        intent.putExtra("price", text);
                        startActivity(intent);


                    } else {
                        Toast.makeText(getApplicationContext(), "Please Login First", Toast.LENGTH_LONG).show();
                    }

                }
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
            pDialog = new ProgressDialog(SingleItemView.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            int success;
            UserSessionLogin session;

            session = new UserSessionLogin(getApplicationContext());
            HashMap<String, String> user = session.getUserDetails();
            String user_email= (user.get(UserSessionLogin.KEY_EMAIL_SESSION)).toString();
            String product_id = id.toString();
             String total=selling_price.toString();
            String quantity="1";
                 try {
                // Building Parameters
                List params = new ArrayList();
                params.add(new BasicNameValuePair("user_email", user_email));
                params.add(new BasicNameValuePair("product_id",product_id));
                params.add(new BasicNameValuePair("quantity",quantity));
                params.add(new BasicNameValuePair("total",total));

                ServiceHandler sh = new ServiceHandler();
                Log.d("request!", "starting");
                // getting product details by making HTTP request
                String jsonStr = sh.makeServiceCall(url, 2, params);//2 for POST
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
                Log.d("add to cart !", " Success");
                Toast.makeText(getApplicationContext(),"Product is added to cart",Toast.LENGTH_LONG).show();
              //  Intent i = new Intent(SingleItemView.this,Activity_main.class);//temp
               // finish();
               // startActivity(i);
                //  return json.getString(TAG_MESSAGE);
            } else {
                Log.d("add to Failure!"," fail");
                Toast.makeText(getApplicationContext(),"Already in Cart",Toast.LENGTH_LONG).show();
                // return json.getString(TAG_MESSAGE);
            }
        }
    }


}
