package com.shervin.restaurant.common;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ProductionRxSchedulers implements RxSchedulers {
    @Inject
    public ProductionRxSchedulers() {
    }

    @Override public <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
