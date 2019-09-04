package com.defcon.seoulhealing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(getBaseContext(), SplashActivity.class));

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        if(prefs.getBoolean("isFirst", true)){
            startActivity(new Intent(this, WelcomeActivity.class));
        }

        Button.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.main_btn_theme1:
                        Toast.makeText(MainActivity.this, "Theme 1", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.main_btn_theme2:
                        Toast.makeText(MainActivity.this, "Theme 2", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.main_btn_theme3:
                        Toast.makeText(MainActivity.this, "Theme 3", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.main_btn_theme4:
                        Toast.makeText(MainActivity.this, "Theme 4", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        Button btnTheme1 = findViewById(R.id.main_btn_theme1);
        Button btnTheme2 = findViewById(R.id.main_btn_theme2);
        Button btnTheme3 = findViewById(R.id.main_btn_theme3);
        Button btnTheme4 = findViewById(R.id.main_btn_theme4);
        btnTheme1.setOnClickListener(onClickListener);
        btnTheme2.setOnClickListener(onClickListener);
        btnTheme3.setOnClickListener(onClickListener);
        btnTheme4.setOnClickListener(onClickListener);
    }
}
