package com.shervin.restaurant.data;

import android.support.annotation.NonNull;

public class RestaurantItemModel {

    public final Restaurant restaurant;
    public boolean isFavor;

    public RestaurantItemModel(@NonNull Restaurant restaurant, boolean isFavor) {
        this.restaurant = restaurant;
        this.isFavor = isFavor;
    }
}
