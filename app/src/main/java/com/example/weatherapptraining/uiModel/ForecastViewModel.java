package com.example.weatherapptraining.uiModel;

import com.example.weatherapptraining.data.DetailedForecast;

public class ForecastViewModel {
    String result;
    ForecastViewModel(DetailedForecast payload){
        this.result = payload.name;
    }
}
