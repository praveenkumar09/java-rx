package org.udemy.rx.dev.sec04.helper;

import org.udemy.rx.common.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NameGeneratorConstructorExec implements Consumer<FluxSink<String>> {

    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        stringFluxSink.next(Util.faker().country().name());
    }
}