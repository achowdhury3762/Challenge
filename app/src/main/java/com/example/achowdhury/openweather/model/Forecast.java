package com.example.achowdhury.openweather.model;

import java.util.List;

public class Forecast {
    List<Weather> weather;
    Integer dt;

    public Integer getDt() {
        return dt;
    }

    public List<Weather> getWeather() {
        return weather;
    }
}
