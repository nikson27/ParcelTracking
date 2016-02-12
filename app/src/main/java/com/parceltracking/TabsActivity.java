package com.parceltracking;

import android.os.Bundle;

/**
 * Created by ioan.contiu on 2/11/2016.
 */
public class TabsActivity extends BaseActivity {
   static TabFragment myTabFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myTabFragment=new TabFragment();
        getLayoutInflater().inflate(R.layout.tab_layout, frameLayout);
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView, myTabFragment).commit();
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
