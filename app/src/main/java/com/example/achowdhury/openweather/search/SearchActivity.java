package com.example.achowdhury.openweather.search;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.achowdhury.openweather.R;
import com.example.achowdhury.openweather.model.Forecast;
import com.example.achowdhury.openweather.util.TextEditUtil;
import com.example.achowdhury.openweather.vhadapter.WeatherAdapter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.Observable;

public class SearchActivity extends AppCompatActivity implements SearchMVP.View {
    private static final String SEARCH_WEATHER_QUERY = "nyc.c4q.ashiquechowdhury.weatherquery";

    private RecyclerView weatherRecycler;
    private WeatherAdapter weatherAdapter;
    private EditText searchEditText;

    @Inject
    SearchPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        AndroidInjection.inject(this);
        init();
    }

    private void init() {
        searchEditText = (EditText) findViewById(R.id.user_input);

        weatherRecycler = (RecyclerView) findViewById(R.id.weather_recycler);
        weatherRecycler.setLayoutManager(new LinearLayoutManager(this));

        Observable<String> textChanges = TextEditUtil.createTextChangeObservable(searchEditText, TextEditUtil.ONE_SECOND, TimeUnit.MILLISECONDS);
        presenter.getWeather(textChanges);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String highScore = sharedPref.getString(getString(R.string.last_saved_search), "");
        searchEditText.setText(highScore);
    }

    @Override
    public void showLoading() {
        //progressbar
    }

    @Override
    public void showWeather(List<Forecast> forecast) {
        weatherAdapter = new WeatherAdapter(forecast);
        weatherRecycler.setAdapter(weatherAdapter);
    }

    @Override
    public void showCurrentCityText(String text) {
        TextView currentCityText = (TextView) findViewById(R.id.city_name_tview);

        currentCityText.setText(text);
    }

    @Override
    public void showLoadingError(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        //progressbar
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        String lastWeatherQuery = (searchEditText == null) ? "" : searchEditText.getText().toString();
        outState.putString(SEARCH_WEATHER_QUERY, lastWeatherQuery);

        saveQuery(lastWeatherQuery);
        super.onSaveInstanceState(outState);
    }

    private void saveQuery(String lastWeatherQuery) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.last_saved_search), lastWeatherQuery);
        editor.commit();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        String search = savedInstanceState.getString(SEARCH_WEATHER_QUERY);
        searchEditText.setText(search);

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onStop() {
        presenter.onStop();

        super.onStop();
    }
}
