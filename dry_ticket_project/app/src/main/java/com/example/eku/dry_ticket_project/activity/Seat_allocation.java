package com.example.eku.dry_ticket_project.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eku.dry_ticket_project.R;

import static com.example.eku.dry_ticket_project.R.color.orange;
import static com.example.eku.dry_ticket_project.R.color.switch_thumb_normal_material_dark;
import static com.example.eku.dry_ticket_project.R.color.white;

public class Seat_allocation extends ActionBarActivity {

    int[] m = new int[5];
    private boolean flag0=false;
    private boolean flag1=false, flag2=false, flag3=false, flag4=false, flag5=false, flag6=false, flag7=false;
int count;
    GridView gridview;
    ViewGroup parent;
    int s0,s1,s2,s3,s4,s5,s6;
    static final String[] MOBILE_OS = new String[] {
            "Android", "iOS","Windows", "Blackberry" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seat_selection);




        s0=0;
         s1=0;
        s3=0;
        s4=1;
        s5=1;
        s6=1;
        m[0]=0;
        m[1]=0;
        m[2]=0;
        m[3]=1;
        m[4]=1;

       gridview = (GridView) findViewById(R.id.gridview);
      // gridview.setAdapter(new ImageAdapter(this));
       gridview.setAdapter(new Grid_img_adapter(this, m));
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {


                int arrLabel = m[position];
                Toast.makeText(
                        getApplicationContext(),
                        "v" + arrLabel+"p"+position, Toast.LENGTH_SHORT).show();
           /*     Log.d("count++>", "" + arrLabel);
                switch(position) {
                    case 0:

                    if (arrLabel == 0) {
                        //  Log.d("count++>", "" + arrLabel);
                        ImageView imageView = (ImageView) gridview.findViewById(R.id.grid_item_image);
                        imageView.setImageResource(R.drawable.seat);
                        m[position] = 1;
                    } else if (arrLabel == 1) {
                        ImageView imageView = (ImageView) gridview.findViewById(R.id.grid_item_image);
                        imageView.setImageResource(R.drawable.ic_launcher);
                        m[position] = 0;
                    }
                        break;
                    case 1:
                        if (arrLabel == 0) {
                            //  Log.d("count++>", "" + arrLabel);
                            ImageView imageView = (ImageView) gridview.findViewById(R.id.grid_item_image);
                            Log.d("done","seat");
                            imageView.setImageResource(R.drawable.seat);
                            m[position] = 1;
                        } else if (arrLabel == 1) {
                            ImageView imageView = (ImageView) gridview.findViewById(R.id.grid_item_image);
                            Log.d("done","ic");
                            imageView.setImageResource(R.drawable.ic_launcher);
                            m[position] = 0;
                        }
                        break;
                }
            }});*/
               switch (position) {
                    case 0:
                        if (m[0]== 1) {
                            Toast.makeText(getApplicationContext(), "already book", Toast.LENGTH_SHORT).show();
                        } else {
                            if (flag0 == false) {
                                count = count + 1;
                                Log.d("count++>", "" + count+"flag0"+""+flag1);

                                ImageView imageView = (ImageView) gridview.findViewById(R.id.grid_item_image);
                                imageView.setImageResource(R.drawable.seat);

                                flag0 = true;
                            } else if (flag0 == true) {

                                count = count - 1;
                                Log.d("count-->", "" + count+"flag0"+""+flag1);
                                ImageView imageView = (ImageView) gridview.findViewById(R.id.grid_item_image);
                                imageView.setImageResource(R.drawable.ic_launcher);
                                flag0 = false;
                            }

                        }
                        break;
                    case 1:
                        if (m[1] == 1) {
                            Toast.makeText(getApplicationContext(), "already book", Toast.LENGTH_SHORT).show();
                        } else {
                            if (flag1 == false) {
                                count = count + 1;
                                Log.d("count++>", "" + count+"flag1"+""+flag1);

                                ImageView imageView = (ImageView) gridview.findViewById(R.id.grid_item_image);
                                imageView.setImageResource(R.drawable.seat);
                                flag1 = true;
                               
                            } else if (flag1 == true) {

                                count = count - 1;
                                Log.d("count-->", "" + count+"flag1"+""+flag1);
                                ImageView imageView = (ImageView) gridview.findViewById(R.id.grid_item_image);
                                imageView.setImageResource(R.drawable.ic_launcher);
                                flag1 = false;
                            }
                        }

                        break;
               case 2:
                        if (m[2]== 1) {
                            Toast.makeText(getApplicationContext(), "already book", Toast.LENGTH_SHORT).show();
                        } else {
                            if (flag2 == false) {
                                count = count + 1;
                                Log.d("count++>", "" + count);
                                ImageView imageView = (ImageView) gridview.findViewById(R.id.grid_item_image);
                                imageView.setImageResource(R.drawable.seat);
                                flag2 = true;

                            } else if (flag2 == true) {

                                count = count - 1;
                                Log.d("count-->", "" + count);
                                ImageView imageView = (ImageView) gridview.findViewById(R.id.grid_item_image);
                                imageView.setImageResource(R.drawable.ic_launcher);
                                flag2 = false;
                            }
                        }
                        break;
                    case 3:
                        if (m[3] == 1) {
                            Toast.makeText(getApplicationContext(), "already book", Toast.LENGTH_SHORT).show();
                        } else {
                            if (flag3 == false) {
                                count = count + 1;
                                Log.d("count++>", "" + count);
                                ImageView imageView = (ImageView) gridview.findViewById(R.id.grid_item_image);
                                imageView.setImageResource(R.drawable.seat);
                                flag3 = true;

                            } else if (flag3 == true) {

                                count = count - 1;
                                Log.d("count-->", "" + count);
                                ImageView imageView = (ImageView) gridview.findViewById(R.id.grid_item_image);
                                imageView.setImageResource(R.drawable.ic_launcher);
                                flag3 = false;
                            }
                        }
                        break;
                    case 4:
                        if (m[4] == 1) {
                            Toast.makeText(getApplicationContext(), "already book", Toast.LENGTH_SHORT).show();
                        } else {
                            if (flag4 == false) {
                                count = count + 1;
                                Log.d("count++>", "" + count);
                                ImageView imageView = (ImageView) gridview.findViewById(R.id.grid_item_image);
                                imageView.setImageResource(R.drawable.seat);
                                flag4 = true;

                            } else if (flag4 == true) {

                                count = count - 1;
                                Log.d("count-->", "" + count);
                                ImageView imageView = (ImageView) gridview.findViewById(R.id.grid_item_image);
                                imageView.setImageResource(R.drawable.ic_launcher);
                                flag4 = false;
                            }
                        }
                        break;

                    case 5:
                        if (m[5] == 1) {
                            Toast.makeText(getApplicationContext(), "already book", Toast.LENGTH_SHORT).show();
                        } else {
                            if (flag5 == false) {
                                count = count + 1;
                                Log.d("count++>", "" + count);
                                ImageView imageView = (ImageView) gridview.findViewById(R.id.grid_item_image);
                                imageView.setImageResource(R.drawable.seat);
                                flag5 = true;

                            } else if (flag5 == true) {

                                count = count - 1;
                                Log.d("count-->", "" + count);
                                ImageView imageView = (ImageView) gridview.findViewById(R.id.grid_item_image);
                                imageView.setImageResource(R.drawable.ic_launcher);
                                flag5 = false;
                            }
                        }
                        break;

                }
                //  Toast.makeText(getApplication(),position,Toast.LENGTH_SHORT).show();

            }
        });
        getSupportActionBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_file
                , menu);
        return true;
    }
}