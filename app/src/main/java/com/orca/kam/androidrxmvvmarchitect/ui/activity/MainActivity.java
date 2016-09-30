package com.orca.kam.androidrxmvvmarchitect.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.jakewharton.rxbinding.support.design.widget.RxNavigationView;
import com.jakewharton.rxbinding.support.v4.widget.RxSwipeRefreshLayout;
import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView;
import com.jakewharton.rxbinding.support.v7.widget.RxSearchView;
import com.jakewharton.rxbinding.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding.view.RxView;
import com.orca.kam.androidrxmvvmarchitect.R;
import com.orca.kam.androidrxmvvmarchitect.dagger.component.ActivityComponent;
import com.orca.kam.androidrxmvvmarchitect.databinding.ActivityMainBinding;
import com.orca.kam.androidrxmvvmarchitect.model.API;
import com.orca.kam.androidrxmvvmarchitect.ui.view.RxDialog;
import com.orca.kam.androidrxmvvmarchitect.viewmodel.activity.MainViewModel;
import com.trello.rxlifecycle.ActivityEvent;

import java.util.concurrent.TimeUnit;

public class MainActivity extends BaseActivity implements MainViewModel.MainViewListener {

    private MainViewModel vm;
    private ActivityMainBinding binding;

    private String language, location;


    @Override protected void injectComponent(ActivityComponent activityComponent) {
        activityComponent.inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        vm = new MainViewModel(this);
        binding.setMainViewModel(vm);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBar(binding.toolbar);
        setNavigation(binding.navView);
        setSwipeRefresh(binding.refresh);
        setupRecyclerView(binding.recycler);
        setLanguageFab(binding.fabLanguage);
        setLocationFab(binding.fabLocation);
    }


    private void setToolBar(Toolbar toolBar) {
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        RxToolbar.navigationClicks(toolBar)
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aVoid -> openDrawer(true));
    }


    private void setNavigation(NavigationView navigation) {
        RxNavigationView.itemSelections(navigation)
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .doOnNext(aVoid -> openDrawer(false))
                .doOnNext(aVoid -> vm.resetAdapter())
                .subscribe(menuItem -> vm.executeGithubApi(menuItem.getItemId(), language, location));
    }


    private void setSwipeRefresh(SwipeRefreshLayout swipeRefreshLayout) {
        RxSwipeRefreshLayout.refreshes(swipeRefreshLayout)
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .doOnNext(aVoid -> vm.resetAdapter())
                .subscribe(aVoid -> showSwipeRefresh(false));
    }


    private void setupRecyclerView(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        vm.setLayoutManager(layoutManager);

        recyclerView.setAdapter(vm.getGitHubItemAdapter());
        RxRecyclerView.scrollEvents(recyclerView)
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(vm.recyclerViewScrollAction());
    }


    private void setLanguageFab(FloatingActionButton floatingActionButton) {
        RxView.clicks(floatingActionButton)
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aVoid -> {
                    RxDialog.showLanguageDialog(this)
                            .compose(bindUntilEvent(ActivityEvent.DESTROY))
                            .subscribe(language -> this.language = language);
                });
    }


    private void setLocationFab(FloatingActionButton floatingActionButton) {
        RxView.clicks(floatingActionButton)
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aVoid -> {
                    RxDialog.showLocationDialog(this)
                            .compose(bindUntilEvent(ActivityEvent.DESTROY))
                            .subscribe(location -> this.location = location);
                });
    }


    private void openDrawer(boolean isDrawerOpen) {
        if (isDrawerOpen) {
            binding.drawerLayout.openDrawer(GravityCompat.START);
        } else {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = binding.drawerLayout;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        RxSearchView.queryTextChanges((SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search)))
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .debounce(1500, TimeUnit.MILLISECONDS)
                .filter(charSequence -> charSequence != null && !charSequence.toString().isEmpty())
                .doOnNext(charSequence -> vm.resetAdapter())
                .subscribe(charSequence -> vm.executeGithubApi(API.API_SEARCH_USER, charSequence.toString()));
        return true;
    }


    @Override protected void onDestroy() {
        super.onDestroy();
        vm.destroy();
    }


    @Override public void showSnackBar(CharSequence msg) {
        Snackbar.make(binding.coordinatorLayout, msg, Snackbar.LENGTH_LONG).show();
    }


    @Override public void showSwipeRefresh(boolean refresh) {
        binding.refresh.setRefreshing(refresh);
    }


    @Override public void setToolBarTitle(String title) {
        binding.toolbar.setTitle(title);
    }
}