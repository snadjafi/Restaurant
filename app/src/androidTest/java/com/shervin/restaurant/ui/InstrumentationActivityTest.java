package com.shervin.restaurant.ui;

import android.support.test.InstrumentationRegistry;

import com.shervin.restaurant.TestMainApplication;
import com.shervin.restaurant.di.TestMainApplicationComponent;

public class InstrumentationActivityTest {
    TestMainApplication getApp() {
        return (TestMainApplication) InstrumentationRegistry.getInstrumentation().getTargetContext()
                .getApplicationContext();
    }

    TestMainApplicationComponent getAppComponent() {
        return ((TestMainApplicationComponent) getApp().getMainApplicationComponent());
    }
}
