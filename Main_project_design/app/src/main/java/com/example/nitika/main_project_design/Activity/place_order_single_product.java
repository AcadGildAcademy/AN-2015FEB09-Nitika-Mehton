package com.example.nitika.main_project_design.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nitika.main_project_design.R;
import com.example.nitika.main_project_design.Utiles.ServiceHandler;
import com.example.nitika.main_project_design.Utiles.UserSessionLogin;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by NITIKA on 14-Apr-15.
 */
public class place_order_single_product extends Activity {

TextView p_name,quanitity,price,g_total,email;
    private ProgressDialog pDialog;

    TextView txt_name,txt_price,txt_quanity;
    Button order;
    String user_email,user_name,quantity,product_name,grand_total;
    String product_id,total_product,price1;
    private static String url ="http://bishasha.com/json/whdeal_order_singleProduct.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_order_single_item);
        p_name=(TextView)findViewById(R.id._product_name);
        quanitity=(TextView)findViewById(R.id._product_quantity);
        price=(TextView)findViewById(R.id._product_price);
        g_total=(TextView)findViewById(R.id._total);
        order=(Button)findViewById(R.id.btn_order);
        email=(TextView)findViewById(R.id._email);

        Intent i = getIntent();
        grand_total=i.getStringExtra("grand_total");
        user_email = i.getStringExtra("user_email");
        user_name =i.getStringExtra("user_name");
        quantity=i.getStringExtra("quantity");
        product_name=i.getStringExtra("product_name");
        product_id=i.getStringExtra("product_id");
        total_product=i.getStringExtra("total_product");
        price1=i.getStringExtra("price");
        Toast.makeText(getApplicationContext(),user_email,Toast.LENGTH_SHORT).show();
        Log.d("emaillllll",""+user_email);

        email.setText(user_email.toString());
        p_name.setText(product_name.toString());
        quanitity.setText(quantity.toString());
        g_total.setText(grand_total.toString());
        price.setText(price1.toString());

        if(Integer.parseInt(quantity)<=Integer.parseInt(total_product)) {

            order.setEnabled(true);
        }
        else{

          order.setEnabled(false);

            Log.d("canot palced ordre",""+"");
        }
order.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {



        new GetContacts().execute();


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
            pDialog = new ProgressDialog(place_order_single_product.this);
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
            String id_product = product_id.toString();
            String total=g_total.getText().toString();
            String quantity1=quantity.toString();
            try {
                // Building Parameters
                List params = new ArrayList();
                params.add(new BasicNameValuePair("user_email", user_email));
                params.add(new BasicNameValuePair("product_id",id_product));
                params.add(new BasicNameValuePair("quantity",quantity1));
               params.add(new BasicNameValuePair("total",total));

                ServiceHandler sh = new ServiceHandler();
                Log.d("request!", "starting");
                // getting product details by making HTTP request
                String jsonStr = sh.makeServiceCall(url, 2, params);//2 for POST
                JSONObject json = new JSONObject(jsonStr);
                // check your log for json response
                Log.d("store to cart attempt", json.toString());
                // json success tag
                success = json.getInt(Activity_main.TAG_SUCCESS);
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
                Toast.makeText(getApplicationContext(),"THANKS FOR ORDER",Toast.LENGTH_LONG).show();
                //  Intent i = new Intent(SingleItemView.this,Activity_main.class);//temp
                // finish();
                // startActivity(i);
                //  return json.getString(TAG_MESSAGE);
            } else {
                Log.d("add to Failure!"," fail");
                Toast.makeText(getApplicationContext(),"CONNECT TO INTERNET",Toast.LENGTH_LONG).show();
                // return json.getString(TAG_MESSAGE);
            }
        }
    }


}
