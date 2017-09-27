package com.shervin.restaurant.common;

import rx.Observable;

public interface RxSchedulers {

    <T> Observable.Transformer<T, T> applySchedulers();
}
