package com.orca.kam.androidrxmvvmarchitect.viewmodel.adapter;

import android.databinding.BaseObservable;

import com.orca.kam.androidrxmvvmarchitect.model.user.GitHubUser;

/**
 * Created by Kang Young Won on 2016-07-25.
 */
public class UserItemViewModel extends BaseObservable {
    private GitHubUser gitHubUser;


    public UserItemViewModel(GitHubUser gitHubUser) {
        this.gitHubUser = gitHubUser;
    }


    public String getName() {
        return gitHubUser.login;
    }


    public String getUrl() {
        return gitHubUser.htmlUrl;
    }


    public void getUser(GitHubUser gitHubUser) {
        this.gitHubUser = gitHubUser;
        notifyChange();
    }
}