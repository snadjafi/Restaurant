package com.shervin.restaurant.datasource;

import com.shervin.restaurant.Service;
import com.shervin.restaurant.contract.data.RestaurantDataContract;
import com.shervin.restaurant.data.Restaurant;
import com.shervin.restaurant.data.RestaurantDetail;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class RestaurantRemoteDataSource implements RestaurantDataContract.RemoteDataSource {

    private final Service service;

    @Inject RestaurantRemoteDataSource(Service service) {
        this.service = service;
    }

    @Override
    public Observable<List<Restaurant>> fetch(double lat, double lng) {
        return service.getRestaurants(lat, lng);
    }

    @Override
    public Observable<RestaurantDetail> getRestaurant(int restaurantId) {
        return service.getRestaurant(restaurantId);
    }
}
