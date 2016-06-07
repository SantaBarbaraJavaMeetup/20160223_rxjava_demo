package com.citrix.demo;

import rx.Subscriber;

public class TraceSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
        System.out.println("[" + Thread.currentThread() +"] onCompleted");
    }

    @Override
    public void onError(Throwable e) {
        System.out.println("[" + Thread.currentThread() +"] onError");
    }

    @Override
    public void onNext(T t) {
        System.out.println("[" + Thread.currentThread() +"] onNext: " + t);
    }
}
