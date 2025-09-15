package org.udemy.rx.sec01;

import org.udemy.rx.sec01.publisher.PublisherImpl;
import org.udemy.rx.sec01.subscriber.SubscriberImpl;

import java.time.Duration;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        demo4();
    }

    private static void demo1(){
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
    }

    private static void demo2() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());
    }

    private static void demo3() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());
    }

    private static void demo4() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(11);
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(3);
        Thread.sleep(Duration.ofSeconds(2).toMillis());
    }
}
