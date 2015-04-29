package com.example.nitika.main_project_design.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
 * Created by NITIKA on 26-Apr-15.
 */
public class Contact_us extends Activity {

    EditText email,message;
    Button send,cancel;
    private ProgressDialog pDialog;
    UserSessionLogin session;
    private static String url = "http://bishasha.com/json/whdeal_contact_us.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us);

        email =(EditText)findViewById(R.id.email);
        message=(EditText)findViewById(R.id.id_msg);
        send =(Button)findViewById(R.id.send);
        cancel=(Button)findViewById(R.id.cancel);
        email.setEnabled(false);
        session = new UserSessionLogin(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        // get name
        if( session.isUserLoggedIn()) {
            String user_str = user.get(UserSessionLogin.KEY_EMAIL_SESSION);
            email.setText(user_str);
        }

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((!email.equals("")  && !message.equals("")))
                {

                    new GetContacts().execute();

                }
                else
                {
                    Toast.makeText(getApplication(), "All fields are  mandatory", Toast.LENGTH_SHORT).show();

                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
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
            pDialog = new ProgressDialog(Contact_us.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            int success;

//get data from EditText
            String user_email = email.getText().toString();
            String msg =message.getText().toString();
            try {
                // Building Parameters
                List params = new ArrayList();

                params.add(new BasicNameValuePair("email",user_email));

                params.add(new BasicNameValuePair("message",msg));


                ServiceHandler sh = new ServiceHandler();
                Log.d("request!", "starting");
                // getting product details by making HTTP request
                String jsonStr = sh.makeServiceCall(url, ServiceHandler.POST,params);//2 for POST
                JSONObject json = new JSONObject(jsonStr);
                // check your log for json response
                Log.d("sign up attempt", json.toString());
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

                Toast.makeText(getApplicationContext(),"Your Message is Received",Toast.LENGTH_LONG).show();

            } else {

                Toast.makeText(Contact_us.this,"Please Connect to Internet",Toast.LENGTH_LONG).show();

            }
        }
    }
}
