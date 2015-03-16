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
import android.widget.CheckBox;
import android.widget.EditText;
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
    CheckBox checkBox;
    private static String url = "http://bishasha.com/json/whdeal_login.php";

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
        checkBox=(CheckBox)findViewById(R.id.id_remember_me);

        loadMySavePreferences();
        sign_in=(Button)findViewById(R.id.signin);
        sign_in.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String user_name_value = name1.getText().toString();
                String user_password_value = password1.getText().toString();




                if (user_name_value.equals("") || user_password_value.equals("")) {
                    Toast.makeText(getApplication(), "All fields are mentatory", Toast.LENGTH_SHORT).show();
                }
                else{
                    new GetContacts().execute();

                //code for preferences
                    savedMySharedPreferences("check", checkBox.isChecked());
                    if(checkBox.isChecked())
                    {
                        String st = name1.getText().toString();
                        String st2=password1.getText().toString();
                        savedMySharedPreferences("user", st);
                        savedMySharedPreferences("password",st2);



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
                        = sh.makeServiceCall(url, 1, params);

                JSONObject json = new JSONObject(jsonStr);

                // check your log for json response
                Log.d("Login attempt", json.toString());

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
                Log.d("Login Successful!", "Login Success");
                Toast.makeText(getApplicationContext(),"login success",Toast.LENGTH_LONG).show();
                Intent i = new Intent(Login.this, Index.class);
                finish();
                startActivity(i);
                //  return json.getString(TAG_MESSAGE);
            } else {
                Log.d("Login Failure!","login fail");
                Toast.makeText(Login.this,"login fail",Toast.LENGTH_LONG).show();
                // return json.getString(TAG_MESSAGE);
            }
        }
    }




    public  void loadMySavePreferences()
    {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        String str = sp.getString("user","abc@gmail.com");
        String pass=sp.getString("pass","def");
        Boolean ck =sp.getBoolean("check", true);
        name1.setText(str);
        password1.setText(pass);
        checkBox.setChecked(ck);


    }



    public void savedMySharedPreferences(String Key,String values)
    {
        SharedPreferences sp=PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor ed =sp.edit();
        ed.putString(Key,values);
        ed.commit();

    }
    public void savedMySharedPreferences(String Key,Boolean values)
    {
        SharedPreferences sp =PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor ed =sp.edit();
        ed.putBoolean(Key,values);
        ed.commit();
    }


}
