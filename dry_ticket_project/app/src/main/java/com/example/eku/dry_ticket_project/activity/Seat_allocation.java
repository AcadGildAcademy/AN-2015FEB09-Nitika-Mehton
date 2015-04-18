package com.example.eku.dry_ticket_project.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eku.dry_ticket_project.R;
import com.example.eku.dry_ticket_project.pref.UserSession;
import com.example.eku.dry_ticket_project.utils.ServiceHandler;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.eku.dry_ticket_project.R.color.orange;
import static com.example.eku.dry_ticket_project.R.color.switch_thumb_normal_material_dark;
import static com.example.eku.dry_ticket_project.R.color.white;

public class Seat_allocation extends ActionBarActivity {

    int[] m = new int[5];
    int [] post =new int [5];
    private boolean flag0=false;
    JSONArray contacts = null;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_S0 = "s0";
    private static final String TAG_S1 = "s1";
    private static final String TAG_S2 = "s2";
    private static final String TAG_S3 = "s3";
    private static final String TAG_S4 = "s4";
    private static final String TAG_ID = "id";
   UserSession session;
    private boolean flag1=false, flag2=false, flag3=false, flag4=false, flag5=false, flag6=false, flag7=false;
int count;
    private ProgressDialog pDialog;
    GridView gridview;
    ViewGroup parent;
    private static String url = "http://bishasha.com/json/get_sitting_plan.php";
    private static String url_post= "http://bishasha.com/json/post_sitting_plan.php";
    String s1,s2,s3,s4,s0,sid;
String str_id;
Button book_b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seat_selection);
  book_b=(Button)findViewById(R.id.book_btn);

Log.d("enter","");

        new GetUserData().execute();

       gridview = (GridView) findViewById(R.id.gridview);
      // gridview.setAdapter(new ImageAdapter(this));

    //   gridview.setAdapter(new Grid_img_adapter(this, m));

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {


                int arrLabel = m[position];
                Toast.makeText(
                        getApplicationContext(),
                        "v" + arrLabel+"p"+position, Toast.LENGTH_SHORT).show();
            switch (position) {
                case 0:
                    if (arrLabel == 1) {

                        Toast.makeText(getApplicationContext(), "already book", Toast.LENGTH_SHORT).show();
                    } else {
                        if (flag0 == false) {
                            count = count + 1;
                            Log.d("count++>",""+count );
                            ImageView imageView = (ImageView) v.findViewById(R.id.grid_item_image);
                            imageView.setImageResource(R.drawable.blue_c);
                            post[0]=1;
                            flag0 = true;
                        } else {
                            count = count - 1;
                            Log.d("count-->",""+count );
                            ImageView imageView = (ImageView) v.findViewById(R.id.grid_item_image);
                            imageView.setImageResource(R.drawable.ic_launcher);
                            post[0]=0;
                            flag0 = false;
                        }
                    }
                    break;

                case 1:
                if (arrLabel == 1) {

                    Toast.makeText(getApplicationContext(), "already book", Toast.LENGTH_SHORT).show();
                } else {
                    if (flag1== false) {
                        count = count + 1;
                        Log.d("count++>",""+count );
                        ImageView imageView = (ImageView) v.findViewById(R.id.grid_item_image);
                        imageView.setImageResource(R.drawable.blue_c);
                        post[1]=1;
                        flag1 = true;
                    } else {
                        count = count - 1;
                        Log.d("count-->",""+count );
                        ImageView imageView = (ImageView) v.findViewById(R.id.grid_item_image);
                        imageView.setImageResource(R.drawable.ic_launcher);
                        post[1]=0;
                        flag1 = false;
                    }
                }
                    break;
                case 2:
                    if (arrLabel == 1) {

                        Toast.makeText(getApplicationContext(), "already book", Toast.LENGTH_SHORT).show();
                    } else {
                        if (flag2== false) {
                            count = count + 1;
                            Log.d("count++>",""+count );
                            ImageView imageView = (ImageView) v.findViewById(R.id.grid_item_image);
                            imageView.setImageResource(R.drawable.blue_c);
                            post[2]=1;
                            flag2 = true;
                        } else {
                            count = count - 1;
                            Log.d("count-->",""+count );
                            ImageView imageView = (ImageView) v.findViewById(R.id.grid_item_image);
                            imageView.setImageResource(R.drawable.ic_launcher);
                            post[2]=0;
                            flag2 = false;
                        }
                    }
                    break;
                case 3:
                    if (arrLabel == 1) {

                        Toast.makeText(getApplicationContext(), "already book", Toast.LENGTH_SHORT).show();
                    } else {
                        if (flag3== false) {
                            count = count + 1;
                            Log.d("count++>",""+count );
                            ImageView imageView = (ImageView) v.findViewById(R.id.grid_item_image);
                            imageView.setImageResource(R.drawable.blue_c);
                            post[3]=1;
                            flag3 = true;
                        } else {
                            count = count - 1;
                            Log.d("count-->",""+count );
                            ImageView imageView = (ImageView) v.findViewById(R.id.grid_item_image);
                            imageView.setImageResource(R.drawable.ic_launcher);
                            post[3]=0;
                            flag3 = false;
                        }
                    }
                    break;
                case 4:
                    if (arrLabel == 1) {

                        Toast.makeText(getApplicationContext(), "already book", Toast.LENGTH_SHORT).show();
                    } else {
                        if (flag4== false) {
                            count = count + 1;
                            Log.d("count++>",""+count );
                            ImageView imageView = (ImageView) v.findViewById(R.id.grid_item_image);
                            imageView.setImageResource(R.drawable.blue_c);
                            post[4]=1;
                            flag4 = true;
                        } else {
                            count = count - 1;
                            Log.d("count-->",""+count );
                            ImageView imageView = (ImageView) v.findViewById(R.id.grid_item_image);
                            imageView.setImageResource(R.drawable.ic_launcher);
                            post[4]=0;
                            flag4 = false;
                        }
                    }
                    break;

            }

                Log.d("----->>post[0]=",""+post[0]);
                Log.d("----->>post[1]=",""+post[1]);
                Log.d("----->>post[2]=",""+post[2]);
                Log.d("----->>post[3]=",""+post[3]);
                Log.d("----->>post[4]=",""+post[4]);
            }
        });


        book_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetContacts().execute();
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

    private class GetUserData extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Seat_allocation.this);
            pDialog.setMessage("Please wait...");
            //   pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params1) {



            ServiceHandler sh = new ServiceHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);


            if (jsonStr != null) {
                try {

                    JSONObject json = new JSONObject(jsonStr);
                    int success = json.getInt(TAG_SUCCESS);

                    Log.d("Login attempt", json.toString());
                    // json success tag

                    if (success == 1) {
                        contacts = json.getJSONArray("sitting_plan");

                        JSONObject c = contacts.getJSONObject(0);
                           s0=c.getString(TAG_S0);
                          s1=c.getString((TAG_S1));
                        s2= c.getString(TAG_S2);
                        s3=c.getString(TAG_S3);
                        s4=c.getString(TAG_S4);
                        sid=c.getString(TAG_ID);

                  post[0]=  m[0]=Integer.parseInt(s0);
                        Log.d("m[0]=",""+m[0]);
                        post[1]=     m[1]=Integer.parseInt(s1);
                        Log.d("m[1]=",""+m[1]);
                        post[2]=   m[2]=Integer.parseInt(s2);
                        Log.d("m[2]=",""+m[2]);
                        post[3]=    m[3]=Integer.parseInt(s3);
                        Log.d("m[3]=",""+m[3]);
                        post[4]=   m[4]=Integer.parseInt(s4);
                        Log.d("m[4]=",""+m[4]);
                        str_id=sid;

                    }

                    Log.d("success", "" + success);
                    //  publishProgress(success);
                } catch (JSONException e) {
                    e.printStackTrace();
                }}
            else {
                Log.d("poooooo", "nnnnn");
            }
            return null;

        }


        @Override
        protected void onPostExecute(String file_url) {
            //super.onPostExecute(result);
            // Dismiss the progress dialog
         //  int x= Integer.parseInt(s0);

            gridview.setAdapter(new Grid_img_adapter(Seat_allocation.this, m));

            if (pDialog.isShowing())
                pDialog.dismiss();
        }
    }
    /**
     * Async task class to get json by making HTTP call
     * */

    private class GetContacts extends AsyncTask<Void,Integer, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Seat_allocation.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            int success;
            Log.d("<<<>>post[0]=",""+post[0]);
            Log.d("<<>>post[1]=",""+post[1]);
            Log.d("<<>>post[2]=",""+post[2]);
            Log.d("-<<>>post[3]=",""+post[3]);
            Log.d("<<>>post[4]=",""+post[4]);
            Log.d("<<>>id=",""+str_id);
            try {
                // Building Parameters-9+++++++++++++++++++++++++++++

                List params = new ArrayList();

                params.add(new BasicNameValuePair("id",str_id));
                params.add(new BasicNameValuePair("s0",Integer.toString(post[0])));
                params.add(new BasicNameValuePair("s1",Integer.toString(post[1])));
                params.add(new BasicNameValuePair("s2",Integer.toString(post[2])));
                params.add(new BasicNameValuePair("s3",Integer.toString(post[3])));
                params.add(new BasicNameValuePair("s4",Integer.toString(post[4])));


                ServiceHandler sh = new ServiceHandler();
                Log.d("request!", "starting");
                // getting product details by making HTTP request
                String jsonStr = sh.makeServiceCall( url_post, ServiceHandler.POST,params);//2 for POST
                JSONObject json = new JSONObject(jsonStr);
                // check your log for json response
                Log.d("sign up attempt", json.toString());
                // json success tag
                success = json.getInt(TAG_SUCCESS);
                Log.d("success",""+success);
                publishProgress(success);
            }catch(JSONException e){
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
        }


        //Handling updation to your UI
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int  success=values[0];
            if (success == 1) {
                Log.d("update Successful!", " Success");
                Toast.makeText(getApplicationContext(),"Update",Toast.LENGTH_LONG).show();
              ;
            } else {
                Log.d("update fail"," fail");

                // return json.getString(TAG_MESSAGE);
            }
        }
    }
}


