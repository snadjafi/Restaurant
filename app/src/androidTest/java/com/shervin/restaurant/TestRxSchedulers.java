package com.shervin.restaurant;

import com.shervin.restaurant.common.RxSchedulers;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class TestRxSchedulers implements RxSchedulers {

    @Inject
    public TestRxSchedulers() {
    }

    @Override public <T> Observable.Transformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}