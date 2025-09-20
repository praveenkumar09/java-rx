package org.udemy.rx.assignment.sec01;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;

public class PublisherImpl implements Publisher<String> {

    private final static Logger log = org.slf4j.LoggerFactory.getLogger(PublisherImpl.class);

    @Override
    public void subscribe(Subscriber<? super String> subscriber) {
        log.info("publisher subscribe called");
        SubscriptionImpl subscription = new SubscriptionImpl(subscriber);
        subscriber.onSubscribe(subscription);
    }
}
