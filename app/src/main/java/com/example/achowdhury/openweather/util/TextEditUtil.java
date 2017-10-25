package com.example.achowdhury.openweather.util;

import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class TextEditUtil {
    public static long ONE_SECOND = 1000;
    public static long TWO_SECONDs = 2000;
    public static long THREE_SECONDS = 3000;
    public static long FOUR_SECONDS = 4000;
    public static long FIVE_SECONDS = 5000;

    public static Observable<String> createTextChangeObservable(EditText userInputName, long timeBetween, TimeUnit unitOfTime) {
        return RxTextView.textChanges(userInputName)
                .filter(new Predicate<CharSequence>() {
                    @Override
                    public boolean test(@NonNull CharSequence charSequence) throws Exception {
                        return charSequence.length() >= 3;
                    }
                })
                .debounce(timeBetween, unitOfTime)
                .map(new Function<CharSequence, String>() {
                    @Override
                    public String apply(@NonNull CharSequence charSequence) throws Exception {
                        return charSequence.toString();
                    }
                });
    }
}
