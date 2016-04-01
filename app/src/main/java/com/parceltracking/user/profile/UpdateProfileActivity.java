package com.parceltracking.user.profile;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.parceltracking.AccountDetails;
import com.parceltracking.AppConstants.AppConstants;
import com.parceltracking.AppConstants.AppLinks;
import com.parceltracking.BaseActivity;
import com.parceltracking.R;
import com.parceltracking.user.UserDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ioan.contiu on 2/19/2016.
 */
public class UpdateProfileActivity extends BaseActivity {

    private final String TAG="Update Activity";
    private ProgressDialog pDialog;
    EditText fname;
    EditText lname;
    EditText phone;
    EditText city;
    EditText state;
    EditText addrln1;
    EditText addrln2;
    EditText creditLimit;
    Button cancel;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_update_profile, frameLayout);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        initView();
        updateView();

        cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(UpdateProfileActivity.this, "saving", Toast.LENGTH_SHORT).show();
                save();

                finish();
            }
        });

    }

    private void save() {

        AccountDetails details = new AccountDetails(this);
        String savefName = fname.getText().toString();
        String savelName = lname.getText().toString();
        String savePhone = phone.getText().toString();
        String saveCity = city.getText().toString();
        String saveAddrln1 = addrln1.getText().toString();
        String saveState = state.getText().toString();
        String saveAddrln2 = addrln2.getText().toString();
        String saveCredit = creditLimit.getText().toString();
        UserDetails uDetails = new UserDetails(details.getLoginInformation(), savefName, savelName, savePhone, saveCity, saveState, saveAddrln1, saveAddrln2, saveCredit);
        details.saveUserInformation(uDetails);
        SaveOnServer(String.valueOf(details.getLoginInformation()), savefName, savelName, savePhone, saveCity, saveState, saveAddrln1, saveAddrln2, saveCredit);

    }

    private void initView() {
        fname = (EditText) findViewById(R.id.update_fname);
        lname = (EditText) findViewById(R.id.update_lname);
        phone = (EditText) findViewById(R.id.update_phone);
        city = (EditText) findViewById(R.id.update_city);
        addrln1 = (EditText) findViewById(R.id.update_addressline1);
        state = (EditText) findViewById(R.id.update_state);
        addrln2 = (EditText) findViewById(R.id.update_addressline2);
        creditLimit = (EditText) findViewById(R.id.update_creditlimit);
        cancel = (Button) findViewById(R.id.btn_update_profile_discard);
        save = (Button) findViewById(R.id.btn_update_profile_save);

    }

    private void updateView() {

        UserDetails userDetails = loginState.getUserInformation();
        if (userDetails.getfName() != null) {
            fname.setText(userDetails.getfName());
        }
        if (userDetails.getlName() != null) {
            lname.setText(userDetails.getlName());
        }

        if (userDetails.getPhone() != null) {
            phone.setText(userDetails.getPhone());
        }


        if (userDetails.getAddressLine1() != null) {
            addrln1.setText(userDetails.getAddressLine1());
        }
        if (userDetails.getAddressLine2() != null) {
            addrln2.setText(userDetails.getAddressLine2());
        }

        if (userDetails.getCity() != null) {
            city.setText(userDetails.getCity());
        }
        if (userDetails.getState() != null) {
            state.setText(userDetails.getState());
        }

        if (userDetails.getCredit() != null) {
            creditLimit.setText(userDetails.getCredit());
        }

    }

    private void SaveOnServer(final String id, final String fName, final String lName,final String phone,final String city,final  String state,final String addrln1,final String addrln2,final String credit) {

        pDialog.setMessage("Updating ...");
       // showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppLinks.URL_UPDATE_PROFILE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Update Response: " + response.toString());
              //  hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean(AppConstants.ERROR);
                    String errorMsg = jObj.getString(AppConstants.ERROR_MSG);
                    if (!error) {
                        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();


                    } else {

                        // Error occurred in registration. Get the error
                        // message

                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Updating Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
              //  hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put(AppConstants.FIRST_NAME, fName);
                params.put(AppConstants.LAST_NAME, lName);
                params.put(AppConstants.PHONE, phone);
                params.put(AppConstants.CITY, city);
                params.put(AppConstants.UID, id);
                params.put(AppConstants.STATE, state);
                params.put(AppConstants.ADDRESSLINE1, addrln1);
                params.put(AppConstants.ADDRESSLINE2, addrln2);
                params.put(AppConstants.CREDIT, credit);


                return params;
            }

        };

        // Adding request to request queue
        Volley.newRequestQueue(getApplicationContext()).add(strReq);

    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}
