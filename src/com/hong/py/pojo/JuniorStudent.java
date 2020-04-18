package com.hong.py.pojo;

/**
 * author: hongpy
 * create time: 2020-04-18 16:46
 * description:
 * life for code
 */
public class JuniorStudent extends Student implements Comparable<JuniorStudent> {

    @Override
    public int compareTo(JuniorStudent o) {
        if(o.getAge()>this.getAge())
            return 1;
        if(o.getAge()<this.getAge())
            return -1;
        return 0;
    }
}
