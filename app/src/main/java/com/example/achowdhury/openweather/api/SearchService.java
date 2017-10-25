package com.example.achowdhury.openweather.api;

import com.example.achowdhury.openweather.model.ForecastResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {
    @GET("data/2.5/forecast")
    Observable<ForecastResponse> getWeatherInUS(@Query("q") String cityQuery, @Query("AppId") String appId);
}