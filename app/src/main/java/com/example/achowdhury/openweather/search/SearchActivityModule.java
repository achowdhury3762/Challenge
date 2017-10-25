package com.example.achowdhury.openweather.search;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchActivityModule {

    @Provides
    SearchMVP.View providesStarWarsView(SearchActivity searchActivity) {
        return searchActivity;
    }

    @Provides
    SearchPresenter providesSWPresenter(SearchNetworkService service, SearchMVP.View view) {
        return new SearchPresenter(service, view);
    }
}
