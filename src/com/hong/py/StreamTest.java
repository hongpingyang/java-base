package com.hong.py;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.IntStream;

public class StreamTest {

    public static void main(String[] args) {
        IntStream.Builder builder = IntStream.builder();
        builder.add(20);
        builder.add(13);
        builder.add(-2);
        builder.add(18);
        IntStream stream = builder.build();

        IntStream stream2=IntStream.builder()
                .add(20)
                .add(13)
                .add(-2)
                .add(18)
                .build();

        IntStream stream3=IntStream.builder()
                .add(20)
                .add(13)
                .add(-2)
                .add(18)
                .build();

        IntStream stream4=IntStream.builder()
                .add(20)
                .add(13)
                .add(-2)
                .add(18)
                .build();

        System.out.println(stream.max().getAsInt());
        System.out.println(stream2.sum());
        System.out.println(stream3.count());
        System.out.println(stream4.average());


        Collection books=new HashSet();
        books.add("spring");
        books.add("spring-mvc");
        books.add("mybatis");
        books.add("springboot");
        books.add("springCloud");
        books.add("高数");

        System.out.println(books.stream().filter(p->p.toString().contains("spring")).count());



    }
}
