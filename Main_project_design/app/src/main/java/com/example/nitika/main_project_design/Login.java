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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by NITIKA on 05-Feb-15.
 */
public class Login extends Activity{

    private ProgressDialog pDialog;

    EditText email, password,confirm_password;
    Button sign_in, logout;
    CheckBox checkBox;
    Boolean status;
    private static String url = "http://bishasha.com/json/whdeal_login.php";
    UserSessionLogin session;




    private static final String TAG_SUCCESS = "success";


    JSONArray contacts = null;
    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
//        if(session.checkLogin())
  //          finish();
        email =(EditText)findViewById(R.id.id_username);
        password =(EditText)findViewById(R.id.id_password);
        checkBox=(CheckBox)findViewById(R.id.id_remember_me);
        logout=(Button)findViewById(R.id.logout);
        session = new UserSessionLogin(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();

        // get name
        String password_str = user.get(UserSessionLogin.KEY_PASSWORD_SESSION);

        // get email
        String email_str = user.get(UserSessionLogin.KEY_EMAIL_SESSION);


        // Show user data on activity
        email.setText(email_str);
        password.setText(password_str);
        contactList = new ArrayList<HashMap<String, String>>();


      loadMySavePreferences();//for email and password

        Toast.makeText(getApplicationContext(),
                "User Login Status: " + session.isUserLoggedIn(),Toast.LENGTH_LONG).show();


        sign_in=(Button)findViewById(R.id.signin);
        sign_in.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String user_name_value = email.getText().toString();
                String user_password_value = password.getText().toString();

                if (!isValidEmail(user_name_value)) {
                    email.setError("Invalid Email");
                }
                else if (!isValidPassword(user_password_value)) {
                    password.setError("Invalid Password");
                }
                else{
                    new GetContacts().execute();
                    String st = email.getText().toString();
                    String st2 = password.getText().toString();
                    session.createUserLoginSession(st,st2);
                    //code for preferences
                    savedMySharedPreferences("check", checkBox.isChecked());
                    if (checkBox.isChecked()) {

                        savedMySharedPreferences("user", st);
                        savedMySharedPreferences("password", st2);
                    }

                }
            }
       });
       logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               session.logoutUser();
           }
       });


    }//end of main


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
            String username = email.getText().toString();
            String password = Login.this.password.getText().toString();

                        try {
                // Building Parameters
                List params = new ArrayList();
                params.add(new BasicNameValuePair("email", username));
                params.add(new BasicNameValuePair("password", password));

                ServiceHandler sh = new ServiceHandler();
                Log.d("request!", "starting");
              // getting product details by making HTTP request
                String jsonStr = sh.makeServiceCall(url, 1, params);
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
            String st = email.getText().toString();
            String st2 = password.getText().toString();
            Boolean status;
            if (success == 1) {
                Log.d("Login Successful!", "Login Success");

                Toast.makeText(getApplicationContext(),"login success",Toast.LENGTH_LONG).show();
               // session.createUserLoginSession(st,st2);

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
        email.setText(str);
        password.setText(pass);
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
