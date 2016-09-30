package com.orca.kam.androidrxmvvmarchitect.model.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kang Young Won on 2016-07-22.
 */


public class GitHubUser implements Parcelable {

    public long id;
    public String url;
    @SerializedName("html_url")
    public String htmlUrl;
    public String login;
    @SerializedName("avatar_url")
    public String avatarUrl;


    public GitHubUser() {
    }


    @Override public int describeContents() {
        return 0;
    }


    @Override public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(id);
        parcel.writeString(url);
        parcel.writeString(htmlUrl);
        parcel.writeString(login);
        parcel.writeString(avatarUrl);
    }


    protected GitHubUser(Parcel parcel) {
        id = parcel.readLong();
        url = parcel.readString();
        htmlUrl = parcel.readString();
        login = parcel.readString();
        avatarUrl = parcel.readString();
    }


    public static final Creator<GitHubUser> CREATOR = new Creator<GitHubUser>() {
        @Override
        public GitHubUser createFromParcel(Parcel in) {
            return new GitHubUser(in);
        }


        @Override
        public GitHubUser[] newArray(int size) {
            return new GitHubUser[size];
        }
    };


    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (o instanceof GitHubUser) {
            GitHubUser that = (GitHubUser) o;
            return Objects.equal(id, that.id)
                    && Objects.equal(url, that.url)
                    && Objects.equal(htmlUrl, that.htmlUrl)
                    && Objects.equal(login, that.login)
                    && Objects.equal(avatarUrl, that.avatarUrl);
        } else {
            return false;
        }
    }


    @Override public int hashCode() {
        return Objects.hashCode(id, url, htmlUrl, login, avatarUrl);
    }
}