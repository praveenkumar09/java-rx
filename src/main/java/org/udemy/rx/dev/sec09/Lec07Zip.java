package org.udemy.rx.dev.sec09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import org.udemy.rx.dev.sec09.car.BodyFrame;
import org.udemy.rx.dev.sec09.car.Car;
import org.udemy.rx.dev.sec09.car.Engine;
import org.udemy.rx.dev.sec09.car.Wheels;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec07Zip {

    private static final Logger log = LoggerFactory.getLogger(Lec07Zip.class);

    public static void main(String[] args) {
        log.info("Starting application");
        Flux.zip(getCarBody(),
                getCarEngine(),
                getCarWheels())
                .transform(Util.fluxMapper(
                        t -> new Car(t.getT1(),
                                t.getT2(),
                                t.getT3())
                ))
                .subscribe(Util.subscriber("subscriber-1"));

        Util.sleep(5);
    }

    private static Flux<BodyFrame> getCarBody(){
        return Flux.range(1,5)
                .transform(Util.fluxMapper(
                        i -> new BodyFrame(Util.faker().color().name(),i)
                ))
                .delayElements(Duration.ofMillis(100));
    }

    private static Flux<Engine> getCarEngine(){
        return Flux.range(1,3)
                .transform(Util.fluxMapper(
                        i -> new Engine(Util.faker().color().name(),i)
                ))
                .delayElements(Duration.ofMillis(200));
    }

    private static Flux<Wheels> getCarWheels(){
        return Flux.range(1,10)
                .transform(Util.fluxMapper(
                        i -> new Wheels(Util.faker().color().name(),i)
                ))
                .delayElements(Duration.ofMillis(75));
    }


}
