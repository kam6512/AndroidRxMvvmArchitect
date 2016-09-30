package com.orca.kam.androidrxmvvmarchitect.ui.adapter.holder;

import com.orca.kam.androidrxmvvmarchitect.databinding.ItemUserBinding;
import com.orca.kam.androidrxmvvmarchitect.model.user.GitHubUser;
import com.orca.kam.androidrxmvvmarchitect.viewmodel.adapter.UserItemViewModel;

/**
 * Created by Kang Young Won on 2016-07-25.
 */
public class UserItemViewHolder extends GithubItemViewHolder {

    private final ItemUserBinding binding;


    public UserItemViewHolder(final ItemUserBinding binding) {
        super(binding.cardView);
        this.binding = binding;
    }


    @Override public void bindViews(GitHubUser gitHubUser) {
        UserItemViewModel userItemViewModel = new UserItemViewModel(gitHubUser);
        binding.setViewModel(userItemViewModel);
    }

}
