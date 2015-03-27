package com.example.nitika.main_project_design;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by NITIKA on 05-Feb-15.
 */
public class SignUp extends Activity{

    private ProgressDialog pDialog;

    EditText email,password1,name;
    Button sign_up, cancel;

    private static String url = "http://bishasha.com/json/whdeal_signup.php";

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

        setContentView(R.layout.signup);

        contactList = new ArrayList<HashMap<String, String>>();

        name=(EditText)findViewById(R.id.id_name);
        email =(EditText)findViewById(R.id.id_username);
        password1=(EditText)findViewById(R.id.id_password);



        sign_up =(Button)findViewById(R.id.id_sign_up);
        sign_up.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String user_name_value = email.getText().toString();
                String user_password_value = password1.getText().toString();
                String user_name=name.getText().toString();
                if (user_name.equals("") ) {
                    Toast.makeText(getApplication(), "All fields are mandatory", Toast.LENGTH_SHORT).show();
                }
                 else if (!isValidEmail(user_name_value)) {
                    email.setError("Invalid Email");
                } else if (!isValidPassword(user_password_value)) {
                    password1.setError("Invalid Password");
                } else {
                    new GetContacts().execute();
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
           pDialog = new ProgressDialog(SignUp.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            int success;
            String temp_email,temp_password;
            String username = email.getText().toString();
            String password = password1.getText().toString();
            String name1 = name.getText().toString();
                        try {
                // Building Parameters
                List params = new ArrayList();
                params.add(new BasicNameValuePair("name",name1));
                params.add(new BasicNameValuePair("email", username));
                params.add(new BasicNameValuePair("password", password));

                ServiceHandler sh = new ServiceHandler();
                Log.d("request!", "starting");
              // getting product details by making HTTP request
                String jsonStr = sh.makeServiceCall(url, 2, params);//2 for POST
                JSONObject json = new JSONObject(jsonStr);
                // check your log for json response
                Log.d("sign up attempt", json.toString());
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
                Log.d("sign-up Successful!", "sign-up Success");
                Toast.makeText(getApplicationContext(),"sign up success",Toast.LENGTH_LONG).show();
                Intent i = new Intent(SignUp.this, Login.class);
               finish();
                startActivity(i);
                //  return json.getString(TAG_MESSAGE);
            } else {
                Log.d("Sign-Up Failure!","Sign-Up fail");
                Toast.makeText(SignUp.this,"Sign up fail",Toast.LENGTH_LONG).show();
                // return json.getString(TAG_MESSAGE);
            }
        }
    }



    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
       Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 2) {
            return true;
        }
        return false;
    }

}
