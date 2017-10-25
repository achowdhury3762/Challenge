package com.example.achowdhury.openweather.search;

import com.example.achowdhury.openweather.model.ForecastResponse;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

class SearchPresenter implements SearchMVP.Presenter {

    private CompositeDisposable disposables;
    private final SearchNetworkService service;
    private final SearchMVP.View view;

    public SearchPresenter(SearchNetworkService service, SearchMVP.View view) {
        this.service = service;
        this.view = view;

        disposables = new CompositeDisposable();
    }


    @Override
    public void onStop() {
        view.hideLoading();

        disposables.dispose();
    }

    @Override
    public void getWeather(final Observable<String> weatherQuery) {
        Disposable d = service.getWeatherForCity(weatherQuery, new SearchNetworkService.WeatherResponseCallback() {
            @Override
            public void onSuccess(ForecastResponse response) {
                view.hideLoading();

                view.showWeather(response.getForecastList());
                view.showCurrentCityText(response.getCity().getName());
            }

            @Override
            public void onFailure(Error e) {
                view.hideLoading();

                view.showLoadingError(e.getMessage());
            }
        });
    }

}
