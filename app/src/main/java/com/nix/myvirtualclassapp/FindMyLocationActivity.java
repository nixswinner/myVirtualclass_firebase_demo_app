package com.nix.myvirtualclassapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FindMyLocationActivity extends AppCompatActivity {

    private TextView txtCoordinates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_my_location);
        Button btnFindLocation = findViewById(R.id.btn_Location);

        btnFindLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMyLocation();

            }
        });

        txtCoordinates = findViewById(R.id.txtCoordinates);
    }

    private void getMyLocation(){
        //check if the user has given
        checkLocationPermission();

        Location location = null;
        LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //execute

                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                Log.e("Location ",""+location);


        }else{
            //enable
            Toast.makeText(this,"Kindly Enable your GPS",Toast.LENGTH_SHORT).show();
            enableGPS();
        }
    }
    private void enableGPS(){
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }

    private void checkLocationPermission(){
        ActivityCompat.requestPermissions(FindMyLocationActivity.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==1){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //location permission
                getMyLocation();
            }else {
                Toast.makeText(FindMyLocationActivity.this,
                        "Ooops!Permission Denied",Toast.LENGTH_SHORT).show();
            }
        }
    }
}