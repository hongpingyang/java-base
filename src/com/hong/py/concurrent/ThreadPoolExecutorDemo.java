package com.hong.py.concurrent;

import java.util.List;
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
 *
 *
 * 线程池的设计：加入队列的设计，是为了避免多线程执行时全局锁，当允许的线程数大于核心线程数的后，加入队列不需要全局锁，
 *
 * 对于线程池、包括线程的异常处理推荐一下方式:
 * 1： 直接try/catch
 * 2： 线程直接重写uncaughtException
 * 3： 也可以直接重写protected void afterExecute(Runnable r, Throwable t) { }方法
 *
 * 一般而言:CPU密集型任务应配置尽可能小的线程，如配置N(cpu数)+1个线程的线程池。
 *         IO密集型任务应尽可能配置N(cpu数)*2个线程的线程池
 *
 */
public class ThreadPoolExecutorDemo {

    volatile static int finalI = 0;
    public static void main(String[] args) throws InterruptedException {

        MyThreadPoolExecutor executor = new MyThreadPoolExecutor(5,
                10, 5, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(5),new ThreadPoolExecutor.AbortPolicy());

        PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue();

        System.out.println(Thread.currentThread().getName()+"主线程");

        try {
            for (int i = 0; i < 10; i++) {

                //submit 会吞掉异常，使用  Future去获取异常
                Future<String> submit = executor.submit(() -> {
                    finalI++;
                    Thread.sleep(500);
                    if (finalI == 2) {
                        int i1 = finalI / 0;
                    }
                    System.out.println(Thread.currentThread().getName() + "这是个哈哈线程池");
                    return "哈哈";
                });

                try {
                    String s = submit.get();
                    System.out.println(s);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                //会抛出异常 ，但其它的还能task正常执行
                executor.execute(() -> {
                    try {
                        if (finalI != 7) {
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (finalI == 7) {
                        int i1 = finalI / 0;
                    }
                    System.out.println(Thread.currentThread().getName() + finalI+"这是个线程池");
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


        Thread.sleep(4000);

        executor.shutdown(); //优雅关闭
        List<Runnable> runnables = executor.shutdownNow();//会返回未执行的任务

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
            if (t != null) {
                //有异常得处理异常
            }
            //System.out.println(t.getMessage());
        }

        @Override
        protected void terminated() {
            super.terminated();
            System.out.println(this.getLargestPoolSize());

        }
    }

}

