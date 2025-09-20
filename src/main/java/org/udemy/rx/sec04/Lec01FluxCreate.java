package org.udemy.rx.sec04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxCreate {

    private static final Logger log = LoggerFactory.getLogger(Lec01FluxCreate.class);

    public static void main(String[] args) {
        log.info("Main method started");
        Flux.create(fluxSink -> {
           for(int i=0; i<10; i++){
               fluxSink.next(Util.faker().country().name());
               if(i==9){
                   fluxSink.complete();
               }
           }
        })
                .subscribe(Util.subscriber("subscriber-1"));
    }
}
