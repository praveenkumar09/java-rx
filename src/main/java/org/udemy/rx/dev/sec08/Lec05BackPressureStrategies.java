package org.udemy.rx.dev.sec08;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

public class Lec05BackPressureStrategies {

    private static final Logger log = LoggerFactory.getLogger(Lec05BackPressureStrategies.class);

    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small","16");
        Flux<Integer> flux = Flux.create(sink -> {
                    for (int i = 0; i <= 500 && !sink.isCancelled(); i++) {
                        log.info("generating state : {}", i);
                        sink.next(i);
                        Util.sleepMilliSeconds(50);
                    }
                    sink.complete();
                }, FluxSink.OverflowStrategy.BUFFER)
                .subscribeOn(Schedulers.parallel())
                .cast(Integer.class);

        flux
                //Buffer Strategy - keep in memory and release to subscriber
                //.onBackpressureBuffer()
                //Error strategy send error to downstream and cancel upstream
                //.onBackpressureError()
                //Fixed size buffer strategy - keep in memory and release to subscriber
                // if buffer gets more than the size mentioned, then throw error
                //.onBackpressureBuffer(10)
                //Drop Strategy - drop the items from upstream if the producer produces
                //more than the subscriber can handle
                //.onBackpressureDrop()
                //Latest Strategy - keep only the latest item from upstream
                //.onBackpressureLatest()
                .limitRate(1)
                .publishOn(Schedulers.boundedElastic())
                .map(Lec05BackPressureStrategies::timeConsumingOperation)
                .subscribe();
        Util.sleep(60);

    }

    private static int timeConsumingOperation(int i) {
        log.info("Processing {}", i);
        Util.sleep(1);
        return i;
    }

}
