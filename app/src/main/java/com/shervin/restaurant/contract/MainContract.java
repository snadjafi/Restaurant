package com.shervin.restaurant.contract;

import android.support.annotation.NonNull;

import com.shervin.restaurant.data.Restaurant;
import com.shervin.restaurant.data.RestaurantItemModel;

import java.util.List;

import rx.Subscriber;

public interface MainContract {

    interface View {

        void showLoading();

        void hideLoading();

        void showError(Throwable e);

        void setRestaurants(@NonNull List<RestaurantItemModel> restaurants);
    }

    interface Presenter {

        void fetchRestaurants(double lat, double lng);

        void onFavoriteClick(@NonNull Restaurant restaurant);
    }

    interface Usecase {

        void fetchRestaurants(double lat, double lng, @NonNull Subscriber subscriber);

        void clear();

        void saveFavorite(Restaurant restaurant);
    }
}
