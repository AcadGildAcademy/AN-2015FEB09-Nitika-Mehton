package com.example.nitika.main_project_design;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by NITIKA on 05-Feb-15.
 */
public class Sign_up extends Activity{

    EditText name1 ,password1,email;
    Button sign_in, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signup);

        name1 =(EditText)findViewById(R.id.id_username);
        password1=(EditText)findViewById(R.id.id_password);
       email =(EditText)findViewById(R.id.id_email);

        sign_in=(Button)findViewById(R.id.signin);
        sign_in.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               String user_name_value= name1.getText().toString();
                String user_password_value= password1.getText().toString();
                String user_confirm_password=email.getText().toString();
               //validation and condition



            }
        });

    }


}
