package com.example.eku.dry_ticket_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eku.dry_ticket_project.R;
import com.example.eku.dry_ticket_project.pref.UserSession;

import java.util.HashMap;

/**
 * Created by DELL on 11-03-2015.
 */
public class first_activity extends ActionBarActivity{
    // FragmentManager fm = getFragmentManager();
    TextView dry,welcome;
    Button stop;
    UserSession session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        dry = (TextView) findViewById(R.id.txtdry);
       welcome = (TextView) findViewById(R.id.txtwelcome);
        session=new UserSession(getApplicationContext());
       HashMap<String, String> user= session.getUserDetails();
        if(session.isUserLoggedIn())
        {
            String user_entered= user.get(UserSession.KEY_EMAIL);
            welcome.setText(user_entered);
        }
        // stop=(Button)findViewById(R.id.stop);

        welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"welcome in drytickets",Toast.LENGTH_SHORT).show();


            }
        });
        dry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dry.setVisibility(View.GONE);
            }
        });
        getSupportActionBar();
       // getActionBar();
        // changing action bar color
        //  getActionBar().setBackgroundDrawable(
        //        new ColorDrawable(Color.parseColor("#ffff1217")));
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
            Intent intent = new Intent(first_activity.this, sign_in.class);
            startActivity(intent);
        } else if (id == R.id.add_menu) {
            Intent intent = new Intent(first_activity.this, sign_up.class);
            startActivity(intent);
            // CustomDialog cd = new CustomDialog();
            // cd.show(fm, "Dialog");

            return true;
        } else if (id == R.id.now_on_sale) {
            Intent intent = new Intent(first_activity.this, NowOnSale.class);
            startActivity(intent);

        } else if (id == R.id.upcoming) {
            Intent intent = new Intent(first_activity.this, UpcomingEvents.class);
            intent.putExtra("url_string", "http://bishasha.com/json/upcoming_events.php");
            startActivity(intent);

        } else if (id == R.id.past) {
            Intent intent = new Intent(first_activity.this, PastEvents.class);
            intent.putExtra("url_string", "http://bishasha.com/json/past_events.php");
            startActivity(intent);

        } else if (id == R.id.booking) {
            Intent intent = new Intent(first_activity.this, Seat_allocation.class);
            startActivity(intent);

        } else if (id == R.id.artists) {
            Intent intent = new Intent(first_activity.this, Artist_information.class);
            startActivity(intent);

        } else if (id == R.id.venue) {
            Intent intent = new Intent(first_activity.this, Venue.class);
            startActivity(intent);

        } else if (id == R.id.contact_us) {
            Intent intent = new Intent(first_activity.this, ContactUs.class);
            startActivity(intent);


        } return super.onOptionsItemSelected(item);
    }
}
