package com.example.nitika.main_project_design;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by NITIKA on 31-Mar-15.
 */
public class Cart_item extends ActionBarActivity {
    private ProgressDialog pDialog;
    ListView listview;

    ListViewAdapter adapter;
    Cart_Adapter cart_adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;
    static  String TAG_SUCCESS="success";
    static String TAG_ID = "id";
    static String TAG_NAME = "name";
    static String TAG_DESCRIPTION = "description";
    static String TAG_IMAGE_PATH = "image_path";
    static String TAG_SELL_COST = "selling_price";
    static String TAG_COST_PRICE = "cost_price";
    static String TAG_STATUS = "status";
    static String TAG_BRAND = "brand";
    static String TAG_CATEGORY = "category";
    static String TAG_QUANTITY ="quantity";
    static String TAG_TOTAL="total";
    static String TAG_TOTAL_PRODUCT="totol_product";
    private static String url ="http://bishasha.com/json/whdeal_SeeCartItem.php";

    TextView total_tv;
    int total_int;
    String t_string;
    TextView total_edit_txt;
    Button buy;
    JSONArray contacts = null;
    UserSessionLogin session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_list_ui);

        session = new UserSessionLogin(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        total_tv=(TextView)findViewById(R.id.total_value);
        total_edit_txt=(TextView)findViewById(R.id.total_btn);
        buy=(Button)findViewById(R.id.cart_buy);
        // get name
        if( session.isUserLoggedIn()) {
            String user_str = user.get(UserSessionLogin.KEY_EMAIL_SESSION);
            new DownloadJSON().execute();
            Toast.makeText(getApplication(),"done",Toast.LENGTH_SHORT).show();
        }
        getActionBar();

    buy.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            HashMap<String, String> user = session.getUserDetails();
            String user_str = user.get(UserSessionLogin.KEY_EMAIL_SESSION);
            String ss=Integer.toString(total_int);
            Intent intent =new Intent(Cart_item.this,DEMOcart_User_Profile.class);

            intent.putExtra("grand_total",ss);
            intent.putExtra("user_email",user_str);

            startActivity(intent);

        }
    });
    }
    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(Cart_item.this);
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

            HashMap<String, String> user = session.getUserDetails();
            String user1 = (user.get(UserSessionLogin.KEY_EMAIL_SESSION));
            String user_email= user1.toString();

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
                    int success = jsonObj.getInt(TAG_SUCCESS);
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

                            String id = c.getString(TAG_ID);
                            String name = c.getString(TAG_NAME);
                            String description = c.getString(TAG_DESCRIPTION);
                            String image_path = c.getString(TAG_IMAGE_PATH);
                            String cost_price = c.getString(TAG_COST_PRICE);
                            String selling_price = c.getString(TAG_SELL_COST);
                            String brand= c.getString(TAG_BRAND);
                            String quantity=c.getString(TAG_QUANTITY);
                            String total=c.getString(TAG_TOTAL);
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
                            contact.put(TAG_QUANTITY,quantity);
                            contact.put(TAG_TOTAL,total);
                            contact.put(TAG_TOTAL_PRODUCT,total_product);


                            if(total.matches("\\d+")) //check if only digits. Could also be text.matches("[0-9]+")
                            {
                                String ss=total.toString();
                                total_int += Integer.parseInt(ss);

                                Log.d("$$$$", "" +total_tv.toString() );
                            }
                            else
                            {
                                Log.d("not a valid number","");
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

            String ss=Integer.toString(total_int);
            Log.d("<<>>>",""+ss);
            total_tv.setText(ss);
            listview = (ListView) findViewById(R.id.cart_listview);
            // Pass the results into ListViewAdapter.java
           cart_adapter = new Cart_Adapter(Cart_item.this, arraylist);
            // Set the adapter to the ListView
            listview.setAdapter(cart_adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.login_id_menu) {
            Intent intent = new Intent(Cart_item.this, Login.class);

            startActivity(intent);
        }
        if (id == R.id.signup_id_menu) {
            Intent intent = new Intent(Cart_item.this, SignUp.class);
            startActivity(intent);
        }
        if(id == R.id.cart_menu)
        {
            session = new UserSessionLogin(getApplicationContext());
            HashMap<String, String> user = session.getUserDetails();
            // get name
            if( session.isUserLoggedIn()) {
                String user_str = user.get(UserSessionLogin.KEY_EMAIL_SESSION);
                Intent intent = new Intent(Cart_item.this, Cart_item.class);
                startActivity(intent);

            }
            else{
                Toast.makeText(getApplicationContext(),"Login First",Toast.LENGTH_SHORT).show();
            }
        }
        if(id==R.id.mobile)
        {
            Intent intent =new Intent(Cart_item.this,Activity_main.class);
            intent.putExtra("category","mobile");
            startActivity(intent);
        }
        if(id==R.id.camera)
        {
            Intent intent =new Intent(Cart_item.this,Activity_main.class);
            intent.putExtra("category","camera");
            startActivity(intent);
        }
        if(id==R.id.fire_table)
        {
            Intent intent =new Intent(Cart_item.this,Activity_main.class);
            intent.putExtra("category","fire table");
            startActivity(intent);
        }
        if(id==R.id.accessories)
        {
            Intent intent =new Intent(Cart_item.this,Activity_main.class);
            intent.putExtra("category","accessories");
            startActivity(intent);
        }
        if(id==R.id.car)
        {
            Intent intent =new Intent(Cart_item.this,Activity_main.class);
            intent.putExtra("category","Car");
            startActivity(intent);
        }
        if(id==R.id.laptop_computer)
        {
            Intent intent =new Intent(Cart_item.this,Activity_main.class);
            intent.putExtra("category","computer");
            startActivity(intent);
        }
        if(id==R.id.tablets)
        {
            Intent intent =new Intent(Cart_item.this,Activity_main.class);
            intent.putExtra("category","tablets");
            startActivity(intent);
        }
        if(id==R.id.video_games)
        {
            Intent intent =new Intent(Cart_item.this,Activity_main.class);
            intent.putExtra("category","video games");
            startActivity(intent);
        }
        if(id==R.id.gadgets)
        {
            Intent intent =new Intent(Cart_item.this,Activity_main.class);
            intent.putExtra("category","gadgets");
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }


}
