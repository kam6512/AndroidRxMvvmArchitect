package com.orca.kam.androidrxmvvmarchitect.dagger.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kang Young Won on 2016-04-28.
 */
@Module
public class AppModule {
    private final Application application;


    public AppModule(Application application) {
        this.application = application;
    }


    @Provides @Singleton public Application getApplication() {
        return application;
    }

}
