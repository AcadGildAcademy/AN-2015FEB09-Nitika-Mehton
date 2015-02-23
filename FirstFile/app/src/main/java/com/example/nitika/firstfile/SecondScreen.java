package com.example.nitika.firstfile;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Created by NITIKA on 10-Feb-15.
 */
public class SecondScreen extends ActionBarActivity {
    ListView lv ;
    TextView tv;
    Button b_asc,b_desc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_name);//imp
        ActionBar actionBar = getActionBar();


        getActionBar();
        tv = (TextView) findViewById(R.id.id_text);
        lv = (ListView) findViewById(R.id.id_list);
        b_asc = (Button) findViewById(R.id.id_asc);
        b_desc = (Button) findViewById(R.id.id_desc);

        //code for bundle extraction and welcome note
        Bundle bundle = getIntent().getExtras();

        if (!bundle.isEmpty()) {
            final String name = bundle.getString("key_username");
            int id = bundle.getInt("key_id");
            tv.setText("welcome " + name + " your id is " + id);
        }
        //code for list
        final String month[] = {"jan", "feb", "march", "april", "may", "june", "july", "augest", "sep", "oct", "nov", "dec"};


        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, month);
        lv.setAdapter(aa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                      @Override
                                      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                          Toast.makeText(getApplicationContext(), month[position], Toast.LENGTH_LONG).show();
                                      }
                                  }
        );

        //code for ascending button


        b_asc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //

                Arrays.sort(month);
                ArrayAdapter<String> aa = new ArrayAdapter<String>(SecondScreen.this, android.R.layout.simple_list_item_1, android.R.id.text1, month);
                lv.setAdapter(aa);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                              @Override
                                              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                                  Toast.makeText(getApplicationContext(), month[position], Toast.LENGTH_LONG).show();
                                              }
                                          }
                );
                //
            }


        });

        b_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Arrays.sort(month);
                List list =Arrays.asList(month);
                Collections.reverse(list);

                ArrayAdapter<String> aa = new ArrayAdapter<String>(SecondScreen.this, android.R.layout.simple_list_item_1, android.R.id.text1, month);
                lv.setAdapter(aa);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                              @Override
                                              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                                  Toast.makeText(getApplicationContext(), month[position], Toast.LENGTH_LONG).show();
                                              }
                                          }
                );
            }
        });

        registerForContextMenu(lv);

    }


    //code for menu


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater m =getMenuInflater();
        m.inflate(R.menu.menu,menu);

        //menu.add(0,01,1,"hai");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
       // item.setTitle("eku pagal hai");
        if(id==R.id.fav)
        {
            Toast.makeText(getApplicationContext(), "id fav", Toast.LENGTH_LONG).show();
        }
        else if(id==R.id.fav1)
        {
            Toast.makeText(getApplicationContext(), "id fav1", Toast.LENGTH_LONG).show();
        }
          else if (id==R.id.fav2)
        {
            Toast.makeText(getApplicationContext(), "id fav2", Toast.LENGTH_LONG).show();
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("welcome");
        menu.add(0,01,1,"menu id 1");

    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId()==1){
            Toast.makeText(getApplicationContext(),"Clicked on " + item.getTitle(),Toast.LENGTH_LONG).show();
        }
        return super.onContextItemSelected(item);
    }
    //end code for menus
}
