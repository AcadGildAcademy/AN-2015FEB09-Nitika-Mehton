package com.example.nitika.main_project_design;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NITIKA on 05-Mar-15.
 */
public class Index extends ActionBarActivity {
  Spinner menu;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_ui);



        getActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if(id==R.id.login_id_menu)
        {
            Intent intent=new Intent(Index.this,Login.class);
            startActivity(intent);
        }
        if(id==R.id.signup_id_menu)
        {
            Intent intent=new Intent(Index.this,Sign_up.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}

