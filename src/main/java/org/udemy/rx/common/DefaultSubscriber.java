package org.udemy.rx.common;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSubscriber<T> implements Subscriber<T> {

    private static final Logger log = LoggerFactory.getLogger(DefaultSubscriber.class);
    private String name;

    public DefaultSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        log.info("Subscription received, name : {}",this.name);
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T t) {
        log.info("Received item: {} ,name : {}",t, this.name);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("Error occurred: {}, name : {}",throwable.getStackTrace(),this.name);
    }

    @Override
    public void onComplete() {
        log.info("Completed : name : {}",this.name);
    }
}
