package com.defcon.seoulhealing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ResultActivity extends AppCompatActivity {
    String API_KEY = "e284ba69d2b34c208f15160fdb8a8e8c";
    String API_THEME = "";
    String API_URL_DEFAULT = "https://map.seoul.go.kr/smgis/apps/theme.do?cmd=getContentsList&page_no=1&page_size=9&key=%s&coord_x=126.9752884&coord_y=37.5649732&distance=3000&search_type=0&search_name=&theme_id=%s&subcate_id=100061,1";
    String API_URL = "";

    ArrayList<HealingListItem> itemData;
    HealingListAdapter listAdapter;
    ListView resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    private class getJSON extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog = new ProgressDialog(ResultActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Loading...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strs) {
            try {
                String jsonPage = getStringFromUrl(API_URL);
                JSONObject json = new JSONObject(jsonPage);
                JSONArray jArr = json.getJSONArray("body");

                itemData = new ArrayList<HealingListItem>();

                for (int i=0; i<jArr.length(); i++){
                    json = jArr.getJSONObject(i);
                    itemData.add(new HealingListItem(null, json.getString("COT_VALUE_01"), json.getString("THM_THEME_NAME"), json.getString("COT_ADDR_FULL_NEW")));
                }
                listAdapter = new HealingListAdapter(ResultActivity.this, itemData);
            } catch (Exception e) {
            }
            return "OK";
        }
        @Override
        protected void onPostExecute(String result) {
            resultList.setAdapter(listAdapter);
            progressDialog.cancel();
        }
    }

    public String getStringFromUrl(String pUrl){
        BufferedReader bufreader=null;
        HttpURLConnection urlConnection = null;

        StringBuffer page=new StringBuffer();
        try {
            URL url= new URL(pUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream contentStream = urlConnection.getInputStream();

            bufreader = new BufferedReader(new InputStreamReader(contentStream,"UTF-8"));
            String line = null;

            while((line = bufreader.readLine())!=null){
                page.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                bufreader.close();
                urlConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return page.toString();
    }
}
