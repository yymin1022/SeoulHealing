package com.defcon.seoulhealing;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        RelativeLayout splashLayout = findViewById(R.id.splash_layout);
        AnimationDrawable splashAnim = (AnimationDrawable) splashLayout.getBackground();
        splashAnim.setEnterFadeDuration(0);
        splashAnim.setExitFadeDuration(700);
        splashAnim.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1000);
    }

    @Override
    public void onBackPressed() { }
}
