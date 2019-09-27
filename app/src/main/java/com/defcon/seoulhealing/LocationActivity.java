package com.defcon.seoulhealing;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class LocationActivity extends AppCompatActivity {
    double latitude = 0;
    double longitude = 0;
    String locationSelect = "";

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

        ArrayAdapter<String> locationArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_location_item, locationArray);
        locationArrayAdapter.setDropDownViewResource(R.layout.spinner_location_item);
        locationSpinner.setAdapter(locationArrayAdapter);
        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        doneBtn.setEnabled(false);
                        doneBtn.setText("지역을 선택해주세요.");
                        break;
                    case 1:
                        AlertDialog.Builder builder = new AlertDialog.Builder(LocationActivity.this);
                        builder.setMessage("위성 상태에 따라 현재위치를 파악하지 못하거나 정확하지 않을 수 있습니다.");
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
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
                                    longitude = location.getLongitude();
                                    latitude = location.getLatitude();

                                    locationSelect = longitude + ":" + latitude;
                                    doneBtn.setEnabled(true);
                                    doneBtn.setText("완료");
                                }catch(Exception e){
                                    Log.e("Error", e.toString());
                                    Toast.makeText(LocationActivity.this, "현재위치를 확인하지 못했습니다.\n다시 시도하거나 직접 지역을 선택해주세요.", Toast.LENGTH_SHORT).show();
                                    doneBtn.setEnabled(false);
                                    doneBtn.setText("지역을 다시 선택해주세요.");
                                }
                            }
                        });
                        builder.create().show();

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
