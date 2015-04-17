package com.example.eku.dry_ticket_project.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eku.dry_ticket_project.R;
import com.example.eku.dry_ticket_project.utils.ServiceHandler;
import com.example.eku.dry_ticket_project.pref.UserSession;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactUs extends ActionBarActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    EditText fullname,fullphone,fullemail,fullmessage;
    Button sendbutton;
    private ProgressDialog pDialog;
    UserSession session;

    private static String url="http://bishasha.com/json/contact_us.php";
    private static final String TAG_SUCCESS="success";

    ArrayList<HashMap<String,String>>contactList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        contactList=new ArrayList<HashMap<String, String>>();
        fullname=(EditText)findViewById(R.id.contactus1);
        fullphone=(EditText)findViewById(R.id.contactus2);
        fullemail=(EditText)findViewById(R.id.contactus3);
        fullmessage=(EditText)findViewById(R.id.contactus4);
        sendbutton=(Button)findViewById(R.id.send);

    /*    if (session.isUserLoggedIn()) {
            sendbutton.setEnabled(true);
        }
        else {
            Intent intent=new Intent(ContactUs.this,sign_in.class);
            startActivity(intent);
        }
*/


        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_value=fullname.getText().toString();
                String phone_value=fullphone.getText().toString();
                String email_value=fullemail.getText().toString();
                String msg_value=fullmessage.getText().toString();

                if (name_value.equals("") || phone_value.equals("")||email_value.equals("")||msg_value.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "All fields are mandatory", Toast.LENGTH_SHORT).show();

                }
                else if (!isValidEmail(email_value)) {
                    fullemail.setError("Invalid Email");
                }


                else   if (!isValidPhone(phone_value)) {
                    fullphone.setError("Invalid Password");
                }
               else
                {
                    // Calling async task to get json
                   new GetContacts().execute();
                }
            }
        });getSupportActionBar();
        //getActionBar();
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(-33.820558,151.00706)).title("Dry Tickets"));
        CameraUpdate center=
                CameraUpdateFactory.newLatLng(new LatLng(-33.820558,151.00706));
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(15);
        mMap.moveCamera(center);
        mMap.animateCamera(zoom);
    }


    private class GetContacts extends AsyncTask<Void,Integer,Void> {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            //showing progress dialog
            pDialog=new ProgressDialog(ContactUs.this);
            pDialog.setMessage("Please wait..");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            int success;
            String name_value=fullname.getText().toString();
            String phone_value=fullphone.getText().toString();
            String email_value=fullemail.getText().toString();
            String msg_value=fullmessage.getText().toString();

            try{
                List params=new ArrayList();

                params.add(new BasicNameValuePair("name",name_value));
                params.add(new BasicNameValuePair("phone",phone_value));
                params.add(new BasicNameValuePair("email",email_value));
                params.add(new BasicNameValuePair("message",msg_value));
                ServiceHandler sh=new ServiceHandler();
                Log.d("request!","starting");

                String jsonStr=sh.makeServiceCall(url,2,params);
                JSONObject json=new JSONObject(jsonStr);
                success=json.getInt(TAG_SUCCESS);
                Log.d("success","success");
                publishProgress(success);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(pDialog.isShowing())
                pDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int success=values[0];
        /*    String name_value=fullname.getText().toString();
            String phone_value=fullphone.getText().toString();
            String email_value=fullemail.getText().toString();
            String msg_value=fullmessage.getText().toString();
*/

            if (success==1)
            {
                Log.d("Enquiry saved","Enquiry Saved");
                Toast.makeText(getApplicationContext(),"Enquiry Saved",Toast.LENGTH_LONG).show();
               // session.createUserLoginSession(name_value,email_value);
               // finish();
            }
            else
            {
                Log.d("Failure","fail");
                Toast.makeText(getApplicationContext(),"fail to save",Toast.LENGTH_LONG).show();
            }
        }
    }  // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating phone no
    private boolean isValidPhone(String phone) {
        if (phone != null && phone.length() >=10) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.menu_file, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sign_in) {
            Intent intent = new Intent(ContactUs.this, sign_in.class);
            startActivity(intent);
        } else if (id == R.id.add_menu) {
            Intent intent = new Intent(ContactUs.this, sign_up.class);
            startActivity(intent);

            return true;
        } else if (id == R.id.now_on_sale) {
            Intent intent = new Intent(ContactUs.this, NowOnSale.class);
            startActivity(intent);

        } else if (id == R.id.upcoming) {
            Intent intent = new Intent(ContactUs.this, UpcomingEvents.class);
            intent.putExtra("url_string","http://bishasha.com/json/upcoming_events.php");
            startActivity(intent);

        } else if (id == R.id.past) {
            Intent intent = new Intent(ContactUs.this, PastEvents.class);
            intent.putExtra("url_string", "http://bishasha.com/json/past_events.php");
            startActivity(intent);

        } else if (id == R.id.booking) {
            Intent intent = new Intent(ContactUs.this, Seat_allocation.class);
            startActivity(intent);

        } else if (id == R.id.artists) {
            Intent intent = new Intent(ContactUs.this, Artist_information.class);
            startActivity(intent);

        } else if (id == R.id.venue) {
            Intent intent = new Intent(ContactUs.this, Venue.class);
            startActivity(intent);

        }else if (id == R.id.contact_us) {
            Intent intent = new Intent(ContactUs.this, ContactUs.class);
            startActivity(intent);


        }

        return super.onOptionsItemSelected(item);
    }


}





