package com.example.nitika.demo_dialog;

import android.app.AlertDialog;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends FragmentActivity {
             Button dbutton;
            Button frag_button;

    FragmentManager fm=getFragmentManager();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbutton=(Button)findViewById(R.id.id_dbutton);
        frag_button=(Button)findViewById(R.id.id_frag_button);
        dbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("My dialog ")
                        .setMessage("do u want to close")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                     finish();

                            }
                        })
                        .setNegativeButton("no",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),"No",Toast.LENGTH_LONG).show();
                            }
                        })
                        .setIcon(R.drawable.ic_launcher)
                        .show();

            }
        });

        frag_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alert_Fragment alert_fragment=new Alert_Fragment();
                alert_fragment.show(fm,"hello");



            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
