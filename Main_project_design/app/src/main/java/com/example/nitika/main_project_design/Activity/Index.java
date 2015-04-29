package com.example.nitika.main_project_design.Activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nitika.main_project_design.R;
import com.example.nitika.main_project_design.Utiles.UserSessionLogin;

import java.util.HashMap;

/**
 * Created by NITIKA on 05-Mar-15.
 */
public class Index extends ActionBarActivity
{

    TextView tv;
    UserSessionLogin session;
    Button order_btn,contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_ui);
        tv = (TextView) findViewById(R.id.textView2);
        order_btn=(Button)findViewById(R.id.my_order_btn);
        contact=(Button)findViewById(R.id.contact);


        session = new UserSessionLogin(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        // get name
        if( session.isUserLoggedIn()) {
            String user_str = user.get(UserSessionLogin.KEY_EMAIL_SESSION);
            tv.setText(user_str);
        }
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( session.isUserLoggedIn()) {
                    session = new UserSessionLogin(getApplicationContext());
                    HashMap<String, String> user = session.getUserDetails();
                    String user_str = user.get(UserSessionLogin.KEY_EMAIL_SESSION);
                    Intent intent = new Intent(Index.this, Contact_us.class);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(getApplicationContext(),"Login First",Toast.LENGTH_SHORT).show();
                }


            }
        });
        order_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session = new UserSessionLogin(getApplicationContext());
                HashMap<String, String> user = session.getUserDetails();
                // get name
                if( session.isUserLoggedIn()) {
                    String user_str = user.get(UserSessionLogin.KEY_EMAIL_SESSION);
                   Intent intent = new Intent(Index.this, See_my_order.class);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(getApplicationContext(),"Login First",Toast.LENGTH_SHORT).show();
                }

            }
        });
  //  getSupportActionBar();
       // getActionBar();
     getSupportActionBar();

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

        if(id==R.id.home_id_menu)
        {
            Intent intent = new Intent(Index.this, Index.class);
           finish();
            startActivity(intent);
        }
        if (id == R.id.login_id_menu) {
            Intent intent = new Intent(Index.this, Login.class);

            startActivity(intent);
        }

        if(id == R.id.cart_menu)
        {
            session = new UserSessionLogin(getApplicationContext());
            HashMap<String, String> user = session.getUserDetails();
            // get name
            if( session.isUserLoggedIn()) {
                String user_str = user.get(UserSessionLogin.KEY_EMAIL_SESSION);
                Intent intent = new Intent(Index.this, Cart_item.class);
                startActivity(intent);

            }
            else{
                Toast.makeText(getApplicationContext(),"Login First",Toast.LENGTH_SHORT).show();
            }
        }
        if(id==R.id.mobile)
        {
            Intent intent =new Intent(Index.this,Activity_main.class);
            intent.putExtra("category","mobile");
            startActivity(intent);
        }
        if(id==R.id.camera)
        {
            Intent intent =new Intent(Index.this,Activity_main.class);
            intent.putExtra("category","camera");
            startActivity(intent);
        }
        if(id==R.id.fire_table)
        {
            Intent intent =new Intent(Index.this,Activity_main.class);
            intent.putExtra("category","fire table");
            startActivity(intent);
        }
        if(id==R.id.accessories)
        {
            Intent intent =new Intent(Index.this,Activity_main.class);
            intent.putExtra("category","accessories");
            startActivity(intent);
        }
        if(id==R.id.car)
        {
            Intent intent =new Intent(Index.this,Activity_main.class);
            intent.putExtra("category","Car");
            startActivity(intent);
        }
        if(id==R.id.laptop_computer)
        {
            Intent intent =new Intent(Index.this,Activity_main.class);
            intent.putExtra("category","computer");
            startActivity(intent);
        }
        if(id==R.id.tablets)
        {
            Intent intent =new Intent(Index.this,Activity_main.class);
            intent.putExtra("category","tablets");
            startActivity(intent);
        }
        if(id==R.id.video_games)
        {
            Intent intent =new Intent(Index.this,Activity_main.class);
            intent.putExtra("category","video games");
            startActivity(intent);
        }
        if(id==R.id.gadgets)
        {
            Intent intent =new Intent(Index.this,Activity_main.class);
            intent.putExtra("category","gadgets");
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }


}
