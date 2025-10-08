package org.udemy.rx.dev.sec12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class Lec02SinkOneEmitVal {
    private static final Logger log = LoggerFactory.getLogger(Lec02SinkOneEmitVal.class);

    public static void main(String[] args) {
        log.info("Starting application");
        demo4();
    }

    private static void demo1() {
        Sinks.One<String> sink = Sinks.one();
        sink.tryEmitValue("Hello World");
        Mono.from(sink.asMono())
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(3);
    }

    private static void demo2() {
        Sinks.One<Object> sink = Sinks.one();
        Mono<Object> mono = sink.asMono();
        mono.subscribe(Util.subscriber("subscriber-1"));
        sink.tryEmitEmpty();
    }

    private static void demo3() {
        Sinks.One<Object> sink = Sinks.one();
        Mono<Object> mono = sink.asMono();
        mono.subscribe(Util.subscriber("subscriber-1"));
        sink.tryEmitError(new RuntimeException("Error"));
    }

    private static void demo4() {
        Sinks.One<Object> sink = Sinks.one();
        Mono<Object> mono = sink.asMono();
        mono.subscribe(Util.subscriber("sam"));
        sink.emitValue("Hello World!",
                ((signalType, emitResult) -> {
                    log.info("SignalType: {}, EmitResult: {}",
                            signalType.name(),
                            emitResult.name());
                    return emitResult.isSuccess();
                }));
    }
}
