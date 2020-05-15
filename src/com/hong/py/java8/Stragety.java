package com.hong.py.java8;

import java.util.Objects;

/**
 * author: hongpy
 * create time: 2020-05-14 23:16
 * description:
 * life for code
 */
@FunctionalInterface
public interface Stragety<T> {

    boolean doAction(T student);


    default Stragety<T> andThen(Stragety<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> doAction(t)&&after.doAction(t);
    }

    default Stragety<T> orThen(Stragety<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> doAction(t)||after.doAction(t);
    }
}
