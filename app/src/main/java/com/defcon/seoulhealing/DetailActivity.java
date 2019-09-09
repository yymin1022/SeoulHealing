package com.defcon.seoulhealing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
String contentID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        contentID = intent.getStringExtra("CONTENT_ID");
        Toast.makeText(getApplicationContext(), contentID, Toast.LENGTH_SHORT).show();
    }
}