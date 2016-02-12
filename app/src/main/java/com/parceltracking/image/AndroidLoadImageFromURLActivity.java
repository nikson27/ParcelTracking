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
        String imageUrl = "http://api.androidhive.info/images/sample.jpg";
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
        imageLoader.displayImage(imageUrl, imageView);
        // Loader image - will be shown before loading image
        int loader = R.drawable.transfer;

        // Imageview to show
      //  ImageView image = (ImageView) findViewById(R.id.image);
//
        // Image url
      //  String image_url = "http://api.androidhive.info/images/sample.jpg";

        // ImageLoader class instance
       // ImageLoader imgLoader = new ImageLoader(getApplicationContext());

        // whenever you want to load an image from url
        // call DisplayImage function
        // url - image url to load
        // loader - loader image, will be displayed before getting image
        // image - ImageView
      //  imgLoader.DisplayImage(image_url, loader, image);
    }
}