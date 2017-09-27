package com.shervin.restaurant.contract.data;

import android.support.annotation.NonNull;

import com.shervin.restaurant.data.Restaurant;
import com.shervin.restaurant.data.RestaurantDetail;

import java.util.List;

import rx.Observable;

public interface RestaurantDataContract {

    interface Repository {

        Observable<List<Restaurant>> fetch(double lat, double lng);

        Observable<List<Restaurant>> getFavorites();

        Observable<RestaurantDetail> fetch(int restaurantId);

        Observable<Void> saveFavorite(@NonNull Restaurant restaurant);
    }

    interface LocalDataSource {

        Observable<List<Restaurant>> getFavorites();

        Observable<Void> saveFavorite(@NonNull Restaurant restaurant);
    }

    interface RemoteDataSource {

        Observable<List<Restaurant>> fetch(double lat, double lng);

        Observable<RestaurantDetail> getRestaurant(int restaurantId);
    }
}
