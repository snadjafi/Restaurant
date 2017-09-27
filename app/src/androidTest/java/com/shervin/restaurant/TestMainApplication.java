package com.shervin.restaurant;

import com.shervin.restaurant.di.DaggerTestMainApplicationComponent;
import com.shervin.restaurant.di.TestApplicationModule;
import com.shervin.restaurant.di.TestMainApplicationComponent;

public class TestMainApplication extends MainApplication {

    @Override protected TestMainApplicationComponent buildAppComponent() {
        return DaggerTestMainApplicationComponent
                .builder()
                .appModule(new TestApplicationModule(this))
                .build();
    }
}
