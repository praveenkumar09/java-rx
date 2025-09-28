package org.udemy.rx.dev.sec09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import org.udemy.rx.dev.sec09.helper.NameGenerator;

public class Lec02StartWithUseCase {

    private static final Logger log = LoggerFactory.getLogger(Lec02StartWithUseCase.class);

    public static void main(String[] args) {
        log.info("Starting application");
        NameGenerator generator = new NameGenerator();
        generator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("sam"));

        generator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("mike"));

        generator.generateNames()
                .take(3)
                .subscribe(Util.subscriber("jake"));
    }

}
