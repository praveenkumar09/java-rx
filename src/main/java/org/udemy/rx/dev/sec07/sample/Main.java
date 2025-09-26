package org.udemy.rx.dev.sec07.sample;

import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.SynchronousSink;

import java.time.Duration;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        //Consumer<SynchronousSink<Object>> fluxSinkConsumer = (val) -> val.next(Util.faker().name().fullName());
        NameService nameServiceGenerator = new NameService();
        Flux<String> stringFlux = Flux.create(nameServiceGenerator).share()
                .take(10)
                .delayElements(Duration.ofSeconds(5));
        stringFlux
                .subscribe(Util.subscriber("subscriber-1"));
        stringFlux
                .subscribe(Util.subscriber("subscriber-2"));
        for(int i=0; i<10; i++){
            nameServiceGenerator.generateName();
        }
        Util.sleep(100);
    }
}
