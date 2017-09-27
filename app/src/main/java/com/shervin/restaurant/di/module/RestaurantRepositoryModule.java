package com.shervin.restaurant.di.module;

import com.shervin.restaurant.contract.data.RestaurantDataContract;
import com.shervin.restaurant.datasource.RestaurantLocalDataSource;
import com.shervin.restaurant.datasource.RestaurantRemoteDataSource;
import com.shervin.restaurant.repository.RestaurantRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Reusable;

@Module
public abstract class RestaurantRepositoryModule {
    @Binds @Reusable abstract RestaurantDataContract.Repository providerepository(RestaurantRepository repository);

    @Binds @Reusable abstract RestaurantDataContract.LocalDataSource provideLocalDataSource(RestaurantLocalDataSource localDataSource);

    @Binds @Reusable abstract RestaurantDataContract.RemoteDataSource provideRemoteDataSource(RestaurantRemoteDataSource remoteDataSource);
}
