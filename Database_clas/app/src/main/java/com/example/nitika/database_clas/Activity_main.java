package com.example.nitika.database_clas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
    ArrayList<String> top = null;
        ArrayList<String>title=null;
    ArrayList<String> dis = null;
    ArrayList<String> date=null;
    ArrayList<Integer> ii=null;
    ImageView img ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);
        //

        img= (ImageView)findViewById(R.id.img_id);
        top= new ArrayList<>();
        title= new ArrayList<>();
        dis= new ArrayList<>();
        date= new ArrayList<>();
        ii=new ArrayList<>();


        DatabaseHandler db = new DatabaseHandler(this);

//
        Log.e("<<<<<<<<<<<<<<inserting.", ">>>>>>>>>>>>>>");
        db.addContact(new Contact("nitika1", "7508824566", "dis1", "date1", 1));
        db.addContact(new Contact("megha","10000000","dis2","date2",0));
        Toast.makeText(getApplication(),"done",Toast.LENGTH_LONG).show();
        Log.d("000000000000","111111111111111111111");

        List<Contact> contacts= db.getAllContacts();
        for(Contact cn : contacts)
        {
            String  log ="ID-->"+cn.getID()+",names-->"+cn.getNAME()+",ph no-->"+cn.getPHONE()
                    +",dis--->"+cn.getDIS()+",date-->"+cn.getDATE()+"satus-->"+cn.getIMGG();
            Log.d("name",log);
            Toast.makeText(getApplicationContext(), cn.getNAME(), Toast.LENGTH_SHORT).show();
       title.add(cn.getPHONE());//for title
            dis.add(cn.getDIS());//for discreiption
            date.add(cn.getDATE());//for date
            ii.add(cn.getIMGG());//status of image

        }





        //
        //

        Toast.makeText(getApplicationContext(), "You Clicked at " + "1", Toast.LENGTH_SHORT).show();

       Custom_list adapter1 = new Custom_list(Activity_main.this,top,title,dis,date,ii );
                   list = (ListView) findViewById(R.id.id_listView);


         list.setAdapter(adapter1);
        Toast.makeText(getApplicationContext(), "You Clicked at " , Toast.LENGTH_SHORT).show();


            list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    ImageView button = (ImageView) view.findViewById(R.id.img_id);
                    String tag = button.getTag().toString();
                    Toast.makeText(getApplication(),tag,Toast.LENGTH_LONG).show();
                    if (tag.equals("1")) {


                        button.setTag("2");
                        button.setImageResource(R.drawable.images);
                    } else {

                        button.setTag("1");
                       button.setImageResource(R.drawable.black);

                    }

                    return true;
                }
            });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });
        getActionBar();
        registerForContextMenu(list);




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

           final EditText tit = (EditText) promptsView.findViewById(R.id.id_name);
            final EditText dis1 = (EditText) promptsView.findViewById(R.id.id_phone_no);
            int month = myDatePicker.getMonth() + 1;
            int day = myDatePicker.getDayOfMonth();
            int year = myDatePicker.getYear();

           // String source = myDatePicker;
            final SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");//("yyyy-MM-dd");



            // set dialog message
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String dateFormat = dateformat.format(new Date(myDatePicker.getYear(),
                            myDatePicker.getMonth(), myDatePicker.getDayOfMonth()));

                    date.add((dateFormat));
                    top.add(dateFormat);
                    title.add(tit.getText().toString());
                    dis.add(dis1.getText().toString());

/*
                    map1.put(date, top);
                    map1.put(date, title);
                    map1.put(date, dis);
                    map1.put(date, date);
                    maps.add(map1);
*/



             //       Custom_list adapter1 = new Custom_list(Activity_main.this, top, title, dis, date,ii);
           //         list = (ListView) findViewById(R.id.id_listView);
               //     list.setAdapter(adapter1);


                }
            });
            alertDialogBuilder.setNegativeButton("Cancel",
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
