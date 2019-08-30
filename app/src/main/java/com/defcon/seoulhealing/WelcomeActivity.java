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
        locationArray.add("강남");
        locationArray.add("관악");
        locationArray.add("구로");

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
