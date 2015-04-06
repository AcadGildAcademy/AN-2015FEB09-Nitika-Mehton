package com.example.nitika.main_project_design;

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

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NITIKA on 02-Apr-15.
 */
public class User_Profile extends ActionBarActivity {

    private ProgressDialog pDialog;
String user_email,user_name;
    EditText email,name,last_name,user_adress,zip,city,state,phno,country;
    Button save,cancel;
    private static String url = "http://bishasha.com/json/whdeal_profile.php";
    private static final String TAG_SUCCESS = "success";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_user);

        name=(EditText)findViewById(R.id.id_name);
        last_name=(EditText)findViewById(R.id.id_last_name);
        email =(EditText)findViewById(R.id.id_user_email);
        user_adress=(EditText)findViewById(R.id.id_address);
        zip=(EditText)findViewById(R.id.id_pin);
        city=(EditText)findViewById(R.id.id_city);
        state=(EditText)findViewById(R.id.id_state);
        phno=(EditText)findViewById(R.id.ph_no);

save=(Button)findViewById(R.id.save_bt);
        cancel=(Button)findViewById(R.id.cancel_bt);



        Intent i = getIntent();
         user_email = i.getStringExtra("user_email");
        user_name =i.getStringExtra("user_name");
        email.setText(user_email);
        email.setEnabled(false);
        name.setText(user_name);
         name.setEnabled(false);

save.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String user_email = email.getText().toString();
        String user_first_name=name.getText().toString();
        String user_last_name=last_name.getText().toString();

        String address= user_adress.getText().toString();
        String state1=state.getText().toString();
        String city1=city.getText().toString();
        String user_pin=zip.getText().toString();
        String ph_no=phno.getText().toString();



        if(!user_first_name.equals("")&& !user_last_name.equals("")&& !address.equals("")&& !state.equals("")
                && !city.equals("")&& !user_pin.equals("")&& !ph_no.equals(""))
        {
            Toast.makeText(getApplication(), "All fields are mandatory", Toast.LENGTH_SHORT).show();
        }

        else
        {
            Toast.makeText(getApplication(), "All ", Toast.LENGTH_SHORT).show();
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
            pDialog = new ProgressDialog(User_Profile.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            int success;

//get data from EditText
            String user_email = email.getText().toString();
            String user_first_name=name.getText().toString();
            String user_last_name=last_name.getText().toString();

            String address= user_adress.getText().toString();
            String state1=state.getText().toString();
            String city1=city.getText().toString();
            String user_pin=zip.getText().toString();
            String ph_no=phno.getText().toString();
            Log.d("data", "" + user_email + "-->" + user_first_name + "\n"
                    + user_last_name + "\n" + address + "\n" + state + "\n" + city + "\n");
            try {
                // Building Parameters
                List params = new ArrayList();

                params.add(new BasicNameValuePair("email",user_email));
                params.add(new BasicNameValuePair("name",user_first_name));
                params.add(new BasicNameValuePair("last_name",user_last_name));
                params.add(new BasicNameValuePair("address",address));
                params.add(new BasicNameValuePair("state",state1));
                params.add(new BasicNameValuePair("city",city1));
                params.add(new BasicNameValuePair("pin_code",user_pin));
                params.add(new BasicNameValuePair("mobile",ph_no));
              //  params.add(new BasicNameValuePair("country",countryname));


                ServiceHandler sh = new ServiceHandler();
                Log.d("request!", "starting");
                // getting product details by making HTTP request
                String jsonStr = sh.makeServiceCall(url, ServiceHandler.POST,params);//2 for POST
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
                Log.d("update Successful!", " Success");
                Toast.makeText(getApplicationContext(),"DONE",Toast.LENGTH_LONG).show();
                //  String user_email = email.getText().toString();
                // String user_name=name.getText().toString();
                Intent i = new Intent(User_Profile.this, Login.class) ;
                i.putExtra("user_email",user_email);
                i.putExtra("user_name",user_name);
                finish();
                startActivity(i);
                //  return json.getString(TAG_MESSAGE);
            } else {
                Log.d("update fail"," fail");
                Toast.makeText(User_Profile.this," fail",Toast.LENGTH_LONG).show();
                // return json.getString(TAG_MESSAGE);
            }
        }
    }
}
