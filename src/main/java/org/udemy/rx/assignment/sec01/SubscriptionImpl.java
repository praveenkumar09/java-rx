package org.udemy.rx.assignment.sec01;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;


public class SubscriptionImpl implements Subscription {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SubscriptionImpl.class);
    private final Subscriber<? super String> subscriber;
    private final Faker faker = Faker.instance();
    private boolean cancelled = false;

    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void request(long l) {
        log.info("request called : {}",l);
        if(cancelled) return;
        for (long i = 0; i < l; i++) {
            subscriber.onNext(faker.idNumber().ssnValid());
        }
        subscriber.onComplete();
    }

    @Override
    public void cancel() {
        log.info("subscriber has cancelled");
        this.cancelled = true;
    }
}
