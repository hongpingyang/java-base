package com.hong.py.jvm;

public class Overload {

    static abstract class  Human{

    }

    static class Man extends Human {

    }

    static class Woman extends Human {

    }

    public void sayHello(Human guy) {
        System.out.println("hello,guy");
    }

    public void sayHello(Man guy) {
        System.out.println("hello,guy");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello,guy");
    }


    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();

    }
}
