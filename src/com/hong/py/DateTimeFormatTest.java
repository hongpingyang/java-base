package com.hong.py;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatTest {

    public static void main(String[] args) {

        DateTimeFormatter[] formatters=new DateTimeFormatter[]{
            DateTimeFormatter.ISO_LOCAL_DATE,
                DateTimeFormatter.ISO_LOCAL_TIME,
                DateTimeFormatter.ISO_LOCAL_DATE_TIME,
                DateTimeFormatter.ofPattern("Gyyyy%%MMM%%dd HH:mm:ss")
        };

        LocalDateTime dateTime=LocalDateTime.now();

        for (int i = 0; i < formatters.length; i++) {
            System.out.println(dateTime.format(formatters[i]));
            System.out.println(formatters[i].format(dateTime));
        }

        String timeStr="2014==06==19 10时43分09秒";
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy==MM==dd HH时mm分ss秒");
        LocalDateTime date=LocalDateTime.parse(timeStr,formatter);
        System.out.println(date);

    }

}
