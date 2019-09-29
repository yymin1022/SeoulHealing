package com.defcon.seoulhealing;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class LocationActivity extends AppCompatActivity {
    double latitude = 0;
    double longitude = 0;
    String locationSelect = "";

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;

    Button doneBtn;
    Location location;
    LocationManager locationManager;
    SharedPreferences prefs;
    Spinner locationSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        startActivity(new Intent(getBaseContext(), SplashActivity.class));

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        doneBtn = findViewById(R.id.location_btn_done);
        locationSpinner = findViewById(R.id.location_spinner_location);
        prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        if(prefs.getBoolean("isFirst", true)){
            startActivity(new Intent(this, WelcomeActivity.class));
        }

        startActivityAnimation();

        final ArrayList<String> locationArray = new ArrayList<>();
        locationArray.add("지역을 선택해주세요");
        locationArray.add("현재위치를 중심으로 찾기");
        locationArray.add("강남구");
        locationArray.add("강동구");
        locationArray.add("강북구");
        locationArray.add("강서구");
        locationArray.add("관악구");
        locationArray.add("광진구");
        locationArray.add("구로구");
        locationArray.add("금천구");
        locationArray.add("노원구");
        locationArray.add("도봉구");
        locationArray.add("동대문구");
        locationArray.add("동작구");
        locationArray.add("마포구");
        locationArray.add("서대문구");
        locationArray.add("서초구");
        locationArray.add("성동구");
        locationArray.add("성북구");
        locationArray.add("송파구");
        locationArray.add("양천구");
        locationArray.add("영등포구");
        locationArray.add("용산구");
        locationArray.add("은평구");
        locationArray.add("종로구");
        locationArray.add("중구");
        locationArray.add("중랑구");

        ArrayAdapter<String> locationArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_location_item, locationArray);
        locationArrayAdapter.setDropDownViewResource(R.layout.spinner_location_item);
        locationSpinner.setAdapter(locationArrayAdapter);
        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        doneBtn.setEnabled(false);
                        doneBtn.setTextColor(Color.parseColor("#afc8df"));
                        doneBtn.setText("지역이 선택되지 않았습니다");
                        break;
                    case 1:
                        AlertDialog.Builder builder = new AlertDialog.Builder(LocationActivity.this);
                        builder.setMessage("위성 상태에 따라 현재위치를 파악하지 못하거나 정확하지 않을 수 있습니다.");
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try{
                                    getLocationInfo(); //gps 정보 불러오는 메소드

                                }catch(Exception e){
                                    Log.e("Error", e.toString());
                                }
                            }
                        });
                        builder.create().show();

                        break;
                    default:
                        doneBtn.setEnabled(true);
                        doneBtn.setTextColor(Color.parseColor("#ffffff"));
                        doneBtn.setText("다음");
                        locationSelect = locationArray.get(i);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocationActivity.this, MainActivity.class);
                intent.putExtra("LOCATION", locationSelect);
                startActivity(intent);
            }
        });
    }

    private void getLocationInfo() {
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isNetworkEnabled) {
            Toast.makeText(LocationActivity.this, "GPS와 네트워크 상태를 확인해주세요!", Toast.LENGTH_SHORT).show();
        } else {

            int hasFineLocationPermission = ContextCompat.checkSelfPermission(LocationActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION);
            int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(LocationActivity.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION);

            if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED && hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) { }

            if (isNetworkEnabled) {

                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);

                if (locationManager != null)
                {
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    checkGPS();
                }
            }

            if (isGPSEnabled)
            {
                if (location == null)
                {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, locationListener);
                    if (locationManager != null)
                    {
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        checkGPS();
                    }
                }
            }
        }
    }

    final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            checkGPS();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
    
    private void checkGPS() {
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            locationSelect = longitude + ":" + latitude;

            doneBtn.setEnabled(true);
            doneBtn.setTextColor(Color.parseColor("#ffffff"));
            doneBtn.setText("다음");
        } else {
            Toast.makeText(LocationActivity.this, "현재위치를 확인하지 못했습니다.\n다시 시도하거나 직접 지역을 선택해주세요.", Toast.LENGTH_SHORT).show();
            doneBtn.setEnabled(false);
            doneBtn.setTextColor(Color.parseColor("#afc8df"));
            doneBtn.setText("지역을 다시 선택해주세요");
        }
    }

    private void stopUpdateGPS() {
        if(locationManager != null) {
            locationManager.removeUpdates(locationListener);
        }
    }

    private void startActivityAnimation() {
        ImageView locationMap = findViewById(R.id.location_img_map);
        Animation blink = AnimationUtils.loadAnimation(this, R.anim.blink_location_map);
        locationMap.startAnimation(blink);
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopUpdateGPS();
    }
}
