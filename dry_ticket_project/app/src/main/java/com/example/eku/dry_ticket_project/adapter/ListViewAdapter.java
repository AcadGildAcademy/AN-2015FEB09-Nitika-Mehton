package com.example.eku.dry_ticket_project.adapter;

/**
 * Created by DELL on 21-03-2015.
 */
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eku.dry_ticket_project.utils.ImageLoader;
import com.example.eku.dry_ticket_project.R;
import com.example.eku.dry_ticket_project.activity.NowOnSale;
import com.example.eku.dry_ticket_project.activity.singleitemview;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    ImageLoader imageLoader;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public ListViewAdapter(Context context,
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

        ImageView image_path = null;
       // TextView title;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.listview_item, parent, false);
        // Get the position
        resultp = data.get(position);

        //title = (TextView) itemView.findViewById(R.id.detail);

       // title.setText(resultp.get(NowOnSale.TITLE));
       // Log.d("2222",title.getText().toString());
        // Capture position and set results to the ImageView
        // Passes image_path images URL into ImageLoader.class
        image_path = (ImageView) itemView.findViewById(R.id.flag);
        imageLoader.DisplayImage(resultp.get(NowOnSale.IMAGE_PATH), image_path);
        // Capture ListView item click
        itemView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position
                resultp = data.get(position);
                Bundle bundle=new Bundle();
                Intent intent = new Intent(context, singleitemview.class);
                // Pass all data desc
                bundle.putString("image_path", resultp.get(NowOnSale.IMAGE_PATH));
                bundle.putString("id", resultp.get(NowOnSale.ID));
                bundle.putString("desc", resultp.get(NowOnSale.DESC));
                bundle.putString("price", resultp.get(NowOnSale.PRICE));
                bundle.putString("date", resultp.get(NowOnSale.DATE));
                bundle.putString("time", resultp.get(NowOnSale.TIME));
                bundle.putString("venue", resultp.get(NowOnSale.VENUE));
              //  intent.putExtra("detail",resultp.get(NowOnSale.DETAIL));
                // Start SingleItemView Class
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });
        return itemView;
    }
}