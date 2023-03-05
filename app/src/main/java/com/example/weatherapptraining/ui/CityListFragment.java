package com.example.weatherapptraining.ui;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapptraining.service.CityService;
import com.example.weatherapptraining.uiModel.CityListViewModel;

public class CityListFragment extends AppCompatActivity {
    private CityListPage view;
    private CityListViewModel model;
    private CityService cityService;
    public CityListFragment(CityService cityService){
        this.cityService = cityService;
    }
}




