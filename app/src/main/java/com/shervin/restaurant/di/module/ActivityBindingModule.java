package com.shervin.restaurant.di.module;

import com.shervin.restaurant.ui.MainActivity;
import com.shervin.restaurant.ui.RestaurantDetailActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainActivityModule.class, RestaurantRepositoryModule.class})
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector(modules = {RestaurantDetailActivityModule.class, RestaurantRepositoryModule.class})
    abstract RestaurantDetailActivity restaurantDetailActivity();
}
