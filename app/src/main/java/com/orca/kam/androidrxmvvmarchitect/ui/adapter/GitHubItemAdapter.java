package com.orca.kam.androidrxmvvmarchitect.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.google.common.collect.Lists;
import com.orca.kam.androidrxmvvmarchitect.R;
import com.orca.kam.androidrxmvvmarchitect.databinding.*;
import com.orca.kam.androidrxmvvmarchitect.model.repo.GitHubRepo;
import com.orca.kam.androidrxmvvmarchitect.model.user.GitHubUser;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Kang Young Won on 2016-07-25.
 */
public class GitHubItemAdapter extends RecyclerView.Adapter<GithubItemViewHolder> {

    private static final int TYPE_REPOSITORY = 0;
    private static final int TYPE_USER = 1;
    private static int CURRENT_TYPE = TYPE_REPOSITORY;

    private List<GitHubRepo> repoList = Lists.newArrayList();
    private List<GitHubUser> userList = Lists.newArrayList();

    private final LayoutInflater layoutInflater;


    @Inject public GitHubItemAdapter(final Context context) {
        layoutInflater = LayoutInflater.from(context);
    }


    @Override public GithubItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_REPOSITORY) {
            ItemRepoBinding repoBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_repo, parent, false);
            return new RepoItemViewHolder(repoBinding);
        } else {
            ItemUserBinding userBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_user, parent, false);
            return new UserItemViewHolder(userBinding);
        }
    }


    @Override public void onBindViewHolder(GithubItemViewHolder holder, int position) {
        if (CURRENT_TYPE == TYPE_REPOSITORY) {
            holder.bindViews(repoList.get(position));
        } else {
            holder.bindViews(userList.get(position));
        }
    }


    @Override public int getItemCount() {
        if (CURRENT_TYPE == TYPE_REPOSITORY) {
            return repoList.size();
        } else {
            return userList.size();
        }
    }


    @Override public int getItemViewType(int position) {
        return CURRENT_TYPE;
    }


    public void clearAllList() {
        clearRepoList();
        clearUserList();
    }


    public void clearRepoList() {
        repoList.clear();
        notifyDataSetChanged();
    }


    public void clearUserList() {
        userList.clear();
        notifyDataSetChanged();
    }


    public void addRepoList(final List<GitHubRepo> gitHubRepos) {
        repoList.addAll(gitHubRepos);
        CURRENT_TYPE = TYPE_REPOSITORY;
        notifyDataSetChanged();
    }


    public void addUserList(final List<GitHubUser> gitHubUsers) {
        userList.addAll(gitHubUsers);
        CURRENT_TYPE = TYPE_USER;
        notifyDataSetChanged();
    }
}