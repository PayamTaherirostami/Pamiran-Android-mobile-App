package com.pamiranindustries.pamiran;

import android.app.ProgressDialog;
import android.content.Context;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class CamerasActivity extends AppCompatActivity {
//    https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2
//    https://demonuts.com/Demonuts/JsonTest/Tennis/json_parsing.php
    private String URLstring = " https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2";
//    private static ProgressDialog mProgressDialog;
    private ListView listView;
    ArrayList<DataModel> dataModelArrayList;
    private ListAdapter listAdapter;
    List<DataModel> cameraList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera2);

        listView = findViewById(R.id.lv);

        retrieveJSON();
//            loadCameraData(" https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2");
    }

    private void retrieveJSON() {

//        showSimpleProgressDialog(this, "Loading...","Fetching Json",false);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
        new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Log.d("strrrrr", ">>" + response);
                try {
                    JSONObject obj = new JSONObject(response);
                    dataModelArrayList = new ArrayList<>();
                    JSONArray features = obj.getJSONArray("Features");
                    for (int j = 1; j < features.length(); j++) {
                        JSONObject point = features.getJSONObject(j);
                        JSONArray pointCoords = point.getJSONArray("PointCoordinate");
                        double[] coords = {pointCoords.getDouble(0), pointCoords.getDouble(1)};
                        JSONArray cameras = point.getJSONArray("Cameras");
                        for (int i = 0; i < cameras.length(); i++) {
                            JSONObject camera = cameras.getJSONObject(i);
                            DataModel Cam = new DataModel();
                            camera.getString("Description");
                            camera.getString("ImageUrl");
                            camera.getString("Type");
//                            camera.getString("PointCoordinate");

                            cameraList.add(Cam);
                        }
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
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }


    private void setupListview(){
//        removeSimpleProgressDialog();  //will remove progress dialog
        listAdapter = new ListAdapter(this, dataModelArrayList);
        listView.setAdapter(listAdapter);
    }

//    public static void removeSimpleProgressDialog() {
//        try {
//            if (mProgressDialog != null) {
//                if (mProgressDialog.isShowing()) {
//                    mProgressDialog.dismiss();
//                    mProgressDialog = null;
//                }
//            }
//        } catch (IllegalArgumentException ie) {
//            ie.printStackTrace();
//
//        } catch (RuntimeException re) {
//            re.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

//    public static void showSimpleProgressDialog(Context context, String title,
//                                                String msg, boolean isCancelable) {
//        try {
//            if (mProgressDialog == null) {
//                mProgressDialog = ProgressDialog.show(context, title, msg);
//                mProgressDialog.setCancelable(isCancelable);
//            }
//
//            if (!mProgressDialog.isShowing()) {
//                mProgressDialog.show();
//            }
//
//        } catch (IllegalArgumentException ie) {
//            ie.printStackTrace();
//        } catch (RuntimeException re) {
//            re.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}