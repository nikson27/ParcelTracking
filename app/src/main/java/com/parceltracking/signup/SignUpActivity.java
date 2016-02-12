package com.parceltracking.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;

import com.parceltracking.BaseActivity;
import com.parceltracking.R;
import com.parceltracking.SentFragment;
import com.parceltracking.TabFragment;
import com.parceltracking.login.LoginActivity;

/**
 * Created by ioan.contiu on 2/8/2016.
 */
public class SignUpActivity extends BaseActivity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_signup);
        getLayoutInflater().inflate(R.layout.activity_signup, frameLayout);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
