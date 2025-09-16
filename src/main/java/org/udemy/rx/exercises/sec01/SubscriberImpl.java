package org.udemy.rx.exercises.sec01;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;

public class SubscriberImpl implements Subscriber<String> {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SubscriberImpl.class);

    @Override
    public void onSubscribe(Subscription subscription) {
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
}
