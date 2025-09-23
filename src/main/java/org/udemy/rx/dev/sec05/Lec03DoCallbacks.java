package org.udemy.rx.dev.sec05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

/*
    do hooks/callbacks
 */
public class Lec03DoCallbacks {

    private static final Logger log = LoggerFactory.getLogger(Lec03DoCallbacks.class);

    public static void main(String[] args) {

        Flux.<Integer>create(fluxSink -> {
                log.info("producer begins");
                for (int i = 0; i < 4; i++) {
                    fluxSink.next(i);
                }
                fluxSink.complete();
                //fluxSink.error(new RuntimeException("oops"));
                log.info("producer ends");
            })
                //after producer send complete signal
            .doOnComplete(() -> log.info("doOnComplete-1"))
                // very first time before anything starts
            .doFirst(() -> log.info("doFirst-1"))
                // emitted after everytime next() method is called
            .doOnNext(item -> log.info("doOnNext-1: {}", item))
                // will be invoke when the subscription object is being passed
            .doOnSubscribe(subscription -> log.info("doOnSubscribe-1: {}", subscription))
                // will be invoke when the request method is being called
            .doOnRequest(request -> log.info("doOnRequest-1: {}", request))
                // will be invoke when the error method is being called
            .doOnError(error -> log.info("doOnError-1: {}", error.getMessage()))
                // will be invoke when the complete or error method is being called
            .doOnTerminate(() -> log.info("doOnTerminate-1")) // complete or error case
                // will be invoke when the cancel method is being called
            .doOnCancel(() -> log.info("doOnCancel-1"))
                // will be invoke when the producer produced the item but subscriber is facing an error or already call cancel i.e when the data is discarded
            .doOnDiscard(Object.class, o -> log.info("doOnDiscard-1: {}", o))
                // will be invoke as the final step irrespective of the reason complete or cancel or error
            .doFinally(signal -> log.info("doFinally-1: {}", signal)) // finally irrespective of the reason
            .take(2)
            .doOnComplete(() -> log.info("doOnComplete-2"))
            .doFirst(() -> log.info("doFirst-2"))
            .doOnNext(item -> log.info("doOnNext-2: {}", item))
            .doOnSubscribe(subscription -> log.info("doOnSubscribe-2: {}", subscription))
            .doOnRequest(request -> log.info("doOnRequest-2: {}", request))
            .doOnError(error -> log.info("doOnError-2: {}", error.getMessage()))
            .doOnTerminate(() -> log.info("doOnTerminate-2")) // complete or error case
            .doOnCancel(() -> log.info("doOnCancel-2"))
            .doOnDiscard(Object.class, o -> log.info("doOnDiscard-2: {}", o))
            .doFinally(signal -> log.info("doFinally-2: {}", signal)) // finally irrespective of the reason
            //.take(4)
            .subscribe(Util.subscriber("subscriber"));


    }

}