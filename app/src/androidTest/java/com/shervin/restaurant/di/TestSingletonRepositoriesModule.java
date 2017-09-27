package com.shervin.restaurant.di;

import com.shervin.restaurant.contract.data.RestaurantDataContract;
import com.shervin.restaurant.repository.RestaurantRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

@Module
public class TestSingletonRepositoriesModule {

    @Provides
    @Singleton
    public RestaurantDataContract.Repository provideRestaurantRepository() {
        return mock(RestaurantRepository.class);
    }
}
