package com.example.nitika.database_clas;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by NITIKA on 23-Feb-15.
 */
public class Custom_list extends ArrayAdapter<String> {
    private final Activity context;
    private ArrayList<String> top;
    private ArrayList<String> title;
    private ArrayList<String> dis;
    private ArrayList<String> date;
    private ArrayList<Integer> ii;

    public Custom_list(Activity context, ArrayList<String> top, ArrayList<String> title,
                       ArrayList<String> dis, ArrayList<String> date, ArrayList<Integer> ii)
    {
        super(context,R.layout.list_ui,top);
        this.context=context;
        this.top=top;
        this.title=title;
        this.dis=dis;
        this.date=date;
        this.ii = ii;

    }
    static class ViewHolder {
        public TextView text1;
        public TextView text2;
        public TextView text3;
        public TextView text4;
        public ImageView img;
        public  Integer ii;
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
            LayoutInflater inflater = context.getLayoutInflater();
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



            holder.text1.setText(top.listIterator(position).next().toString());
            holder.text2.setText(title.listIterator(position).next().toString());
            holder.text3.setText(dis.listIterator(position).next().toString());
            holder.text4.setText(date.listIterator(position).next().toString());

           // holder.text2.setText(top.get(position).toString());
          //  String tag= holder.img.getTag().toString();
           /* if(tag.equals("1"))
            {
            holder.img.setImageResource(R.drawable.black);
                holder.img.setTag("1");
            }
            else if(tag.equals("2")){
                holder.img.setImageResource(R.drawable.images);
                holder.img.setTag("2");

            }
            else{
                holder.img.setImageResource(R.drawable.black);
                holder.img.setTag("1");

            }
*/

           holder.img.setImageResource(R.drawable.black);
            holder.img.setTag("1");

        }
            return rowView;
        }

    @Override
    public String toString() {
        return "Custom_list{" +
                "context=" + context +
                ", top=" + top +
                ", title=" + title +
                ", dis=" + dis +
                ", date=" + date +
                '}';
    }
}
