package com.parceltracking;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends BaseActivity {
   /* DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    Button btnLogIn;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    Toolbar toolbar;
    ActionBarDrawerToggle mDrawerToggle;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.start);
        getLayoutInflater().inflate(R.layout.start, frameLayout);
      /*  mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
*/
        /**
         * Setup click events on the Navigation View Items.
         */

    }
    /**
     *Setup the DrawerLayout and NavigationView
     */

    /*         mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
             mNavigationView = (NavigationView) findViewById(R.id.navigationMenu) ;
             btnLogIn=(Button)findViewById(R.id.Login);
*/
    /**
     * Lets inflate the very first fragment
     * Here , we are inflating the TabFragment as the first Fragment
     */
/*
             mFragmentManager = getSupportFragmentManager();
             mFragmentTransaction = mFragmentManager.beginTransaction();
             mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
*/

    /**
     * Setup click events on the Navigation View Items.
     */
/*
            mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                 @Override
                 public boolean onNavigationItemSelected(MenuItem menuItem) {
                     mDrawerLayout.closeDrawers();


                     if (menuItem.getItemId() == R.id.nav_item_sent) {
                         FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                         fragmentTransaction.replace(R.id.containerView, new SentFragment()).commit();

                     }

                     if (menuItem.getItemId() == R.id.nav_item_inbox) {
                         FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                         xfragmentTransaction.replace(R.id.containerView, new TabFragment()).commit();
                     }


                     return true;
                 }

             });

        /**
         * Setup Drawer Toggle of the Toolbar
         */
/*
                toolbar = (Toolbar) findViewById(R.id.toolbar);
                mDrawerToggle  = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

                mDrawerLayout.setDrawerListener(mDrawerToggle);

                mDrawerToggle.syncState();
*/
    //  }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }
}