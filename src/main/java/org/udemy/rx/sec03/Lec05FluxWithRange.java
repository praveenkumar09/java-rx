package org.udemy.rx.sec03;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

public class Lec05FluxWithRange {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Lec05FluxWithRange.class);
    private static final Faker faker = Faker.instance();

    public static void main(String[] args) {
        Flux.range(0,5)
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(5);
        Flux.range(1,10)
                .map(i -> faker.name().fullName())
                .subscribe(val -> log.info("got val : {}", val));
    }

}
