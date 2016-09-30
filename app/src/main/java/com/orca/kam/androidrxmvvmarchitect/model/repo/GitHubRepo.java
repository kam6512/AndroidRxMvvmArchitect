package com.orca.kam.androidrxmvvmarchitect.model.repo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;
import com.orca.kam.androidrxmvvmarchitect.model.user.GitHubUser;

/**
 * Created by Kang Young Won on 2016-07-22.
 */
public class GitHubRepo implements Parcelable {
    public long id;
    public String name;
    public String description;
    public int forks;
    public int watchers;
    @SerializedName("stargazers_count")
    public int stars;
    public String language;
    public String homepage;
    public GitHubUser owner;
    public boolean fork;


    public GitHubRepo() {
    }


    public boolean hasHomepage() {
        return homepage != null && !homepage.isEmpty();
    }


    public boolean hasLanguage() {
        return language != null && !language.isEmpty();
    }


    public boolean isFork() {
        return fork;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeInt(this.forks);
        dest.writeInt(this.watchers);
        dest.writeInt(this.stars);
        dest.writeString(this.language);
        dest.writeString(this.homepage);
        dest.writeParcelable(this.owner, 0);
        dest.writeByte(fork ? (byte) 1 : (byte) 0);
    }


    protected GitHubRepo(Parcel in) {
        this.id = in.readLong();
        this.name = in.readString();
        this.description = in.readString();
        this.forks = in.readInt();
        this.watchers = in.readInt();
        this.stars = in.readInt();
        this.language = in.readString();
        this.homepage = in.readString();
        this.owner = in.readParcelable(GitHubUser.class.getClassLoader());
        this.fork = in.readByte() != 0;
    }


    public static final Parcelable.Creator<GitHubRepo> CREATOR = new Parcelable.Creator<GitHubRepo>() {
        public GitHubRepo createFromParcel(Parcel source) {
            return new GitHubRepo(source);
        }


        public GitHubRepo[] newArray(int size) {
            return new GitHubRepo[size];
        }
    };


    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (o instanceof GitHubRepo) {
            GitHubRepo that = (GitHubRepo) o;
            return Objects.equal(id, that.id)
                    && Objects.equal(name, that.name)
                    && Objects.equal(description, that.description)
                    && Objects.equal(forks, that.forks)
                    && Objects.equal(watchers, that.watchers)
                    && Objects.equal(stars, that.stars)
                    && Objects.equal(language, that.language)
                    && Objects.equal(homepage, that.homepage)
                    && Objects.equal(owner, that.owner)
                    && Objects.equal(fork, that.fork);
        } else {
            return false;
        }
    }


    @Override public int hashCode() {
        return Objects.hashCode(id, name, description, forks, watchers, stars, language, homepage, owner, fork);
    }
}