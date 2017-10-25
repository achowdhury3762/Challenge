package com.example.achowdhury.openweather;

import android.app.Application;
import android.content.Context;

import com.example.achowdhury.openweather.api.SearchService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
class AppModule {
    private static final String baseUrl = "http://api.openweathermap.org/";

    @Provides
    Context bindsContext(Application application) {
        return application;
    }

    @Singleton
    @Provides
    Retrofit providesRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    @Singleton
    @Provides
    SearchService providesSearchService(Retrofit retrofit) {
        return retrofit.create(SearchService.class);
    }
}