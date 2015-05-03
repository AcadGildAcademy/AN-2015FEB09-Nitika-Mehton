package com.example.nitika.main_project_design.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.nitika.main_project_design.R;
import com.example.nitika.main_project_design.Utiles.ImageLoader;

/**
 * Created by NITIKA on 03-May-15.
 */
public class image extends Activity {
    ImageView image;
    String image_path;
    ImageLoader imageLoader = new ImageLoader(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_ui);
        image=(ImageView)findViewById(R.id.imageView);
        Intent i = getIntent();


        image_path = i.getStringExtra("image");
        imageLoader.DisplayImage(image_path, image);
    }
}
