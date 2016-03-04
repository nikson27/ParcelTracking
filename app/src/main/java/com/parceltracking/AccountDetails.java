package com.parceltracking;

import android.content.Context;
import android.content.SharedPreferences;

import com.parceltracking.AppConstants.AppConstants;
import com.parceltracking.user.UserDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by ioan.contiu on 2/16/2016.
 */
public class AccountDetails {
    private SharedPreferences saveState;
    public static final String myPref = "accountdetails";
    private static boolean login = false;


    public AccountDetails(Context context) {
        saveState = context.getSharedPreferences(myPref, context.MODE_PRIVATE);
    }

    public void saveUserInformation(JSONObject jObj) throws JSONException {
        SharedPreferences.Editor editor = saveState.edit();
        String fname = jObj.getString(AppConstants.FNAME);
        String lname = jObj.getString(AppConstants.LNAME);
        String phone = jObj.getString(AppConstants.PHONE);
        String city = jObj.getString(AppConstants.CITY);
        String state = jObj.getString(AppConstants.STATE);
        String addrLine1 = jObj.getString(AppConstants.ADDRESSLINE1);
        String addrLine2 = jObj.getString(AppConstants.ADDRESSLINE2);
        String credit = jObj.getString(AppConstants.CREDIT);
        editor.putString(AppConstants.FNAME, fname);
        editor.putString(AppConstants.LNAME, lname);
        editor.putString(AppConstants.PHONE, phone);
        editor.putString(AppConstants.CITY, city);
        editor.putString(AppConstants.STATE, state);
        editor.putString(AppConstants.ADDRESSLINE1, addrLine1);
        editor.putString(AppConstants.ADDRESSLINE2, addrLine2);
        editor.putString(AppConstants.CREDIT, credit);
        editor.commit();


    }
    public void saveUserInformation(UserDetails details){
        SharedPreferences.Editor editor = saveState.edit();
        editor.putString(AppConstants.FNAME, details.getfName());
        editor.putString(AppConstants.LNAME, details.getlName());
        editor.putString(AppConstants.PHONE, details.getPhone());
        editor.putString(AppConstants.CITY, details.getCity());
        editor.putString(AppConstants.STATE, details.getState());
        editor.putString(AppConstants.ADDRESSLINE1, details.getAddressLine1());
        editor.putString(AppConstants.ADDRESSLINE2, details.getAddressLine2());
        editor.putString(AppConstants.CREDIT, details.getCredit());
        editor.commit();
    }

    public UserDetails getUserInformation() {
        String fname = saveState.getString(AppConstants.FNAME, null);
        String lname = saveState.getString(AppConstants.LNAME, null);
        String phone = saveState.getString(AppConstants.PHONE, null);
        String city = saveState.getString(AppConstants.CITY, null);
        String state = saveState.getString(AppConstants.STATE, null);
        String addrLine1 = saveState.getString(AppConstants.ADDRESSLINE1, null);
        String addrLine2 = saveState.getString(AppConstants.ADDRESSLINE2, null);
        String credit = saveState.getString(AppConstants.CREDIT, null);
        UserDetails user = new UserDetails(fname, lname, phone, city, state, addrLine1, addrLine2, credit);

        return user;
    }

    public void saveLoginState(boolean loginstate) {
        SharedPreferences.Editor editor = saveState.edit();
        editor.putBoolean(AppConstants.DETAILS, loginstate);
        editor.commit();
    }

    public void deleteUserData() {
        SharedPreferences.Editor editor = saveState.edit();
        editor.clear();
        editor.commit();
    }

    public boolean getLoginInformation() {
        login = saveState.getBoolean(AppConstants.DETAILS, false);
        if (login == false) {
            return false;
        }
        return true;
    }
}
