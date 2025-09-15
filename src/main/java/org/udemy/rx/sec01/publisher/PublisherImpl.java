package org.udemy.rx.sec01.publisher;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;

public class PublisherImpl implements Publisher<String> {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(PublisherImpl.class);

    @Override
    public void subscribe(Subscriber<? super String> subscriber) {
        var subscription = new SubscriptionImpl(subscriber);
        subscriber.onSubscribe(subscription);
    }
}
