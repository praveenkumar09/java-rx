package org.udemy.rx.dev.sec04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.ArrayList;
import java.util.List;

public class Lec03FluxSinkExample {
    private static final Logger log = LoggerFactory.getLogger(Lec03FluxSinkExample.class);

    public static void main(String[] args) {
        List<String> countries = new ArrayList<>();
        log.info("Main method started");
        Flux.create(sink -> {
            Runnable runnable = () -> {
                for (int i = 0; i < 1000; i++) {
                    sink.next(Util.faker().country().name());
                }
            };
            for (int i = 0; i < 10; i++) {
                Thread.ofVirtual().start(runnable);
            }
        })
                .subscribe(val -> {
                    log.info("got val : {}", val);
                    countries.add(val.toString());
                });
        Util.sleep(5);
        log.info("countries size: {}",countries.size());
    }
}
