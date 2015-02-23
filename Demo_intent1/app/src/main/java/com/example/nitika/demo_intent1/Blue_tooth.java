package com.example.nitika.demo_intent1;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by NITIKA on 19-Feb-15.
 */
public class Blue_tooth extends Activity {
    Switch blue1;
    Switch dis;
    Button show;
    ListView lv;
    BluetoothAdapter myBluetoothAdapter;
    Set<android.bluetooth.BluetoothDevice> pairedDevices;
    ArrayAdapter<String> BTarrayadt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth_ui);

        blue1 = (Switch) findViewById(R.id.id_blue_switch);
        dis = (Switch) findViewById(R.id.id_dis_switch);
        show = (Button) findViewById(R.id.id_showname_b);
        lv = (ListView) findViewById(R.id.listView);

                myBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
    blue1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()

    {
        @Override
        public void onCheckedChanged (CompoundButton buttonView,boolean isChecked){
        if (isChecked) {
            if (!myBluetoothAdapter.isEnabled()) {
                Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(turnOn, 0);
                Toast.makeText(getApplicationContext(), "Turned on"
                        , Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Already on",
                        Toast.LENGTH_LONG).show();
            }
        }
            else
        {
            myBluetoothAdapter.disable();
            Toast.makeText(getApplicationContext(), "bluetooth off",
                    Toast.LENGTH_LONG).show();
        }
    }
    }

    );
        dis.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    Intent getVisible = new Intent(BluetoothAdapter.
                            ACTION_REQUEST_DISCOVERABLE);
                    startActivityForResult(getVisible, 0);
                }
            }
        });

      show.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              pairedDevices = myBluetoothAdapter.getBondedDevices();

              ArrayList list = new ArrayList();
              for(BluetoothDevice bt : pairedDevices)
                  list.add(bt.getName());

              Toast.makeText(getApplicationContext(),"Showing Paired Devices",
                      Toast.LENGTH_SHORT).show();
              final ArrayAdapter adapter = new ArrayAdapter(Blue_tooth.this,android.R.layout.simple_list_item_1);

              lv.setAdapter(adapter);
          }
      });


}
}

