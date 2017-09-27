package com.shervin.restaurant.datasource;

import android.support.annotation.NonNull;

import com.shervin.restaurant.contract.data.RestaurantDataContract;
import com.shervin.restaurant.data.Restaurant;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import rx.Observable;

public class RestaurantLocalDataSource implements RestaurantDataContract.LocalDataSource {

    @Inject RestaurantLocalDataSource() {
    }

    @Override
    public Observable<List<Restaurant>> getFavorites() {
        try (Realm realm = Realm.getDefaultInstance()) {
            List<Restaurant> favorites = realm.where(Restaurant.class).findAll();
            return favorites == null
                    ? Observable.just(new ArrayList<>())
                    : Observable.just(realm.copyFromRealm(favorites));
        }
    }

    @Override
    public Observable<Void> saveFavorite(@NonNull Restaurant restaurant) {
        try (Realm realmInstance = Realm.getDefaultInstance()) {
            realmInstance.executeTransaction((realm) -> realm.insertOrUpdate(restaurant));
        }

        return Observable.empty();
    }
}
