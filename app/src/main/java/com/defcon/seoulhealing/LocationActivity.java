package com.defcon.seoulhealing;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LocationActivity extends AppCompatActivity {
    String locationSelect;

    Button doneBtn;
    LocationManager locationManager;
    SharedPreferences prefs;
    Spinner locationSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        startActivity(new Intent(getBaseContext(), SplashActivity.class));

        doneBtn = findViewById(R.id.location_btn_done);
        locationSpinner = findViewById(R.id.location_spinner_location);
        prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        if(prefs.getBoolean("isFirst", true)){
            startActivity(new Intent(this, WelcomeActivity.class));
        }

        final ArrayList<String> locationArray = new ArrayList<>();
        locationArray.add("힐링장소를 찾을 구를 선택해주세요.");
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

        ArrayAdapter<String> locationArrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                locationArray);
        locationSpinner.setAdapter(locationArrayAdapter);
        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        doneBtn.setEnabled(false);
                        doneBtn.setText("설정이 완료되지 않았습니다.");
                        break;
                    case 1:
                        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

                        try{
                            Criteria criteria = new Criteria();
                            criteria.setAccuracy(Criteria.NO_REQUIREMENT);
                            criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);
                            criteria.setAltitudeRequired(false);
                            criteria.setBearingRequired(false);
                            criteria.setSpeedRequired(false);
                            criteria.setCostAllowed(true);
                            String bestProvider = locationManager.getBestProvider(criteria, true);
                            @SuppressLint("MissingPermission")
                            Location location = locationManager.getLastKnownLocation(bestProvider);
                            double longitude = location.getLongitude();
                            double latitude = location.getLatitude();

                            locationSelect = longitude + ":" + latitude;

                            doneBtn.setEnabled(true);
                            doneBtn.setText("완료");

                            // Get Address from Lat, Lang Value
                            //Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.KOREA);
                            //List<Address> address;
                            //address = geocoder.getFromLocation(latitude, longitude, 1);

                            //if (address != null && address.size() > 0) {
                            //    String currentLocationAddress = address.get(0).getAddressLine(0);
                            //    Toast.makeText(getApplicationContext(), currentLocationAddress, Toast.LENGTH_SHORT).show();
                            //}
                        }catch(Exception e){
                            Log.e("Error", e.toString());
                            doneBtn.setEnabled(false);
                            doneBtn.setText("현재 위치를 확인하지 못했습니다. 다시시도하거나 직접 지역을 선택해주세요.");
                        }
                        break;
                    default:
                        doneBtn.setEnabled(true);
                        doneBtn.setText("완료");
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
}
