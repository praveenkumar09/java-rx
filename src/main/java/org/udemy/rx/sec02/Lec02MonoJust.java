package org.udemy.rx.sec02;

import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.sec01.subscriber.SubscriberImpl;
import reactor.core.publisher.Mono;

public class Lec02MonoJust {

    private static final Logger log = LoggerFactory.getLogger(Lec02MonoJust.class);

    public static void main(String[] args) {
        Mono<String> sampleMonoString = Mono.just("Hello World!");
        var subscriber = new SubscriberImpl();
        sampleMonoString.subscribe(subscriber);

        //subscriber.getSubscription().request(10);
        subscriber.getSubscription().cancel();
    }
}
