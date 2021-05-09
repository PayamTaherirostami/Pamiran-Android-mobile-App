package com.pamiranindustries.pamiran;

import android.app.ProgressDialog;
import android.content.Context;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CamerasActivity extends AppCompatActivity {

    private GoogleMap mMap;
    private String URLstring = " https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2";
    private static ProgressDialog mProgressDialog;
    private ListView listView;
    private ListAdapter listAdapter;
    public static final String TAG = "Basic Network Demo";
    // Whether there is a Wi-Fi connection.
    private static boolean wifiConnected = false;
    // Whether there is a mobile connection.
    private static boolean mobileConnected = false;

    ArrayList<Camera> cameraList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera2);

        //check the connection of device
        // if it's connected run the main function and read the data from the link
        // if not connected send a toast msg
    if (checkNetwork()){
        retrieveJSON();
    }else if(!checkNetwork()){
//        removeSimpleProgressDialog();
        Toast.makeText(CamerasActivity.this,"Please connect to Internet.You are disconnected! Turn the WIFI or the LTE on to run this feature!",Toast.LENGTH_LONG).show();
        Toast.makeText(CamerasActivity.this,"Please connect your device!",Toast.LENGTH_LONG).show();
    }

        listView = findViewById(R.id.lv);
        retrieveJSON();
    }

    // main function to read the data
    private void retrieveJSON() {
//        showSimpleProgressDialog(this, "Loading...","Fetching Json",false);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                Log.d("strPayam", ">>" + response);
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray features = obj.getJSONArray("Features");

//                    Log.d("strPayam", ">>" + features.length());
                            for (int j = 0; j < features.length(); j++) {
                                JSONObject point = features.getJSONObject(j);
                                JSONArray points = point.getJSONArray("PointCoordinate");
                                double[] coords={points.getDouble(0),points.getDouble(1)};
//                                Log.d("strPayam", ">>" + coords);
                                JSONArray cameras = features.getJSONObject(j).getJSONArray("Cameras");
                                Camera camera = new Camera(cameras.getJSONObject(0).getString("Description"),
                                        cameras.getJSONObject(0).getString("ImageUrl"),
                                        cameras.getJSONObject(0).getString("Type"),coords);
                                cameraList.add(camera);
                            }
                            setupListview();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    private void setupListview() {
//        removeSimpleProgressDialog();  //will remove progress dialog
        listAdapter = new ListAdapter(this, cameraList);
        listView.setAdapter(listAdapter);
    }
//remove the dialogbox
    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//show the dialogbox
    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkNetwork() {
        boolean have_WIFI = false;
        boolean have_MobData = false;
        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if (wifiConnected) {
                Log.i(TAG, getString(R.string.wifi_connection));
                have_WIFI = true;
            }
            if (mobileConnected) {
                Log.i(TAG, getString(R.string.mobile_connection));
                have_MobData = true;
            }
            else {
                Log.i(TAG, getString(R.string.no_wifi_or_mobile));
            }

        }
        return have_MobData || have_WIFI;
    }

}