package org.udemy.rx.assignment.sec01;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;

public class SubscriberImpl implements Subscriber<String> {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SubscriberImpl.class);

    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        log.info("Subscription received");
    }

    @Override
    public void onNext(String s) {
        log.info("Received item: {} ",s);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("Error occurred: {}",throwable.getMessage());
    }

    @Override
    public void onComplete() {
        log.info("Completed");
    }

    public Subscription getSubscription() {
        return subscription;
    }
}
