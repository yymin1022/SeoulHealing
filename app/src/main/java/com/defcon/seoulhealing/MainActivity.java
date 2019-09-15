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

        ImageView locationImage = findViewById(R.id.main_image_location_logo);
        ImageView mapImage = findViewById(R.id.main_image_location_map);
        TextView locationText = findViewById(R.id.main_text_location);

        String location = prefs.getString("location", "관악구");
        switch(location){
            case "강남구":
                locationImage.setImageResource(R.drawable.img_ci_gangnam);
                mapImage.setImageResource(R.drawable.img_map_gangnam);
                locationText.setText(location);
                break;
            case "강동구":
                locationImage.setImageResource(R.drawable.img_ci_gangdong);
                mapImage.setImageResource(R.drawable.img_map_gangdong);
                locationText.setText(location);
                break;
            case "강북구":
                locationImage.setImageResource(R.drawable.img_ci_gangbuk);
                mapImage.setImageResource(R.drawable.img_map_gangbuk);
                locationText.setText(location);
                break;
            case "강서구":
                locationImage.setImageResource(R.drawable.img_ci_gangseo);
                mapImage.setImageResource(R.drawable.img_map_gangseo);
                locationText.setText(location);
                break;
            case "관악구":
                locationImage.setImageResource(R.drawable.img_ci_geumcheon);
                mapImage.setImageResource(R.drawable.img_map_geumcheon);
                locationText.setText(location);
                break;
            case "광진구":
                locationImage.setImageResource(R.drawable.img_ci_gwangjin);
                mapImage.setImageResource(R.drawable.img_map_gwangjin);
                locationText.setText(location);
                break;
            case "구로구":
                locationImage.setImageResource(R.drawable.img_ci_guro);
                mapImage.setImageResource(R.drawable.img_map_guro);
                locationText.setText(location);
                break;
            case "금천구":
                locationImage.setImageResource(R.drawable.img_ci_geumcheon);
                mapImage.setImageResource(R.drawable.img_map_geumcheon);
                locationText.setText(location);
                break;
            case "노원구":
                locationImage.setImageResource(R.drawable.img_ci_nowon);
                mapImage.setImageResource(R.drawable.img_map_nowon);
                locationText.setText(location);
                break;
            case "도봉구":
                locationImage.setImageResource(R.drawable.img_ci_dobong);
                mapImage.setImageResource(R.drawable.img_map_dobong);
                locationText.setText(location);
                break;
            case "동대문구":
                locationImage.setImageResource(R.drawable.img_ci_dongdaemun);
                mapImage.setImageResource(R.drawable.img_map_dongdaemun);
                locationText.setText(location);
                break;
            case "동작구":
                locationImage.setImageResource(R.drawable.img_ci_dongjak);
                mapImage.setImageResource(R.drawable.img_map_dongjak);
                locationText.setText(location);
                break;
            case "마포구":
                locationImage.setImageResource(R.drawable.img_ci_mapo);
                mapImage.setImageResource(R.drawable.img_map_mapo);
                locationText.setText(location);
                break;
            case "서대문구":
                locationImage.setImageResource(R.drawable.img_ci_seodaemun);
                mapImage.setImageResource(R.drawable.img_map_seodaemun);
                locationText.setText(location);
                break;
            case "서초구":
                locationImage.setImageResource(R.drawable.img_ci_seocho);
                mapImage.setImageResource(R.drawable.img_map_seocho);
                locationText.setText(location);
                break;
            case "성동구":
                locationImage.setImageResource(R.drawable.img_ci_seongdong);
                mapImage.setImageResource(R.drawable.img_map_seongdong);
                locationText.setText(location);
                break;
            case "성북구":
//                locationImage.setImageResource(R.drawable.img_ci_seongbuk);
                mapImage.setImageResource(R.drawable.img_map_seongbuk);
                locationText.setText(location);
                break;
            case "송파구":
                locationImage.setImageResource(R.drawable.img_ci_songpa);
                mapImage.setImageResource(R.drawable.img_map_songpa);
                locationText.setText(location);
                break;
            case "양천구":
                locationImage.setImageResource(R.drawable.img_ci_yangcheon);
                mapImage.setImageResource(R.drawable.img_map_yangcheon);
                locationText.setText(location);
                break;
            case "영등포구":
                locationImage.setImageResource(R.drawable.img_ci_yeongdeungpo);
                mapImage.setImageResource(R.drawable.img_map_yeongdeungpo);
                locationText.setText(location);
                break;
            case "용산구":
                locationImage.setImageResource(R.drawable.img_ci_yongsan);
                mapImage.setImageResource(R.drawable.img_map_yongsan);
                locationText.setText(location);
                break;
            case "은평구":
                locationImage.setImageResource(R.drawable.img_ci_eunpyeong);
                mapImage.setImageResource(R.drawable.img_map_eunpyeong);
                locationText.setText(location);
                break;
            case "종로구":
                locationImage.setImageResource(R.drawable.img_ci_jongno);
                mapImage.setImageResource(R.drawable.img_map_jongno);
                locationText.setText(location);
                break;
            case "중구":
                locationImage.setImageResource(R.drawable.img_ci_jung);
                mapImage.setImageResource(R.drawable.img_map_jung);
                locationText.setText(location);
                break;
            case "중랑구":
                locationImage.setImageResource(R.drawable.img_ci_jungnang);
                mapImage.setImageResource(R.drawable.img_map_jungnang);
                locationText.setText(location);
                break;
        }

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
                        themeIntent.putExtra("THEME", "100200,100214,100162,100167");
                        break;
                    case R.id.main_btn_child:
                        themeIntent.putExtra("THEME", "100736,100362,100235,100273");
                        break;
                    case R.id.main_btn_relax:
                        themeIntent.putExtra("THEME", "100123,100211,100304,100569,100715,11107096,100891,11101181");
                        break;
                    case R.id.main_btn_activity:
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
