package com.orca.kam.androidrxmvvmarchitect.model.auth;

import android.os.Parcel;
import android.os.Parcelable;

import com.f2prateek.rx.preferences.RxSharedPreferences;
import com.orca.kam.androidrxmvvmarchitect.App;

/**
 * Created by Kang Young Won on 2016-07-29.
 */
public class GitHubAuth implements Parcelable {
    private RxSharedPreferences preferences = App.getPreferences();



    public GitHubAuth() {
    }


    protected GitHubAuth(Parcel in) {
    }


    public static final Creator<GitHubAuth> CREATOR = new Creator<GitHubAuth>() {
        @Override
        public GitHubAuth createFromParcel(Parcel in) {
            return new GitHubAuth(in);
        }


        @Override
        public GitHubAuth[] newArray(int size) {
            return new GitHubAuth[size];
        }
    };


    @Override public int describeContents() {
        return 0;
    }


    @Override public void writeToParcel(Parcel parcel, int i) {
    }
}
