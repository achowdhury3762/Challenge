package com.example.achowdhury.openweather.vhadapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.achowdhury.openweather.R;
import com.example.achowdhury.openweather.model.Forecast;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder> {

    private final List<Forecast> forecast;

    public WeatherAdapter(List<Forecast> forecast) {
        this.forecast = forecast;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.weather_viewholder, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        holder.bind(forecast.get(position));
    }

    @Override
    public int getItemCount() {
        return forecast.size();
    }
}
