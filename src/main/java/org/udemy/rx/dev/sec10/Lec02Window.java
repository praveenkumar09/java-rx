package org.udemy.rx.dev.sec10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Duration;

public class Lec02Window {
    private static final Logger log = LoggerFactory.getLogger(Lec02Window.class);

    public static void main(String[] args) {
        log.info("Starting application");
        eventNameStream()
                .window(Duration.ofMillis(600))
                        .flatMap(Lec02Window::processEvents)
                .subscribe();
        Util.sleep(10);
    }


    private static Flux<String> eventNameStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> "Event Name : " + (i+1));
    }


    private static Mono<Void> processEvents(Flux<String> flux) {
        Path path = null;
        try {
            path = Files.createFile(Path.of("src/main/resources/sec10/" + Util.faker().random().hex() + ".txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Path finalPath = path;
        return flux
                .doOnNext(e -> {
                    try {
                        Files.writeString(finalPath, e + "\n", StandardOpenOption.APPEND);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .doOnComplete(System.out::println)
                .then();

    }
}
