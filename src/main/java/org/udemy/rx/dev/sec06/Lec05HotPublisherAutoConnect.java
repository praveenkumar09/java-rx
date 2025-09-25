package org.udemy.rx.dev.sec06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05HotPublisherAutoConnect {
    private static final Logger log = LoggerFactory.getLogger(Lec05HotPublisherAutoConnect.class);

    public static void main(String[] args) {
        log.info("Hot Publisher With AutoConnect Demo");
        Flux<String> movieStream = movieStream().publish().autoConnect();
        Util.sleep(2);
        movieStream
                .take(2)
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(2);
        movieStream
                .take(2)
                .subscribe(Util.subscriber("subscriber-2"));
        Util.sleep(10);
    }

    private static Flux<String> movieStream() {
        return Flux
                .generate(
                        () -> {
                            log.info("received request");
                            return 1;
                        },
                        (counter, sink) -> {
                            log.info("playing movie scene : {}", counter);
                            sink.next("movie-" + counter);
                            return counter + 1;
                        },
                        (counter) -> log.info("completed counter : {}", counter)
                )
                .take(10)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }
}
