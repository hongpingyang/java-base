package com.hong.py;


import org.apache.http.impl.execchain.TunnelRefusedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class JConsoleDemo {

    static class OOMObject{
        public byte[] placeHolder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }

        System.gc();
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        //fillHeap(1000);
        testThread();
    }

    public static  void  createBusyThread(){
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                }
            }
        },"testBusyThread");
        thread.start();
    }

    public static  void  createLockThread(final Object lock){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {

                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "testlockThread");
        thread.start();
    }

    public static  void testThread() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
        br.readLine();
        Object obj = new Object();
        createLockThread(obj);
    }


}
