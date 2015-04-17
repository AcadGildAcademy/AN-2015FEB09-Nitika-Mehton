package com.example.eku.dry_ticket_project.activity;

/**
 * Created by NITIKA on 17-Apr-15.
 */
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;
import com.example.eku.dry_ticket_project.R;
public class Grid_img_adapter extends BaseAdapter {

    private Context context;
   //1 private final String[] gridValues;
   private final int[] gridValues;
    //Constructor to initialize values
    public Grid_img_adapter(Context context, int[ ] gridValues) {

        this.context        = context;
        this.gridValues     = gridValues;
    }

    @Override
    public int getCount() {

        // Number of times getView method call depends upon gridValues.length
        return gridValues.length;
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }


    // Number of times getView method call depends upon gridValues.length

    public View getView(int position, View convertView, ViewGroup parent) {

        // LayoutInflator to call external grid_item.xml file

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from grid_item.xml ( Defined Below )

            gridView = inflater.inflate( R.layout.seat , null);

            // set value into textview




            // set image based on selected text

            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            int arrLabel = gridValues[ position ];
            if (arrLabel==0) {

                imageView.setImageResource(R.drawable.ic_launcher);

            } else if (arrLabel==1) {

                imageView.setImageResource(R.drawable.seat);

            } else {

                imageView.setImageResource(R.drawable.ic_launcher);
            }
/*
            if (arrLabel.equals("Windows")) {

                imageView.setImageResource(R.drawable.d_logo);

            } else if (arrLabel.equals("iOS")) {

                imageView.setImageResource(R.drawable.ic_launcher);

            } else if (arrLabel.equals("Blackberry")) {

                imageView.setImageResource(R.drawable.abc_ab_share_pack_holo_dark);

            } else {

                imageView.setImageResource(R.drawable.ic_launcher);
            }
*/
        } else {

            gridView = (View) convertView;
        }

        return gridView;
    }
}