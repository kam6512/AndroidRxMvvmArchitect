package com.orca.kam.androidrxmvvmarchitect.dagger.component;

import android.app.Activity;

import com.orca.kam.androidrxmvvmarchitect.dagger.module.ActivityModule;
import com.orca.kam.androidrxmvvmarchitect.dagger.scope.PerActivity;
import com.orca.kam.androidrxmvvmarchitect.ui.activity.MainActivity;

import dagger.Component;

/**
 * Created by Kang Young Won on 2016-04-28.
 */
@PerActivity
@Component(dependencies = {AppComponent.class}, modules = {ActivityModule.class})
public interface ActivityComponent {
    Activity activity();

    void inject(MainActivity mainActivity);
}
