package com.shervin.restaurant.repository;

import android.support.annotation.NonNull;

import com.shervin.restaurant.contract.data.RestaurantDataContract;
import com.shervin.restaurant.data.Restaurant;
import com.shervin.restaurant.data.RestaurantDetail;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class RestaurantRepository implements RestaurantDataContract.Repository {

    private final RestaurantDataContract.LocalDataSource localDataSource;
    private final RestaurantDataContract.RemoteDataSource remoteDateSource;

    @Inject RestaurantRepository(RestaurantDataContract.LocalDataSource localDataSource, RestaurantDataContract.RemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDateSource = remoteDataSource;
    }

    @Override
    public Observable<List<Restaurant>> fetch(double lat, double lng) {
        return remoteDateSource.fetch(lat, lng);
    }

    @Override
    public Observable<RestaurantDetail> fetch(int restaurantId) {
        return remoteDateSource.getRestaurant(restaurantId);
    }

    @Override
    public Observable<Void> saveFavorite(@NonNull Restaurant restaurant) {
        return localDataSource.saveFavorite(restaurant);
    }

    @Override
    public Observable<List<Restaurant>> getFavorites() {
        return localDataSource.getFavorites();
    }
}
