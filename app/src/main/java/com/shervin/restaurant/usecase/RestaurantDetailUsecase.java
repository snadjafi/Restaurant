package com.shervin.restaurant.usecase;

import android.support.annotation.NonNull;

import com.shervin.restaurant.contract.RestaurantDetailContract;
import com.shervin.restaurant.contract.data.RestaurantDataContract;

import javax.inject.Inject;

import rx.Subscriber;

public class RestaurantDetailUsecase extends BaseUsecase implements RestaurantDetailContract.Usecase {

    private final RestaurantDataContract.Repository repository;

    @Inject RestaurantDetailUsecase(RestaurantDataContract.Repository repository) {
        this.repository = repository;
    }

    @Override
    public void fetchRestaurant(int restaurantId, @NonNull Subscriber subscriber) {
        execute(repository.fetch(restaurantId), subscriber);
    }
}
