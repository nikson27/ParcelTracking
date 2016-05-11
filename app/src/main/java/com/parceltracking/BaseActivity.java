package com.parceltracking;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.parceltracking.AppConstants.ActivityConstants;
import com.parceltracking.AppConstants.AppConstants;
import com.parceltracking.image.AndroidLoadImageFromURLActivity;
import com.parceltracking.user.profile.UpdateProfileActivity;
import com.parceltracking.user.login.LoginActivity;
import com.parceltracking.user.signup.RegisterActivity;

/**
 * Created by ioan.contiu on 2/8/2016.
 */
public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout mDrawerLayout;
    public NavigationView mNavigationView;
    public Toolbar toolbar;
    public ActionBarDrawerToggle mDrawerToggle;
    public FragmentManager mFragmentManager;
    public FragmentTransaction mFragmentTransaction;
    public FrameLayout frameLayout;
    public static AccountDetails loginState;
    private int mSelectedId = R.id.nav_item_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginState = new AccountDetails(getApplicationContext());
        if (loginState.getLoginInformation() > 0) {
            setContentView(R.layout.activity_base_login);
        } else {
            setContentView(R.layout.activity_base);
        }

        initView();

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
                InputMethodManager inputMethodManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
                InputMethodManager inputMethodManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

    }


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

        mDrawerToggle.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            toolbar.setVisibility(View.GONE);
        } else {
            toolbar.setVisibility(View.VISIBLE);
        }
        super.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        mSelectedId = menuItem.getItemId();
        itemSelection(mSelectedId);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void initView() {
        mNavigationView = (NavigationView) findViewById(R.id.navigation_drawer_container);
        mNavigationView.setNavigationItemSelectedListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        frameLayout = (FrameLayout) findViewById(R.id.containerView);
        mFragmentManager = getSupportFragmentManager();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void itemSelection(int mSelectedId) {
        Intent intent;
        int callingActivity = getIntent().getIntExtra(AppConstants.CALLING_ACTIVITY, 0);
        switch (mSelectedId) {

            case R.id.nav_item_home:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                if (callingActivity == ActivityConstants.HomeActivity)
                    return;
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(AppConstants.CALLING_ACTIVITY, ActivityConstants.HomeActivity);
                startActivity(intent);
                break;

            case R.id.nav_item_sent:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                if (callingActivity == ActivityConstants.ImageLoaderActivity)
                    return;
                intent = new Intent(getApplicationContext(), AndroidLoadImageFromURLActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(AppConstants.CALLING_ACTIVITY, ActivityConstants.ImageLoaderActivity);
                startActivity(intent);
                break;


            case R.id.nav_item_search:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                if (callingActivity == ActivityConstants.TabsActivity)
                    return;
                intent = new Intent(getApplicationContext(), TabsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(AppConstants.CALLING_ACTIVITY, ActivityConstants.TabsActivity);
                startActivity(intent);
                break;
            case R.id.nav_item_signup:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                if (callingActivity == ActivityConstants.SignUpActivity)
                    return;
                intent = new Intent(getApplicationContext(), RegisterActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(AppConstants.CALLING_ACTIVITY, ActivityConstants.SignUpActivity);
                startActivity(intent);
                break;

            case R.id.nav_item_login:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                if (callingActivity == ActivityConstants.LoginActivity)
                    return;
                intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(AppConstants.CALLING_ACTIVITY, ActivityConstants.LoginActivity);
                startActivity(intent);
                break;

            case R.id.nav_item_logout:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                AlertDialog diaBox = AskOption();
                diaBox.show();
                break;

            case R.id.nav_item_update_profile:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                if (callingActivity == ActivityConstants.UpdateProfile)
                    return;
                intent = new Intent(getApplicationContext(), UpdateProfileActivity.class);
                intent.putExtra(AppConstants.CALLING_ACTIVITY, ActivityConstants.UpdateProfile);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.nav_item_my_orders:
                mDrawerLayout.closeDrawer(GravityCompat.START);
                if (callingActivity == ActivityConstants.MyOrders)
                    return;
                intent = new Intent(getApplicationContext(), OrderHistory.class);
                intent.putExtra(AppConstants.CALLING_ACTIVITY, ActivityConstants.MyOrders);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;

        }

    }

    private AlertDialog AskOption() {
        AlertDialog myLogoutDialog = new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Logout?")
                .setMessage("Do you want to logout?")
                .setIcon(R.drawable.gnome_session_logout)

                .setPositiveButton("yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        loginState.saveLoginState(0);
                        loginState.deleteUserData();
                        Toast.makeText(BaseActivity.this, "Logout Succcesfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        dialog.dismiss();
                    }

                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myLogoutDialog;

    }
}