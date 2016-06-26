package com.github.s0nerik.rxbus;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

public class RxBus {
    private static PublishSubject<Object> subject = PublishSubject.create();

    public static <T> Observable<T> on(final Class<T> type) {
        return subject.filter(new Func1<Object, Boolean>() {
            @Override
            public Boolean call(Object o) {
                return o != null && type.isAssignableFrom(o.getClass());
            }
        }).cast(type);
    }

    public static void post(Object event) {
        subject.onNext(event);
    }
}