package com.defcon.seoulhealing;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {
    String locationSelect;

    Button doneBtn;
    Button locationBtn;
    LinearLayout locationLayout;
    LinearLayout permissionLayout;
    SharedPreferences prefs;
    SharedPreferences.Editor ed;
    Spinner locationSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        doneBtn = findViewById(R.id.welcome_btn_done);
        locationBtn = findViewById(R.id.welcome_btn_locationPermission);
        locationSpinner = findViewById(R.id.welcome_spinner_location);

        locationLayout = findViewById(R.id.welcome_layout_location);
        permissionLayout = findViewById(R.id.welcome_layout_permission);

        setupLocationBtn();

        final ArrayList<String> locationArray = new ArrayList<>();
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
                locationSelect = locationArray.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder locationDialog = new AlertDialog.Builder(WelcomeActivity.this);
                locationDialog.setTitle("위치정보 사용 권한");
                locationDialog.setMessage("원활한 애플리케이션의 이용을 위해 위치정보 사용 권한을 허용해주셔야합니다.");
                locationDialog.setCancelable(false);
                locationDialog.setPositiveButton("동의하기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TedPermission.with(WelcomeActivity.this)
                                .setPermissionListener(new PermissionListener() {
                                    @Override
                                    public void onPermissionGranted() {
                                        Toast.makeText(getApplicationContext(), "위치정보 사용 권한이 허용되었습니다.", Toast.LENGTH_SHORT).show();
                                        setupLocationBtn();
                                    }

                                    @Override
                                    public void onPermissionDenied(List<String> deniedPermissions) {
                                        setupLocationBtn();
                                    }
                                })
                                .setDeniedMessage("위치정보 사용 권한이 허용되지 않았습니다. 애플리케이션 사용 중 예상치 못한 문제가 발생할 수 있으며, [설절] > [앱 및 알림] > [Seoul Healing] > [권한]으로 이동하여 위치정보 사용 권한을 허용해주세요.")
                                .setPermissions(Manifest.permission.ACCESS_FINE_LOCATION)
                                .check();
                        }
                });
                locationDialog.show();
            }
        });
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefs = getSharedPreferences("prefs", MODE_PRIVATE);
                ed = prefs.edit();
                ed.putString("location", locationSelect);
                ed.putBoolean("isFirst", false);
                ed.apply();
                finish();
            }
        });
    }

    public void setupLocationBtn(){
        if(TedPermission.isGranted(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            locationBtn.setEnabled(false);
            locationBtn.setText("위치정보 사용 권한이 허용되었습니다.");

            doneBtn.setEnabled(true);
            doneBtn.setText("완료");
        }else{
            locationBtn.setEnabled(true);
            locationBtn.setText("위치정보 사용 권한 허용하기");

            doneBtn.setEnabled(false);
            doneBtn.setText("설정이 완료되지 않았습니다.");
        }
    }
}
