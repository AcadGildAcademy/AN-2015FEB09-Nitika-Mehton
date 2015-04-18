package com.example.nitika.main_project_design.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by NITIKA on 05-Feb-15.
 */
public class Login extends  ActionBarActivity
 {

    private ProgressDialog pDialog;

    EditText email, password,confirm_password;
    Button sign_in, logout;
    CheckBox checkBox;
    TextView profile;
    Boolean status;
   private static String url = "http://bishasha.com/json/whdeal_login.php";


    UserSessionLogin session;




    private static final String TAG_SUCCESS = "success";


    JSONArray contacts = null;
    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        sign_in=(Button)findViewById(R.id.signin);
        email =(EditText)findViewById(R.id.id_username);
        password =(EditText)findViewById(R.id.id_password);
        checkBox=(CheckBox)findViewById(R.id.id_remember_me);
        logout=(Button)findViewById(R.id.logout);

        session = new UserSessionLogin(getApplicationContext());
       loadMySavePreferences();//for email and password
        getActionBar();
        if(session.isUserLoggedIn())
        {
          sign_in.setEnabled(false);
        }



       /* {
            Toast.makeText(getApplicationContext(), ""+checkBox.isChecked(),Toast.LENGTH_LONG).show();
            HashMap<String, String> user = session.getUserDetails();

            // get name
            String password_str = user.get(session.KEY_PASSWORD_SESSION);

            // get email
            String email_str = user.get(session.KEY_EMAIL_SESSION);

            // Show user data on activity
            password.setText(Html.fromHtml(password_str + "</b>"));
            email.setText(Html.fromHtml(email_str + "</b>"));
            contactList = new ArrayList<HashMap<String, String>>();




        }*/

        //Toast.makeText(getApplicationContext(),"User Login Status: " + session.isUserLoggedIn(),Toast.LENGTH_LONG).show();



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

                    // Starting MainActivity
                    savedMySharedPreferences("check", checkBox.isChecked());
                    if (checkBox.isChecked()) {

                        savedMySharedPreferences("user", st);
                       savedMySharedPreferences("password", st2);
                    }
                    else if (!checkBox.isChecked())
                    {
                        savedMySharedPreferences("user","");
                        savedMySharedPreferences("password","");

                    }

                    Intent i = new Intent(getApplicationContext(), Index.class);
                   i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    // Add new Flag to start new Activity
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);

                    finish();
                    //code for preferences


                }
            }
       });
       logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               session.logoutUser();
               String st = email.getText().toString();
               String st2 = password.getText().toString();

               // Starting MainActivity
               savedMySharedPreferences("check", checkBox.isChecked());
               if (checkBox.isChecked()) {

                   savedMySharedPreferences("user", st);
                   savedMySharedPreferences("password", st2);
               }
               else if (!checkBox.isChecked())
               {
                   savedMySharedPreferences("user","");
                   savedMySharedPreferences("password","");

               }

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
                String jsonStr = sh.makeServiceCall(url, 1, params);// 1 for get
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

                Toast.makeText(getApplicationContext(),"Login success",Toast.LENGTH_LONG).show();
                session.createUserLoginSession(st,st2);
                Intent i = new Intent(Login.this, Index.class);
            //    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                // Add new Flag to start new Activity
              // i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               finish();
                startActivity(i);

            } else {
                Log.d("Login Failure!","login fail");
                Toast.makeText(Login.this,"Login fail",Toast.LENGTH_LONG).show();

            }
        }
    }




    public  void loadMySavePreferences()
    {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        String str = sp.getString("user","");
        String pass=sp.getString("password","");
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
             Intent intent = new Intent(Login.this, Login.class);

             startActivity(intent);
         }
         if (id == R.id.signup_id_menu) {
             Intent intent = new Intent(Login.this, SignUp.class);
             startActivity(intent);
         }
         if(id == R.id.cart_menu)
         {
             session = new UserSessionLogin(getApplicationContext());
             HashMap<String, String> user = session.getUserDetails();
             // get name
             if( session.isUserLoggedIn()) {
                 String user_str = user.get(UserSessionLogin.KEY_EMAIL_SESSION);
                 Intent intent = new Intent(Login.this, Cart_item.class);
                 startActivity(intent);

             }
             else{
                 Toast.makeText(getApplicationContext(),"Login First",Toast.LENGTH_SHORT).show();
             }
         }
         if(id==R.id.mobile)
         {
             Intent intent =new Intent(Login.this,Activity_main.class);
             intent.putExtra("category","mobile");
             startActivity(intent);
         }
         if(id==R.id.camera)
         {
             Intent intent =new Intent(Login.this,Activity_main.class);
             intent.putExtra("category","camera");
             startActivity(intent);
         }
         if(id==R.id.fire_table)
         {
             Intent intent =new Intent(Login.this,Activity_main.class);
             intent.putExtra("category","fire table");
             startActivity(intent);
         }
         if(id==R.id.accessories)
         {
             Intent intent =new Intent(Login.this,Activity_main.class);
             intent.putExtra("category","accessories");
             startActivity(intent);
         }
         if(id==R.id.car)
         {
             Intent intent =new Intent(Login.this,Activity_main.class);
             intent.putExtra("category","Car");
             startActivity(intent);
         }
         if(id==R.id.laptop_computer)
         {
             Intent intent =new Intent(Login.this,Activity_main.class);
             intent.putExtra("category","computer");
             startActivity(intent);
         }
         if(id==R.id.tablets)
         {
             Intent intent =new Intent(Login.this,Activity_main.class);
             intent.putExtra("category","tablets");
             startActivity(intent);
         }
         if(id==R.id.video_games)
         {
             Intent intent =new Intent(Login.this,Activity_main.class);
             intent.putExtra("category","video games");
             startActivity(intent);
         }
         if(id==R.id.gadgets)
         {
             Intent intent =new Intent(Login.this,Activity_main.class);
             intent.putExtra("category","gadgets");
             startActivity(intent);
         }


         return super.onOptionsItemSelected(item);
     }



 }
