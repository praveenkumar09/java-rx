package org.udemy.rx.assignment.sec03;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;

public class StockSubscriber implements Subscriber<String> {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(StockSubscriber.class);
    private static int BALANCE = 1000;
    private static int STOCK_QTY = 0;
    private Subscription subscriptionOb = null;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscriptionOb = subscription;
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(String s) {
        log.info("Received item: {} ",s);
        int stockPrice = Integer.parseInt(s);
        int STOCK_BUY_PRICE = 90;
        int STOCK_SELL_PRICE = 110;
        if(stockPrice < STOCK_BUY_PRICE && BALANCE >= stockPrice){
            log.info("Stock price is below 90, so we will buy a stock");
            BALANCE -= stockPrice;
            STOCK_QTY++;
            log.info("Balance after purchase: {}: Stock Qty : {}",BALANCE,STOCK_QTY);
        }else if(stockPrice > STOCK_SELL_PRICE && STOCK_QTY > 0){
            BALANCE = BALANCE + (STOCK_QTY * stockPrice);
            STOCK_QTY = 0;
            log.info("Final Balance after sell: {}",BALANCE);
            subscriptionOb.cancel();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("Error occurred: {}",throwable.getMessage());
    }

    @Override
    public void onComplete() {
        log.info("Completed");
    }
}
