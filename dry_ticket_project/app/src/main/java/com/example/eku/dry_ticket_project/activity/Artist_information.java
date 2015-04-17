package com.example.eku.dry_ticket_project.activity;

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

import com.example.eku.dry_ticket_project.R;
import com.example.eku.dry_ticket_project.adapter.ListViewAdapter_artists;
import com.example.eku.dry_ticket_project.utils.JSONfunctions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by megha on 4/6/2015.
 */
public class Artist_information extends ActionBarActivity
{
    // Declare Variables
    JSONObject jsonobject;
    JSONArray jsonarray;
    ListView listview;
    ListViewAdapter_artists adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;
   public static String ARTIST_ID = "artist_id";
    public static String ARTIST_NAME = "artist_name";
    public static String ARTIST_DESCRIPTION = "artist_description";
    public static String ARTIST_PHOTO_PATH = "artist_photo_path";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Get the view from listview_main.xml
        setContentView(R.layout.artist_listview);
        // Execute DownloadJSON AsyncTask
        new DownloadJSON().execute();
      getSupportActionBar();
    }

    // DownloadJSON AsyncTask
    private class DownloadJSON extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(Artist_information.this);
            mProgressDialog.setTitle("PLEASE wait for a while");
            mProgressDialog.setMessage("Loading...Artist page");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }
        @Override
        protected Void doInBackground(Void... params)
        {
            // Create an array
            arraylist = new ArrayList<HashMap<String, String>>();
            // Retrieve JSON Objects from the given URL address
            jsonobject = JSONfunctions.getJSONfromURL("http://bishasha.com/json/artists_information.php");
            try
            {
                // Locate the array name in JSON
                jsonarray = jsonobject.getJSONArray("artist_information");
                for (int i = 0; i < jsonarray.length(); i++)
                {
                    HashMap<String, String> map = new HashMap<String, String>();
                    jsonobject = jsonarray.getJSONObject(i);
                    // Retrive JSON Objects
                    map.put("artist_name", jsonobject.getString("artist_name"));
                    map.put("artist_description", jsonobject.getString("artist_description"));
                    map.put("artist_photo_path",jsonobject.getString("artist_photo_path"));
                    //map.put("event_name", jsonobject.getString("event_name"));
                    arraylist.add(map);
                }
            }
            catch (JSONException e)
            {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void args)
        {
            // Locate the listview in listview_main.xml
            listview = (ListView) findViewById(R.id.artist_list);
            // Pass the results into ListViewAdapter.java
            adapter = new ListViewAdapter_artists(Artist_information.this, arraylist);
            // Set the adapter to the ListView
            listview.setAdapter(adapter);
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    }  @Override
       public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater m = getMenuInflater();
    m.inflate(R.menu.menu_file, menu);
    return super.onCreateOptionsMenu(menu);
}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sign_in) {
            Intent intent = new Intent(Artist_information.this, sign_in.class);
            startActivity(intent);
        } else if (id == R.id.add_menu) {
            Intent intent = new Intent(Artist_information.this, sign_up.class);
            startActivity(intent);

            return true;
        } else if (id == R.id.now_on_sale) {
            Intent intent = new Intent(Artist_information.this, NowOnSale.class);
            startActivity(intent);

        } else if (id == R.id.upcoming) {
            Intent intent = new Intent(Artist_information.this, UpcomingEvents.class);
            intent.putExtra("url_string","http://bishasha.com/json/upcoming_events.php");
            startActivity(intent);

        } else if (id == R.id.past) {
            Intent intent = new Intent(Artist_information.this, PastEvents.class);
            intent.putExtra("url_string", "http://bishasha.com/json/past_events.php");
            startActivity(intent);

        } else if (id == R.id.booking) {
            Intent intent = new Intent(Artist_information.this, Seat_allocation.class);
            startActivity(intent);

        } else if (id == R.id.artists) {
            Intent intent = new Intent(Artist_information.this, Artist_information.class);
            startActivity(intent);

        } else if (id == R.id.venue) {
            Intent intent = new Intent(Artist_information.this, Venue.class);
            startActivity(intent);

        }else if (id == R.id.contact_us) {
            Intent intent = new Intent(Artist_information.this, ContactUs.class);
            startActivity(intent);


        }

        return super.onOptionsItemSelected(item);
    }

}
