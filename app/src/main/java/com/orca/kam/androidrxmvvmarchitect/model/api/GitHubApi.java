package com.orca.kam.androidrxmvvmarchitect.model.api;

import com.orca.kam.androidrxmvvmarchitect.BuildConfig;
import com.orca.kam.androidrxmvvmarchitect.model.repo.GitHubRepo;
import com.orca.kam.androidrxmvvmarchitect.model.repo.GitHubRepoResult;
import com.orca.kam.androidrxmvvmarchitect.model.user.GitHubUser;
import com.orca.kam.androidrxmvvmarchitect.model.user.GitHubUserResult;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Kang Young Won on 2016-07-22.
 */
public interface GitHubApi {
/*@POST("login/oauth/authorize") Observable<String> authorize(
            @Query("client_id") String clientId,
            @Query("client_secret") String clientId,
            @Query("") String clientId,
            );*/

    @GET("login/oauth/access_token") Observable<String> token();

    @GET Observable<GitHubUser> userFromUrl(@Url String userUrl);

    @GET("users/{userName}") Observable<GitHubUserResult> user(@Path("userName") String userName);

    @GET("users/{userName}/repos") Observable<List<GitHubRepo>> publicRepositories(@Path("userName") String userName);


    @GET("search/users") Observable<GitHubUserResult> userList(
            @Query("page") int page,
            @Query("q") String userName);

    @GET("search/users") Observable<GitHubUserResult> topUsersOfFollow(
            @Query("page") int page,
            @Query(value = "q", encoded = true) String query);


    @GET("search/repositories") Observable<GitHubRepoResult> topReposOfStars(
            @Query("page") int page,
            @Query(value = "q", encoded = true) String query);


    class Factory {
        public static GitHubApi create() {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.GITHUB_API_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(GitHubApi.class);
        }
    }
}
