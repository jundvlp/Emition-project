package com.example.weatherapptraining.data;

import java.util.ArrayList;

public class DetailedForecast {
  public String name;
    // weather identifier - cloudy, sunny, ect;
    int temperature;
    int pressure;
    int humidity;
    int wind;
    ArrayList<Forecast> forecast;
}
