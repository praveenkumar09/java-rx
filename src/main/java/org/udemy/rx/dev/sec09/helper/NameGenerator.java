package org.udemy.rx.dev.sec09.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NameGenerator {
    private static final Logger log = LoggerFactory.getLogger(NameGenerator.class);
    private final List<String> names = new ArrayList<>();

    public Flux<String> generateNames(){
         return Flux.generate(sink -> {
             log.info("Generating names");
             Util.sleep(1);
             String name = Util.faker().name().firstName();
             names.add(name);
             sink.next(name);
         })
                 .startWith(names)
                 .cast(String.class);
    }

}
