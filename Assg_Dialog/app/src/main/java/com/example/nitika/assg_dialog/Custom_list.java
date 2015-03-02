package com.example.nitika.assg_dialog;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by NITIKA on 23-Feb-15.
 */
public class Custom_list extends ArrayAdapter<String> {
    private final Activity context;
    private   List<String> name;
    private List<String> phone_no;

    public Custom_list(Activity context ,List<String> name,List<String> phone_no )
    {
         super(context,R.layout.list_ui,name);
        this.context=context;
        this.name=name;
        this.phone_no=phone_no;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // name= new ArrayList<String>();
       // phone_no= new ArrayList<String>();
        LayoutInflater layoutInflater= context.getLayoutInflater();
        View rowView =layoutInflater.inflate(R.layout.list_ui,null,true);
        TextView txt_name =(TextView)rowView.findViewById(R.id.id_name);
        TextView txt_ph_no =(TextView)rowView.findViewById(R.id.id_phone_no);
        txt_name.setText(name.get(position).toString());
        txt_ph_no.setText(phone_no.get(position).toString());
       return (rowView);

    }


}
