package com.example.eku.dry_ticket_project.activity;

/**
 * Created by DELL on 03-04-2015.
 */
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.eku.dry_ticket_project.utils.JSONfunctions;
import com.example.eku.dry_ticket_project.adapter.ListViewAdapter_venue;
import com.example.eku.dry_ticket_project.R;

public class Venue extends ActionBarActivity {
    // Declare Variables
    JSONObject jsonobject;
    JSONArray jsonarray;
    ListView listview;
    ListViewAdapter_venue adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;
    public static String TITLE = "venue_name";

    public static String DESC = "venue_desc";

    public static String IMAGE_PATH = "venue_image_path";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from listview_main.xml
        setContentView(R.layout.listview_main);
        // Execute DownloadJSON AsyncTask
        new DownloadJSON().execute();
       getSupportActionBar();
    }

    // DownloadJSON AsyncTask
    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(Venue.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Wait For Result");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            // Create an array
            arraylist = new ArrayList<HashMap<String, String>>();
            // Retrieve JSON Objects from the given URL address
            jsonobject = JSONfunctions
                    .getJSONfromURL("http://bishasha.com/json/venue_event.php");

            try {
                // Locate the array name in JSON
                jsonarray = jsonobject.getJSONArray("venue_event");

                for (int i = 0; i < jsonarray.length(); i++) {
                    HashMap<String, String> map = new HashMap<String, String>();
                    jsonobject = jsonarray.getJSONObject(i);
                    // Retrive JSON Objects
                    map.put("venue_name", jsonobject.getString("venue_name"));
                    map.put("venue_desc", jsonobject.getString("venue_desc"));
                    map.put("venue_image_path", jsonobject.getString("venue_image_path"));

                    // Set the JSON Objects into the array
                    arraylist.add(map);
                }
            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(R.id.listview);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter_venue(Venue.this, arraylist);
            // Set the adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
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
            Intent intent = new Intent(Venue.this, sign_in.class);
            startActivity(intent);
        } else if (id == R.id.add_menu) {
            Intent intent = new Intent(Venue.this, sign_up.class);
            startActivity(intent);

            return true;
        } else if (id == R.id.now_on_sale) {
            Intent intent = new Intent(Venue.this, NowOnSale.class);
            startActivity(intent);

        } else if (id == R.id.upcoming) {
            Intent intent = new Intent(Venue.this, UpcomingEvents.class);
            intent.putExtra("url_string", "http://bishasha.com/json/upcoming_events.php");
            startActivity(intent);

        } else if (id == R.id.past) {
            Intent intent = new Intent(Venue.this, PastEvents.class);
            intent.putExtra("url_string", "http://bishasha.com/json/past_events.php");
            startActivity(intent);

        } else if (id == R.id.booking) {
            Intent intent = new Intent(Venue.this,Seat_allocation.class);
            startActivity(intent);

        } else if (id == R.id.artists) {
            Intent intent = new Intent(Venue.this, Artist_information.class);
            startActivity(intent);

        } else if (id == R.id.venue) {
            Intent intent = new Intent(Venue.this, Venue.class);
            startActivity(intent);

        } else if (id == R.id.contact_us) {
            Intent intent = new Intent(Venue.this, ContactUs.class);
            startActivity(intent);


        }
        return super.onOptionsItemSelected(item);
    }
}


