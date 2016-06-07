package com.citrix.demo;

import rx.Observable;
import rx.Subscription;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class B_TimeObservables {

    public static void main(String[] args) throws Exception {
        /**
         * Observable.timer (computation scheduler)
         */
        Observable.timer(1, TimeUnit.SECONDS).subscribe(new TraceSubscriber<>());
        Thread.sleep(2000);

        /**
         * Observable.interval (computation computation)
         */
        Subscription subscription = Observable.interval(1, TimeUnit.SECONDS).subscribe(new TraceSubscriber<>());
        Thread.sleep(5000);
        subscription.unsubscribe();
        Thread.sleep(2000);

        /**
         * Observable.create (provided scheduler)
         */
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);
        Observable.create((s) ->
            executor.scheduleWithFixedDelay(() -> s.onNext(System.currentTimeMillis()), 0, 1, TimeUnit.SECONDS)
        ).subscribe(new TraceSubscriber<>());
    }

}
