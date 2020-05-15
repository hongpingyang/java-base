package com.hong.py.java8;

import com.hong.py.pojo.Student;

import java.util.Objects;

/**
 * author: hongpy
 * create time: 2020-05-14 23:00
 * description:
 * life for code
 */
@FunctionalInterface
public interface AppleStragety {

    boolean doAction(Student student);

    default AppleStragety andThen(AppleStragety after) {
        Objects.requireNonNull(after);
        return (Student t) -> doAction(t)&&after.doAction(t);
    }
}
