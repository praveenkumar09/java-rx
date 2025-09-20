package org.udemy.rx.assignment.sec02.main;

import org.slf4j.Logger;
import org.udemy.rx.assignment.sec02.impl.FileServiceImpl;
import org.udemy.rx.common.Util;

public class MainAssignment01 {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(MainAssignment01.class);

    public static void main(String[] args) {
        new FileServiceImpl()
                .read("/Users/praveenkumarthangaraj/Documents/JavaCourse/ReactiveProgramming/java-rx/src/main/java/org/udemy/rx/sec02/assignment/resources/Readfile.txt").subscribe(
                        i -> log.info("Received value : {}",i),
                        err -> log.error("error",err),
                        () -> log.info("completed"));
        Util.sleep(10);

        new FileServiceImpl()
                .read("/Users/Documents/JavaCourse/ReactiveProgramming/java-rx/src/main/java/org/udemy/rx/sec02/assignment/resources/Readfile.txt").subscribe(
                        i -> log.info("Received value : {}",i),
                        err -> log.error("error",err),
                        () -> log.info("completed"));
        Util.sleep(10);
    }
}
