package com.defcon.seoulhealing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    ImageView headerImage;
    ListView resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        headerImage = findViewById(R.id.result_image_header);
        resultList = findViewById(R.id.result_list_healing);

        currentTheme = getIntent().getStringExtra("THEME");
        currentLocation = getIntent().getStringExtra("LOCATION");
        
        getIntent().getIntExtra



        switch(currentTheme){
            case "ACTIVITY":
                headerImage.setImageResource(R.drawable.result_header_activity);
                break;
            case "CHILD":
                headerImage.setImageResource(R.drawable.result_header_child);
                break;
            case "RELAX":
                headerImage.setImageResource(R.drawable.result_header_relax);
                break;
            case "TRAVEL":
                headerImage.setImageResource(R.drawable.result_header_travel);
                break;

        }

        new getJSON().execute();
    }

    private class getJSON extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog = new ProgressDialog(ResultActivity.this);
        LinearLayout resultLayout = findViewById(R.id.result_layout);

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            resultLayout.setVisibility(View.INVISIBLE);
            progressDialog.setMessage("Loading...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strs){
            switch(currentTheme){
                case "ACTIVITY":
                    API_THEME = "100736,100362,100235,100770";
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
                default:
                    API_COORD_X = currentLocation.split(":")[1];
                    API_COORD_Y = currentLocation.split(":")[0];
            }

            if(API_THEME == null || API_THEME.equals("")){
                finish();
            }
            API_URL = String.format(Locale.getDefault(), API_URL_DEFAULT, API_KEY, API_COORD_Y, API_COORD_X, API_THEME);

            try{
                String jsonPage = getStringFromUrl(API_URL);

                JSONObject json = new JSONObject(jsonPage);
                JSONArray jArr = json.getJSONArray("body");

                itemData = new ArrayList<>();

                for(int i = 0; i < jArr.length(); i++){
                    json = jArr.getJSONObject(i);

                    Drawable healingDrwable = null;
                    String healingTitle = json.getString("COT_CONTS_NAME").replaceAll("&#91;", "[").replaceAll("&#93;", "]").replaceAll("&lt;", "<").replaceAll("&gt;", ">");
                    String healingTheme = json.getString("THM_THEME_NAME").replaceAll("&#91;", "[").replaceAll("&#93;", "]").replaceAll("&lt;", "<").replaceAll("&gt;", ">");
                    String healingThemeID = json.getString("COT_THEME_ID").replaceAll("&#91;", "[").replaceAll("&#93;", "]").replaceAll("&lt;", "<").replaceAll("&gt;", ">");
                    String healingContentID = json.getString("COT_CONTS_ID").replaceAll("&#91;", "[").replaceAll("&#93;", "]").replaceAll("&lt;", "<").replaceAll("&gt;", ">");

                    switch(healingThemeID){
                        case "100200":
                            healingDrwable = getDrawable(R.drawable.theme_camping);
                            break;
                        case "100214":
                            healingDrwable = getDrawable(R.drawable.theme_seoulhand);
                            break;
                        case "100162":
                            healingDrwable = getDrawable(R.drawable.theme_memoryseoul);
                            break;
                        case "100167":
                            healingDrwable = getDrawable(R.drawable.theme_extravel);
                            break;
                        case "100736":
                            healingDrwable = getDrawable(R.drawable.theme_ttareung);
                            break;
                        case "100362":
                            healingDrwable = getDrawable(R.drawable.theme_mtnandpark);
                            break;
                        case "100235":
                            healingDrwable = getDrawable(R.drawable.theme_bike_);
                            break;
                        case "100770":
                            healingDrwable = getDrawable(R.drawable.theme_study);
                            break;
                        case "100123":
                            healingDrwable = getDrawable(R.drawable.theme_sasaek);
                            break;
                        case "100211":
                            healingDrwable = getDrawable(R.drawable.theme_dullegil);
                            break;
                        case "100304":
                            healingDrwable = getDrawable(R.drawable.theme_jarakgil);
                            break;
                        case "100569":
                            healingDrwable = getDrawable(R.drawable.theme_walking_seoul);
                            break;
                        case "100715":
                            healingDrwable = getDrawable(R.drawable.theme_trail);
                            break;
                        case "11101096":
                            healingDrwable = getDrawable(R.drawable.theme_7017);
                            break;
                        case "100891":
                            healingDrwable = getDrawable(R.drawable.theme_sunryegil);
                            break;
                        case "11101181":
                            healingDrwable = getDrawable(R.drawable.theme_no_car);
                            break;
                        case "100361":
                            healingDrwable = getDrawable(R.drawable.theme_forest_yua);
                            break;
                        case "100769":
                            healingDrwable = getDrawable(R.drawable.theme_gwanak_family);
                            break;
                        case "100325":
                            healingDrwable = getDrawable(R.drawable.theme_playground);
                            break;
                    }

                    itemData.add(new HealingListItem(healingDrwable, healingTitle, healingTheme, healingThemeID, healingContentID));
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
                    healingDetailIntent.putExtra("THEME_NAME", itemData.get(i).getThemeStr());
                    healingDetailIntent.putExtra("THEME", currentTheme);
                    startActivity(healingDetailIntent);
                }
            });
            progressDialog.dismiss();
            resultLayout.setVisibility(View.VISIBLE);
            startActivityAnimation();
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

    private void startActivityAnimation() {
        CardView cardViewHeader = findViewById(R.id.result_cardview_header);
        ListView listHealing = findViewById(R.id.result_list_healing);

        Animation fadeInCardHeader = AnimationUtils.loadAnimation(this, R.anim.fade_in_theme_header);
        Animation fadeInListHealing = AnimationUtils.loadAnimation(this, R.anim.fade_in_list_healing);

        cardViewHeader.startAnimation(fadeInCardHeader);
        listHealing.startAnimation(fadeInListHealing);
    }
}
