package com.example.nitika.database_clas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.nio.charset.MalformedInputException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by NITIKA on 23-Feb-15.
 */
public class Activity_main extends ActionBarActivity {

    final Context context = this;
     ListView list;
        int i=0;

    List<Contact> contact;

    DatabaseHandler db;

   Custom_list adapter1;

    EditText tit;
   EditText dis1;
    int month ;
    int day ;
    int year;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);
        //

        getActionBar();


        contact = new ArrayList<Contact>();

       db = new DatabaseHandler(this);

        contact= db.getAllContacts();
        for(Contact cn : contact)
        {
            String  log ="ID-->"+cn.getID()+",names-->"+cn.getNAME()
                    +",dis--->"+cn.getDIS()+",date-->"+cn.getDATE()+"satus-->"+cn.getStatus();
            Log.d("name",log);

        }

        //
        //


      adapter1 = new Custom_list(Activity_main.this,R.layout.list_ui,contact );
                   list = (ListView) findViewById(R.id.id_listView);

         list.setAdapter(adapter1);
        registerForContextMenu(list);

            list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    Contact contact_update=contact.get(position);

                    if(contact_update.getStatus()==1)
                    {
                        contact_update.setStatus(2);

                    }
                    else{
                        contact_update.setStatus(1);

                    }
                    if(db.updateContact(contact_update)>0) {


                        finish();
                        Intent intent=new Intent(Activity_main.this,Activity_main.class);
                        startActivity(intent);

                        Toast.makeText(Activity_main.this,"success",Toast.LENGTH_LONG).show();
                    }

                    return true;
                }
            });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });
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
            final DatePicker myDatePicker = (DatePicker) promptsView.findViewById(R.id.dialog_date);
            // so that the calendar view won't appear
            myDatePicker.setCalendarViewShown(false);

           tit = (EditText) promptsView.findViewById(R.id.id_name);
           dis1 = (EditText) promptsView.findViewById(R.id.id_phone_no);
           month = myDatePicker.getMonth() + 1;
           day = myDatePicker.getDayOfMonth();
           year = myDatePicker.getYear();

           // String source = myDatePicker;
            final SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");//("yyyy-MM-dd");



            // set dialog message
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String dateFormat = dateformat.format(new Date(myDatePicker.getYear(),
                            myDatePicker.getMonth(), myDatePicker.getDayOfMonth()));

                    db.addContact(new Contact(tit.getText().toString(), dis1.getText().toString(), ("" + year + month + day), 1));

                    Log.d("tag",tit.getText().toString());

                    finish();
                    Intent intent = new Intent(Activity_main.this, Activity_main.class);
                    startActivity(intent);


                }
            }).setNegativeButton("Cancel",
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
}
