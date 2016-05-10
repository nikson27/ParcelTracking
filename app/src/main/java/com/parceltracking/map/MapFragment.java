package com.parceltracking.map;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.parceltracking.R;
import com.parceltracking.map.DirectionPath.DirectionsJSONParser;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapFragment extends Fragment {
    MapView mapView;
    GoogleMap map;
    Marker marker;
    LatLng currentLocation;
    ArrayList<LatLng> markerPoints;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //  return inflater.inflate(R.layout.map_layout,null);
        View v = inflater.inflate(com.parceltracking.R.layout.map_layout, container, false);
        // Gets the MapView from the XML layout and creates it

        try {
            MapsInitializer.initialize(getActivity());
        } catch (Exception e) {

        }
        markerPoints = new ArrayList<LatLng>();
        switch (GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity())) {
            case ConnectionResult.SUCCESS:
                Toast.makeText(getActivity(), "Map loaded succesfully", Toast.LENGTH_SHORT).show();
                mapView = (MapView) v.findViewById(R.id.map);
                mapView.onCreate(savedInstanceState);
                // Gets to GoogleMap from the MapView and does initialization stuff
                if (mapView != null) {
                    map = mapView.getMap();
                    map.getUiSettings().setMyLocationButtonEnabled(true);

                    if(map!=null){

                        // Enable MyLocation Button in the Map
                        map.setMyLocationEnabled(true);

                        // Setting onclick event listener for the map
                        map.setOnMapClickListener(new OnMapClickListener() {

                            @Override
                            public void onMapClick(LatLng point) {

                                // Already two locations
                                if(markerPoints.size()>1){
                                    markerPoints.clear();
                                    map.clear();
                                }

                                // Adding new item to the ArrayList
                                markerPoints.add(point);

                                // Creating MarkerOptions
                                MarkerOptions options = new MarkerOptions();

                                // Setting the position of the marker
                                options.position(point);

                                /**
                                 * For the start location, the color of marker is GREEN and
                                 * for the end location, the color of marker is RED.
                                 */
                                if(markerPoints.size()==1){
                                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                                }else if(markerPoints.size()==2){
                                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                                }

                                // Add new marker to the Google Map Android API V2
                                map.addMarker(options);

                                // Checks, whether start and end locations are captured
                                if(markerPoints.size() >= 2){
                                    LatLng origin = markerPoints.get(0);
                                    LatLng dest = markerPoints.get(1);

                                    // Getting URL to the Google Directions API
                                    String url = getDirectionsUrl(origin, dest);

                                    DownloadTask downloadTask = new DownloadTask();

                                    // Start downloading json data from Google Directions API
                                    downloadTask.execute(url);
                                }
                            }
                        });
                    }
                    if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for Activity#requestPermissions for more details.
                        return v;
                    }
                    map.setMyLocationEnabled(true);
                   /* CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(43.1, -87.9), 10);
                    map.animateCamera(cameraUpdate);*/
                }
                break;
            case ConnectionResult.SERVICE_MISSING:
                Toast.makeText(getActivity(), "Google play services are missing", Toast.LENGTH_SHORT).show();
                break;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
                Toast.makeText(getActivity(), "Google play services update is required", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getActivity(), GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity()), Toast.LENGTH_SHORT).show();
        }


        map.setOnMyLocationChangeListener(myLocationChangeListener);
        // Updates the location and zoom of the MapView

        return v;

    }

    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            currentLocation = new LatLng(location.getLatitude(), location.getLongitude());

            // map.clear();
            marker = map.addMarker(new MarkerOptions().position(currentLocation));
            if (map != null) {
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 16.0f));
            }
        }
    };


    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
        MapStateManager mapStateManager = new MapStateManager(getActivity());
        CameraPosition position = mapStateManager.getSavedCameraPosition();
        if (position != null) {
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
            map.moveCamera(update);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onStop() {
        super.onStop();
        MapStateManager mapStateManager = new MapStateManager(getActivity());
        mapStateManager.saveMapState(map);
    }

    private String getDirectionsUrl(LatLng origin,LatLng dest){

        // Origin of route
        String str_origin = "origin="+origin.latitude+","+origin.longitude;

        // Destination of route
        String str_dest = "destination="+dest.latitude+","+dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin+"&"+str_dest+"&"+sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;

        return url;
    }
    /** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while( ( line = br.readLine()) != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){
            Log.d("Exception while downloading url", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String>{

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try{
                // Fetching the data from web service
                data = downloadUrl(url[0]);
                Log.d("DATADATADATADATA",data);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    /** A class to parse the Google Places in JSON format */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();

            // Traversing through all the routes
            for(int i=0;i<result.size();i++){
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(5);
                lineOptions.color(Color.RED);
            }

            // Drawing polyline in the Google Map for the i-th route
            map.addPolyline(lineOptions);
        }
    }
}
