package com.citrix.demo;

import rx.Observable;
import rx.schedulers.Schedulers;

public class D_MoreAsync {

    public static void main(String[] args) throws Exception {
        /**
         * Observable.just (async)
         */
        Observable.just("a", "b", "c").subscribeOn(Schedulers.computation()).subscribe(new TraceSubscriber<>());
        Thread.sleep(1000);

        /**
         * Observable.create (async)
         */
        Observable.<String>create((s) -> {
            s.onNext("a");
            s.onNext("b");
            s.onNext("c");
            s.onCompleted();
        }).subscribeOn(Schedulers.computation()).subscribe(new TraceSubscriber<>());
        Thread.sleep(1000);

        /**
         * Observable.range (async)
         */
        Observable.range(0, 10, Schedulers.computation()).subscribe(new TraceSubscriber<>());
        Thread.sleep(1000);
    }

}
