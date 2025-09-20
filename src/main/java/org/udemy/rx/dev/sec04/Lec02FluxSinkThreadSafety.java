package org.udemy.rx.dev.sec04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import org.udemy.rx.dev.sec04.helper.NameGenerator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.ArrayList;
import java.util.List;

public class Lec02FluxSinkThreadSafety {

    private static final Logger log = LoggerFactory.getLogger(Lec02FluxSinkThreadSafety.class);

    public static void main(String[] args) {
        addItemToListViaNormalThread();
        addItemToListViaFluxSink();
    }

    private static void addItemToListViaNormalThread(){
        log.info("addItemToListViaNormalThread called");
        List<String> list = new ArrayList<>();
        Runnable runnable = () -> {
            for(int i=0; i<1000; i++){
                list.add(Util.faker().country().name());
            }
        };
        for(int i=0; i<10; i++){
            Thread.ofVirtual().start(runnable);
        }
        Util.sleep(5);
        log.info("list size: {}",list.size());
    }

    private static void addItemToListViaFluxSink(){
     log.info("addItemToListViaFluxSink called");
     List<String> list = new ArrayList<>();
     NameGenerator nameGenerator = new NameGenerator();
     Flux.create(nameGenerator)
             .subscribe(list::add);
     Runnable runnable = () -> {
         for(int i=0; i<1000; i++){
             nameGenerator.generateCountryName();
         }
     };
     for(int i=0; i<10; i++){
         Thread.ofVirtual().start(runnable);
     }
     Util.sleep(5);
     log.info("list size: {}",list.size());
    }
}
