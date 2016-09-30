package com.orca.kam.androidrxmvvmarchitect.dagger.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Kang Young Won on 2016-04-28.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
