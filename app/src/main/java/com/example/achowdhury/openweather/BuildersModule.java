package com.example.achowdhury.openweather;

import com.example.achowdhury.openweather.search.SearchActivity;
import com.example.achowdhury.openweather.search.SearchActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = {SearchActivityModule.class})
    abstract SearchActivity bindsSearchActivity();
}