package com.orca.kam.androidrxmvvmarchitect.model;

import com.orca.kam.androidrxmvvmarchitect.R;

/**
 * Created by Kang Young Won on 2016-07-27.
 */
public enum API {
    API_SEARCH_USER
            (R.id.action_search),

    API_TOP_FOLLOW_USER
            (R.id.nav_top_follow_user,
                    "followers:>1"),

    API_TOP_FOLLOW_USER_LANGUAGE
            (R.id.nav_top_follow_user_language,
                    "followers:>1+language:%s"),
    API_TOP_FOLLOW_USER_LOCATION
            (R.id.nav_top_follow_user_location,
                    "followers:>1+location:%s"),
    API_TOP_FOLLOW_USER_LANGUAGE_LOCATION
            (R.id.nav_top_follow_user_language_location,
                    "followers:>1+language:%s+location:%s"),
    API_TOP_STAR_REPO
            (R.id.nav_top_star_repo,
                    "stars:>1"),
    API_TOP_STAR_REPO_LANGUAGE
            (R.id.nav_top_star_repo_language,
                    "stars:>1+language:%s");

    private int resId;
    private String queryFormat;


    API(int resId) {
        this.resId = resId;
        this.queryFormat = null;
    }


    API(int resId, String queryFormat) {
        this.resId = resId;
        this.queryFormat = queryFormat;
    }


    public int getResId() {
        return resId;
    }


    public String getQueryFormat() {
        return queryFormat;
    }


    public static API getApi(int resId) {
        for (API api : values()) {
            if (api.getResId() == resId) {
                return api;
            }
        }
        return null;
    }
}
