package com.defcon.seoulhealing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String location = "";

    LocationManager locationManager;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(getBaseContext(), SplashActivity.class));

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        if(prefs.getBoolean("isFirst", true)){
            startActivity(new Intent(this, WelcomeActivity.class));
        }

//        new getCurrentLocation().execute();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivityAnimation();
            }
        }, 1000);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ImageView locationImage = findViewById(R.id.main_image_location_logo);
        ImageView mapImage = findViewById(R.id.main_image_location_map);
        TextView locationText = findViewById(R.id.main_text_location);

        location = prefs.getString("location", "관악구");
        switch(location){
            case "강남구":
                locationImage.setImageResource(R.drawable.img_ci_gangnam);
                mapImage.setImageResource(R.drawable.img_map_gangnam);
                locationText.setText(location);
                break;
            case "강동구":
                locationImage.setImageResource(R.drawable.img_ci_gangdong);
                mapImage.setImageResource(R.drawable.img_map_gangdong);
                locationText.setText(location);
                break;
            case "강북구":
                locationImage.setImageResource(R.drawable.img_ci_gangbuk);
                mapImage.setImageResource(R.drawable.img_map_gangbuk);
                locationText.setText(location);
                break;
            case "강서구":
                locationImage.setImageResource(R.drawable.img_ci_gangseo);
                mapImage.setImageResource(R.drawable.img_map_gangseo);
                locationText.setText(location);
                break;
            case "관악구":
                locationImage.setImageResource(R.drawable.img_ci_gwanak);
                mapImage.setImageResource(R.drawable.img_map_gwanak);
                locationText.setText(location);
                break;
            case "광진구":
                locationImage.setImageResource(R.drawable.img_ci_gwangjin);
                mapImage.setImageResource(R.drawable.img_map_gwangjin);
                locationText.setText(location);
                break;
            case "구로구":
                locationImage.setImageResource(R.drawable.img_ci_guro);
                mapImage.setImageResource(R.drawable.img_map_guro);
                locationText.setText(location);
                break;
            case "금천구":
                locationImage.setImageResource(R.drawable.img_ci_geumcheon);
                mapImage.setImageResource(R.drawable.img_map_geumcheon);
                locationText.setText(location);
                break;
            case "노원구":
                locationImage.setImageResource(R.drawable.img_ci_nowon);
                mapImage.setImageResource(R.drawable.img_map_nowon);
                locationText.setText(location);
                break;
            case "도봉구":
                locationImage.setImageResource(R.drawable.img_ci_dobong);
                mapImage.setImageResource(R.drawable.img_map_dobong);
                locationText.setText(location);
                break;
            case "동대문구":
                locationImage.setImageResource(R.drawable.img_ci_dongdaemun);
                mapImage.setImageResource(R.drawable.img_map_dongdaemun);
                locationText.setText(location);
                break;
            case "동작구":
                locationImage.setImageResource(R.drawable.img_ci_dongjak);
                mapImage.setImageResource(R.drawable.img_map_dongjak);
                locationText.setText(location);
                break;
            case "마포구":
                locationImage.setImageResource(R.drawable.img_ci_mapo);
                mapImage.setImageResource(R.drawable.img_map_mapo);
                locationText.setText(location);
                break;
            case "서대문구":
                locationImage.setImageResource(R.drawable.img_ci_seodaemun);
                mapImage.setImageResource(R.drawable.img_map_seodaemun);
                locationText.setText(location);
                break;
            case "서초구":
                locationImage.setImageResource(R.drawable.img_ci_seocho);
                mapImage.setImageResource(R.drawable.img_map_seocho);
                locationText.setText(location);
                break;
            case "성동구":
                locationImage.setImageResource(R.drawable.img_ci_seongdong);
                mapImage.setImageResource(R.drawable.img_map_seongdong);
                locationText.setText(location);
                break;
            case "성북구":
                locationImage.setImageResource(R.drawable.img_ci_seongbuk);
                mapImage.setImageResource(R.drawable.img_map_seongbuk);
                locationText.setText(location);
                break;
            case "송파구":
                locationImage.setImageResource(R.drawable.img_ci_songpa);
                mapImage.setImageResource(R.drawable.img_map_songpa);
                locationText.setText(location);
                break;
            case "양천구":
                locationImage.setImageResource(R.drawable.img_ci_yangcheon);
                mapImage.setImageResource(R.drawable.img_map_yangcheon);
                locationText.setText(location);
                break;
            case "영등포구":
                locationImage.setImageResource(R.drawable.img_ci_yeongdeungpo);
                mapImage.setImageResource(R.drawable.img_map_yeongdeungpo);
                locationText.setText(location);
                break;
            case "용산구":
                locationImage.setImageResource(R.drawable.img_ci_yongsan);
                mapImage.setImageResource(R.drawable.img_map_yongsan);
                locationText.setText(location);
                break;
            case "은평구":
                locationImage.setImageResource(R.drawable.img_ci_eunpyeong);
                mapImage.setImageResource(R.drawable.img_map_eunpyeong);
                locationText.setText(location);
                break;
            case "종로구":
                locationImage.setImageResource(R.drawable.img_ci_jongno);
                mapImage.setImageResource(R.drawable.img_map_jongno);
                locationText.setText(location);
                break;
            case "중구":
                locationImage.setImageResource(R.drawable.img_ci_jung);
                mapImage.setImageResource(R.drawable.img_map_jung);
                locationText.setText(location);
                break;
            case "중랑구":
                locationImage.setImageResource(R.drawable.img_ci_jungnang);
                mapImage.setImageResource(R.drawable.img_map_jungnang);
                locationText.setText(location);
                break;
        }

        final ImageButton btnActivity = findViewById(R.id.main_btn_activity);
        final ImageButton btnChild = findViewById(R.id.main_btn_child);
        final ImageButton btnRelax = findViewById(R.id.main_btn_relax);
        final ImageButton btnTravel = findViewById(R.id.main_btn_travel);

        btnActivity.setVisibility(View.VISIBLE);
        btnChild.setVisibility(View.VISIBLE);
        btnRelax.setVisibility(View.VISIBLE);
        btnTravel.setVisibility(View.VISIBLE);

        Button.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent themeIntent = new Intent(MainActivity.this, ResultActivity.class);
                themeIntent.putExtra("LOCATION", location);

                if(isNetworkConnected()) {
                    switch(view.getId()){
                        case R.id.main_btn_activity:
                            themeIntent.putExtra("THEME", "ACTIVITY");
                            setThemeButtonAnimation(btnActivity, R.anim.scale_btn_activity, btnChild, btnRelax, btnTravel, themeIntent);
                            break;
                        case R.id.main_btn_child:
                            themeIntent.putExtra("THEME", "CHILD");
                            setThemeButtonAnimation(btnChild, R.anim.scale_btn_child, btnActivity, btnRelax, btnTravel, themeIntent);
                            break;
                        case R.id.main_btn_relax:
                            themeIntent.putExtra("THEME", "RELAX");
                            setThemeButtonAnimation(btnRelax, R.anim.scale_btn_relax, btnActivity, btnChild, btnTravel, themeIntent);
                            break;
                        case R.id.main_btn_travel:
                            themeIntent.putExtra("THEME", "TRAVEL");
                            setThemeButtonAnimation(btnTravel, R.anim.scale_btn_travel, btnActivity, btnChild, btnRelax, themeIntent);
                            break;
                        default:
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            break;
                    }
                } else if(!isNetworkConnected()) {
                    Toast.makeText(getApplicationContext(), "인터넷 연결 상태를 확인해주세요!", Toast.LENGTH_SHORT).show();
                }
            }
        };

        btnActivity.setOnClickListener(onClickListener);
        btnChild.setOnClickListener(onClickListener);
        btnRelax.setOnClickListener(onClickListener);
        btnTravel.setOnClickListener(onClickListener);
    }

    private void startActivityAnimation() {
        ImageView locationMap = findViewById(R.id.main_image_location_map);
        CardView cardLocation = findViewById(R.id.main_card_location);
        ImageButton btnActivity = findViewById(R.id.main_btn_activity);
        ImageButton btnChild = findViewById(R.id.main_btn_child);
        ImageButton btnRelax = findViewById(R.id.main_btn_relax);
        ImageButton btnTravel = findViewById(R.id.main_btn_travel);

        Animation locationMapAnim = AnimationUtils.loadAnimation(this, R.anim.fade_in_location_map);
        Animation cardLocationAnim = AnimationUtils.loadAnimation(this, R.anim.scale_card_location);
        Animation btnActivityAnim = AnimationUtils.loadAnimation(this, R.anim.fade_in_btn_activity);
        Animation btnChildAnim = AnimationUtils.loadAnimation(this, R.anim.fade_in_btn_child);
        Animation btnRelaxAnim = AnimationUtils.loadAnimation(this, R.anim.fade_in_btn_relax);
        Animation btnTravelAnim = AnimationUtils.loadAnimation(this, R.anim.fade_in_btn_travel);

        locationMap.startAnimation(locationMapAnim);
        cardLocation.startAnimation(cardLocationAnim);
        btnActivity.startAnimation(btnActivityAnim);
        btnChild.startAnimation(btnChildAnim);
        btnRelax.startAnimation(btnRelaxAnim);
        btnTravel.startAnimation(btnTravelAnim);
    }

    private void setThemeButtonAnimation(final ImageButton selectButton, int animEffect, final ImageButton otherButton1, final ImageButton otherButton2, final ImageButton otherButton3, final Intent intent) {

        Animation scale = AnimationUtils.loadAnimation(this, animEffect);
        Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);

        selectButton.startAnimation(scale);
        otherButton1.startAnimation(fadeOut);
        otherButton2.startAnimation(fadeOut);
        otherButton3.startAnimation(fadeOut);

        selectButton.setClickable(false);
        otherButton1.setClickable(false);
        otherButton2.setClickable(false);
        otherButton3.setClickable(false);

        scale.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                selectButton.setVisibility(View.INVISIBLE);
                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                otherButton1.setVisibility(View.INVISIBLE);
                otherButton2.setVisibility(View.INVISIBLE);
                otherButton3.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
    }

    private Boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isMobileAvailable = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isAvailable();
        boolean isMobileConnect = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        boolean isWifiAvailable = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isAvailable();
        boolean isWifiConnect = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();

        if ((isWifiAvailable && isWifiConnect) || (isMobileAvailable && isMobileConnect)){
            return true;
        }else{
            return false;
        }
    }

    private class getCurrentLocation extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getApplicationContext());
            progressDialog.setMessage("현재 위치를 검색중입니다.");
//            progressDialog.show();
        }
        @Override
        protected Void doInBackground(Void... params){
            try{
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                String provider = location.getProvider();
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();
                double altitude = location.getAltitude();

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, gpsLocationListener);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, gpsLocationListener);
            }catch(SecurityException e){
                Log.e("Error", e.toString());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void param) {
            progressDialog.cancel();
        }

        final LocationListener gpsLocationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                String provider = location.getProvider();
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();
                double altitude = location.getAltitude();

                Log.d("GPS", longitude + ":" + latitude);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
    }
}
