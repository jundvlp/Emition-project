package com.example.weatherapptraining;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherapptraining.data.DetailedForecast;
import com.example.weatherapptraining.uiModel.ForecastViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
   private RelativeLayout homeRl;
   private ProgressBar loadingPB;
   private TextView cityNameTV,temperatureTV,conditionTV;
   public RecyclerView weatherRV;
   private TextInputEditText cityEdt;
   public ImageView iconIV,searchIV;
   private ArrayList<ForecastViewModel> forecastViewModelArrayList;
   private DetailedForecast detailedForecast;
   public LocationManager locationManager;
   private final int PERMISSION_CODE = 1;
   private String cityName;

   @Override
protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
       setContentView(R.layout.detailed_forecast_layout);
       homeRl = findViewById(R.id.idRLHome);
       loadingPB = findViewById(R.id.idPbLoading);
       cityNameTV = findViewById(R.id.idTVCityName);
       temperatureTV = findViewById(R.id.idTVTemperature);
       conditionTV = findViewById(R.id.idTVCondition);
       weatherRV = findViewById(R.id.idRvWeather);
       cityEdt = findViewById(R.id.idEdtCity);
       iconIV = findViewById(R.id.idIVIcon);
       searchIV = findViewById(R.id.idIVSearch);
       forecastViewModelArrayList = new ArrayList<>();
       detailedForecast = new DetailedForecast(this,forecastViewModelArrayList);
       weatherRV.setAdapter(detailedForecast);
       locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
       if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
           ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},PERMISSION_CODE);
       }
       Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
       if (location != null){cityName = getCityName(location.getLongitude(),location.getLatitude());
           getWeatherInfo(cityName);
       } else {
           cityName = "Kyiv";
           getWeatherInfo(cityName);
       }

       searchIV.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String city = Objects.requireNonNull(cityEdt.getText()).toString();
               if(city.isEmpty()){
                   Toast.makeText(MainActivity.this, "Please enter city Name", Toast.LENGTH_SHORT).show();
               }else {
                   cityNameTV.setText(cityName);
                   getWeatherInfo(city);
               }
           }
       });


   }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==PERMISSION_CODE){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permissions granted..", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Please provide the permissions",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private String getCityName(double longitude, double latitude){
       String cityName = "Not found";
       Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
       try {
           List<Address> addresses = gcd.getFromLocation(latitude,longitude,10);

           for(Address adr : addresses){
               if(adr!=null){
                   String city = adr.getLocality();
                   if(city!=null && !city.equals("")){
                       cityName = city;
                   }else{
                       Log.d("TAG","CITY NOT FOUND");
                       Toast.makeText(this,"User City Not Found. .", Toast.LENGTH_SHORT).show();
                   }
               }
           }
       }catch (IOException e){
           e.printStackTrace();
       }
       return cityName;

   }
   private void getWeatherInfo(String cityName){
       String url = "http://api.openweathermap.org/data/2.5/weather";
       cityNameTV.setText(cityName);
       RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

       JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
           @Override
           public void onResponse(JSONObject response) {
               loadingPB.setVisibility(View.GONE);
               homeRl.setVisibility(View.VISIBLE);
               forecastViewModelArrayList.clear();
               try {
                   String temperature = response.getJSONObject("current").getString("temp_c");
                   temperatureTV.setText(temperature+"");
                   int isDay = response.getJSONObject("current").getInt("is_day");
                   String condition = response.getJSONObject("current").getJSONObject("condition").getString("text");
                   String conditionIcon = response.getJSONObject("current").getJSONObject("condition").getString("icon");
                   Picasso.get().load("http:".concat(conditionIcon)).into(iconIV);
                   conditionTV.setText(condition);
                   if(isDay==1){
                       Picasso.get().load("https://i.stack.imgur.com/NO8hx.png");
                   }else{
                       Picasso.get().load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS_5pkk5Z4GMSHLgUiAb5peBckymY6DrIsQOg&usqp=CAU");
                   }

                   JSONObject forecastObj = response.getJSONObject("forecast");
                   JSONObject forecastO = forecastObj.getJSONArray("forecastday").getJSONObject(0);
                   JSONArray hourArray = forecastO.getJSONArray("hour");

                   for(int i=0; i<hourArray.length(); i++){
                       JSONObject hourObj = hourArray.getJSONObject(i);
                       String time = hourObj.getString("time");
                       String temper = hourObj.getString("temp_c");
                       String img = hourObj.getJSONObject("condition").getString("icon");
                       String wind = hourObj.getString("wind_kph");
                       forecastViewModelArrayList.add(new ForecastViewModel(time,temper,img,wind));
                   }
                   detailedForecast.notifyDataSetChanged();

               } catch (JSONException e) {
                   e.printStackTrace();
               }

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Toast.makeText(MainActivity.this, "Please enter valid city name.. ", Toast.LENGTH_SHORT).show();
           }
       });
       requestQueue.add(jsonObjectRequest);

   }
}