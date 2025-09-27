package org.udemy.rx.dev.sec07.real;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec09Parallel {

    private static final Logger log = LoggerFactory.getLogger(Lec09Parallel.class);

    public static void main(String[] args) {
        log.info("Application started");
        Flux.range(1,10)
                .parallel(3)
                .runOn(Schedulers.boundedElastic())
                .map(Lec09Parallel::process)
                .sequential()
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(3);
    }

    private static int process(int i){
        log.info("Processing {}", i);
        Util.sleep(1);
        return i * 2;
    }

}
