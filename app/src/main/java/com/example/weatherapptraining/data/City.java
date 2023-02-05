package com.example.weatherapptraining.data;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.weatherapptraining.R;

public class City extends AppCompatActivity {
    public String name;
   private EditText etCity;
   private Button btnGet;
   private TextView tvResult;
    int maxTemperature;
    int minTemperature;
    // weather identifier - cloudy, sunny, ect
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_layout);

        etCity= findViewById(R.id.etCity);
        btnGet = findViewById(R.id.btnGet);
        tvResult = findViewById(R.id.tvResult);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etCity.getText().toString();
                tvResult.setText(name);
            }
        });
    }
}
