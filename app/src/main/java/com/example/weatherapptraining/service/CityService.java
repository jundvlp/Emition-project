package com.example.weatherapptraining.service;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherapptraining.R;
import com.example.weatherapptraining.data.City;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.text.DecimalFormat;
import java.util.ArrayList;

// ArrayList<City>getCities;

public class CityService extends AppCompatActivity {
    private EditText etCity;
    private Button btn1;
    private TextView tvResult;
    private NetworkGateway networkGateway;
    private  final String url = "http://api.openweathermap.org/data/2.5/weather";
    private  final String appid = "&APPID=77d67887fba87dec09dfddb0d4601e4c";
    DecimalFormat df = new DecimalFormat("#.##");

    public CityService(NetworkGateway networkGateway) {
        this.networkGateway = networkGateway;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_layout);
        etCity = findViewById(R.id.etCity);
        btn1 = findViewById(R.id.btnGet);
        tvResult = findViewById(R.id.tvResult);
    }
    public void getWeatherDetails(View view) {
        String tempUrl = "";
        String city = etCity.getText().toString().trim();
        if (city.equals("")) {
            tvResult.setText("City field can not be empty!");
        } else {
            tempUrl = url + "?q=" + city + "&appid=" + appid;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.d("response", response);
                String output = "";
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                    JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                    String description = jsonObjectWeather.getString("descriprtion");
                    JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                    double temp = jsonObjectMain.getDouble("temp") - 273.15;
                    double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
                    float pressure = jsonObjectMain.getInt("pressure");
                    int humidity = jsonObjectMain.getInt("humidity");
                    JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind");
                    String wind = jsonObjectWind.getString("speed");
                    JSONObject jsonObjectClouds = jsonResponse.getJSONObject("clouds");
                    String clouds = jsonObjectClouds.getString("all");
                    JSONObject jsonObjectSys = jsonResponse.getJSONObject("sys");
                    String countryName = jsonObjectSys.getString("country");
                    String cityName = jsonResponse.getString("name");
                    tvResult.setTextColor(Color.rgb(68, 134, 199));
                    output += "Current weather of " + cityName + " (" + countryName + ")"
                            + "\n Temp: " + df.format(temp) + " ℃"
                            + "\n Feels Like: " + df.format(feelsLike) + " ℃"
                            + "\n Humidity: " + humidity + "%"
                            + "\n Description: " + description
                            + "\n Wind Speed: " + wind + "m/s (meters per second)"
                            + "\n Cloudiness: " + clouds + "%"
                            + "\n Pressure: " + pressure + " hPa";
                    tvResult.setText(output);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

}