package com.example.weatherapptraining.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapptraining.R;

import java.time.LocalDateTime;
import java.util.ArrayList;

// weather identifier - cloudy, sunny, ect
//int temperature;
// LocalDateTime time;
public class Forecast extends RecyclerView.Adapter<Forecast.ViewHolder> {
    private Context context;
    private ArrayList<DetailedForecast> forecastArrayList;

    public Forecast(Context context, ArrayList<DetailedForecast> forecastArrayList) {
        this.context = context;
        this.forecastArrayList = forecastArrayList;
    }
    @NonNull
    @Override
    public Forecast.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_rv_item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Forecast.ViewHolder holder, int position) {

        Picasso.get().load().into(holder.conditionIV);
    }

    @Override
    public int getItemCount() {
        return forecastArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView windTV,temperatureTV,timeTV;
        private ImageView conditionIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            windTV = itemView.findViewById(R.id.idTVWindSpeed);
            temperatureTV = itemView.findViewById(R.id.idTVTemperature);
            timeTV = itemView.findViewById(R.id.idTVTime);
            conditionIV = itemView.findViewById(R.id.idTVCondition);

        }
    }
}
