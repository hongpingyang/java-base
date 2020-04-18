package com.hong.py.concurrent;

import java.util.concurrent.*;

/**
 * author: hongpy
 * create time: 2020-04-16 22:32
 * description:
 * life for code
 *
 * RejectedExecutionHandler饱和策略：就是任务数量已经超过最大线程数量了采取的一种措施
 * 1：AbortPolicy：直接抛出异常RejectedExecutionException
 * 2: CallerRunsPolicy:直接调用者的线程执行。
 * 3：DiscardOldestPolicy：丢弃队列里最老的一个任务，并执行当前任务。
 * 4：DiscardPolicy：不处理，直接丢弃。
 *
 * 建议使用有界队列，有界队列能增加系统的稳定性和预警能力，可以根据需要设大一点，比如几千。
 * 有一次我们组使用的后台任务线程池的队列和线程池全满了，不断的抛出抛弃任务的异常，
 * 通过排查发现是数据库出现了问题，导致执行 SQL 变得非常缓慢，
 * 因为后台任务线程池里的任务全是需要向数据库查询和插入数据的，
 * 所以导致线程池里的工作线程全部阻塞住，任务积压在线程池里。
 * 如果当时我们设置成无界队列，线程池的队列就会越来越多，有可能会撑满内存，导致整个系统不可用，而不只是后台任务出现问题。
 * 当然我们的系统所有的任务是用的单独的服务器部署的，而我们使用不同规模的线程池跑不同类型的任务，但是出现这样问题时也会影响到其他任务。
 *
 * submit方法会吃掉异常 execute方法不会
 *
 * 对于线程池、包括线程的异常处理推荐一下方式:
 * 1： 直接try/catch
 * 2： 线程直接重写uncaughtException
 * 3： 也可以直接重写protected void afterExecute(Runnable r, Throwable t) { }方法
 */
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {

        MyThreadPoolExecutor executor = new MyThreadPoolExecutor(5,
                10, 5, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(5),new ThreadPoolExecutor.AbortPolicy());

        System.out.println(Thread.currentThread().getName()+"主线程");

        try {
            for (int i = 0; i < 30; i++) {

                int finalI = i;
                /*executor.submit(() -> {
                    Thread.sleep(500);
                    if (finalI == 22) {
                        int i1 = finalI / 0;
                    }
                    System.out.println(Thread.currentThread().getName() + "这是个线程池");
                    return "哈哈";
                });*/

                executor.execute(() -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (finalI == 7) {
                        int i1 = finalI / 0;
                    }
                    System.out.println(Thread.currentThread().getName() + "这是个线程池");
                    //return "哈哈";
                });

           /* try {
                Object o = result.get();
                System.out.println(o);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }*/
            }
        } catch (RejectedExecutionException ex) {
            System.out.println(ex.getMessage());
        }

        executor.terminated();
    }


    public static class MyThreadPoolExecutor extends ThreadPoolExecutor {


        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue,RejectedExecutionHandler rejectedExecutionHandler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, rejectedExecutionHandler);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            super.beforeExecute(t, r);

        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            //System.out.println(t.getMessage());
        }

        @Override
        protected void terminated() {
            super.terminated();
            System.out.println(this.getLargestPoolSize());

        }
    }

}

