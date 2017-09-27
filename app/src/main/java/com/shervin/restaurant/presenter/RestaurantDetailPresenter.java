package com.shervin.restaurant.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import com.shervin.restaurant.contract.RestaurantDetailContract;
import com.shervin.restaurant.data.RestaurantDetail;

import javax.inject.Inject;

import rx.Subscriber;

public class RestaurantDetailPresenter implements RestaurantDetailContract.Presenter, LifecycleObserver {

    private final RestaurantDetailContract.View view;
    private final RestaurantDetailContract.Usecase usecase;
    private final Lifecycle lifecycle;

    @Inject RestaurantDetailPresenter(RestaurantDetailContract.View view, RestaurantDetailContract.Usecase usecase, Lifecycle lifecycle) {
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
    public void fetchRestaurantDetail(int restaurantId) {
        view.showLoading();
        usecase.fetchRestaurant(restaurantId, new Subscriber<RestaurantDetail>() {
            @Override
            public void onCompleted() {
                //no-ops
            }

            @Override
            public void onError(Throwable e) {
                view.hideLoading();
                view.showError(e);
            }

            @Override
            public void onNext(RestaurantDetail restaurantDetail) {
                view.hideLoading();
                view.setDetail(restaurantDetail);
            }
        });
    }
}
