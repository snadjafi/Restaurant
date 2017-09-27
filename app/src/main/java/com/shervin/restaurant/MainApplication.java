package com.shervin.restaurant;

import android.app.Activity;
import android.app.Application;

import com.shervin.restaurant.di.DaggerMainApplicationComponent;
import com.shervin.restaurant.di.MainApplicationComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

public class MainApplication extends Application implements HasActivityInjector {

    @Inject DispatchingAndroidInjector<Activity> dispatchingActivityInjector;
    MainApplicationComponent mainApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDependencyInjector();
        Timber.plant(new Timber.DebugTree());
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private void initDependencyInjector() {
        mainApplicationComponent = buildAppComponent();
        mainApplicationComponent.inject(this);
    }

    protected MainApplicationComponent buildAppComponent() {
        return (MainApplicationComponent) DaggerMainApplicationComponent.builder().create(this);
    }

    public MainApplicationComponent getMainApplicationComponent() {
        return mainApplicationComponent;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingActivityInjector;
    }
}
