package com.example.nitika.minner_proj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by NITIKA on 07-Mar-15.
 */
public class AllCompleted extends ActionBarActivity {
    public List<Contact> allCompleted = null;
    public ListView listview;
    public Custom_list adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);

        getSupportActionBar();
        final DatabaseHandler db = new DatabaseHandler(this);

        listview = (ListView) findViewById(R.id.id_listView);

        allCompleted = db.getAllCompleted();
        adapter = new Custom_list(this, R.layout.list_ui, allCompleted);
        if (listview != null) {
            listview.setAdapter(adapter);
        }

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                                @Override
                                                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                                    Contact contact = allCompleted.get(position);
                                                    db.deleteContact(contact);
                                                    Toast.makeText(getApplicationContext(), contact.getNAME() + " has been deleted!", Toast.LENGTH_SHORT).show();
                                                    allCompleted = db.getAllCompleted();
                                                    adapter.updateList(allCompleted);

                                                    return true;

                                                }


        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, Activity_main.class);
        startActivity(intent);
    }
}
