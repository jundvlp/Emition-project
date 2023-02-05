package com.example.weatherapptraining.uiModel;

import com.example.weatherapptraining.data.City;

public class CityListViewModel {
    String result;
    CityListViewModel(City payload){
       this.result = payload.name;
    }
}
