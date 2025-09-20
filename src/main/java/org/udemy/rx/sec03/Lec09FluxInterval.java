package org.udemy.rx.sec03;

import org.slf4j.Logger;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import com.github.javafaker.Faker;

import java.time.Duration;

public class Lec09FluxInterval {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Lec09FluxInterval.class);
    private static final Faker FAKER = Util.faker();


    public static void main(String[] args) {
        Flux.interval(Duration.ofSeconds(1))
                .map(i -> "message-"+i+"-" + FAKER.name().fullName())
                .subscribe(Util.subscriber("subscriber-1"));

        Util.sleep(7);
    }
}
