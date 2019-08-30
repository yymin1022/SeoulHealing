package com.defcon.seoulhealing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //스플래시 화면 실행
        startActivity(new Intent(getBaseContext(), SplashActivity.class));
    }
}
