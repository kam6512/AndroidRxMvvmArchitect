package com.orca.kam.androidrxmvvmarchitect;

import android.app.Application;
import android.preference.PreferenceManager;

import com.f2prateek.rx.preferences.RxSharedPreferences;
import com.facebook.stetho.Stetho;
import com.orca.kam.androidrxmvvmarchitect.dagger.component.AppComponent;
import com.orca.kam.androidrxmvvmarchitect.dagger.component.DaggerAppComponent;
import com.orca.kam.androidrxmvvmarchitect.dagger.module.AppModule;

/**
 * Created by Kang Young Won on 2016-07-22.
 */
public class App extends Application {
    private AppComponent appComponent;
    private static RxSharedPreferences preferences;


    @Override public void onCreate() {
        super.onCreate();
        initializeInjector();
        initializeRxSharedPreference();
        setStetho();
    }


    private void initializeInjector() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this)).build();
    }


    private void initializeRxSharedPreference() {
        preferences = RxSharedPreferences.create(PreferenceManager.getDefaultSharedPreferences(this));
    }


    private void setStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }


    @Override public void onTerminate() {
        super.onTerminate();
        preferences = null;
    }


    public AppComponent getAppComponent() {
        if (appComponent == null) {
            initializeInjector();
        }
        return appComponent;
    }


    public static RxSharedPreferences getPreferences() {
        return preferences;
    }
}