package com.example.weatherapptraining.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapptraining.R;
import com.example.weatherapptraining.uiModel.ForecastViewModel;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DetailedForecast extends RecyclerView.Adapter<DetailedForecast.ViewHolder> {
  private Context context;
  private ArrayList<ForecastViewModel> forecastViewModelArrayList;

  public DetailedForecast(Context context, ArrayList<ForecastViewModel> forecastViewModelArrayList) {
    this.context = context;
    this.forecastViewModelArrayList = forecastViewModelArrayList;
  }

  @NonNull
  @Override
  public DetailedForecast.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.weather_rv_item,parent,false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull DetailedForecast.ViewHolder holder, int position) {
    ForecastViewModel model = forecastViewModelArrayList.get(position);
    holder.temperatureTV.setText(model.getTemperature() + "°C");
    Picasso.get().load("http://".concat(model.getIcon())).into(holder.conditionIV);
    holder.windTV.setText(model.getWindSpeed()+"Km/h");
    SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    SimpleDateFormat output = new SimpleDateFormat("hh:mm aa");
    try{
      Date t = input.parse(model.getTime());
      holder.timeTV.setText(output.format(t));
    }catch (ParseException e) {
      e.printStackTrace();
    }
  }

  @Override
  public int getItemCount() {
    return forecastViewModelArrayList.size();
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