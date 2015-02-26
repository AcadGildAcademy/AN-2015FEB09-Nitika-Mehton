package com.example.nitika.minner_proj;
import com.example.nitika.minner_proj.CustomListAdapter;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.example.nitika.minner_proj.Custom_class;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by NITIKA on 26-Feb-15.
 */
public class Main_Activity extends ActionBarActivity{

    final Context context = this;
    String name,dis,date;
    int i=0;
    ListView list;
    List<Custom_class> products;
   // final DatePicker myDatePicker = (DatePicker)findViewById(R.id.myDatePicker);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_ui);
          //  name=EditText



        getActionBar();


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



            Toast.makeText(getApplication(), "done", Toast.LENGTH_LONG).show();

            // get prompts.xml view
            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.alert_fragment_dialog_ui, null);
            final DatePicker myDatePicker = (DatePicker) promptsView.findViewById(R.id.myDatePicker);

            // so that the calendar view won't appear
            myDatePicker.setCalendarViewShown(false);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);

            // set prompts.xml to alertdialog builder
            alertDialogBuilder.setView(promptsView);

            final EditText username = (EditText) promptsView
                    .findViewById(R.id.id_name);
            final EditText userph = (EditText) promptsView
                    .findViewById(R.id.id_dis);

            // set dialog message
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    CustomListAdapter adapter1;
                                    adapter1 = new CustomListAdapter(Main_Activity.this,products);
                                    list = (ListView) findViewById(R.id.content_frame);
                                    list.setAdapter(adapter1);

                                }
                            }


                    )
                    .setNegativeButton("Cancel",
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


    //context menu




}
