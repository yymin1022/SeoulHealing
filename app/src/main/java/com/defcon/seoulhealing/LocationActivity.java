package com.defcon.seoulhealing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

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
                        doneBtn.setEnabled(false);
                        doneBtn.setText("설정이 완료되지 않았습니다.");
//                        Snackbar.make(view, "아직 구현되지 않은 기능입니다.", Snackbar.LENGTH_SHORT).show();

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
                            Location location = locationManager.getLastKnownLocation(bestProvider);
                            double longitude = location.getLongitude();
                            double latitude = location.getLatitude();

                            Toast.makeText(getApplicationContext(), "Current Location\nLong : " + longitude + "\nLati : " + latitude, Toast.LENGTH_SHORT).show();

                        }catch(SecurityException e){
                            Log.e("Error", e.toString());
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
