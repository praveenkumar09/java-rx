package org.udemy.rx.dev.sec10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec01Buffer {
    private static final Logger log = LoggerFactory.getLogger(Lec01Buffer.class);

    public static void main(String[] args) {
        demo4();
        Util.sleep(20);
    }


    private static void demo1(){
        eventNameStream()
                .buffer()
                .subscribe(Util.subscriber("subscriber-1"));
    }

    private static void demo2(){
        eventNameStream()
                .buffer(3)
                .subscribe(Util.subscriber("subscriber-1"));
    }

    private static void demo3(){
        eventNameStream()
                .buffer(Duration.ofMillis(200))
                .subscribe(Util.subscriber("subscriber-1"));
    }

    private static void demo4(){
        eventNameStream()
                .bufferTimeout(3,Duration.ofMillis(1000))
                .subscribe(Util.subscriber("subscriber-1"));
    }


    private static Flux<String> eventNameStream() {
        return Flux.interval(Duration.ofMillis(200))
                .take(10)
                .concatWith(Flux.never())
                .map(i -> "Event Name : " + Util.faker().name().firstName());
    }
}
