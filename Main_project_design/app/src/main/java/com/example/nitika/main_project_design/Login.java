package com.example.nitika.main_project_design;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by NITIKA on 05-Feb-15.
 */
public class Login extends Activity{

    private ProgressDialog pDialog;

    EditText name1 ,password1,confirm_password;
    Button sign_in, cancel;
    private static String url = "http://bishasha.com/json/whdeal_login.php?email=abc@gmail.com&password=def";

    // JSON Node names
   // private static final String TAG_PRODUCT = "product";
   //private static final String TAG_ID = "id";

    private static final String TAG_EMAIL = "email";
    private static final String TAG_PASSWORD = "password";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    JSONArray contacts = null;
    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);
        contactList = new ArrayList<HashMap<String, String>>();

        name1 =(EditText)findViewById(R.id.id_username);
        password1=(EditText)findViewById(R.id.id_password);

        sign_in=(Button)findViewById(R.id.signin);
        sign_in.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String user_name_value = name1.getText().toString();
                String user_password_value = password1.getText().toString();




                if (user_name_value.equals("") || user_password_value.equals("")) {
                    Toast.makeText(getApplication(), "All fields are mentatory", Toast.LENGTH_SHORT).show();
                }
                else
                    new GetContacts().execute();

            }




        });

    }





    /**
     * Async task class to get json by making HTTP call
     * */

     private class GetContacts extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplication(),"pre",Toast.LENGTH_SHORT).show();
            super.onPreExecute();
            // Showing progress dialog
           pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {


            int success;
            String temp_email,temp_password;
            String username = name1.getText().toString();
            String password = password1.getText().toString();
            try {
                // Building Parameters
                List params = new ArrayList();
                params.add(new BasicNameValuePair("email", username));
                params.add(new BasicNameValuePair("password", password));

                ServiceHandler sh = new ServiceHandler();
                Log.d("request!", "starting");
                // getting product details by making HTTP request
                String jsonStr
                        = sh.makeServiceCall(url, 2, params);

                JSONObject json = new JSONObject(jsonStr);

                // check your log for json response
                Log.d("Login attempt", json.toString());

                // json success tag
                success = json.getInt(TAG_SUCCESS);



                    if (success == 1) {
                        Log.d("Login Successful!", json.toString());
                        Intent i = new Intent(Login.this, Index.class);
                        finish();
                        startActivity(i);
                      //  return json.getString(TAG_MESSAGE);
                    } else {
                        Log.d("Login Failure!", json.getString(TAG_MESSAGE));
                       // return json.getString(TAG_MESSAGE);
                    }

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

    }



}
