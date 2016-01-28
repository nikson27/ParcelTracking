package com.parceltracking.map;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;


/**
 * Created by ioan.contiu on 1/28/2016.
 */
public class MapStateManager {
    private static final String LOGITUDE="logitude";
    private static final String LATITUDE="latitude";
    private static final String ZOOM="zoom";
    private static final String BEARING="bearing";
    private static final String TILT="tilt";
    private static final String PREFS_NAME="mapCameraState";


    private SharedPreferences mapStatePrefs;

    public MapStateManager(Context context){
        mapStatePrefs=context.getSharedPreferences(PREFS_NAME,context.MODE_PRIVATE);
    }

    public void saveMapState(GoogleMap map){
        SharedPreferences.Editor editor=mapStatePrefs.edit();
        CameraPosition position=map.getCameraPosition();
        editor.putFloat(LATITUDE, (float) position.target.latitude);
        editor.putFloat(LOGITUDE, (float) position.target.longitude);
        editor.putFloat(ZOOM,position.zoom);
        editor.putFloat(TILT,position.tilt);
        editor.putFloat(BEARING,position.bearing);
        editor.commit();
    }

    public CameraPosition getSavedCameraPosition(){
        double latitude=mapStatePrefs.getFloat(LATITUDE,0);
        if(latitude==0){
            return null;
        }
        double longitude=mapStatePrefs.getFloat(LOGITUDE,0);
        LatLng target=new LatLng(latitude,longitude);
        float zoom=mapStatePrefs.getFloat(ZOOM,0);
        float tilt=mapStatePrefs.getFloat(TILT,0);
        float bearing=mapStatePrefs.getFloat(BEARING,0);
        CameraPosition position=new CameraPosition(target,zoom,tilt,bearing);
        return position;
    }
}
