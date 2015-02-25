package com.example.nitika.assg_dialog;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by NITIKA on 23-Feb-15.
 */
public class Custom_list extends ArrayAdapter<String> {
    private final Activity context;
    private final  String[] name;
    private final  String[] phone_no;

    public Custom_list(Activity context,String[] name,String[] phone_no )
    {
        super(context,R.layout.list_ui,name);
        this.context=context;
        this.name=name;
        this.phone_no=phone_no;


    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater= context.getLayoutInflater();
        View rowView =layoutInflater.inflate(R.layout.list_ui,null,true);
        TextView txt_name =(TextView)rowView.findViewById(R.id.id_name);
        TextView txt_ph_no =(TextView)rowView.findViewById(R.id.id_phone_no);
        txt_name.setText(name[position]);
        txt_ph_no.setText(phone_no[position]);
       return (rowView);

    }

    @Override
    public int getCount() {

        return name.length;
    }
}
