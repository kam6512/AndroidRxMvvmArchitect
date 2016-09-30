package com.orca.kam.androidrxmvvmarchitect.viewmodel.adapter;

import android.databinding.BaseObservable;

import com.orca.kam.androidrxmvvmarchitect.BuildConfig;
import com.orca.kam.androidrxmvvmarchitect.model.repo.GitHubRepo;

/**
 * Created by Kang Young Won on 2016-07-25.
 */
public class RepoItemViewModel extends BaseObservable {
    private GitHubRepo gitHubRepo;


    public RepoItemViewModel(GitHubRepo gitHubRepo) {
        this.gitHubRepo = gitHubRepo;
    }


    public String getName() {
        return gitHubRepo.name;
    }


    public String getLanguage() {
        if (gitHubRepo.hasLanguage()) {
            return gitHubRepo.language;
        }
        return BuildConfig.UNKNOWN;
    }


    public String getDescription() {
        return gitHubRepo.description;
    }


    public String getStars() {
        return gitHubRepo.stars + "\nStars";
    }


    public String getWatchers() {
        return gitHubRepo.watchers + "\nWatchers";
    }


    public String getForks() {
        return gitHubRepo.forks + "\nForks";
    }


    public void setRepository(GitHubRepo gitHubRepo) {
        this.gitHubRepo = gitHubRepo;
        notifyChange();
    }
}