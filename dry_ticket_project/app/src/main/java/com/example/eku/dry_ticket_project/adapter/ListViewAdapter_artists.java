package com.example.eku.dry_ticket_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eku.dry_ticket_project.activity.Artist_detail_information;
import com.example.eku.dry_ticket_project.activity.Artist_information;
import com.example.eku.dry_ticket_project.R;
import com.example.eku.dry_ticket_project.utils.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;



public class ListViewAdapter_artists extends BaseAdapter
{
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public ListViewAdapter_artists(Context context, ArrayList<HashMap<String, String>> arraylist)
    {
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
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        TextView artist_name_select,artist_description;
        ImageView artist_photo_path;
        Button button;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.artist_mainpage, parent, false);
        resultp = data.get(position);
        artist_name_select = (TextView) itemView.findViewById(R.id.artist_name_select);
        //artist_photo_path = (ImageView) itemView.findViewById(R.id.artist_image);
        //artist_description = (TextView) itemView.findViewById(R.id.artist_description);
        //button = (Button) itemView.findViewById(R.id.buy_button);
        artist_name_select.setText(resultp.get(Artist_information.ARTIST_NAME));
        //imageLoader.DisplayImage(resultp.get(Now_on_sale_firstpage.IMAGE_PATH),artist_photo_path);
        //artist_description.setText(resultp.get(Artist_information.ARTIST_DESCRIPTION));
        itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                // Get the position
                resultp = data.get(position);
                Bundle bundle= new Bundle();
                //Bundle bundle = intent.getExtras();
                bundle.putString("artist_name", resultp.get(Artist_information.ARTIST_NAME ));
                bundle.putString("artist_photo_path", resultp.get(Artist_information.ARTIST_PHOTO_PATH ));
                bundle.putString("artist_description", resultp.get(Artist_information.ARTIST_DESCRIPTION ));
                Intent intent = new Intent(context, Artist_detail_information.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return itemView;
    }
}
