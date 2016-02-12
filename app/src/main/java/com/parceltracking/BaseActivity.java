package com.parceltracking;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.parceltracking.image.AndroidLoadImageFromURLActivity;
import com.parceltracking.login.LoginActivity;
import com.parceltracking.signup.SignUpActivity;

/**
 * Created by ioan.contiu on 2/8/2016.
 */
public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

  public  DrawerLayout mDrawerLayout;
    public  NavigationView mNavigationView;
    String currentFragment=null;

    public  Toolbar toolbar;
    public  ActionBarDrawerToggle mDrawerToggle;

     public  FragmentManager mFragmentManager;
     public  FragmentTransaction mFragmentTransaction;
    private int mSelectedId=R.id.nav_item_inbox;
    public FrameLayout frameLayout;





    private void initView() {
        mNavigationView= (NavigationView) findViewById(R.id.navigation_drawer_container);
        mNavigationView.setNavigationItemSelectedListener(this);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawerLayout);
        frameLayout = (FrameLayout) findViewById(R.id.containerView);
        mFragmentManager = getSupportFragmentManager();
        toolbar= (Toolbar) findViewById(R.id.toolbar);
    }

    private void itemSelection(int mSelectedId) {
        Intent intent;
        int callingActivity = getIntent().getIntExtra("calling-activity", 0);
        switch(mSelectedId){

         /*   case R.id.nav_item_inbox:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                Intent intent3 = new Intent(getApplicationContext(), TabsActivity.class);
                startActivity(intent3);

                break;
*/
            case R.id.nav_item_sent:
                if(callingActivity==ActivityConstants.ImageLoaderActivity)
                    return;
                intent = new Intent(getApplicationContext(), AndroidLoadImageFromURLActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("calling-activity", ActivityConstants.ImageLoaderActivity);
                startActivity(intent);
                break;


            case R.id.nav_item_draft:
                mDrawerLayout.closeDrawer(GravityCompat.START);

                if(callingActivity==ActivityConstants.TabsActivity)
                    return;
                intent = new Intent(getApplicationContext(), TabsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("calling-activity", ActivityConstants.TabsActivity);
                startActivity(intent);

                break;
            case R.id.nav_item_signup:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                if(callingActivity==ActivityConstants.SignUpActivity)
                    return;
                intent = new Intent(getApplicationContext(), SignUpActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("calling-activity", ActivityConstants.SignUpActivity);
                startActivity(intent);
                break;

            case R.id.nav_item_login:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                if(callingActivity==ActivityConstants.LoginActivity)
                    return;
                intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("calling-activity", ActivityConstants.LoginActivity);
                startActivity(intent);
                break;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);
        initView();

        /*
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_drawer_container) ;
        btnLogIn=(Button) findViewById(R.id.Login);
        btnSignUp=(Button) findViewById(R.id.SignUp);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
*/

        mDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name,R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        //default it set first item as selected
     //  mSelectedId=savedInstanceState ==null ? R.id.nav_item_inbox: savedInstanceState.getInt("SELECTED_ID");
        itemSelection(mSelectedId);
      /*  mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,toolbar,R.string.app_name,
                R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

*/

       /* mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
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
*/

        /**
         * Lets inflate the very first fragment
         * Here , we are inflating the TabFragment as the first Fragment
         */



/*
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
*/

    }


/*
    @Override
    public void setContentView(final int layoutResID) {
        // Your base layout here
        fullLayout= (LinearLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        actContent= (FrameLayout) fullLayout.findViewById(R.id.containerView);

        // Setting the content of layout your provided to the act_content frame
        getLayoutInflater().inflate(layoutResID, actContent, true);
        super.setContentView(fullLayout);

        // here you can get your drawer buttons and define how they
        // should behave and what must they do, so you won't be
        // needing to repeat it in every activity class
    }
*/
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
        mDrawerToggle.onConfigurationChanged(newConfig);
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){

            toolbar.setVisibility(View.GONE);
        }
        else
        {
            toolbar.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        mSelectedId=menuItem.getItemId();
        itemSelection(mSelectedId);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //save selected item so it will remains same even after orientation change
        outState.putInt("SELECTED_ID",mSelectedId);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}