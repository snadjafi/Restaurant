package com.shervin.restaurant.contract;

import android.support.annotation.NonNull;

import com.shervin.restaurant.data.RestaurantDetail;

import rx.Subscriber;

public interface RestaurantDetailContract {

    interface View {

        void showLoading();

        void hideLoading();

        void showError(Throwable e);

        void setDetail(@NonNull RestaurantDetail restaurantDetail);
    }

    interface Presenter {

        void fetchRestaurantDetail(int restaurantId);
    }

    interface Usecase {

        void fetchRestaurant(int restaurantId, @NonNull Subscriber subscriber);

        void clear();
    }
}
