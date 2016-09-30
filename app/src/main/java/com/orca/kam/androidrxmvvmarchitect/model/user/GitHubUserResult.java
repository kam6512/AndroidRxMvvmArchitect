package com.orca.kam.androidrxmvvmarchitect.model.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kang Young Won on 2016-07-22.
 */
public class GitHubUserResult implements Parcelable {

    @SerializedName("items")
    private List<GitHubUser> userList;


    public GitHubUserResult() {
    }


    public List<GitHubUser> getUserList() {
        return userList;
    }


    @Override public int describeContents() {
        return 0;
    }


    @Override public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeArray(userList.toArray());
    }


    protected GitHubUserResult(Parcel parcel) {
        parcel.readList(new ArrayList<GitHubUser>(), getClass().getClassLoader());
    }


    public static final Creator<GitHubUserResult> CREATOR = new Creator<GitHubUserResult>() {
        @Override
        public GitHubUserResult createFromParcel(Parcel in) {
            return new GitHubUserResult(in);
        }


        @Override
        public GitHubUserResult[] newArray(int size) {
            return new GitHubUserResult[size];
        }
    };


    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (o instanceof GitHubUserResult) {
            GitHubUserResult that = (GitHubUserResult) o;
            return Objects.equal(userList, that.userList);
        } else {
            return false;
        }
    }


    @Override public int hashCode() {
        return Objects.hashCode(userList.size());
    }
}