package com.shervin.restaurant;

import com.shervin.restaurant.common.RxSchedulers;

import rx.Observable;
import rx.schedulers.Schedulers;

public class UnitTestingRxSchedulers implements RxSchedulers {
    @Override
    public <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.immediate())
                .observeOn(Schedulers.immediate());
    }
}
