package com.shervin.restaurant.di;

import com.shervin.restaurant.di.module.MainActivityModule;
import com.shervin.restaurant.di.module.RestaurantDetailActivityModule;
import com.shervin.restaurant.ui.MainActivity;
import com.shervin.restaurant.ui.RestaurantDetailActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TestActivityBindingModule {

    @ContributesAndroidInjector(modules = {RestaurantDetailActivityModule.class})
    protected abstract RestaurantDetailActivity restaurantDetailActivity();

    @ContributesAndroidInjector(modules = {MainActivityModule.class})
    protected abstract MainActivity mainActivity();
}
