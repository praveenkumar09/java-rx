package org.udemy.rx.dev.sec04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.assignment.sec01.SubscriberImpl;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

public class Lec04FluxCreateDownstreamDemand {

    private static final Logger log = LoggerFactory.getLogger(Lec04FluxCreateDownstreamDemand.class);

    public static void main(String[] args) {
        log.info("Main method started");
        //produceEarly();
        produceOnDemand();
    }

    private static void produceEarly() {
        SubscriberImpl subscriber = new SubscriberImpl();
        Flux.<String>create(sink -> {
            for(int i=0; i<10; i++){
                var name = Util.faker().name().firstName();
                sink.next(name);
                log.info("Generated name: {}",name);
            }
            sink.complete();
        })
                .subscribe(subscriber);

        subscriber.getSubscription().request(2);
        subscriber.getSubscription().request(2);
        subscriber.getSubscription().cancel();
    }

    private static void produceOnDemand(){
        SubscriberImpl subscriber = new SubscriberImpl();
        Flux<String> stringFlux = Flux.<String>create(sink -> {
            sink.onRequest(req -> {
                for (int i = 0; i < req && !sink.isCancelled(); i++) {
                    var name = Util.faker().name().firstName();
                    log.info("Generated name: {}", name);
                    sink.next(name);
                }
            });
        });
        stringFlux
                .subscribe(subscriber);
        //stringFlux.subscribe(Util.subscriber("subscriber-2"));
        //stringFlux.subscribe(Util.subscriber("subscriber-3"));
        subscriber.getSubscription().request(2);
        subscriber.getSubscription().request(2);
        subscriber.getSubscription().cancel();
    }

}
