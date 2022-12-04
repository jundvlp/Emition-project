package com.example.weatherapptraining;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {
   private EditText etCity;
   private TextView tvResult;
   private  Button btn1;
    private static final String url = "http://api.openweathermap.org/data/2.5/weather";
    private static final String appid = "8e855dde33aa6ad06703722e2cc9627e";
    DecimalFormat df = new DecimalFormat("#.##");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etCity = findViewById(R.id.etCity);
        btn1 = findViewById(R.id.butt1);
        tvResult = findViewById(R.id.tvResult);

        etCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String cityInput = etCity.getText().toString().trim();
                btn1.setEnabled(!cityInput.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    public void button1(View view) {
        System.out.print("clickonbutton1");
        setContentView(R.layout.activity_main2);
        String tempUrl = "";
        String city = etCity.getText().toString().trim();
        if(city.equals("")){
            tvResult.setText("City field can not be empty!");
        }else{
            tempUrl = url + "?q=" + city + "&appid=" + appid;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response);
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
    public void button2(View view) {
        System.out.print("clickonbutton2");
        setContentView(R.layout.activity_main2);
    }
    public void button3(View view) {
        System.out.print("clickonbutton3");
        setContentView(R.layout.activity_main2);
    }
}