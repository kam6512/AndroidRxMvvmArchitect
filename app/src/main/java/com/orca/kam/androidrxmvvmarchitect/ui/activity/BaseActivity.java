package com.orca.kam.androidrxmvvmarchitect.ui.activity;

import android.os.Bundle;

import com.orca.kam.androidrxmvvmarchitect.App;
import com.orca.kam.androidrxmvvmarchitect.dagger.component.ActivityComponent;
import com.orca.kam.androidrxmvvmarchitect.dagger.component.AppComponent;
import com.orca.kam.androidrxmvvmarchitect.dagger.component.DaggerActivityComponent;
import com.orca.kam.androidrxmvvmarchitect.dagger.module.ActivityModule;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by Kang Young Won on 2016-07-22.
 */
public abstract class BaseActivity extends RxAppCompatActivity {
    private ActivityComponent activityComponent;


    abstract protected void injectComponent(ActivityComponent activityComponent);


    private ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .appComponent(getAppComponent())
                    .activityModule(new ActivityModule(this))
                    .build();
        }
        return activityComponent;
    }


    private AppComponent getAppComponent() {
        return ((App) getApplication()).getAppComponent();
    }


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectComponent(getActivityComponent());
    }
}
