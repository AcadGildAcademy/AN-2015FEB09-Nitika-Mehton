package com.example.eku.dry_ticket_project.adapter;

/**
 * Created by DELL on 03-04-2015.
 */
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eku.dry_ticket_project.utils.ImageLoader;
import com.example.eku.dry_ticket_project.R;
import com.example.eku.dry_ticket_project.activity.Venue;
import com.example.eku.dry_ticket_project.activity.single_venue;

public class ListViewAdapter_venue extends BaseAdapter {
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public ListViewAdapter_venue(Context context,
                           ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
        imageLoader = new ImageLoader(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        // Declare Variables
        ImageView image_path;
        TextView title;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.venue_item, parent, false);
        // Get the position
        resultp = data.get(position);

        title = (TextView) itemView.findViewById(R.id.venue_name);

        // Locate the ImageView in listview_item       //
        image_path = (ImageView) itemView.findViewById(R.id.flag5);

        title.setText(resultp.get(Venue.TITLE));

        // Capture position and set results to the ImageView
        // Passes image_path images URL into ImageLoader.class
    //  imageLoader.DisplayImage(resultp.get(Venue.IMAGE_PATH), image_path);
        // Capture ListView item click
        itemView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position
                resultp = data.get(position);
                Intent intent = new Intent(context, single_venue.class);
                // Pass all data desc
                intent.putExtra("venue_desc", resultp.get(Venue.DESC));

                intent.putExtra("venue_name", resultp.get(Venue.TITLE));
                // Pass all data image_path
                intent.putExtra("venue_image_path", resultp.get(Venue.IMAGE_PATH));


                context.startActivity(intent);

            }
        });
        return itemView;
    }
}


