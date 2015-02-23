package com.example.nitika.firstfile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by NITIKA on 05-Feb-15.
 */
public class FirstScreen extends Activity{

    EditText name1 ,password1;
    Button sign_in, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.first_name);

        name1 =(EditText)findViewById(R.id.username);
        password1=(EditText)findViewById(R.id.userpassword);
        sign_in=(Button)findViewById(R.id.signin);
        sign_in.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
        String user_name_value= name1.getText().toString();
                String user_password_value= password1.getText().toString();

                if (user_name_value.equals("nitika")&& user_password_value.equals("nitika"))
                {
                    Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(FirstScreen.this,SecondScreen.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("key_username",user_name_value);
                    bundle.putInt("key_id",123);
                    intent.putExtras(bundle);
                    startActivity((intent));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"failure",Toast.LENGTH_LONG).show();
                }


            }
        });

    }


}
