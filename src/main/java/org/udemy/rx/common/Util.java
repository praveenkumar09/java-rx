package org.udemy.rx.common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Util {
    private static final Logger log = LoggerFactory.getLogger(Util.class);


    private static final Faker faker = Faker.instance();

    public static <T> Subscriber<T> subscriber(){
        return new DefaultSubscriber<>("");
    }

    public static <T> Subscriber<T> subscriber(String name){
        return new DefaultSubscriber<>(name);
    }

    public static Faker faker(){
        return faker;
    }

    public static void sleep(int seconds){
        try {
            Thread.sleep(Duration.ofSeconds(seconds));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sleepMilliSeconds(int milliSeconds){
        try {
            Thread.sleep(Duration.ofMillis(milliSeconds));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static <T> UnaryOperator<Flux<T>> fluxLogger(String name){
        return flux -> flux
                .doOnSubscribe(s -> log.info("Subscribed to flux: {}", name))
                .doOnCancel(() -> log.info("Flux cancelled: {}", name))
                .doOnComplete(() -> log.info("Flux completed: {}", name));
    }

    public static <T,R> Function<Flux<T>, Flux<R>> fluxMapper(Function<T, R> mapper){
        return flux -> flux.map(mapper);
    }
}
