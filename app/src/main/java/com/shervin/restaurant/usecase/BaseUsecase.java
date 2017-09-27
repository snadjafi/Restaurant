package com.shervin.restaurant.usecase;

import android.support.annotation.CallSuper;
import android.support.annotation.VisibleForTesting;

import com.shervin.restaurant.common.RxSchedulers;
import com.shervin.restaurant.util.RxUtil;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseUsecase {

    private CompositeSubscription subscriptions = new CompositeSubscription();
    @Inject RxSchedulers schedulers;

    @VisibleForTesting
    public void setRxSchedulers(RxSchedulers rxSchedulers) {
        this.schedulers = rxSchedulers;
    }

    protected <E> void execute(Observable<E> useCaseObservable, Subscriber useCaseSubscriber) {
        subscriptions = RxUtil.getNewCompositeSubIfUnsubscribed(subscriptions);
        Subscription subscription = useCaseObservable.compose(schedulers.applySchedulers())
                        .subscribe(useCaseSubscriber);

        subscriptions.add(subscription);
    }

    @CallSuper
    public void clear() {
        subscriptions.clear();
    }
}
