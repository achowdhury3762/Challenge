package com.example.achowdhury.openweather.search;

import com.example.achowdhury.openweather.model.Forecast;

import java.util.List;

import io.reactivex.Observable;

public interface SearchMVP {
    interface View {
        void showLoading();

        void showWeather(List<Forecast> forecast);

        void showLoadingError(String error);

        void hideLoading();

        void showCurrentCityText(String text);
    }

    interface Presenter {
        void onStop();

        void getWeather(Observable<String> weatherCityQuery);
    }
}
