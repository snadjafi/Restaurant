package com.shervin.restaurant.di;

import com.shervin.restaurant.MainApplication;
import com.shervin.restaurant.contract.data.RestaurantDataContract;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        TestApplicationModule.class,
        TestSingletonRepositoriesModule.class,
        TestActivityBindingModule.class,
        AndroidSupportInjectionModule.class
})
public interface TestMainApplicationComponent extends MainApplicationComponent {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<MainApplication> {
        public abstract Builder appModule(TestApplicationModule module);
        @Override public abstract TestMainApplicationComponent build();
        @Override public void seedInstance(MainApplication instance) {
            appModule(new TestApplicationModule(instance));
        }
    }


    RestaurantDataContract.Repository restaurantRepository();
}
