package com.example.nitika.demo_intent1;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URI;
import java.net.URL;

/**
 * Created by NITIKA on 15-Feb-15.
 */
public class Second_file extends Activity {
    Button b_specific,b_developer,b_search;
    EditText ed_search;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_ui);

        b_specific =(Button)findViewById(R.id.id_specific);
        b_developer=(Button)findViewById(R.id.id_developer);
        b_search=(Button)findViewById(R.id.id_b_search);
        ed_search=(EditText)findViewById(R.id.id_txt_search);

           b_specific.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {


                   final Uri marketUri = Uri.parse("market://details?id=" + "com.venticake.retrica&hl=en");


                   startActivity(new Intent(Intent.ACTION_VIEW, marketUri));

               }
           });

           b_developer.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    Intent intent1 =new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/developer?id=Facebook&hl=en"));
                            startActivity(intent1);



               }
           });

           b_search.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                String sear = ed_search.getText().toString();

                   Intent intent1 =new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/search?q="+sear));
                   startActivity(intent1);



               }
           });

    }



}
