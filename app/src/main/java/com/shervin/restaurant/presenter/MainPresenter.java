package com.shervin.restaurant.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;

import com.shervin.restaurant.contract.MainContract;
import com.shervin.restaurant.data.Restaurant;
import com.shervin.restaurant.data.RestaurantItemModel;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

public class MainPresenter implements MainContract.Presenter, LifecycleObserver {

    private final MainContract.View view;
    private final MainContract.Usecase usecase;
    private final Lifecycle lifecycle;

    @Inject MainPresenter(MainContract.View view, MainContract.Usecase usecase, Lifecycle lifecycle) {
        this.view = view;
        this.usecase = usecase;
        this.lifecycle = lifecycle;
        this.lifecycle.addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY) void onDestroy() {
        usecase.clear();
        lifecycle.removeObserver(this);
    }

    @Override
    public void fetchRestaurants(double lat, double lng) {
        view.showLoading();
        usecase.fetchRestaurants(lat, lng, new Subscriber<List<RestaurantItemModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.hideLoading();
                view.showError(e);
            }

            @Override
            public void onNext(List<RestaurantItemModel> restaurants) {
                view.hideLoading();
                view.setRestaurants(restaurants);
            }
        });
    }

    @Override
    public void onFavoriteClick(@NonNull Restaurant restaurant) {
        usecase.saveFavorite(restaurant);
    }
}
