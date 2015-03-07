package com.example.nitika.minner_proj;

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
import android.widget.ListView;
import android.widget.Toast;

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

    EditText title;
   EditText discription_txt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);
        //

        getActionBar();




        contact = new ArrayList<Contact>();

    db = new DatabaseHandler(this);


             contact = db.getAllContacts();
           for (Contact cn : contact) {
                String log = "ID-->" + cn.getID() + ",names-->" + cn.getNAME()
                        + ",dis--->" + cn.getDIS() + ",date-->" + cn.getDATE() + "satus-->" + cn.getStatus();
                Log.d("name", log);

            }


      adapter1 = new Custom_list(this,R.layout.list_ui,contact );
                   list = (ListView) findViewById(R.id.id_listView);
if(list!=null) {
    list.setAdapter(adapter1);
}
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

           title = (EditText) promptsView.findViewById(R.id.id_name);
           discription_txt = (EditText) promptsView.findViewById(R.id.id_discription);



            final SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");//("yyyy-MM-dd");



            // set dialog message
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String dateFormat = dateformat.format(new Date(myDatePicker.getYear()-1900,
                            myDatePicker.getMonth(), myDatePicker.getDayOfMonth()));

                    if(title.getText().toString().equals("")){
                        title.setError("title cannot be empty");
                    }
                    else if (discription_txt.getText().toString().equals(""))
                    {
                        discription_txt.setError("must have a discription");
                    }
                    else {
                        db.addContact(new Contact(title.getText().toString(), discription_txt.getText().toString(), (dateFormat), 1));


                        finish();
                        Intent intent = new Intent(Activity_main.this, Activity_main.class);
                        startActivity(intent);

                    }
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

        if(id==R.id.tumb)
        {

        //   contact= db.getAllCompleted();

            Intent intent = new Intent(getApplicationContext(), AllCompleted.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
