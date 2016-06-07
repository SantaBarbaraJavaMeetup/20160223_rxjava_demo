package com.citrix.demo;

import rx.Observable;

import java.util.concurrent.TimeUnit;

public class F_Amb {

    public static void main(String[] args) throws Exception {
        Observable<String> letters = Observable
                .timer((int)(Math.random() * 1000), TimeUnit.MILLISECONDS)
                .flatMap((n) -> Observable.just("a", "b", "c"));
        Observable<Integer> numbers = Observable
                .timer((int)(Math.random() * 1000), TimeUnit.MILLISECONDS)
                .flatMap((n) -> Observable.range(1, 3));
        Observable.amb(letters, numbers).subscribe(new TraceSubscriber<>());
        Thread.sleep(3000);
    }

}
