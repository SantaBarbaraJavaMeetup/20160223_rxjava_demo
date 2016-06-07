package com.citrix.demo;

import rx.Observable;

public class E_Zip {

    public static void main(String[] args) {
        Observable<String> letters = Observable.just("a");
        Observable<Integer> numbers = Observable.range(1, 3);
        Observable.zip(letters, numbers, (l, n) -> "(" + l + ", " + n + ")").subscribe(new TraceSubscriber<>());
    }

}
