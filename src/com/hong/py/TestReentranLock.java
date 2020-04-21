package com.hong.py;

import java.util.concurrent.locks.ReentrantLock;

import com.hong.py.pojo.ThreadConditionClass;

/**
 * 公平和非公平锁
 */
public class TestReentranLock {
	
	// true 表示为公平锁，false为非公平锁
    static ReentrantLock lock=new ReentrantLock(true);
    static ThreadConditionClass threadConditionClass=new ThreadConditionClass();
    
    public static void main(String[] args) {
//		for (int i = 0; i <5; i++) {
//			new Thread(new ThreadClass(i, lock)).start();
//		}
		
		 for (int i=0; i<10; i++) {
	            new PutThread("p"+i, i).start();
	            new TakeThread("t"+i).start();
	    }

        Thread.currentThread().interrupt();
	}
    
    static class PutThread extends Thread {
        private int num;
        public PutThread(String name, int num) {
            super(name);
            this.num = num;
        }
        public void run() {
            try {
                Thread.sleep(1);    // �߳�����1ms
                threadConditionClass.put();        // ��BoundedBuffer��д������
            } catch (InterruptedException e) {
            }
        }
    }

    static class TakeThread extends Thread {
        public TakeThread(String name) {
            super(name);
        }
        public void run() {
            try {
                Thread.sleep(10);                    // �߳�����1ms
               threadConditionClass.get();   // ��BoundedBuffer��ȡ������
            } catch (InterruptedException e) {
            }
        }
    }
    
}
