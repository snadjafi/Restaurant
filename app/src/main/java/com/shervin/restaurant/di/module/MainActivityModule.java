package com.shervin.restaurant.di.module;

import android.arch.lifecycle.Lifecycle;

import com.shervin.restaurant.contract.MainContract;
import com.shervin.restaurant.presenter.MainPresenter;
import com.shervin.restaurant.ui.MainActivity;
import com.shervin.restaurant.usecase.MainUsecase;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MainActivityModule {

    @Provides static Lifecycle providesLifecycle(MainActivity activity) {
        return activity.getLifecycle();
    }

    @Binds public abstract MainContract.View provideView(MainActivity activity);

    @Binds public abstract MainContract.Presenter providePresenter(MainPresenter presenter);

    @Binds public abstract MainContract.Usecase provideUsecase(MainUsecase usecase);
}
