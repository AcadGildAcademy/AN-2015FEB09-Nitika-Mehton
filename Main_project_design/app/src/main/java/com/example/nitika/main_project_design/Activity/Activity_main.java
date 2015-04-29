package com.example.nitika.main_project_design.Activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nitika.main_project_design.Adapter.ListViewAdapter;
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
 * Created by NITIKA on 23-Feb-15.
 */
public class Activity_main extends ActionBarActivity
 {
    // Declare Variables
    JSONObject jsonobject;
    JSONArray jsonarray;
     UserSessionLogin session;
    ListView listview;
    ListViewAdapter adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;
 public    static  String TAG_SUCCESS="success";
     public    static String TAG_ID = "id";
     public  static String TAG_NAME = "name";
     public  static String TAG_DESCRIPTION = "description";
     public   static String TAG_IMAGE_PATH = "image_path";
     public    static String TAG_SELL_COST = "selling_price";
     public  static String TAG_COST_PRICE = "cost_price";
     public    static String TAG_STATUS = "status";
     public   static String TAG_BRAND = "brand";
     public   static String TAG_CATEGORY = "category";
     public   static String TAG_DATE_ORDER = "date_order";
     public   static String TAG_QUANTITY ="quantity";
     public    static String TAG_TOTAL="total";
     public static String TAG_TOTAL_PRODUCT="totol_product";
    private static String url ="http://bishasha.com/json/products.php";
    // "http://bishasha.com/json/product_demo.php";
    String category;
    JSONArray contacts = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from listview_main.xml
       setContentView(com.example.nitika.main_project_design.R.layout.activity_main_ui);
       // setContentView(.layout.activity_main_ui);
        Intent i = getIntent();
        // Get the result of name
        category = i.getStringExtra("category");               // selected from menu
        Toast.makeText(getApplicationContext(),category,Toast.LENGTH_LONG).show();
        // Execute DownloadJSON AsyncTask
        //getActionBar();
        getSupportActionBar();
        new DownloadJSON().execute();
    }

    // DownloadJSON AsyncTask
    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(Activity_main.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Android JSON Parse Tutorial");
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

//code for menu

            List params = new ArrayList();
            params.add(new BasicNameValuePair("category",category));
            //
            ServiceHandler sh = new ServiceHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET,params);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {

                    JSONObject jsonObj = new JSONObject(jsonStr);
                    int success = jsonObj.getInt(TAG_SUCCESS);
                    //


                    //
                  if (success == 1)
                    {
                        // Getting JSON Array node
                        contacts = jsonObj.getJSONArray("whdeal_products");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString(TAG_ID);
                        String name = c.getString(TAG_NAME);
                       String description = c.getString(TAG_DESCRIPTION);
                        String image_path = c.getString(TAG_IMAGE_PATH);
                        String cost_price = c.getString(TAG_COST_PRICE);
                        String selling_price = c.getString(TAG_SELL_COST);
                       String brand= c.getString(TAG_BRAND);
                        String total_product=c.getString(TAG_TOTAL_PRODUCT);
                        HashMap<String, String> contact = new HashMap<String, String>();
                        // adding each child node to HashMap key => value
                        contact.put(TAG_ID, id);
                        contact.put(TAG_NAME, name);
                        contact.put(TAG_DESCRIPTION, description);
                        contact.put(TAG_IMAGE_PATH, image_path);
                        contact.put(TAG_COST_PRICE,cost_price);
                        contact.put(TAG_SELL_COST,selling_price);
                        contact.put(TAG_BRAND,brand);
                        contact.put(TAG_TOTAL_PRODUCT,total_product);


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
            listview = (ListView) findViewById(com.example.nitika.main_project_design.R.id.listview);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter(Activity_main.this, arraylist);
            // Set the adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         MenuInflater menuInflater = getMenuInflater();
        // menuInflater.inflate(com.example.nitika.main_project_design.R.menu.menu, menu);
menuInflater.inflate(com.example.nitika.main_project_design.R.menu.menu,menu);
         return super.onCreateOptionsMenu(menu);


     }

     @Override
     public boolean onOptionsItemSelected(MenuItem item) {
         int id = item.getItemId();
         if(id==R.id.home_id_menu)
         {
             Intent intent = new Intent(Activity_main.this, Index.class);
             finish();
             startActivity(intent);
         }
         if (id == com.example.nitika.main_project_design.R.id.login_id_menu) {
             Intent intent = new Intent(Activity_main.this, Login.class);

             startActivity(intent);
         }

         if(id == R.id.cart_menu)
         {
             session = new UserSessionLogin(getApplicationContext());
             HashMap<String, String> user = session.getUserDetails();
             // get name
             if( session.isUserLoggedIn()) {
                 String user_str = user.get(UserSessionLogin.KEY_EMAIL_SESSION);
                 Intent intent = new Intent(Activity_main.this, Cart_item.class);
                 startActivity(intent);

             }
             else{
                 Toast.makeText(getApplicationContext(),"Login First",Toast.LENGTH_SHORT).show();
             }
         }
         if(id==R.id.mobile)
         {
             Intent intent =new Intent(Activity_main.this,Activity_main.class);
             intent.putExtra("category","mobile");
             startActivity(intent);
         }
         if(id==R.id.camera)
         {
             Intent intent =new Intent(Activity_main.this,Activity_main.class);
             intent.putExtra("category","camera");
             startActivity(intent);
         }
         if(id==R.id.fire_table)
         {
             Intent intent =new Intent(Activity_main.this,Activity_main.class);
             intent.putExtra("category","fire table");
             startActivity(intent);
         }
         if(id==R.id.accessories)
         {
             Intent intent =new Intent(Activity_main.this,Activity_main.class);
             intent.putExtra("category","accessories");
             startActivity(intent);
         }
         if(id==R.id.car)
         {
             Intent intent =new Intent(Activity_main.this,Activity_main.class);
             intent.putExtra("category","Car");
             startActivity(intent);
         }
         if(id==R.id.laptop_computer)
         {
             Intent intent =new Intent(Activity_main.this,Activity_main.class);
             intent.putExtra("category","computer");
             startActivity(intent);
         }
         if(id==R.id.tablets)
         {
             Intent intent =new Intent(Activity_main.this,Activity_main.class);
             intent.putExtra("category","tablets");
             startActivity(intent);
         }
         if(id==R.id.video_games)
         {
             Intent intent =new Intent(Activity_main.this,Activity_main.class);
             intent.putExtra("category","video games");
             startActivity(intent);
         }
         if(id==R.id.gadgets)
         {
             Intent intent =new Intent(Activity_main.this,Activity_main.class);
             intent.putExtra("category","gadgets");
             startActivity(intent);
         }


         return super.onOptionsItemSelected(item);
     }



 }









