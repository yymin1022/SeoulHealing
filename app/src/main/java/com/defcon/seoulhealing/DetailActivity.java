package com.defcon.seoulhealing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {
String contentID = "";
String themeID = "";

String API_URL_DEFAULT = "https://map.seoul.go.kr/smgis/apps/poi.do?cmd=getNewContentsDetail&key=e284ba69d2b34c208f15160fdb8a8e8c&theme_id=%s&conts_id=%s";
String API_URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        themeID = intent.getStringExtra("THEME_ID");
        contentID = intent.getStringExtra("CONTENT_ID");

        API_URL = String.format(Locale.getDefault(), API_URL_DEFAULT, themeID, contentID);

        new getJSON().execute();
    }

    private class getJSON extends AsyncTask<Void, Void, Void> {

        Drawable img;
        String name, address, addressOld, addressNew, info1, info2, info3, info4, info5, info6, infoName1, infoName2, infoName3, infoName4, infoName5, infoName6;

        ProgressDialog progressDialog = new ProgressDialog(DetailActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                String jsonPage = getStringFromUrl(API_URL);

                JSONObject json = new JSONObject(jsonPage);
                JSONArray jArr = json.getJSONArray("body");

                for(int i = 0; i < jArr.length(); i++) {
                    json = jArr.getJSONObject(i);

                    String imgUrl = String.format(Locale.getDefault(), "https://map.seoul.go.kr%s", json.getString("COT_IMG_MAIN_URL"));
                    name = json.getString("COT_CONTS_NAME");
                    addressOld = json.getString("COT_ADDR_FULL_OLD");
                    addressNew = json.getString("COT_ADDR_FULL_NEW");
                    info1 = json.getString("COT_VALUE_01");
                    info2 = json.getString("COT_VALUE_02");
                    info3 = json.getString("COT_VALUE_03");
                    info4 = json.getString("COT_VALUE_04");
                    info5 = json.getString("COT_VALUE_05");
                    info6 = json.getString("COT_VALUE_06");
                    infoName1 = json.getString("COT_NAME_01");
                    infoName2 = json.getString("COT_NAME_02");
                    infoName3 = json.getString("COT_NAME_03");
                    infoName4 = json.getString("COT_NAME_04");
                    infoName5 = json.getString("COT_NAME_05");
                    infoName6 = json.getString("COT_NAME_06");

                    if(addressNew.equals("")){
                        if(addressOld.equals("")){
                            address = "등록된 주소가 없습니다.";
                        }else{
                            address = addressOld;
                        }
                    }else{
                        address = addressNew;
                    }

                    HttpURLConnection connection = (HttpURLConnection) new URL(imgUrl).openConnection();
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    img = new BitmapDrawable(getResources(), BitmapFactory.decodeStream(input));
                }

            }catch (Exception e) {
                Log.e("Error", e.toString());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.cancel();

            ImageView imgView = findViewById(R.id.detail_img);
            TextView tv_name = findViewById(R.id.detail_text_name);
            TextView tv_address = findViewById(R.id.detail_text_address);
            TextView tv_info1 = findViewById(R.id.detail_text_info_1);
            TextView tv_info2 = findViewById(R.id.detail_text_info_2);
            TextView tv_info3 = findViewById(R.id.detail_text_info_3);
            TextView tv_info4 = findViewById(R.id.detail_text_info_4);
            TextView tv_info5 = findViewById(R.id.detail_text_info_5);
            TextView tv_info6 = findViewById(R.id.detail_text_info_6);
            TextView tv_info_name1 = findViewById(R.id.detail_text_info_name_1);
            TextView tv_info_name2 = findViewById(R.id.detail_text_info_name_2);
            TextView tv_info_name3 = findViewById(R.id.detail_text_info_name_3);
            TextView tv_info_name4 = findViewById(R.id.detail_text_info_name_4);
            TextView tv_info_name5 = findViewById(R.id.detail_text_info_name_5);
            TextView tv_info_name6 = findViewById(R.id.detail_text_info_name_6);

            imgView.setImageDrawable(img);
            tv_name.setText(name);
            tv_address.setText(address);
            tv_info1.setText(info1);
            tv_info2.setText(info2);
            tv_info3.setText(info3);
            tv_info4.setText(info4);
            tv_info5.setText(info5);
            tv_info6.setText(info6);
            tv_info_name1.setText(infoName1);
            tv_info_name2.setText(infoName2);
            tv_info_name3.setText(infoName3);
            tv_info_name4.setText(infoName4);
            tv_info_name5.setText(infoName5);
            tv_info_name6.setText(infoName6);
        }
    }

    public String getStringFromUrl(String pUrl){
        BufferedReader bufreader=null;
        HttpURLConnection urlConnection = null;

        StringBuffer page=new StringBuffer();
        try{
            URL url= new URL(pUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream contentStream = urlConnection.getInputStream();

            bufreader = new BufferedReader(new InputStreamReader(contentStream, StandardCharsets.UTF_8));
            String line;

            while((line = bufreader.readLine())!=null){
                page.append(line);
            }

        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(bufreader != null) {
                    bufreader.close();
                    urlConnection.disconnect();
                }
            }catch(Exception e){
                Log.e("Error", e.toString());
            }
        }
        return page.toString();
    }
}