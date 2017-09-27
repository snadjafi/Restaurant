package com.shervin.restaurant.di;

import android.content.Context;

import com.shervin.restaurant.MainApplication;
import com.shervin.restaurant.TestRxSchedulers;
import com.shervin.restaurant.common.RxSchedulers;

import dagger.Module;
import dagger.Provides;

@Module
public class TestApplicationModule {
    private MainApplication app;

    public TestApplicationModule(MainApplication app) {
        this.app = app;
    }

    @Provides
    public Context providesAppContext() {
        return app;
    }

    @Provides public static RxSchedulers providesRxSchedulers() {
        return new TestRxSchedulers();
    }
}