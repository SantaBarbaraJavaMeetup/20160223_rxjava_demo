package com.citrix.demo;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class C_ThreadBoundTask {

    public static void main(String[] args) throws Exception {
        /**
         * Synchronous
         */
        Observable.create((s) -> {
            s.onNext(executeThreadBoundTask());
            s.onCompleted();
        }).subscribe(new TraceSubscriber<>());

        /**
         * Asychronous (explicit)
         */
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Observable.create((s) -> {
            executor.execute(() -> {
                s.onNext(executeThreadBoundTask());
                s.onCompleted();
            });
        }).subscribe(new TraceSubscriber<>());
        Thread.sleep(2000);

        /**
         * Asychronous (provided)
         */
        Observable.create((s) -> {
            s.onNext(executeThreadBoundTask());
            s.onCompleted();
        }).subscribeOn(Schedulers.io()).subscribe(new TraceSubscriber<>());
        Thread.sleep(2000);

        /**
         * Asychronous (implicit)
         */
        Observable.create((s) -> {
            s.onNext(executeThreadBoundTask());
            s.onCompleted();
        }).subscribeOn(Schedulers.io()).subscribe(new TraceSubscriber<>());
        Thread.sleep(2000);
    }

    private static String executeThreadBoundTask() {
        try { Thread.sleep(1000); } catch (InterruptedException ignore) {}
        return "abc";
    }

}
