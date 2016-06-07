package com.citrix.demo;

import rx.Observable;

public class H_Concat {

    public static void main(String[] args) {
        Observable<String> letters = Observable.just("a", "b", "c");
        Observable<String> numbers = Observable.range(1, 3).map(Long::toString);
        Observable.concat(letters, numbers).subscribe(new TraceSubscriber<>());
    }

}
