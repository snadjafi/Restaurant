package com.shervin.restaurant.util;

import rx.subscriptions.CompositeSubscription;

public class RxUtil {
    public static CompositeSubscription getNewCompositeSubIfUnsubscribed(CompositeSubscription subscription) {
        if (subscription == null || subscription.isUnsubscribed()) {
            return new CompositeSubscription();
        }

        return subscription;
    }
}
