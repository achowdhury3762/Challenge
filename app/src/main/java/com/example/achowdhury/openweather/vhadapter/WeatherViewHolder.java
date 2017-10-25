package com.example.achowdhury.openweather.vhadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.achowdhury.openweather.R;
import com.example.achowdhury.openweather.model.Forecast;
import com.squareup.picasso.Picasso;

import java.util.Date;

public class WeatherViewHolder extends RecyclerView.ViewHolder {
    private ImageView weatherIcon;
    private TextView weatherDescriptionTextView;
    private TextView dateTextView;

    public WeatherViewHolder(View itemView) {
        super(itemView);

        weatherIcon = (ImageView) itemView.findViewById(R.id.weather_icon);
        weatherDescriptionTextView = (TextView) itemView.findViewById(R.id.weather_text_view);
        dateTextView = (TextView) itemView.findViewById(R.id.date_text_view);
    }

    public void bind(Forecast forecast) {
        String icon = forecast.getWeather().get(0).getIcon();
        Integer dt = forecast.getDt();
        Date time = new Date((long) dt *1000);
        String message = forecast.getWeather().get(0).getMain();

        dateTextView.setText(time.toString());
        weatherDescriptionTextView.setText(message);
        Picasso.with(itemView.getContext()).load("http://openweathermap.org/img/w/" + icon + ".png").into(weatherIcon);
    }
}
