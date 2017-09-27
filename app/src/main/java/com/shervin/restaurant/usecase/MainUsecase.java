package com.shervin.restaurant.usecase;

import android.support.annotation.NonNull;

import com.shervin.restaurant.contract.MainContract;
import com.shervin.restaurant.contract.data.RestaurantDataContract;
import com.shervin.restaurant.data.Restaurant;
import com.shervin.restaurant.data.RestaurantItemModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;

public class MainUsecase extends BaseUsecase implements MainContract.Usecase {

    private final RestaurantDataContract.Repository repository;

    @Inject MainUsecase(RestaurantDataContract.Repository repository) {
        this.repository = repository;
    }

    @Override
    public void fetchRestaurants(double lat, double lng, @NonNull Subscriber subscriber) {
        Observable<List<RestaurantItemModel>> combine = Observable.combineLatest(repository.fetch(lat, lng),
                repository.getFavorites(), (restaurants1, favorites1) -> {
            List<RestaurantItemModel> viewModel = new ArrayList<>(restaurants1.size() + favorites1.size());
            for (Restaurant favorite : favorites1) {
                viewModel.add(new RestaurantItemModel(favorite, true));
            }

            for (Restaurant restaurant : restaurants1) {
                if (!favorites1.contains(restaurant)) {
                    viewModel.add(new RestaurantItemModel(restaurant, false));
                }
            }

            return viewModel;
        });


        execute(combine, subscriber);
    }

    @Override
    public void saveFavorite(Restaurant restaurant) {
        repository.saveFavorite(restaurant);
    }
}
