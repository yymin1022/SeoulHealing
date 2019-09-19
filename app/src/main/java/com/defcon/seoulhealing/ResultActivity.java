package com.defcon.seoulhealing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Locale;

public class ResultActivity extends AppCompatActivity{
    String API_COORD_X = "";
    String API_COORD_Y = "";
    String API_KEY = "e284ba69d2b34c208f15160fdb8a8e8c";
    String API_THEME = "";
    String API_URL_DEFAULT = "https://map.seoul.go.kr/smgis/apps/theme.do?cmd=getContentsList&page_no=1&page_size=9&key=%s&coord_x=%s&coord_y=%s&distance=3000&search_type=0&search_name=&theme_id=%s&subcate_id=100061,1";
    String API_URL = "";
    String currentLocation = "";
    String currentTheme = "";

    ArrayList<HealingListItem> itemData;
    HealingListAdapter listAdapter;
    ListView resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        resultList = findViewById(R.id.result_list_healing);

        new getJSON().execute();
    }

    private class getJSON extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog = new ProgressDialog(ResultActivity.this);

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog.setMessage("Loading...");
            progressDialog.create();
        }

        @Override
        protected String doInBackground(String... strs){
            currentTheme = getIntent().getStringExtra("THEME");
            currentLocation = getIntent().getStringExtra("LOCATION");

            Log.d("THEME", currentTheme);
            Log.d("LOCATION", currentLocation);

            switch(currentTheme){
                case "ACTIVITY":
                    API_THEME = "100736,100362,100235,100273";
                    break;
                case "CHILD":
                    API_THEME = "100361,100769,100325";
                    break;
                case "RELAX":
                    API_THEME = "100123,100211,100304,100569,100715,11107096,100891,11101181";
                    break;
                case "TRAVEL":
                    API_THEME = "100200,100214,100162,100167";
                    break;
            }

            switch(currentLocation){
                case "강남구":
                    API_COORD_X = "37.5173510";
                    API_COORD_Y = "127.0474381";
                    break;
                case "강동구":
                    API_COORD_X = "37.5302636";
                    API_COORD_Y = "127.1237964";
                    break;
                case "강북구":
                    API_COORD_X = "37.6397767";
                    API_COORD_Y = "127.0255184";
                    break;
                case "강서구":
                    API_COORD_X = "37.5509103";
                    API_COORD_Y = "126.8495742";
                    break;
                case "관악구":
                    API_COORD_X = "37.4781285";
                    API_COORD_Y = "126.9515024";
                    break;
                case "광진구":
                    API_COORD_X = "37.5385333";
                    API_COORD_Y = "127.0823772";
                    break;
                case "구로구":
                    API_COORD_X = "37.4954703";
                    API_COORD_Y = "126.8876391";
                    break;
                case "금천구":
                    API_COORD_X = "37.4567380";
                    API_COORD_Y = "126.8954720";
                    break;
                case "노원구":
                    API_COORD_X = "37.6540471";
                    API_COORD_Y = "127.0563372";
                    break;
                case "도봉구":
                    API_COORD_X = "37.6687735";
                    API_COORD_Y = "127.0430710";
                    break;
                case "동대문구":
                    API_COORD_X = "37.5744155";
                    API_COORD_Y = "127.0397427";
                    break;
                case "동작구":
                    API_COORD_X = "37.5124018";
                    API_COORD_Y = "126.9394748";
                    break;
                case "마포구":
                    API_COORD_X = "37.5637561";
                    API_COORD_Y = "126.9084211";
                    break;
                case "서대문구":
                    API_COORD_X = "37.5791158";
                    API_COORD_Y = "126.9367789";
                    break;
                case "서초구":
                    API_COORD_X = "37.4835740";
                    API_COORD_Y = "127.0326610";
                    break;
                case "성동구":
                    API_COORD_X = "37.5630725";
                    API_COORD_Y = "127.0366688";
                    break;
                case "성북구":
                    API_COORD_X = "37.5893660";
                    API_COORD_Y = "127.0167430";
                    break;
                case "송파구":
                    API_COORD_X = "37.5144161";
                    API_COORD_Y = "127.1060657";
                    break;
                case "양천구":
                    API_COORD_X = "37.5167508";
                    API_COORD_Y = "126.8665644";
                    break;
                case "영등포구":
                    API_COORD_X = "37.5263440";
                    API_COORD_Y = "126.8962560";
                    break;
                case "용산구":
                    API_COORD_X = "37.5325896";
                    API_COORD_Y = "126.9900429";
                    break;
                case "은평구":
                    API_COORD_X = "37.6027496";
                    API_COORD_Y = "126.9292386";
                    break;
                case "종로구":
                    API_COORD_X = "37.5727864";
                    API_COORD_Y = "126.9791778";
                    break;
                case "중구":
                    API_COORD_X = "37.5637584";
                    API_COORD_Y = "126.9975517";
                    break;
                case "중랑구":
                    API_COORD_X = "37.6065600";
                    API_COORD_Y = "127.0926240";
                    break;
            }

            if(API_THEME == null || API_THEME.equals("")){
                finish();
            }
            API_URL = String.format(Locale.getDefault(), API_URL_DEFAULT, API_KEY, API_COORD_Y, API_COORD_X, API_THEME);

            Log.d("URL OK", API_URL);

            try{
                String jsonPage = getStringFromUrl(API_URL);

                JSONObject json = new JSONObject(jsonPage);
                JSONArray jArr = json.getJSONArray("body");

                itemData = new ArrayList<>();

                for(int i = 0; i < jArr.length(); i++){
                    json = jArr.getJSONObject(i);

                    String healingTitle = json.getString("COT_CONTS_NAME");
                    String healingTheme = json.getString("THM_THEME_NAME");
                    String healingThemeID = json.getString("COT_THEME_ID");
                    String healingAddress;
                    String healingAddressOld = json.getString("COT_ADDR_FULL_OLD");
                    String healingAddressNew = json.getString("COT_ADDR_FULL_NEW");
                    String healingContentID = json.getString("COT_CONTS_ID");

                    if(healingAddressNew.equals("")){
                        if(healingAddressOld.equals("")){
                            healingAddress = "등록된 주소가 없습니다.";
                        }else{
                            healingAddress = healingAddressOld;
                        }
                    }else{
                        healingAddress = healingAddressNew;
                    }
                    itemData.add(new HealingListItem(null, healingTitle, healingAddress, healingTheme, healingThemeID, healingContentID));
                }
                listAdapter = new HealingListAdapter(ResultActivity.this, itemData);
            }catch(Exception e){
                Log.e("Error", e.toString());
            }
            return "OK";
        }
        @Override
        protected void onPostExecute(String result){
            resultList.setAdapter(listAdapter);
            resultList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent healingDetailIntent = new Intent(ResultActivity.this, DetailActivity.class);
                    healingDetailIntent.putExtra("CONTENT_ID", itemData.get(i).getContentIDStr());
                    healingDetailIntent.putExtra("THEME_ID", itemData.get(i).getThemeIDStr());
                    startActivity(healingDetailIntent);
                }
            });
            progressDialog.dismiss();
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
                }
                urlConnection.disconnect();
            }catch(Exception e){
                Log.e("Error", e.toString());
            }
        }
        return page.toString();
    }
}
