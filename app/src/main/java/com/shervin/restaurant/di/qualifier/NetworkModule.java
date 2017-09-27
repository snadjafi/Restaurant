package com.shervin.restaurant.di.qualifier;

import android.content.Context;
import android.support.annotation.NonNull;

import com.shervin.restaurant.BuildConfig;
import com.shervin.restaurant.Service;
import com.shervin.restaurant.di.module.ApplicationModule;
import com.squareup.moshi.Moshi;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module(includes = ApplicationModule.class)
public class NetworkModule {

    private static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB

    public NetworkModule() {
    }

    @Provides @Singleton
    OkHttpClient providesOkHttpClient(@AppContext Context context) {

        // Install an HTTP cache in the application cache directory.
        File cacheDir = new File(context.getCacheDir(), "http");
        Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);

        final OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(25, TimeUnit.SECONDS)
                .readTimeout(25, TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS)
                .followRedirects(true)
                .followSslRedirects(true)
                .addInterceptor(buildLoggingInterceptor())
                .cache(cache);


        return builder.build();
    }

    @Provides @Singleton
    Service providesTuroService(Moshi moshi, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.doordash.com")
                .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(Service.class);
    }

    @NonNull
    private Interceptor buildLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return logging;
    }

    @Provides @Singleton Moshi providesMoshi() {
        return new Moshi.Builder().build();
    }
}
