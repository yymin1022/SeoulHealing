package com.defcon.seoulhealing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Locale;

public class ResultActivity extends AppCompatActivity {
    String API_KEY = "";
    String API_URL = String.format(Locale.getDefault(), "https://map.seoul.go.kr/smgis/apps/theme.do?cmd=getContentsList&page_no=1&page_size=9&key=%s&coord_x=126.9752884&coord_y=37.5649732&distance=3000&search_type=0&search_name=&theme_id=100123&subcate_id=100061,1", API_KEY);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }
}
