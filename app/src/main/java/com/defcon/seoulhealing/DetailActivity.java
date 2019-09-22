package com.defcon.seoulhealing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
String themeName = "";

String API_URL_DEFAULT = "https://map.seoul.go.kr/smgis/apps/poi.do?cmd=getNewContentsDetail&key=e284ba69d2b34c208f15160fdb8a8e8c&theme_id=%s&conts_id=%s";
String API_URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        themeID = intent.getStringExtra("THEME_ID");
        contentID = intent.getStringExtra("CONTENT_ID");
        themeName = intent.getStringExtra("THEME_NAME");

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

            checkContents(tv_name, tv_info1, tv_info2, tv_info3, tv_info4, tv_info5, tv_info6);

            Button.OnClickListener onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (view.getId()) {
                        case R.id.detail_btn_share :
                            shareContents();
                            break;

                        case R.id.detail_btn_navi :
                            startGoogleMap("https://www.google.com/maps/dir//");
                            break;

                        case R.id.detail_btn_map :
                            startGoogleMap("https://www.google.com/maps/place/");
                            break;
                    }
                }
            };
            Button btnShare = findViewById(R.id.detail_btn_share);
            Button btnNavi = findViewById(R.id.detail_btn_navi);
            Button btnMap = findViewById(R.id.detail_btn_map);
            btnShare.setOnClickListener(onClickListener);
            btnNavi.setOnClickListener(onClickListener);
            btnMap.setOnClickListener(onClickListener);
        }

        private void checkContents(TextView textName, TextView textInfo1, TextView textInfo2, TextView textInfo3, TextView textInfo4, TextView textInfo5, TextView textInfo6) {

            LinearLayout layoutCardView = findViewById(R.id.layout_detail_cardview);
            CardView cardInfo1 = findViewById(R.id.detail_card_info1);
            CardView cardInfo2 = findViewById(R.id.detail_card_info2);
            CardView cardInfo3 = findViewById(R.id.detail_card_info3);
            CardView cardInfo4 = findViewById(R.id.detail_card_info4);
            CardView cardInfo5 = findViewById(R.id.detail_card_info5);
            CardView cardInfo6 = findViewById(R.id.detail_card_info6);

            if(textName.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "내용을 불러오는 중 오류가 발생했습니다", Toast.LENGTH_SHORT).show();
                finish();
            }

            if(textInfo1.getText().toString().equals("")) {
                layoutCardView.removeView(cardInfo1);
            }

            if(textInfo2.getText().toString().equals("")) {
                layoutCardView.removeView(cardInfo2);
            }

            if(textInfo3.getText().toString().equals("")) {
                layoutCardView.removeView(cardInfo3);
            }

            if(textInfo4.getText().toString().equals("")) {
                layoutCardView.removeView(cardInfo4);
            }

            if(textInfo5.getText().toString().equals("")) {
                layoutCardView.removeView(cardInfo5);
            }

            if(textInfo6.getText().toString().equals("")) {
                layoutCardView.removeView(cardInfo6);
            }
        }

        private void shareContents() {
            String name = this.name + "\n";
            String address = this.address + "\n" + "\n" + "\n";
            String info1 = this.info1 + "\n" + "\n" + "\n";
            String info2 = this.info2 + "\n" + "\n" + "\n";
            String info3 = this.info3 + "\n" + "\n" + "\n";
            String info4 = this.info4 + "\n" + "\n" + "\n";
            String info5 = this.info5 + "\n" + "\n" + "\n";
            String info6 = this.info6 + "\n" + "\n" + "\n";
            String infoName1 = "- " + this.infoName1 + "\n" + "\n";
            String infoName2 = "- " + this.infoName2 + "\n" + "\n";
            String infoName3 = "- " + this.infoName3 + "\n" + "\n";
            String infoName4 = "- " + this.infoName4 + "\n" + "\n";
            String infoName5 = "- " + this.infoName5 + "\n" + "\n";
            String infoName6 = "- " + this.infoName6 + "\n" + "\n";

            if(info1.equals("\n\n\n")) {
                info1 = "";
                infoName1 = "";
            }
            if(info2.equals("\n\n\n")) {
                info2 = "";
                infoName2 = "";
            }
            if(info3.equals("\n\n\n")) {
                info3 = "";
                infoName3 = "";
            }
            if(info4.equals("\n\n\n")) {
                info4 = "";
                infoName4 = "";
            }
            if(info5.equals("\n\n\n")) {
                info5 = "";
                infoName5 = "";
            }
            if(info6.equals("\n\n\n")) {
                info6 = "";
                infoName6 = "";
            }

            String fullText =
                    "[SeoulHealing]" +
                    "\n" +
                    "\n" +
                    "힐링이 필요한 당신에게 이 장소를 소개합니다!" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "<" + themeName + ">" +
                    "\n" +
                    "\n" +
                    "장소 : " + name +
                    "주소 : " + address +
                    infoName1 + info1 + infoName2 + info2 + infoName3 + info3 + infoName4 + info4 + infoName5 + info5 + infoName6 + info6;

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, fullText);
            startActivity(intent);
        }

        private void startGoogleMap(String url) {
            String fullUrl = "";
            if(address.equals("등록된 주소가 없습니다.")) {
                fullUrl = url + name;
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(fullUrl)));
            } else {
                fullUrl = url + address;
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(fullUrl)));
            }
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