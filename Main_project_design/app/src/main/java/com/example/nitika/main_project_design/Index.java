package com.example.nitika.main_project_design;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by NITIKA on 05-Mar-15.
 */
public class Index extends ActionBarActivity {

    TextView tv;
    UserSessionLogin session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_ui);
        tv = (TextView) findViewById(R.id.textView2);
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

        getActionBar();
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
        if (id == R.id.login_id_menu) {
            Intent intent = new Intent(Index.this, Login.class);
            startActivity(intent);
        }
        if (id == R.id.signup_id_menu) {
            Intent intent = new Intent(Index.this, SignUp.class);
            startActivity(intent);
        }
        if(id==R.id.mobile)
        {
            Intent intent =new Intent(Index.this,Activity_main.class);
            startActivity(intent);
        }
        if(id==R.id.camera)
        {
            Intent intent =new Intent(Index.this,Activity_main.class);
            startActivity(intent);
        }
        if(id==R.id.fire_table)
        {
            Intent intent =new Intent(Index.this,Activity_main.class);
            startActivity(intent);
        }
        if(id==R.id.accessories)
        {
            Intent intent =new Intent(Index.this,Activity_main.class);
            startActivity(intent);
        }
        if(id==R.id.car)
        {
            Intent intent =new Intent(Index.this,Activity_main.class);
            startActivity(intent);
        }
        if(id==R.id.laptop_computer)
        {
            Intent intent =new Intent(Index.this,Activity_main.class);
            startActivity(intent);
        }
        if(id==R.id.tablets)
        {
            Intent intent =new Intent(Index.this,Activity_main.class);
            startActivity(intent);
        }
        if(id==R.id.video_games)
        {
            Intent intent =new Intent(Index.this,Activity_main.class);
            startActivity(intent);
        }
        if(id==R.id.gadgets)
        {
            Intent intent =new Intent(Index.this,Activity_main.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }


}
