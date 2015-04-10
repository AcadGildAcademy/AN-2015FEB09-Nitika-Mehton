package com.example.nitika.main_project_design;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NITIKA on 02-Apr-15.
 */
public class User_Profile_india extends Activity {
    String state_selected,city_selected;
    private ProgressDialog pDialog;
    Spinner state_spinner, city_spinner;
    ArrayAdapter<String> adp1,adp2;
    String user_email,user_name;
    EditText user_email1,first_name,last_name,user_address,zip,phno;
    int pos;
    private static final String TAG_SUCCESS = "success";
    Button save,cancel;
    String[] states,city;

    private static String url = "http://bishasha.com/json/whdeal_profile.php";
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.india_profile_user);
        //
       first_name=(EditText)findViewById(R.id.india_name);
        user_email1 =(EditText)findViewById(R.id.india_user_email);
        last_name=(EditText)findViewById(R.id.india_last_name);
         user_address=(EditText)findViewById(R.id.india_address);
        zip=(EditText)findViewById(R.id.india_pin_code);
        phno=(EditText)findViewById(R.id.india_ph_no);
        state_spinner = (Spinner) findViewById(R.id.india_state);
        city_spinner = (Spinner) findViewById(R.id.india_city);
        states=getResources().getStringArray(R.array.state_list);
        save=(Button)findViewById(R.id.india_save);
        cancel=(Button)findViewById(R.id.india_cancel);

        Intent i = getIntent();
        user_email = i.getStringExtra("user_email");
        user_name =i.getStringExtra("user_name");
        user_email1.setText(user_email);
        user_email1.setEnabled(false);
        first_name.setText(user_name);
       first_name.setEnabled(false);

//code for skip button or cancel
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(User_Profile_india.this,Index.class);
                startActivity(intent);
            }
        });
        //code for save button
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_email = user_email1.getText().toString();
                String user_first_name=first_name.getText().toString();
                String user_last_name=last_name.getText().toString();

                String address=user_address.getText().toString();
                String state=state_selected.toString();
                String city=city_selected.toString();
                String user_pin=zip.getText().toString();
                String ph_no=phno.getText().toString();
                String countryname="INDIA";


               if(!user_first_name.equals("")&& !user_last_name.equals("")&& !address.equals("")&& !state.equals("")
                       && !city.equals("")&& !user_pin.equals("")&& !ph_no.equals(""))
                {
                    new GetContacts().execute();

                }
               else if (!isValidPhoneNo(ph_no)) {
                   phno.setError("Invalid Phone Number");
               }
               else if (!isValidPinCode(user_pin)) {
                  zip.setError("Invalid Phone Number");
               }
                else
                {
                    Toast.makeText(getApplication(), "All fields are mandatory", Toast.LENGTH_SHORT).show();

                }

            }
        });

//code for state and city

        adp1=new ArrayAdapter<String> (this,android.R.layout.simple_dropdown_item_1line,states);
        adp1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        state_spinner.setAdapter(adp1);

        state_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        state_selected=parent.getItemAtPosition(position).toString();

                pos = position;

                add();//to show city of selected state
            }

            private void add() {
                // TODO Auto-generated method stub

                switch (pos) {
                    case 0:
                        city = getResources().getStringArray(R.array.AndhraPradesht);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                         select();

                        break;
                    case 1:
                        city = getResources().getStringArray(R.array.AndhraPradesht);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                           select();

                        break;
                    case 2:
                        city = getResources().getStringArray(R.array.assam);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                          select();

                        break;
                    case 3:
                        city = getResources().getStringArray(R.array.bihar);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                          select();

                        break;
                    case 4:
                        city = getResources().getStringArray(R.array.goa);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                          select();

                        break;

                    case 5:
                        city = getResources().getStringArray(R.array.gujrat);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                           select();

                        break;

                    case 6:
                        city = getResources().getStringArray(R.array.Haryana_list);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                          select();

                        break;
                    case 7:
                        city = getResources().getStringArray(R.array.Mplist);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                           select();

                        break;
                    case 8:
                        city = getResources().getStringArray(R.array.Maharastra_list);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);
                        Toast.makeText(getApplication(), "2222222222", Toast.LENGTH_SHORT).show();
                          select();

                        break;
                    case 9:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);
                        Toast.makeText(getApplication(), "2222222222", Toast.LENGTH_SHORT).show();
                        //   select();

                        break;
                    case 10:
                        city = getResources().getStringArray(R.array.uttarakhand_list);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                     select();

                        break;
                    case 11:
                        city = getResources().getStringArray(R.array.up_list);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                          select();

                        break;
                    case 12:
                        city = getResources().getStringArray(R.array.chandigarah);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                          select();

                        break;
                    case 13:
                        city = getResources().getStringArray(R.array.delhi);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                     select();

                        break;
                    case 14:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                         select();

                        break;
                    case 15:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);
                        select();

                        break;
                    case 16:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;
                    case 17:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();
                        break;
                    case 18:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);
                        select();  //   select();

                        break;
                    case 19:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;
                    case 20:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();
                        break;
                    case 21:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();
                        break;
                    case 22:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);
                        select();
                        break;
                    case 23:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;

                    case 24:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;
                    case 25:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;
                    case 26:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;
                    case 27:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;
                    case 28:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        //   select();

                        break;
                    case 29:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        //   select();

                        break;
                    case 30:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(User_Profile_india.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        //   select();

                        break;


                }


            }

            private void select() {
                // TODO Auto-generated method stub

                city_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        city_selected=arg0.getItemAtPosition(arg2).toString();

                        pos = arg2;



                    }

                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub

                    }
                });
            }


        @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }) ;



        }


    /**
     * Async task class to get json by making HTTP call
     * */

    private class GetContacts extends AsyncTask<Void,Integer, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(User_Profile_india.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            int success;

//get data from EditText
            String user_email = user_email1.getText().toString();
            String user_first_name=first_name.getText().toString();
            String user_last_name=last_name.getText().toString();

            String address=user_address.getText().toString();
            String state=state_selected.toString();
            String city=city_selected.toString();
            String user_pin=zip.getText().toString();
           String ph_no=phno.getText().toString();
String countryname="INDIA";
Log.d("data",""+user_email+"-->"+user_first_name+"\n"
        +user_last_name+"\n"+address+"\n"+state+"\n"+city+"\n");
            try {
                // Building Parameters
                List params = new ArrayList();

                params.add(new BasicNameValuePair("email",user_email));
                params.add(new BasicNameValuePair("name",user_first_name));
                params.add(new BasicNameValuePair("last_name",user_last_name));
                params.add(new BasicNameValuePair("address",address));
                params.add(new BasicNameValuePair("state",state));
                params.add(new BasicNameValuePair("city",city));
                params.add(new BasicNameValuePair("pin_code",user_pin));
                params.add(new BasicNameValuePair("mobile",ph_no));
                params.add(new BasicNameValuePair("country",countryname));


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
                Intent i = new Intent(User_Profile_india.this, Login.class) ;
                i.putExtra("user_email",user_email);
                i.putExtra("user_name",user_name);
                finish();
                startActivity(i);
                //  return json.getString(TAG_MESSAGE);
            } else {
                Log.d("update fail"," fail");
                Toast.makeText(User_Profile_india.this," fail",Toast.LENGTH_LONG).show();
                // return json.getString(TAG_MESSAGE);
            }
        }
    }

    private boolean isValidPhoneNo(String pass) {
        if (pass != null && pass.length() == 10) {
            return true;
        }
        return false;
    }
    private boolean isValidPinCode(String pass) {
        if (pass != null && pass.length() == 6) {
            return true;
        }
        return false;
    }
}

