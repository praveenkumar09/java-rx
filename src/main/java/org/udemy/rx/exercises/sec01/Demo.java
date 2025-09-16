package org.udemy.rx.exercises.sec01;

public class Demo {
    public static void main(String[] args) {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        var subscription = new SubscriptionImpl(subscriber);
        subscription.request(10);
        subscription.cancel();
        subscription.request(10);
    }
}
