package com.orca.kam.androidrxmvvmarchitect.viewmodel.activity;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.jakewharton.rxbinding.support.v7.widget.RecyclerViewScrollEvent;
import com.orca.kam.androidrxmvvmarchitect.model.API;
import com.orca.kam.androidrxmvvmarchitect.model.api.GitHubApi;
import com.orca.kam.androidrxmvvmarchitect.ui.adapter.GitHubItemAdapter;

import retrofit2.adapter.rxjava.HttpException;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Kang Young Won on 2016-07-22.
 */
public class MainViewModel {
    private MainViewListener mainViewListener;

    private GitHubItemAdapter gitHubItemAdapter;
    private GitHubApi gitHubApi = GitHubApi.Factory.create();
    private CompositeSubscription subscription;

    private static API CURRENT_API;
    private int page = 1;

    private LinearLayoutManager layoutManager;
    private boolean recyclerViewLoadingSync = true;
    private int recyclerViewPastVisibleItems, recyclerViewVisibleItemCount, recyclerViewTotalItemCount;


    public MainViewModel(Context context) {
        mainViewListener = (MainViewListener) context;
        gitHubItemAdapter = new GitHubItemAdapter(context);
        subscription = new CompositeSubscription();
    }


    public GitHubItemAdapter getGitHubItemAdapter() {
        return gitHubItemAdapter;
    }


    public void setLayoutManager(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }


    public Action1<RecyclerViewScrollEvent> recyclerViewScrollAction() {
        return recyclerViewScrollEvent -> {
            if (layoutManager == null) {
                return;
            }
            if (recyclerViewScrollEvent.dy() > 0) {
                recyclerViewVisibleItemCount = layoutManager.getChildCount();
                recyclerViewTotalItemCount = layoutManager.getItemCount();
                recyclerViewPastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                if (recyclerViewLoadingSync) {
                    if ((recyclerViewVisibleItemCount + recyclerViewPastVisibleItems) >= recyclerViewTotalItemCount) {
                        recyclerViewLoadingSync = false;
                        executeGithubApi(CURRENT_API);
                    }
                }
            }
        };
    }


    public void resetAdapter() {
        page = 1;
        gitHubItemAdapter.clearAllList();
    }


    public void destroy() {
        if (subscription != null) {
            subscription.unsubscribe();
            subscription = null;
        }
    }


    public void executeGithubApi(int resId, String... queryArgs) {
        executeGithubApi(API.getApi(resId), queryArgs);
    }


    public void executeGithubApi(API api, String... queryArgs) {
        CURRENT_API = api;
        mainViewListener.setToolBarTitle(CURRENT_API.name());
        String queryFormat = CURRENT_API.getQueryFormat();
        switch (CURRENT_API) {
            case API_SEARCH_USER:
                searchGithubUserList(queryArgs[0]);
                break;
            case API_TOP_FOLLOW_USER:
                searchGithubTopFollowUserList(queryFormat);
                break;
            case API_TOP_FOLLOW_USER_LOCATION:
                searchGithubTopFollowUserList(
                        String.format(queryFormat, queryArgs[1]));
                break;
            case API_TOP_FOLLOW_USER_LANGUAGE:
                searchGithubTopFollowUserList(
                        String.format(queryFormat, queryArgs[0]));
                break;
            case API_TOP_FOLLOW_USER_LANGUAGE_LOCATION:
                searchGithubTopFollowUserList(
                        String.format(queryFormat, queryArgs[0], queryArgs[1]));
                break;
            case API_TOP_STAR_REPO:
                searchGithubTopStarRepoList(queryFormat);
                break;
            case API_TOP_STAR_REPO_LANGUAGE:
                searchGithubTopStarRepoList(
                        String.format(queryFormat, queryArgs[0]));
                break;
        }
        mainViewListener.showSwipeRefresh(true);
        mainViewListener.showSnackBar(Integer.toString(page));
    }


    private void searchGithubTopFollowUserList(String query) {
        subscription.add(gitHubApi.topUsersOfFollow(page, query)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(gitHubUserResult -> gitHubItemAdapter.addUserList(gitHubUserResult.getUserList()),
                        onError(), onComplete()));
    }


    private void searchGithubTopStarRepoList(String query) {
        subscription.add(gitHubApi.topReposOfStars(page, query)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(gitHubRepoResult -> gitHubItemAdapter.addRepoList(gitHubRepoResult.getRepoList()),
                        onError(), onComplete()));
    }


    private void searchGithubUserList(String searchName) {
        subscription.add(gitHubApi.userList(page, searchName)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(gitHubResult -> gitHubItemAdapter.addUserList(gitHubResult.getUserList()),
                        onError(), onComplete()));
    }


    private void searchGitHubRepos(String userName) {
        subscription.add(gitHubApi.publicRepositories(userName)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(gitHubRepos -> gitHubItemAdapter.addRepoList(gitHubRepos),
                        onError(), onComplete()));
    }


    private Action1<Throwable> onError() {
        return error -> {
            if (isHttp404(error)) {
                mainViewListener.showSnackBar("User Not found [404]");
            } else {
                mainViewListener.showSnackBar(error.getMessage());
                Log.e("GitHubItemAdapter", error.getMessage());
            }
            recyclerViewLoadingSync = true;
            mainViewListener.showSwipeRefresh(false);
        };
    }


    private static boolean isHttp404(Throwable error) {
        return error instanceof HttpException && ((HttpException) error).code() == 404;
    }


    private Action0 onComplete() {
        return () -> {
            if (gitHubItemAdapter.getItemCount() < 1) {
                mainViewListener.showSnackBar("Count == 0");
            }
            page++;
            recyclerViewLoadingSync = true;
            mainViewListener.showSwipeRefresh(false);
        };
    }


    public interface MainViewListener {
        void showSnackBar(CharSequence msg);

        void showSwipeRefresh(boolean refresh);

        void setToolBarTitle(String title);
    }
}