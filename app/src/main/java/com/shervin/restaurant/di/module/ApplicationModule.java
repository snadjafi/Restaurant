package com.shervin.restaurant.di.module;

import android.content.Context;

import com.shervin.restaurant.MainApplication;
import com.shervin.restaurant.common.ProductionRxSchedulers;
import com.shervin.restaurant.common.RxSchedulers;
import com.shervin.restaurant.di.qualifier.AppContext;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ApplicationModule {
    @Binds
    @AppContext
    public abstract Context providesAppContext(MainApplication app);

    @Binds public abstract RxSchedulers providesRxSchedulers(ProductionRxSchedulers app);
}
