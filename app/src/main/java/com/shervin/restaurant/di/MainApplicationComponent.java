package com.shervin.restaurant.di;

import com.shervin.restaurant.MainApplication;
import com.shervin.restaurant.di.module.ActivityBindingModule;
import com.shervin.restaurant.di.module.ApplicationModule;
import com.shervin.restaurant.di.qualifier.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
        NetworkModule.class,
        AndroidSupportInjectionModule.class})
public interface MainApplicationComponent extends AndroidInjector<MainApplication> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<MainApplication> {
        @Override public abstract MainApplicationComponent build();
    }
}
