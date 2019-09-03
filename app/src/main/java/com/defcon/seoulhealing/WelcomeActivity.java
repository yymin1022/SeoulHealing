package com.defcon.seoulhealing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {
    String locationSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final TextView selectionText = findViewById(R.id.welcome_textview_selection);

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

        final Spinner locationSpinner = findViewById(R.id.welcome_spinner_location);
        locationSpinner.setAdapter(locationArrayAdapter);
        locationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                locationSelect = locationArray.get(i);
                selectionText.setText(locationSelect);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
