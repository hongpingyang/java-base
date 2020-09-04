package com.hong.py.java8;

import com.hong.py.pojo.Student;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * author: hongpy
 * create time: 2020-05-14 21:49
 * description:
 * life for code
 */
public class SellApple {

    private static void prettyPrintApple(List<Student> students,AppleStragety appleStragety) {
        for (Student student :
                students) {
            if (appleStragety.doAction(student)) {
                System.out.println(student);
            }
        }
    }

    private static <T> void prettyPrint(List<T> students,Stragety<T> stragety) {
        for (T student : students) {
            if (stragety.doAction(student)) {
                System.out.println(student);
            }
        }
    }

    private static <T> void prettyPrint(List<T> students) {
        for (T student : students) {
                System.out.println(student);
        }
    }

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        Student student=new Student("hongdadiao",18);
        students.add(student);
        Student student1=new Student("hongdadiao1",22);
        students.add(student1);
        Student student2=new Student("hongdadiao2",26);
        students.add(student2);
        Student student3=new Student("hongdadiao3",30);
        students.add(student3);

        //匿名类
        prettyPrintApple(students, new AppleStragety() {
            @Override
            public boolean doAction(Student student) {
                return student.getAge()>=30;
            }
        });

        //lambada
        prettyPrintApple(students,(Student stu)->stu.getAge()<=18);

        //泛型匿名类
        prettyPrint(students, new Stragety<Student>() {
            @Override
            public  boolean doAction(Student student) {
                return student.getAge()>=30;
            }
        });

        //泛型lambada
        prettyPrint(students,(Student stu)->stu.getAge()<=18);
        //谓词符合
        Stragety<Student> studentStragety=(Student stu)->stu.getAge()<=18;

        System.out.println("==========");
        prettyPrint(students,studentStragety.orThen((Student stu)->stu.getName().contains("2")));
        System.out.println("==========");

        //匿名类排序
        students.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.getAge() < o2.getAge()) {
                    return -1;
                } else if (o1.getAge() > o2.getAge()) {
                    return 1;
                }
                return 0;
            }
        });

        Collections.sort(students,new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                if (o1.getAge() < o2.getAge()) {
                    return -1;
                } else if (o1.getAge() > o2.getAge()) {
                    return 1;
                }
                return 0;
            }
        });

        prettyPrint(students);

        //lambda排序
        students.sort((Student o1, Student o2)->{
            if (o1.getAge() < o2.getAge()) {
              return 1;
            }
             else if (o1.getAge() > o2.getAge()){
               return -1;
            }
            return 0;
        });


        //lambda排序
        students.sort((Student o1, Student o2)->{
            return o1.getAge().compareTo(o2.getAge());
        });
        //类型推断
        students.sort((o1, o2)->{
            return o1.getAge().compareTo(o2.getAge());
        });

        // 提取一个Comparatorstudents
        students.sort(Comparator.comparing((Student o1)-> o1.getAge()));
        students.sort(Comparator.comparing(Student::getAge));
        //逆序
        students.sort(Comparator.comparing(Student::getAge).reversed());
        //年龄一样时，按照name排序
        students.sort(Comparator.comparing(Student::getAge).thenComparing(Student::getName));
        prettyPrint(students);

        Runnable stringRunnable =new Runnable() {
            @Override
            public void run() {

            }
        };

        Runnable stringRunnable1 =()->{ };

        Callable<String> stringCallable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "this is a callable";
            }
        };

        Callable<String> stringCallable1 = () -> "this is a callable";

        //lambda
        List<String> strings = Arrays.asList("a", "b", "c", "d");
        strings.sort((s1,s2)->s1.compareToIgnoreCase(s2));

        //方法引用
        strings.sort(String::compareToIgnoreCase);

        //lamba
        Function<String,Integer> stringtoInteger=(str)->Integer.parseInt(str);
        //方法引用
        Function<String,Integer> stringtoInteger1=Integer::parseInt;


        //Supplier 改成成方法引用 无参构造
        Supplier<Student> c1 = () -> new Student();
        Supplier<Student> c2 = Student::new;
        c1.get();
        c2.get();

        //有参构造  如果构造参数超过3个，jdk没有提供，需要自己定义一个函数式接口,
        BiFunction<String,Integer,Student> stringIntegerStudentBiFunction=Student::new;
        Student hongpy = stringIntegerStudentBiFunction.apply("hpy", 18);
        System.out.println(hongpy);



    }
}
