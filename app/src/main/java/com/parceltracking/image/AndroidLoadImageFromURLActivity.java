package com.parceltracking.image;

import android.os.Bundle;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.parceltracking.BaseActivity;
import com.parceltracking.R;

/**
 * Created by ioan.contiu on 2/12/2016.
 */
public class AndroidLoadImageFromURLActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.image_load);
        getLayoutInflater().inflate(R.layout.image_load, frameLayout);

        ImageView imageView = (ImageView) findViewById(R.id.image);
        String imageUrl = "https://www.google.ro/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png";
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
        imageLoader.displayImage(imageUrl, imageView);

        ImageView imageView2 = (ImageView) findViewById(R.id.image2);
        String imageUrl2 = "https://www.google.ro/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png";
        ImageLoader imageLoader2 = ImageLoader.getInstance();
        imageLoader2.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
        imageLoader2.displayImage(imageUrl2, imageView2);



    }
}