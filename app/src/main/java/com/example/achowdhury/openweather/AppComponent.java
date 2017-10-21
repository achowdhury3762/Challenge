package com.example.achowdhury.openweather;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        BuildersModule.class
})
interface AppComponent extends AndroidInjector<BaseApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(Application app);

        AppComponent build();
    }

    void inject(BaseApp app);
}
