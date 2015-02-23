package com.example.nitika.demo_intent1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.nio.BufferUnderflowException;

/**
 * Created by NITIKA on 15-Feb-15.
 */
public class First_file extends Activity {

    Button b_app;
    Button b_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_ui);

         b_app= (Button)findViewById(R.id.id_app);
        b_setting=(Button)findViewById(R.id.id_setting);
         b_app.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent =new Intent(First_file.this,Second_file.class);
                 startActivity(intent);
                 

             }
         });

        b_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(First_file.this,SettingActivity.class);
                startActivity(intent);


            }
        });


    }
}
