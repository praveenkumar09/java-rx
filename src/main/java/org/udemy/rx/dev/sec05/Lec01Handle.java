package org.udemy.rx.dev.sec05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

public class Lec01Handle {
    private static final Logger log = LoggerFactory.getLogger(Lec01Handle.class);

    public static void main(String[] args) {
        log.info("Application started");
        Flux<Integer> range = Flux.range(1, 10);
        range
                .handle((integer, sink) -> {
                    if(integer ==1){
                        sink.next(-2);
                    }else if(integer == 7){
                        sink.error(new RuntimeException("Received 7 -> Error occurred"));
                    }else if (integer == 4){

                    }else{
                        sink.next(integer);
                    }
                })
                .subscribe(Util.subscriber("subscriber-1"));
    }
}
