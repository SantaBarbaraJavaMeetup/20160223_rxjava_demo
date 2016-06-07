package com.citrix.demo;

import rx.Observable;

import java.util.concurrent.TimeUnit;

public class G_CombineLatest {

    public static void main(String[] args) throws Exception {
        Observable<Long> seq1 = Observable.interval(500, TimeUnit.MILLISECONDS);
        Observable<Long> seq2 = Observable.interval(1, TimeUnit.SECONDS);
        Observable.combineLatest(seq1, seq2, (l, r) -> "(" + l + ", " + r + ")").subscribe(new TraceSubscriber<>());
        Thread.sleep(5000);
    }

}
