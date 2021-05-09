package com.pamiranindustries.pamiran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TrafficMapActivity extends FragmentActivity implements OnMapReadyCallback {


    ArrayList<Camera> cameraList = null;
    private GoogleMap mMap;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    double[] coords;
    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traffic_map);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();

        loadCameras();

    }

    private void fetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.
                PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    currentLocation=location;
                    Toast.makeText(getApplicationContext(),"My Location is: "+currentLocation.getLatitude()+"-"+currentLocation.
                            getLongitude(),Toast.LENGTH_LONG).show();
//                    SupportMapFragment supportMapFragment =  (SupportMapFragment) getSupportFragmentManager().
//                            findFragmentById(R.id.google_map);
//                    supportMapFragment.getMapAsync(TrafficMapActivity.this::onMapReady);

                }
            }
        });
    }

    private void loadCameras() {
        this.cameraList = new ArrayList<>();
        String URLstring = " https://web6.seattle.gov/Travelers/api/Map/Data?zoomId=13&type=2";
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
                                coords = new double[]{points.getDouble(0), points.getDouble(1)};
//                                Log.d("strPayam", ">>" + points);

                                JSONArray cameras = features.getJSONObject(j).getJSONArray("Cameras");
                                Camera camera = new Camera(cameras.getJSONObject(0).getString("Description"),
                                        cameras.getJSONObject(0).getString("ImageUrl"),
                                        cameras.getJSONObject(0).getString("Type"), coords);
                                cameraList.add(camera);
                            }
                            SupportMapFragment supportMapFragment =  (SupportMapFragment) getSupportFragmentManager().
                                    findFragmentById(R.id.google_map);
                            supportMapFragment.getMapAsync(TrafficMapActivity.this::onMapReady);
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
    // if map is ready make those markers for cameras
       @Override
    public void onMapReady(GoogleMap googleMap) {
           for (int i=0; i < cameraList.size(); i++) {
               Camera camera = cameraList.get(i);
               LatLng position = new LatLng(camera.getCoords()[0], camera.getCoords()[1]);
               MarkerOptions markerOptions = new MarkerOptions().position(position)
                       .title(camera.description).snippet(camera.getimageurl());
               googleMap.animateCamera(CameraUpdateFactory.newLatLng(position));
               googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 10));
               Marker marker= googleMap.addMarker((markerOptions));
               marker.setTag(i);


           }
               googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                  @Override
                  public boolean onMarkerClick(Marker marker) {
//                      for (int i = 0; i < cameraList.size(); i++) {
                          Camera camera = cameraList.get((Integer) marker.getTag());
                          TextView locs = findViewById(R.id.locations);
                          locs.setText(camera.getimageurl());


//                      }
                      return false;
                  }
              });

           LatLng latLng= new LatLng( 47.67087823458237, -122.12067053254239);
//           Log.d("PayamCurrentLoc: ","->"+ latLng);
            Marker marker= googleMap.addMarker(new MarkerOptions().
             position(latLng).
            title("Hey")
            .snippet("You are HERE!"));
            marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            marker.setTag(0);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    fetchLastLocation();
                }
            break;

        }
    }

}


