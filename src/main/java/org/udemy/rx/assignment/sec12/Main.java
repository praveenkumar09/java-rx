package org.udemy.rx.assignment.sec12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Starting application");
        SlackRoom room = new SlackRoom("reactor");
        SlackMember sam = new SlackMember("sam");
        SlackMember mike = new SlackMember("mike");
        SlackMember jake = new SlackMember("jake");
        room.addMember(sam);
        room.addMember(mike);
        sam.says("Hi all..");
        Util.sleep(4);
        mike.says("Hey!");
        sam.says(" I simply want to say hi to everyone");
        Util.sleep(4);
        room.addMember(jake);
        jake.says("Hi everyone!. Glad to be here!");
        Util.sleep(4);
    }
}
