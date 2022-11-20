package com.example.weatherapptraining;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void button1(View view) {
        System.out.print("clickonbutton1");
        setContentView(R.layout.activity_main2);

    }
    public void button2(View view) {
        System.out.print("clickonbutton2");
        setContentView(R.layout.activity_main2);
    }
    public void button3(View view) {
        System.out.print("clickonbutton3");
        setContentView(R.layout.activity_main2);
    }
}