package org.udemy.rx.dev.sec01.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;

public class SubscriberImpl implements Subscriber<String> {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SubscriberImpl.class);
    private Subscription subscription;

    public Subscription getSubscription() {
        return subscription;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
    }

    @Override
    public void onNext(String email) {
        log.info("received email: {}",email);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("error occurred: {}",throwable.getMessage());
    }

    @Override
    public void onComplete() {
        log.info("completed");
    }
}
