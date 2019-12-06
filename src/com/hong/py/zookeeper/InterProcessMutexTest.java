package com.hong.py.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.zookeeper
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/11/25 16:03
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/11/25 16:03
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 *
 * public void acquire()throws Exception 获取锁，若失败则阻塞等待直到成功，支持重入
 * public boolean acquire(long time, TimeUnit unit) throws Exception 超时获取锁，超时失败
 * public void release() throws Exception  释放锁
 * 详细的见源码
 **/
public class InterProcessMutexTest {

    //创建连接实体
    private static CuratorFramework client=null;

    public static void main(String[] args) {
        CuratorBase base=new CuratorBase();
        client=base.curatorClient();
        client.start();
        //可重入非公平互斥锁
        InterProcessMutex lock=new InterProcessMutex(client,"/lock");
        for (int i = 0; i < 10; i++) {
            new Thread(new ThreadTest(i,lock)).start();
        }
    }

    private static class ThreadTest implements Runnable {
        private Integer threadFlag;
        private InterProcessMutex lock;
        private boolean haslock=false;
        public ThreadTest(Integer threadFlag, InterProcessMutex lock) {
            this.threadFlag = threadFlag;
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                System.out.println("第" + threadFlag + "线程开始获取锁");
                haslock=lock.acquire(30000, TimeUnit.MILLISECONDS);
                if(haslock) {
                    System.out.println("第" + threadFlag + "线程获取到了锁");
                }
                else {
                    System.out.println("第" + threadFlag + "线程没有获取到了锁");
                }
                //等到1秒后释放锁
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    if(haslock)
                     lock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
