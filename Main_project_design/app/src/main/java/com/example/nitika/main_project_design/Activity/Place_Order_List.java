package com.example.nitika.main_project_design.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nitika.main_project_design.Adapter.Place_order_list_adapter;
import com.example.nitika.main_project_design.R;
import com.example.nitika.main_project_design.Utiles.ServiceHandler;
import com.example.nitika.main_project_design.Utiles.UserSessionLogin;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by NITIKA on 08-Apr-15.
 */
public class Place_Order_List extends Activity {
    ListView listview;
    JSONArray contacts = null;
    UserSessionLogin session;
    private ProgressDialog     mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;
    private static String url ="http://bishasha.com/json/whdeal_SeeCartItem.php";
   private static String url_order= "http://bishasha.com/json/whdeal_Order.php";
 //   private static String url_order= "http://bishasha.com/json/whdeal_Delete_Total_product.php";
    int total_int;
    TextView grand_total_tv,email_tv;
    Place_order_list_adapter adapter;
   String grand_total;
    Button place_order_bt;
    String total_product,quantity_order;
    String user_email_str;
    int order_flag=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_order_list_ui);
        grand_total_tv=(TextView)findViewById(R.id.place_grand_total);
        email_tv=(TextView)findViewById(R.id.place_email);
      place_order_bt=(Button)findViewById(R.id.place_bt);

        Intent i = getIntent();
        grand_total=i.getStringExtra("grand_total");
        Toast.makeText(getApplication(),grand_total,Toast.LENGTH_SHORT).show();
        grand_total_tv.setText(grand_total);
        new DownloadJSON().execute();

       place_order_bt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               new GetContacts().execute();

           }
       });

    }

    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(Place_Order_List.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Wait");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0)//params)
        {
            // Create an array
            arraylist = new ArrayList<HashMap<String, String>>();
            session = new UserSessionLogin(getApplicationContext());
            HashMap<String, String> user = session.getUserDetails();
            String user1 = (user.get(UserSessionLogin.KEY_EMAIL_SESSION));
            String user_email= user1.toString();
//String user_email="nitika@gmail.com";
            List params = new ArrayList();
            params.add(new BasicNameValuePair("user_email",user_email));
            //
            ServiceHandler sh = new ServiceHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET,params);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {

                    JSONObject jsonObj = new JSONObject(jsonStr);
                    int success = jsonObj.getInt(Activity_main.TAG_SUCCESS);
                    //


                    //
                    if (success == 1)
                    {
                        // Getting JSON Array node
                        contacts = jsonObj.getJSONArray("whdeal_products");
                        Log.d("contacts-->",""+contacts);
                        // looping through All Contacts
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i);

                            //String id = c.getString(Cart_item.TAG_ID);
                            String name = c.getString(Activity_main.TAG_NAME);
                           // String description = c.getString(Cart_item.TAG_DESCRIPTION);
                            //String image_path = c.getString(Cart_item.TAG_IMAGE_PATH);
                           // String cost_price = c.getString(Cart_item.TAG_COST_PRICE);
                            String selling_price = c.getString(Activity_main.TAG_SELL_COST);
                           // String brand= c.getString(Cart_item.TAG_BRAND);
                           String quantity=c.getString(Activity_main.TAG_QUANTITY);
                            String total_product=c.getString(Activity_main.TAG_TOTAL_PRODUCT);
                           // String total=c.getString(Cart_item.TAG_TOTAL);

                            HashMap<String, String> contact = new HashMap<String, String>();
                            // adding each child node to HashMap key => value
                     //       contact.put(Cart_item.TAG_ID, id);
                            contact.put(Activity_main.TAG_NAME, name);
                       //     contact.put(Cart_item.TAG_DESCRIPTION, description);
                         //   contact.put(Cart_item.TAG_IMAGE_PATH, image_path);
                           // contact.put(Cart_item.TAG_COST_PRICE,cost_price);
                            contact.put(Activity_main.TAG_SELL_COST,selling_price);
                            //ontact.put(Cart_item.TAG_BRAND,brand);
                            contact.put(Activity_main.TAG_QUANTITY,quantity);
                            contact.put(Activity_main.TAG_TOTAL_PRODUCT,total_product);
                            //contact.put(Cart_item.TAG_TOTAL,total);
                           // total_product=resultp.get(Cart_item.TAG_TOTAL_PRODUCT);
                           // quantity_order=res.get(Cart_item.TAG_QUANTITY);
                          if(Integer.parseInt(quantity)<=Integer.parseInt(total_product)) {
                                Log.d(" palced ordre",""+order_flag);
                            //  place_order_bt.setEnabled(true);
                            }
                            else{

//                              place_order_bt.setEnabled(false);
                    order_flag=0;
                              Log.d("canot palced ordre",""+order_flag);
                            }


                            arraylist.add(contact);
                        }
                    }
                    else
                    {
                        Log.d("no product","");
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            // Locate the listview in listview_main.xml
if(order_flag==0)
{
    place_order_bt.setEnabled(false);

} else {
    place_order_bt.setEnabled(true);
            }
            listview = (ListView) findViewById(R.id.place_listView);
            // Pass the results into ListViewAdapter.java
            adapter = new Place_order_list_adapter(Place_Order_List.this, arraylist);
            // Set the adapter to the ListView
            listview.setAdapter(adapter);
         //   Log.d("quantity-->",quantity_order);
           // Log.d("total-->",total_product);


            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }

    /**
     * Async task class to get json by making HTTP call
     * */

    private class GetContacts extends AsyncTask<Void,Integer, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            mProgressDialog = new ProgressDialog(Place_Order_List.this);
            mProgressDialog.setMessage("Please wait...");
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            int success;
            UserSessionLogin session;

            session = new UserSessionLogin(getApplicationContext());
            HashMap<String, String> user = session.getUserDetails();
            String user_email= (user.get(UserSessionLogin.KEY_EMAIL_SESSION)).toString();
user_email_str=user_email.toString();

            String quantity="1";
            try {
                // Building Parameters
                List params = new ArrayList();
                params.add(new BasicNameValuePair("user_email", user_email_str));


                ServiceHandler sh = new ServiceHandler();

                // getting product details by making HTTP request
                String jsonStr = sh.makeServiceCall(url_order, ServiceHandler.POST, params);//2 for POST
                JSONObject json = new JSONObject(jsonStr);
                // check your log for json response
                Log.d("Place order attempt", json.toString());
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
            if (  mProgressDialog.isShowing())
                mProgressDialog.dismiss();
        }


        //Handling updation to your UI
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int  success=values[0];
            if (success == 1) {
                Log.d("order is Placed!", " Success");
                Toast.makeText(getApplicationContext(),"Order is Placed",Toast.LENGTH_LONG).show();
                Intent i = new Intent(Place_Order_List.this,Index.class);//temp
                finish();
                startActivity(i);
                //  return json.getString(TAG_MESSAGE);
            } else {
                Log.d("Order is not Placed!"," fail");
                Toast.makeText(getApplicationContext(),"Order is not placed",Toast.LENGTH_LONG).show();
                // return json.getString(TAG_MESSAGE);
            }
        }
    }


}
