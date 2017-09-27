package com.shervin.restaurant.di.module;

import android.arch.lifecycle.Lifecycle;

import com.shervin.restaurant.contract.RestaurantDetailContract;
import com.shervin.restaurant.presenter.RestaurantDetailPresenter;
import com.shervin.restaurant.ui.RestaurantDetailActivity;
import com.shervin.restaurant.usecase.RestaurantDetailUsecase;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class RestaurantDetailActivityModule {
    @Provides
    static Lifecycle providesLifecycle(RestaurantDetailActivity activity) {
        return activity.getLifecycle();
    }

    @Binds public abstract RestaurantDetailContract.View provideView(RestaurantDetailActivity activity);

    @Binds public abstract RestaurantDetailContract.Presenter providePresenter(RestaurantDetailPresenter presenter);

    @Binds public abstract RestaurantDetailContract.Usecase provideUsecase(RestaurantDetailUsecase usecase);
}
