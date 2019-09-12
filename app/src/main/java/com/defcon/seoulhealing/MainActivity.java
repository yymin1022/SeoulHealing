package com.defcon.seoulhealing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(getBaseContext(), SplashActivity.class));

//        if(prefs.getBoolean("isFirst", true)){
            startActivity(new Intent(this, WelcomeActivity.class));
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        prefs = getSharedPreferences("prefs", MODE_PRIVATE);

        ImageView mapImage = findViewById(R.id.main_image_location_map);
        mapImage.setImageResource(R.drawable.img_map_gwanak);

        TextView locationText = findViewById(R.id.main_text_location);
        locationText.setText(prefs.getString("location", "관악구"));

        ImageButton btnTheme1 = findViewById(R.id.main_btn_travel);
        ImageButton btnTheme2 = findViewById(R.id.main_btn_child);
        ImageButton btnTheme3 = findViewById(R.id.main_btn_relax);
        ImageButton btnTheme4 = findViewById(R.id.main_btn_activity);

        Button.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent themeIntent = new Intent(MainActivity.this, ResultActivity.class);
                switch(view.getId()){
                    case R.id.main_btn_travel:
                        Toast.makeText(MainActivity.this, "Theme 1", Toast.LENGTH_SHORT).show();
                        themeIntent.putExtra("THEME", "100200,100214,100162,100167");
                        break;
                    case R.id.main_btn_child:
                        Toast.makeText(MainActivity.this, "Theme 2", Toast.LENGTH_SHORT).show();
                        themeIntent.putExtra("THEME", "100736,100362,100235,100273");
                        break;
                    case R.id.main_btn_relax:
                        Toast.makeText(MainActivity.this, "Theme 3", Toast.LENGTH_SHORT).show();
                        themeIntent.putExtra("THEME", "100123,100211,100304,100569,100715,11107096,100891,11101181");
                        break;
                    case R.id.main_btn_activity:
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

        btnTheme1.setOnClickListener(onClickListener);
        btnTheme2.setOnClickListener(onClickListener);
        btnTheme3.setOnClickListener(onClickListener);
        btnTheme4.setOnClickListener(onClickListener);
    }
}
