package com.shervin.restaurant;

import com.shervin.restaurant.data.Restaurant;
import com.shervin.restaurant.data.RestaurantDetail;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface Service {
    @GET("v2/restaurant")
    Observable<List<Restaurant>> getRestaurants(@Query("lat") double lat, @Query("lng") double maxResults);

    @GET("v2/restaurant/{id}")
    Observable<RestaurantDetail> getRestaurant(@Path("id") int restaurantId);
}
