package com.citrix.demo;

import rx.Observable;
import rx.observables.ConnectableObservable;
import rx.schedulers.Schedulers;

public class J_Simulation {

    public static void main(String[] args) throws Exception {
        long time = System.currentTimeMillis();

        ConnectableObservable<String> emailObs = Observable.<String>create((s) -> {
            System.out.println("[" + Thread.currentThread() + "] email");
            s.onNext(loadEmail());
            s.onCompleted();
        }).subscribeOn(Schedulers.io()).publish();

        Observable<String> nameObs = emailObs.flatMap((e) -> Observable.<String>create((s) -> {
            System.out.println("[" + Thread.currentThread() + "] name");
            s.onNext(loadName(e));
            s.onCompleted();
        }).subscribeOn(Schedulers.io()));

        Observable<String> tzObs = emailObs.flatMap((e) -> Observable.<String>create((s) -> {
            System.out.println("[" + Thread.currentThread() + "] tz");
            s.onNext(loadTimezone(e));
            s.onCompleted();
        }).subscribeOn(Schedulers.io()));

        Observable.zip(emailObs, nameObs, tzObs, Person::new).subscribe((p) -> {
            System.out.println("person=" + p + ", duration=" + (System.currentTimeMillis() - time));
        });

        emailObs.connect();

        Thread.sleep(5000);
    }

    private static String loadEmail() {
        delay(1000);
        return "user@test.net";
    }

    private static String loadName(String email) {
        delay(1000);
        return "John Doe";
    }

    private static String loadTimezone(String email) {
        delay(1000);
        return "America/Los_Angeles";
    }

    private static void delay(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ignore) {}
    }

    static class Person {
        final String email;
        final String name;
        final String timeZone;

        public Person(String email, String name, String timeZone) {
            this.email = email;
            this.name = name;
            this.timeZone = timeZone;
        }

        @Override
        public String toString() {
            return "email=" + email + ", name=" + name + ", tz=" + timeZone;
        }
    }

}
