package com.example.eku.dry_ticket_project.activity;

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
import android.widget.Toast;

import com.example.eku.dry_ticket_project.R;
import com.example.eku.dry_ticket_project.utils.ServiceHandler;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class sign_up extends ActionBarActivity {
    private ProgressDialog pDialog;
    EditText name, password,email;
    Button sign_up, cancel;


    private static String url = "http://bishasha.com/json/event_signup.php";

    private static final String TAG_SUCCESS = "success";

    JSONArray contacts = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> contactList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        contactList = new ArrayList<HashMap<String, String>>();


        name = (EditText) findViewById(R.id.username_edit2);
        password = (EditText) findViewById(R.id.password_edit2);
        email=(EditText)findViewById(R.id.email_edit2);
        cancel=(Button)findViewById(R.id.cancel2);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(sign_up.this,first_activity.class);
                startActivity(intent);

getSupportActionBar();
            }
        });
        sign_up = (Button) findViewById(R.id.signup);
        sign_up.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String username_value = name.getText().toString();
                String userpass_value = password.getText().toString();
               String useremail_value= email.getText().toString();
             //  final String email = name.getText().toString();


                if (username_value.equals("") || userpass_value.equals("")||useremail_value.equals("")) {
                    Toast.makeText(getApplicationContext(), "All fields are mandatory", Toast.LENGTH_SHORT).show();
                    //}
                    // Intent intent=new Intent (sign_in.this,dry_ticket.class);
                    //                startActivity (intent);
                }
               else if (!isValidEmail(useremail_value)) {
                    name.setError("Invalid Email");
                }


             else   if (!isValidPassword(userpass_value)) {
                    password.setError("Invalid Password");
                }


                else
                {
                    // Calling async task to get json
                    new GetContacts().execute();


                }
            }
        });
    }
       private class GetContacts extends AsyncTask<Void,Integer,Void>
       {
           @Override
           protected void onPreExecute()
           {
               super.onPreExecute();
               //showing progress dialog
               pDialog=new ProgressDialog(sign_up.this);
               pDialog.setMessage("Please wait..");
               pDialog.setCancelable(false);
               pDialog.show();
           }
           @Override
           protected Void doInBackground(Void... arg0) {
              int success;

               String username_value = email.getText().toString();
               String userpass_value = password.getText().toString();
               String useremail_value = name.getText().toString();
               try{
                   //building params
                   List params=new ArrayList();
                   params.add(new BasicNameValuePair("email",username_value));
                   params.add(new BasicNameValuePair("password",userpass_value));
                   params.add(new BasicNameValuePair("name",useremail_value));
                   ServiceHandler sh=new ServiceHandler();
                   Log.d("request!","starting");

                   //getting detail by making http request
                   String jsonStr=sh.makeServiceCall(url, 2, params);
                   JSONObject json =new JSONObject(jsonStr);

                   //check ur log for json response
                   Log.d("Login attempts",json.toString());

                   //json success tag
                   success=json.getInt(TAG_SUCCESS);
                   Log.d("success","+success");

                   publishProgress(success);
               }catch (JSONException e)
               {
                   e.printStackTrace();
               }
               return null;
           }
           @Override
           protected void onPostExecute(Void result) {
               super.onPostExecute(result);
               //dismiss the progress dialog
               if(pDialog.isShowing())
                   pDialog.dismiss();
           }
           @Override
           protected void onProgressUpdate(Integer... values) {
               super.onProgressUpdate(values);
               int success=values[0];
               if (success==1)
               {
                   Log.d("Login Successful","Login Success");
                   Toast.makeText(getApplicationContext(),"login success",Toast.LENGTH_LONG).show();
                   finish();

               }
               else
               {
                   Log.d("Sign up Failure","sign up fail");
                   Toast.makeText(getApplicationContext(),"sign up fail",Toast.LENGTH_LONG).show();
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

