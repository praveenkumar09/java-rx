package org.udemy.rx.sec02;

import org.slf4j.Logger;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class Lec08MonoFromFuture {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Lec08MonoFromFuture.class);

    public static void main(String[] args) {
         Mono.fromFuture(Lec08MonoFromFuture::getName)
                 .subscribe(Util.subscriber("subscriber-1"));
         Util.sleep(5);
         log.info("main finished");

    }

    private static CompletableFuture<String> getName(){
        return CompletableFuture.supplyAsync(() ->{
            log.info ("Generating name");
            return Util.faker().name().fullName();
        });
    }
}
