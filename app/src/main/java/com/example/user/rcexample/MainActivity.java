package com.example.user.rcexample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.user.rcexample.database.DatabaseHelper;
import com.example.user.rcexample.restful.ProductApi;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;
import java.util.logging.Level;


public class MainActivity extends AppCompatActivity
        implements ProductFragment.OnFragmentInteractionListener
{
    ProductFragment productFragment = ProductFragment.newInstance();
    private LocationRequest mLocationRequest;
    private final String[] INITIAL_PERMS = {Manifest.permission.ACCESS_FINE_LOCATION};
    static Level level = Level.WARNING;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //createLocationRequest();
        getSupportFragmentManager().beginTransaction().
                add(R.id.fragment_container, productFragment, "ProductFragment").commit();
        startLocationUpdates();

    }

    protected void startLocationUpdates()
    {
        // Create the location request to start receiving updates
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setSmallestDisplacement(50f);

        // Create LocationSettingsRequest object using location request
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        // Check whether location settings are satisfied
        // https://developers.google.com/android/reference/com/google/android/gms/location/SettingsClient
        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(locationSettingsRequest);

        // new Google API SDK v11 uses getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, INITIAL_PERMS, 1);;
        }

        LocationServices.getFusedLocationProviderClient(this).requestLocationUpdates(mLocationRequest, new LocationCallback()
        {
            @Override
            public void onLocationResult(LocationResult locationResult)
            {
                Location location = locationResult.getLastLocation();
                Log.i("Lat/Lng", String.valueOf(location.getLatitude()) + "/" + String.valueOf(location.getLongitude()));
                Log.i("", "=====================================");
            }
        }, Looper.myLooper());
    }

    @Override
    public void onFragmentInteraction(Uri uri)
    {

    }
}
