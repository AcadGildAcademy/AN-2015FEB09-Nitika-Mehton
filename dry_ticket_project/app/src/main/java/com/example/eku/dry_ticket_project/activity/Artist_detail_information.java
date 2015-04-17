package com.example.eku.dry_ticket_project.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eku.dry_ticket_project.R;
import com.example.eku.dry_ticket_project.utils.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;


public class Artist_detail_information extends ActionBarActivity
{
    // Declare Variables
    // String event_name;
   public String artist_name;
   public String artist_photo_path;
   public String artist_description;

   ImageLoader imageLoader = new ImageLoader(this);
    HashMap<String, String> resultp = new HashMap<String, String>();
    ArrayList<HashMap<String, String>> data;
    //Button buy_btn;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_page_design);
        artist_name = getIntent().getExtras().getString("artist_name");
        artist_photo_path = getIntent().getExtras().getString("artist_photo_path");
        artist_description = getIntent().getExtras().getString("artist_description");

        TextView name = (TextView) findViewById(R.id.artist_name);
        ImageView photo = (ImageView) findViewById(R.id.artist_image);
        TextView description = (TextView) findViewById(R.id.artist_description);

        // Capture position and set results to the ImageView
        // Passes image URL into ImageLoader.class
        imageLoader.DisplayImage(artist_photo_path, photo);
        name.setText(artist_name);
        description.setText(artist_description);
        final Context context = this;

       getSupportActionBar();
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
            Intent intent = new Intent(Artist_detail_information.this, sign_in.class);
            startActivity(intent);
        } else if (id == R.id.add_menu) {
            Intent intent = new Intent(Artist_detail_information.this, sign_up.class);
            startActivity(intent);

            return true;
        } else if (id == R.id.now_on_sale) {
            Intent intent = new Intent(Artist_detail_information.this, NowOnSale.class);
            startActivity(intent);

        } else if (id == R.id.upcoming) {
            Intent intent = new Intent(Artist_detail_information.this, UpcomingEvents.class);
            intent.putExtra("url_string","http://bishasha.com/json/upcoming_events.php");
            startActivity(intent);

        } else if (id == R.id.past) {
            Intent intent = new Intent(Artist_detail_information.this, PastEvents.class);
            intent.putExtra("url_string", "http://bishasha.com/json/past_events.php");
            startActivity(intent);

        } else if (id == R.id.booking) {
            Intent intent = new Intent(Artist_detail_information.this, Seat_allocation.class);
            startActivity(intent);

        } else if (id == R.id.artists) {
            Intent intent = new Intent(Artist_detail_information.this, Artist_information.class);
            startActivity(intent);

        } else if (id == R.id.venue) {
            Intent intent = new Intent(Artist_detail_information.this, Venue.class);
            startActivity(intent);

        }else if (id == R.id.contact_us) {
            Intent intent = new Intent(Artist_detail_information.this, ContactUs.class);
            startActivity(intent);


        }

        return super.onOptionsItemSelected(item);
    }

}
