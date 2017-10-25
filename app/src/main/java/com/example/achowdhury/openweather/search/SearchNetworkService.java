package com.example.achowdhury.openweather.search;

import com.example.achowdhury.openweather.api.SearchService;
import com.example.achowdhury.openweather.model.ForecastResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SearchNetworkService {
    private SearchService service;

    @Inject
    public SearchNetworkService(SearchService service) {
        this.service = service;
    }

    public Disposable getWeatherForCity(Observable<String> cityQeuries, final WeatherResponseCallback callback) {
        return cityQeuries
                .flatMap(new Function<String, Observable<ForecastResponse>>() {
                    @Override
                    public Observable<ForecastResponse> apply(@NonNull String cityQuery) throws Exception {
                        return service.getWeatherInUS(cityQuery + ",us", "0e8abc9d0e684ede623f7099fc418f01");
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ForecastResponse>() {
                    @Override
                    public void onNext(@NonNull ForecastResponse response) {
                        callback.onSuccess(response);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.onFailure(new Error(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    interface WeatherResponseCallback {
        void onSuccess(ForecastResponse response);

        void onFailure(Error error);
    }
}
