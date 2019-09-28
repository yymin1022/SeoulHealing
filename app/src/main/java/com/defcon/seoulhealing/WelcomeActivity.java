package com.defcon.seoulhealing;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.Manifest;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class WelcomeActivity extends AppCompatActivity {
    Button doneBtn;
    Button locationBtn;
    SharedPreferences prefs;
    SharedPreferences.Editor ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        doneBtn = findViewById(R.id.welcome_btn_done);
        locationBtn = findViewById(R.id.welcome_btn_locationPermission);
        setupLocationBtn();
        startActivityAnimation();

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefs = getSharedPreferences("prefs", MODE_PRIVATE);
                ed = prefs.edit();
                ed.putBoolean("isFirst", false);
                ed.apply();
                finish();
            }
        });
        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder locationDialog = new AlertDialog.Builder(WelcomeActivity.this);
                locationDialog.setTitle("위치정보 사용 권한");
                locationDialog.setMessage("원활한 애플리케이션의 이용을 위해 위치정보 사용 권한을 허용해주셔야합니다");
                locationDialog.setCancelable(false);
                locationDialog.setPositiveButton("동의하기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TedPermission.with(WelcomeActivity.this)
                                .setPermissionListener(new PermissionListener() {
                                    @Override
                                    public void onPermissionGranted() {
                                        Toast.makeText(getApplicationContext(), "위치정보 사용 권한이 허용되었습니다", Toast.LENGTH_SHORT).show();
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
    }

    public void setupLocationBtn(){
        if(TedPermission.isGranted(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            locationBtn.setEnabled(false);
            locationBtn.setBackgroundColor(Color.parseColor("#00000000"));
            locationBtn.setText("위치정보 사용 권한이 허용되었습니다");

            doneBtn.setEnabled(true);
            doneBtn.setText("다음");
            doneBtn.setTextColor(Color.parseColor("#ffffff"));
        }else{
            locationBtn.setEnabled(true);
            locationBtn.setText("위치정보 사용 권한 허용하기");

            doneBtn.setEnabled(false);
            doneBtn.setText("설정이 완료되지 않았습니다");
            doneBtn.setTextColor(Color.parseColor("#afc8df"));
        }
    }

    private void startActivityAnimation() {
        ConstraintLayout welcomeLayout = findViewById(R.id.welcome_layout);
        ImageView welcomeLocation = findViewById(R.id.welcome_img);

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation bounce = AnimationUtils.loadAnimation(WelcomeActivity.this, R.anim.bounce_welcome_map);

        welcomeLayout.startAnimation(fadeIn);
        welcomeLocation.startAnimation(bounce);
    }
}
