package com.orca.kam.androidrxmvvmarchitect.dagger.component;

import android.app.Application;

import com.orca.kam.androidrxmvvmarchitect.dagger.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Kang Young Won on 2016-04-28.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Application getApplication();
}
