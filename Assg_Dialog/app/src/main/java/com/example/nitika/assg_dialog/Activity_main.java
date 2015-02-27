package com.example.nitika.assg_dialog;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by NITIKA on 23-Feb-15.
 */
public class Activity_main extends ActionBarActivity {

    final Context context = this;
     ListView list;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);
        Toast.makeText(getApplicationContext(), "You Clicked at " + "1", Toast.LENGTH_SHORT).show();
        Set<String> name;
        Set<String> ph_no;
        name= new HashSet<String>();
        ph_no= new HashSet<String>();
        name.add("nitika");
        ph_no.add("467346799823");
        name.add("ekta");
        ph_no.add("5423566");

        Custom_list adapter1 = new Custom_list(Activity_main.this,name,ph_no );
        list = (ListView) findViewById(R.id.id_listView);
       list.setAdapter(adapter1);
        Toast.makeText(getApplicationContext(), "You Clicked at " + "2", Toast.LENGTH_SHORT).show();
       list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "You Clicked at " + name[+position], Toast.LENGTH_SHORT).show();
            }
        });
 getActionBar();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m= getMenuInflater();
        m.inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id==R.id.menu_pluse )
        {


            Toast.makeText(getApplication(),"done",Toast.LENGTH_LONG).show();

            // get prompts.xml view
            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.alert_fragment_dialog_ui, null);

           AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            // set prompts.xml to alertdialog builder
            alertDialogBuilder.setView(promptsView);

           final EditText username = (EditText) promptsView
                    .findViewById(R.id.id_name);
            final EditText userph = (EditText) promptsView
                    .findViewById(R.id.id_phone_no);

            // set dialog message
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                          add();



                        }
                    })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
          // Alert_fragment_dialog alert_fragment_dialog =new Alert_fragment_dialog();
            //alert_fragment_dialog.show(fm,"hello>>>>>");
        }

        return super.onOptionsItemSelected(item);
    }


 void add()
 {
     Toast.makeText(getApplicationContext(),"<<>>>",Toast.LENGTH_LONG).show();



 }




}
