package com.example.nitika.minner_proj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by NITIKA on 23-Feb-15.
 */

public class Custom_list extends ArrayAdapter<Contact> {

    List<Contact> contact;
    Context context;
    private final int resource;

    public Custom_list(Context context, int resource, List<Contact> contact) {
        super(context, resource,contact);
        this.context = context;
        this.resource = resource;
        this.contact = contact;
    }

    static class ViewHolder {
        public TextView text1;
        public TextView text2;
        public TextView text3;
        public TextView text4;
        public ImageView img;

    }





    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
/*
        LayoutInflater layoutInflater= context.getLayoutInflater();
        View rowView =layoutInflater.inflate(R.layout.list_ui,null,true);
        TextView txt_name =(TextView)rowView.findViewById(R.id.id_name);
        TextView txt_ph_no =(TextView)rowView.findViewById(R.id.id_phone_no);
        txt_name.setText(name.toString());
        txt_ph_no.setText(phone_no.toString());
       return (rowView);
*/
        int i=0;
        View rowView = convertView;
        // reuse views
        if (rowView == null) {
            LayoutInflater inflater;
            inflater = LayoutInflater.from(context);
            rowView = inflater.inflate(R.layout.list_ui, null);
            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.text1 = (TextView) rowView.findViewById(R.id.top_id);
            viewHolder.text2 = (TextView) rowView.findViewById(R.id.title_id);
            viewHolder.text3 = (TextView) rowView.findViewById(R.id.dis_id);
            viewHolder.text4 = (TextView) rowView.findViewById(R.id.date_id);
            viewHolder.img= (ImageView) rowView.findViewById(R.id.img_id);
            rowView.setTag(viewHolder);

            ViewHolder holder = (ViewHolder) rowView.getTag();

            Contact contact_single=contact.get(position);

          holder.text1.setText(contact_single.getDATE());
            holder.text2.setText(contact_single.getNAME());
            holder.text3.setText(contact_single.getDIS());
            holder.text4.setText(contact_single.getDATE());

           int status= contact_single.getStatus();
            if(status==1)
            {
                holder.img.setImageResource(R.drawable.un_completed);
            }
            else if(status==2){
                holder.img.setImageResource(R.drawable.completed);
            }
            else{
                holder.img.setImageResource(R.drawable.un_completed);
            }

        }
            return rowView;
        }

    public void updateList(List<Contact> newList) {
        contact.clear();
       contact.addAll(newList);
        this.notifyDataSetChanged();
    }

}
