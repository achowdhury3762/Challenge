package com.example.achowdhury.openweather.model;

import java.util.List;

public class ForecastResponse {
    String cod;
    float message;
    List<Forecast> list;
    City city;

    public City getCity() {
        return city;
    }

    public String getCod() {
        return cod;
    }

    public float getMessage() {
        return message;
    }

    public List<Forecast> getForecastList() {
        return list;
    }
}
