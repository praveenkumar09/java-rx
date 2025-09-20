package org.udemy.rx.dev.sec01.publisher;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;

public class SubscriptionImpl implements Subscription {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SubscriptionImpl.class);
    private final Subscriber<? super String> subscriber;
    private final Faker faker;
    private boolean isCancelled = false;
    private static final int MAX_ITEMS = 10;
    int count = 0;

    public SubscriptionImpl(Subscriber<? super String> subscriber) {
        this.subscriber = subscriber;
        this.faker = new Faker();
    }

    @Override
    public void request(long requestedItems) {
        if(isCancelled) return;
        if(requestedItems > MAX_ITEMS) {
            this.subscriber.onError(new RuntimeException("Requested items was greater than max items"));
            isCancelled = true;
            return;
        }
        log.info("subscriber requested {} items",requestedItems);
        for(int i = 0; i<requestedItems && count < MAX_ITEMS; i++) {
            count++;
            subscriber.onNext(this.faker.internet().emailAddress());
        }
        if(count >= MAX_ITEMS) {
            log.info("subscriber has completed");
            this.subscriber.onComplete();
            this.isCancelled = true;
        }
    }

    @Override
    public void cancel() {
        log.info("subscriber has cancelled");
        this.isCancelled = true;
    }
}
