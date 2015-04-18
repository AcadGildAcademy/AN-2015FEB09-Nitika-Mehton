package com.example.nitika.main_project_design.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nitika.main_project_design.R;
import com.example.nitika.main_project_design.Utiles.ServiceHandler;
import com.example.nitika.main_project_design.Utiles.UserSessionLogin;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by NITIKA on 02-Apr-15.
 */
public class india_DEMOcart_User_Profile extends ActionBarActivity {
String str_name,str_last_name,str_city,str_country,str_state,str_pin_code,str_address,str_phone_no;
    private ProgressDialog pDialog;
    UserSessionLogin session;
String user_email,user_name;
    EditText email,name,last_name,user_adress,zip,phno,country;
    Spinner state_spinner, city_spinner;
    Button save,next;
    ArrayAdapter<String> adp1,adp2;
    String[] states,city;
    private static String url = "http://bishasha.com/json/whdeal_profile.php";
    private static String url_getdata="http://bishasha.com/json/whdeal_seeProfile.php";

    ArrayList<HashMap<String, String>> arraylist;
    JSONArray contacts = null;
    static  String TAG_SUCCESS="success";
    static  String TAG_NAME="name";
    static  String TAG_LAST_NAME="last_name";
    static  String TAG_ADDRESS="address";
    static  String TAG_STATE="state";
    static  String TAG_CITY="city";
    static  String TAG_PIN="pin_code";
    static  String TAG_PHONE_NO="mobile";
    static  String TAG_COUNTRY="country";
    String grand_total;
    int pos;
    String state_selected,city_selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.india_democart_profile_user);

        name=(EditText)findViewById(R.id.pp_id_name);
       last_name=(EditText)findViewById(R.id.pp_id_last_name);
       email =(EditText)findViewById(R.id.pp_id_user_email);
        user_adress=(EditText)findViewById(R.id.pp_id_address);
        zip=(EditText)findViewById(R.id.pp_id_pin);
        city_spinner=(Spinner)findViewById(R.id.pp_id_city);
        state_spinner=(Spinner)findViewById(R.id.pp_id_state);
        phno=(EditText)findViewById(R.id.pp_ph_no);
        country=(EditText)findViewById(R.id.pp_id_country);
save=(Button)findViewById(R.id.save_bt);
        next=(Button)findViewById(R.id.next_bt);

        session = new UserSessionLogin(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();

        Intent i = getIntent();
        grand_total=i.getStringExtra("grand_total");
         user_email = i.getStringExtra("user_email");
        user_name =i.getStringExtra("user_name");
        email.setText(user_email);
        email.setEnabled(false);
        name.setText(user_name);

        new GetUserData().execute();
        //next.setEnabled(false);
        String user_email1 = email.getText().toString();
        String user_first_name=name.getText().toString();
        String user_last_name=last_name.getText().toString();
        String address= user_adress.getText().toString();
     //   String state1=state.getText().toString();
       // String city1=city.getText().toString();
        String user_pin=zip.getText().toString();
        String ph_no=phno.getText().toString();
        String country1=country.getText().toString();

       next.setEnabled(false);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(india_DEMOcart_User_Profile.this, Place_Order_List.class);
                i.putExtra("grand_total", grand_total);
                finish();
                startActivity(i);


            }
        });



        save.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        String user_email1 = email.getText().toString();
        String user_first_name=name.getText().toString();
        String user_last_name=last_name.getText().toString();
        String address= user_adress.getText().toString();
        String state=state_selected.toString();
        String city=city_selected.toString();
        String user_pin=zip.getText().toString();
        String ph_no=phno.getText().toString();
        String country1=country.getText().toString();

Log.d("->"+user_email1+"->"+user_first_name+"->"+user_last_name+"->"+address+"->"+state+"->"+
        city+"->"+user_pin+"->"+ph_no+"->"+country1,"");
       if((!user_email1.equals("")  && !user_first_name.equals("")&& !user_last_name.equals("")
               && !address.equals("")&& !state.equals("")&& !city.equals("")&&
           !user_pin.equals("")&& !ph_no.equals("") && !country1.equals("")))
        {
             new GetContacts().execute();
            next.setEnabled(true);
        }
        else
        {
            Toast.makeText(getApplication(), "All fields are  mandatory", Toast.LENGTH_SHORT).show();

        }

    }
});

//code for state and city

        adp1=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,states);
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

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;
                    case 1:
                        city = getResources().getStringArray(R.array.AndhraPradesht);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;
                    case 2:
                        city = getResources().getStringArray(R.array.assam);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;
                    case 3:
                        city = getResources().getStringArray(R.array.bihar);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;
                    case 4:
                        city = getResources().getStringArray(R.array.goa);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;

                    case 5:
                        city = getResources().getStringArray(R.array.gujrat);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;

                    case 6:
                        city = getResources().getStringArray(R.array.Haryana_list);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;
                    case 7:
                        city = getResources().getStringArray(R.array.Mplist);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;
                    case 8:
                        city = getResources().getStringArray(R.array.Maharastra_list);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);
                        //  Toast.makeText(getApplication(), "2222222222", Toast.LENGTH_SHORT).show();
                        select();

                        break;
                    case 9:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);
                        //      Toast.makeText(getApplication(), "2222222222", Toast.LENGTH_SHORT).show();
                        //   select();

                        break;
                    case 10:
                        city = getResources().getStringArray(R.array.uttarakhand_list);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;
                    case 11:
                        city = getResources().getStringArray(R.array.up_list);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;
                    case 12:
                        city = getResources().getStringArray(R.array.chandigarah);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;
                    case 13:
                        city = getResources().getStringArray(R.array.delhi);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;
                    case 14:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;
                    case 15:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);
                        select();

                        break;
                    case 16:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;
                    case 17:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();
                        break;
                    case 18:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);
                        select();  //   select();

                        break;
                    case 19:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;
                    case 20:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();
                        break;
                    case 21:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();
                        break;
                    case 22:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);
                        select();
                        break;
                    case 23:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;

                    case 24:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;
                    case 25:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;
                    case 26:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;
                    case 27:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        select();

                        break;
                    case 28:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        //   select();

                        break;
                    case 29:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
                                android.R.layout.simple_dropdown_item_1line, city);
                        city_spinner.setAdapter(adp2);

                        //   select();

                        break;
                    case 30:
                        city = getResources().getStringArray(R.array.punjab_list);

                        adp2 = new ArrayAdapter<String>(india_DEMOcart_User_Profile.this,
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

    private class GetUserData extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(india_DEMOcart_User_Profile.this);
            pDialog.setMessage("Please wait...");
         //   pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params1) {


            HashMap<String, String> user = session.getUserDetails();
            String user1 = (user.get(UserSessionLogin.KEY_EMAIL_SESSION));
            String email= user1.toString();

            List<NameValuePair> params = new ArrayList<NameValuePair>();
          //  List params = new ArrayList();
            params.add(new BasicNameValuePair("email",email));
            //
            ServiceHandler sh = new ServiceHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url_getdata, ServiceHandler.GET,params);

            Log.d("Response: ", "> " + jsonStr);


            if (jsonStr != null) {
                try {

                    JSONObject json = new JSONObject(jsonStr);
                    int success = json.getInt(TAG_SUCCESS);

                    Log.d("Login attempt", json.toString());
                    // json success tag

                    if (success == 1) {
                        contacts = json.getJSONArray("product");

                            JSONObject c = contacts.getJSONObject(0);

                          //  name = (EditText) findViewById(R.id.pp_id_name);
                       //     last_name = (EditText) findViewById(R.id.pp_id_last_name);
                            // email =(EditText)findViewById(R.id.id_user_email);
                       //     user_adress = (EditText) findViewById(R.id.pp_id_address);
                        //    zip = (EditText) findViewById(R.id.pp_id_pin);
                        //    city = (EditText) findViewById(R.id.pp_id_city);
                         //   state = (EditText) findViewById(R.id.pp_id_state);
                      //      phno = (EditText) findViewById(R.id.pp_ph_no);
                        //    country = (EditText) findViewById(R.id.pp_id_country);
                        str_name=c.getString((TAG_NAME));
                        str_last_name= c.getString(TAG_LAST_NAME);
                        str_city=c.getString(TAG_CITY);
                        str_country=c.getString(TAG_COUNTRY);
                        str_state=c.getString(TAG_STATE);
                        str_pin_code=c.getString(TAG_PIN);
                        str_address=c.getString(TAG_ADDRESS);
                        str_phone_no=c.getString(TAG_PHONE_NO);
                   //         name.setText(name_j.toString());
                      // last_name.setText( (c.getString(TAG_LAST_NAME)));
                        //   user_adress.setText(c.getString(TAG_ADDRESS));
                           //zip.setText( c.getString(TAG_PIN));
                            //city.setText(c.getString(TAG_CITY));
                      //      state.setText( c.getString(TAG_STATE));
                        //    country.setText(c.getString(TAG_COUNTRY));
                          //  phno.setText(c.getString(TAG_PHONE_NO));
                        Log.d("",""+c.getString((TAG_LAST_NAME)));

                        Log.d("",""+c.getString((TAG_NAME)));
                    }

                    Log.d("success", "" + success);
                  //  publishProgress(success);
                } catch (JSONException e) {
                    e.printStackTrace();
                }}
            else {
                Log.d("poooooo", "nnnnn");
            }
                return null;

        }


        @Override
        protected void onPostExecute(String file_url) {
            //super.onPostExecute(result);
            // Dismiss the progress dialog
                name.setText(str_name);
                last_name.setText( str_last_name);
                user_adress.setText(str_address);
                zip.setText( str_pin_code);
           // city_spinner.setT
             //   city.setText(str_city);
               // state.setText( str_state);
                country.setText(str_country);
                 phno.setText(str_phone_no);
            if (pDialog.isShowing())
                pDialog.dismiss();
        }
    }

    /**
     * Async task class to get json by making HTTP call
     * */

    private class GetContacts extends AsyncTask<Void,Integer, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(india_DEMOcart_User_Profile.this);
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
            String state1=state_selected.toString();
            String city1=city_selected.toString();
            String user_pin=zip.getText().toString();
            String ph_no=phno.getText().toString();
            String countryname=country.getText().toString();
            Log.d("data", "" + user_email + "-->" + user_first_name + "\n"
                    + user_last_name + "\n" + address + "\n" + state1 + "\n" + city + "\n");
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
                Toast.makeText(getApplicationContext(),"Update",Toast.LENGTH_LONG).show();
                //  String user_email = email.getText().toString();
                // String user_name=name.getText().toString();
              //  Intent i = new Intent(DEMOcart_User_Profile.this, DEMOcart_User_Profile.class) ;//
               // i.putExtra("user_email",user_email);
                //i.putExtra("user_name",user_name);
                //finish();
                //startActivity(i);
                //  return json.getString(TAG_MESSAGE);
            } else {
                Log.d("update fail"," fail");
                Toast.makeText(india_DEMOcart_User_Profile.this," Fail To Update",Toast.LENGTH_LONG).show();
                // return json.getString(TAG_MESSAGE);
            }
        }
    }
}
