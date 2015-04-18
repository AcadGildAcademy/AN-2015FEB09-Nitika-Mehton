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
import android.widget.Toast;

import com.example.eku.dry_ticket_project.R;
public class Grid_img_adapter extends BaseAdapter {
int x,m;
    Boolean f0=false,f1=false,f2=false;
    int count;
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

      final       ImageView imageView = (ImageView) gridView
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
gridView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Toast.makeText(context, "You Clicked " +x, Toast.LENGTH_LONG).show();
Log.d("x",""+x+"m"+m);


        switch(x) {
            case 0:

                if ( m == 1) {
                    Toast.makeText(context, "You Clicked all", Toast.LENGTH_LONG).show();

                    Log.d("case0", "" + x);
                } else {
                    if(f0==false)
                    {
                        count = count + 1;
                        Log.d("count++>", "" + count+"flag0"+""+f0);


                        imageView.setImageResource(R.drawable.seat);
                        f0 = true;

                    }else  if(f0==true)
                    {
                        count = count - 1;
                        Log.d("count-->", "" + count+"flag0"+""+f0);


                        imageView.setImageResource(R.drawable.ic_launcher);
                        f0 = false;
                    }

                       }
                break;
            case 1:

                if ( m == 1) {
                    Toast.makeText(context, "You Clicked all", Toast.LENGTH_LONG).show();

                    Log.d("case0", "" + x);
                } else {
                    if(f1==false)
                    {
                        count = count + 1;
                        Log.d("count++>", "" + count+"flag1"+""+f1);


                        imageView.setImageResource(R.drawable.seat);
                        f1 = true;

                    }else if(f0==true)
                    {
                        count = count - 1;
                        Log.d("count-->", "" + count+"flag1"+""+f1);


                        imageView.setImageResource(R.drawable.ic_launcher);
                        f1 = false;
                    }

                }
                break;
            case 2:

                if ( m == 1) {
                    Toast.makeText(context, "You Clicked all", Toast.LENGTH_LONG).show();

                    Log.d("case2", "" + x);
                } else {
                    if(f2==false)
                    {
                        count = count + 1;
                        Log.d("count++>", "" + count+"flag2"+""+f2);


                        imageView.setImageResource(R.drawable.seat);
                        f2 = true;

                    }else if(f0==true)
                    {
                        count = count - 1;
                        Log.d("count-->", "" + count+"flag1"+""+f2);


                        imageView.setImageResource(R.drawable.ic_launcher);
                        f2 = false;
                    }

                }
                break;
        }
    }
});*/


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