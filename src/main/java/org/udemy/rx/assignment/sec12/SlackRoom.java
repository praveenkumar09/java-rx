package org.udemy.rx.assignment.sec12;

import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;

public record SlackRoom(String roomName) {
    private static final Logger log = LoggerFactory.getLogger(SlackRoom.class);

    private static final List<SlackMember> member =
            new ArrayList<>();

    private static final Sinks.Many<Object> sink = Sinks
            .many()
            .replay()
            .all();

    private static final Flux<Object> flux = sink.asFlux();

    public void addMember(SlackMember slackMember){
        log.info("Adding member {} to room {}", slackMember.memberName(), roomName);
        flux.subscribe(Util.subscriber(slackMember.memberName()));
        member.add(slackMember);
    }

    public static Sinks.Many<Object> getSink(){
        return sink;
    }
}
