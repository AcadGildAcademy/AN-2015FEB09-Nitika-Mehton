package com.example.nitika.demo_intent1;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Set;

/**
 * Created by NITIKA on 18-Feb-15.
 */
public class SettingActivity extends Activity {

   Button blue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_ui);
        blue=(Button)findViewById(R.id.id_blue);

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingActivity.this,Blue_tooth.class);
                startActivity(intent);

            }
        });


    }
}
