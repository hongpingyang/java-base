package com.hong.py.multithreading;

import java.util.concurrent.Semaphore;


/**
*
 我们提供一个类：

 class FooBar {
 public void foo() {
 for (int i = 0; i < n; i++) {
 print("foo");
 }
 }

 public void bar() {
 for (int i = 0; i < n; i++) {
 print("bar");
 }
 }
 }
 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。

 请设计修改程序，以确保 "foobar" 被输出 n 次。



 示例 1:

 输入: n = 1
 输出: "foobar"
 解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
 示例 2:

 输入: n = 2
 输出: "foobarfoobar"
 解释: "foobar" 将被输出两次。
 *
 */
public class Day1 {

        private volatile   int n;

        Semaphore foo = new Semaphore(1);
        Semaphore bar = new Semaphore(0);
        Semaphore boy = new Semaphore(0);

        public Day1(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {

                // printFoo.run() outputs "foo". Do not change or remove this line.
                foo.acquire();
                printFoo.run();
                bar.release();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {

                // printBar.run() outputs "bar". Do not change or remove this line.
                bar.acquire();
                printBar.run();
                boy.release();
            }
        }

       public void boy(Runnable printBoy) throws InterruptedException {

           for (int i = 0; i < n; i++) {

               // printBoy.run() outputs "boy". Do not change or remove this line.
               boy.acquire();
               printBoy.run();
               foo.release();
          }
     }
}
