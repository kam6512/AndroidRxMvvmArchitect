package com.orca.kam.androidrxmvvmarchitect.dagger.module;

import android.app.Activity;
import android.content.Context;

import com.orca.kam.androidrxmvvmarchitect.dagger.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Kang Young Won on 2016-04-28.
 */
@Module
public class ActivityModule {
    private final Context context;
    private final Activity activity;


    public ActivityModule(Activity activity) {
        this.context = activity;
        this.activity = activity;
    }


    @Provides @PerActivity Activity activity() {
        return activity;
    }


    @Provides @PerActivity Context context() {
        return context;
    }
}