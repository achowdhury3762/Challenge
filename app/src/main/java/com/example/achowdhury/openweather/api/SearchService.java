package com.example.achowdhury.openweather.api;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SearchService {
    @GET("people/{charid}")
    Observable<> getCharacter(@Path("charid") int characterId);
}