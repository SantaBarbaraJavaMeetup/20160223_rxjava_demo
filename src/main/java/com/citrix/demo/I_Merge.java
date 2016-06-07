package com.citrix.demo;

import rx.Observable;

import java.util.concurrent.TimeUnit;

public class I_Merge {

    public static void main(String[] args) throws Exception {
        Observable<String> seq1 = Observable.interval(500, TimeUnit.MILLISECONDS).map((n) -> "x");
        Observable<String> seq2 = Observable.interval(1, TimeUnit.SECONDS).map((n) -> "y");
        Observable.merge(seq1, seq2).subscribe(new TraceSubscriber<>());
        Thread.sleep(5000);
    }

}
