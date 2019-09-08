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
                Intent themeIntent = new Intent(MainActivity.this, ResultActivity.class);
                switch(view.getId()){
                    case R.id.main_btn_theme1:
                        Toast.makeText(MainActivity.this, "Theme 1", Toast.LENGTH_SHORT).show();
                        themeIntent.putExtra("THEME", "100200,100214,100162,100167");
                        break;
                    case R.id.main_btn_theme2:
                        Toast.makeText(MainActivity.this, "Theme 2", Toast.LENGTH_SHORT).show();
                        themeIntent.putExtra("THEME", "100736,100362,100235,100273");
                        break;
                    case R.id.main_btn_theme3:
                        Toast.makeText(MainActivity.this, "Theme 3", Toast.LENGTH_SHORT).show();
                        themeIntent.putExtra("THEME", "100123,100211,100304,100569,100715,11107096,100891,11101181");
                        break;
                    case R.id.main_btn_theme4:
                        Toast.makeText(MainActivity.this, "Theme 4", Toast.LENGTH_SHORT).show();
                        themeIntent.putExtra("THEME", "100361,100769,100325");
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        break;
                }
                startActivity(themeIntent);
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
