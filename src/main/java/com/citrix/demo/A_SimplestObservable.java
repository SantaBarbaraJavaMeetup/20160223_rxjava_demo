package com.citrix.demo;

import rx.Observable;
import rx.Subscriber;

public class A_SimplestObservable {

    public static void main(String[] args) {
        /**
         * Observable.just
         */
        Observable<String> observable = Observable.just("a", "b", "c");

        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext: " + s);
            }
        });

        /**
         * Observable.create
         */
        Observable.<String>create((s) -> {
            s.onNext("a");
            s.onNext("b");
            s.onNext("c");
            s.onCompleted();
        }).subscribe(System.out::println);

        /**
         * Observable.range
         */
        Observable.range(0, 10).subscribe(System.out::println);
    }

}
