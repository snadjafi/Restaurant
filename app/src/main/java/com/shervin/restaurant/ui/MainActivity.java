package com.shervin.restaurant.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.shervin.restaurant.R;
import com.shervin.restaurant.contract.MainContract;
import com.shervin.restaurant.data.RestaurantItemModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements MainContract.View, RestaurantAdapter.ItemClickListener {

    @BindView(R.id.list) RecyclerView recyclerView;
    @BindView(R.id.progress) ProgressBar progressBar;

    @Inject MainContract.Presenter presenter;
    private RestaurantAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        presenter.fetchRestaurants(37.422740, -122.139956);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(Throwable e) {
        //todo: handle error
        Timber.e(e);
    }

    @Override
    public void setRestaurants(@NonNull List<RestaurantItemModel> restaurants) {
        adapter.setItems(restaurants);
    }

    private void initRecyclerView() {
        adapter = new RestaurantAdapter();
        adapter.setItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(RestaurantItemModel restaurant) {
        startActivity(RestaurantDetailActivity.newIntent(this, restaurant.restaurant.id));
    }

    @Override
    public void onFavoriteClick(RestaurantItemModel restaurant) {
        presenter.onFavoriteClick(restaurant.restaurant);
    }
}
