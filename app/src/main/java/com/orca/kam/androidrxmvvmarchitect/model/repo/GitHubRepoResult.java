package com.orca.kam.androidrxmvvmarchitect.model.repo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kang Young Won on 2016-07-22.
 */
public class GitHubRepoResult implements Parcelable {

    @SerializedName("items")
    private List<GitHubRepo> repoList;


    public GitHubRepoResult() {
    }


    public List<GitHubRepo> getRepoList() {
        return repoList;
    }


    @Override public int describeContents() {
        return 0;
    }


    @Override public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeArray(repoList.toArray());
    }


    protected GitHubRepoResult(Parcel parcel) {
        parcel.readList(new ArrayList<GitHubRepo>(), getClass().getClassLoader());
    }


    public static final Creator<GitHubRepoResult> CREATOR = new Creator<GitHubRepoResult>() {
        @Override
        public GitHubRepoResult createFromParcel(Parcel in) {
            return new GitHubRepoResult(in);
        }


        @Override
        public GitHubRepoResult[] newArray(int size) {
            return new GitHubRepoResult[size];
        }
    };


    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (o instanceof GitHubRepoResult) {
            GitHubRepoResult that = (GitHubRepoResult) o;
            return Objects.equal(repoList, that.repoList);
        } else {
            return false;
        }
    }


    @Override public int hashCode() {
        return Objects.hashCode(repoList.size());
    }
}