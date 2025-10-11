package org.udemy.rx.assignment.sec12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public record SlackMember(String memberName) {

    private static Function<String,Sinks.EmitResult> msgOp;

    private static final Logger log = LoggerFactory.getLogger(SlackMember.class);

    public void says(String message){
        log.info("{} says: {}", memberName, message);
        msgOp.apply(message);
    }

    public void setSinkForMessage(Function<String,Sinks.EmitResult> msgSinkOp){
        msgOp = msgSinkOp;
    }


}
