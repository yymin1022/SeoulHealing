package com.defcon.seoulhealing;

import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

        TextView tvAppName = findViewById(R.id.splash_app_name);
        TextView tvSlogan = findViewById(R.id.splash_app_slogan);
        Typeface textFontDosis = Typeface.createFromAsset( getAssets(), "fonts/font_dosis.ttf" );
        Typeface textFontNamsan = Typeface.createFromAsset( getAssets(), "fonts/font_namsan.ttf" );

        tvAppName.setTypeface(textFontDosis);
        tvSlogan.setTypeface(textFontNamsan);

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
