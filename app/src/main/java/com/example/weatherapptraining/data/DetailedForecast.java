package com.example.weatherapptraining.data;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapptraining.R;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class DetailedForecast extends AppCompatActivity {
  public String name;
    // weather identifier - cloudy, sunny, ect;
    int temperature;
    int pressure;
    int humidity;
    int wind;
    ArrayList<Forecast> forecast;
    public Button btnGet;
    public ImageView cloud;
    public TextView temp1;
    public TextView tempview1;
    public TextView Dnipro;
    public ImageView cloud1;
      @Override
      protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.detailed_forecast_layout);

      btnGet= findViewById(R.id.btnGet);
      cloud = findViewById(R.id.cloud);
      temp1 = findViewById(R.id.temp1);
      tempview1 = findViewById(R.id.tempview1);
      Dnipro = findViewById(R.id.Dnipro);
      cloud1 = findViewById(R.id.cloud1);
      }
}
