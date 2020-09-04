package com.hong.py;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CollectionTest {

    public static void main(String[] args) {

        Collection books=new HashSet();
           books.add("spring");
           books.add("spring-mvc");
           books.add("mybatis");
           books.add("springboot");
           books.add("springCloud");
           books.add("高数");

        books.forEach(p-> System.out.println(p));

        LinkedHashSet linkedBooks=new LinkedHashSet();
        linkedBooks.add("spring");
        linkedBooks.add("spring-mvc");
        linkedBooks.add("mybatis");
        linkedBooks.add("springboot");
        linkedBooks.add("springCloud");
        linkedBooks.add("高数");

        linkedBooks.forEach(p-> System.out.println(p));

        ArrayList<String> list = new ArrayList<>();
        list.add("曹操");
        list.add("刘备");
        list.add("孙权");
        list.add("诸葛亮");
        list.add("司马懿");
        list.add("吕布");


        Iterator iterator = books.iterator();
        while (iterator.hasNext())
        {
            String book= (String) iterator.next(); //只能向后遍历

            if(book.equals("高数"))
            {
                iterator.remove();
            }
        }

        ListIterator<String> stringListIterator = list.listIterator();

        while (stringListIterator.hasNext()) {
            String book=stringListIterator.next();
            System.out.println(book);
        }

        while (stringListIterator.hasPrevious()) {
            String book=stringListIterator.previous();
            System.out.println(book);
        }



        System.out.println(books);
        Iterator iterator1 = books.iterator();
        //遍历的Iterator的只能遍历一遍，如果还是使用上面的iterator会导致没有输出。
        iterator1.forEachRemaining(p-> System.out.println(p));

        books.removeIf(els->(((String)els).contains("-")));
        System.out.println(books);


        boolean empty = CollectionUtils.isEmpty(books);
        System.out.println(empty);

        Collection mybatis = CollectionUtils.select(books, p -> {
            return (p.toString().indexOf("s")!=-1);//p.equals("mybatis");
        });
        System.out.println(mybatis);

        /*
         * 集合元素按照给定的格式Transformer进行转化         *
         */
         System.out.println("============ CollectionUtils.transform ==============");
         List<String> clist = new ArrayList<String>();
         clist.add("20170927101124");
         CollectionUtils.transform(clist,new Transformer(){
                SimpleDateFormat fmt =new SimpleDateFormat("yyyyMMddhhmmss");
                 @Override
                 public String transform(Object arg0) {
                        try {
                                return fmt.parse((String)arg0).toString();
                             } catch (ParseException e) {
                                 e.printStackTrace();
                             }
                     return null;
                     }});
         System.out.println(clist);

    }
}
