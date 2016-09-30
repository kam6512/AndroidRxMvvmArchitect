package com.orca.kam.androidrxmvvmarchitect.ui.adapter.holder;

import com.orca.kam.androidrxmvvmarchitect.databinding.ItemRepoBinding;
import com.orca.kam.androidrxmvvmarchitect.model.repo.GitHubRepo;
import com.orca.kam.androidrxmvvmarchitect.viewmodel.adapter.RepoItemViewModel;

/**
 * Created by Kang Young Won on 2016-07-25.
 */
public class RepoItemViewHolder extends GithubItemViewHolder {

    private final ItemRepoBinding binding;


    public RepoItemViewHolder(final ItemRepoBinding binding) {
        super(binding.cardView);
        this.binding = binding;
    }


    @Override public void bindViews(GitHubRepo gitHubRepo) {
        RepoItemViewModel repoItemViewModel = new RepoItemViewModel(gitHubRepo);
        binding.setViewModel(repoItemViewModel);
    }
}
