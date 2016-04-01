package com.parceltracking;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.parceltracking.AppConstants.AppConstants;
import com.parceltracking.user.UserDetails;
import org.json.JSONObject;

/**
 * Created by ioan.contiu on 2/16/2016.
 */

public class AccountDetails {
    private SharedPreferences saveState;
    public static final String myPref = "accountdetails";
    private static int uid = 0;

    /**
     * Accout Details Constructor
     *
     * @params  JSONObject jObj
     * **/
    public AccountDetails(Context context) {
        saveState = context.getSharedPreferences(myPref, Context.MODE_PRIVATE);
    }
    /**
     * Save information about User
     *
     * @params  JSONObject jObj
     * **/
    public void saveUserInformation(JSONObject jObj)  {
        try{
        SharedPreferences.Editor editor = saveState.edit();
        String fname = jObj.getString(AppConstants.FIRST_NAME);
        String lname = jObj.getString(AppConstants.LAST_NAME);
        String phone = jObj.getString(AppConstants.PHONE);
        String city = jObj.getString(AppConstants.CITY);
        String state = jObj.getString(AppConstants.STATE);
        String addrLine1 = jObj.getString(AppConstants.ADDRESSLINE1);
        String addrLine2 = jObj.getString(AppConstants.ADDRESSLINE2);
        String credit = jObj.getString(AppConstants.CREDIT);
        editor.putString(AppConstants.FIRST_NAME, fname);
        editor.putString(AppConstants.LAST_NAME, lname);
        editor.putString(AppConstants.PHONE, phone);
        editor.putString(AppConstants.CITY, city);
        editor.putString(AppConstants.STATE, state);
        editor.putString(AppConstants.ADDRESSLINE1, addrLine1);
        editor.putString(AppConstants.ADDRESSLINE2, addrLine2);
        editor.putString(AppConstants.CREDIT, credit);
        editor.commit();}
        catch(Exception ex) {
            Log.d("AccountDetails", "json Response format " + jObj.toString());
            return;
        }


    }

    /**
     * Save information about User
     * **/
    public void saveUserInformation(UserDetails details){
        SharedPreferences.Editor editor = saveState.edit();
        editor.putString(AppConstants.FIRST_NAME, details.getfName());
        editor.putString(AppConstants.LAST_NAME, details.getlName());
        editor.putString(AppConstants.PHONE, details.getPhone());
        editor.putString(AppConstants.CITY, details.getCity());
        editor.putString(AppConstants.STATE, details.getState());
        editor.putString(AppConstants.ADDRESSLINE1, details.getAddressLine1());
        editor.putString(AppConstants.ADDRESSLINE2, details.getAddressLine2());
        editor.putString(AppConstants.CREDIT, details.getCredit());
        editor.commit();
    }

    /**
     * Gets information about User
     * **/
    public UserDetails getUserInformation() {
        String fname = saveState.getString(AppConstants.FIRST_NAME, null);
        String lname = saveState.getString(AppConstants.LAST_NAME, null);
        String phone = saveState.getString(AppConstants.PHONE, null);
        String city = saveState.getString(AppConstants.CITY, null);
        String state = saveState.getString(AppConstants.STATE, null);
        String addrLine1 = saveState.getString(AppConstants.ADDRESSLINE1, null);
        String addrLine2 = saveState.getString(AppConstants.ADDRESSLINE2, null);
        String credit = saveState.getString(AppConstants.CREDIT, null);
        UserDetails user = new UserDetails(uid,fname, lname, phone, city, state, addrLine1, addrLine2, credit);

        return user;
    }

    /**
     * Save user ID
     * >0 user is logged in
     * **/

    public void saveLoginState(int  uid) {
        SharedPreferences.Editor editor = saveState.edit();
        editor.putInt(AppConstants.DETAILS, uid);
        editor.commit();
    }

    /**
     * Remove all user information
     * **/
    public void deleteUserData() {
        SharedPreferences.Editor editor = saveState.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * Gets user ID
     * >0 user is logged in
     * **/

    public int getLoginInformation() {
        uid = saveState.getInt(AppConstants.DETAILS,0);
        if (uid == 0) {
            return 0;
        }
        return uid;
    }
}
