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
    String API_KEY = "e284ba69d2b34c208f15160fdb8a8e8c";
    String API_THEME = "";
    String API_URL_DEFAULT = "https://map.seoul.go.kr/smgis/apps/theme.do?cmd=getContentsList&page_no=1&page_size=9&key=%s&coord_x=126.9752884&coord_y=37.5649732&distance=3000&search_type=0&search_name=&theme_id=%s&subcate_id=100061,1";
    String API_URL = "";

    ArrayList<HealingListItem> itemData;
    HealingListAdapter listAdapter;
    ListView resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        resultList = findViewById(R.id.result_list_healing);

        Intent themeIntent = getIntent();
        API_THEME = themeIntent.getStringExtra("THEME");
        if(API_THEME == null || API_THEME.equals("")){
            finish();
        }
        API_URL = String.format(Locale.getDefault(), API_URL_DEFAULT, API_KEY, API_THEME);
        new getJSON().execute();
    }

    private class getJSON extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog = new ProgressDialog(ResultActivity.this);

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strs){
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
                    String healingImageUrl = String.format(Locale.getDefault(), "https://map.seoul.go.kr%s", json.getString("COT_IMG_MAIN_URL"));

                    if(healingAddressNew.equals("")){
                        if(healingAddressOld.equals("")){
                            healingAddress = "등록된 주소가 없습니다.";
                        }else{
                            healingAddress = healingAddressOld;
                        }
                    }else{
                        healingAddress = healingAddressNew;
                    }

                    HttpURLConnection connection = (HttpURLConnection) new URL(healingImageUrl).openConnection();
                    connection.connect();
                    InputStream input = connection.getInputStream();

                    Drawable healingImage = new BitmapDrawable(getResources(), BitmapFactory.decodeStream(input));

                    itemData.add(new HealingListItem(healingImage, healingTitle, healingAddress, healingTheme, healingThemeID, healingContentID));
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
            progressDialog.cancel();
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
