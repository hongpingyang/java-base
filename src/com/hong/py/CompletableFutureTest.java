package com.hong.py;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/12/11 14:46
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/12/11 14:46
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        //TestGet();
        //TestAsync();
        //TestWhenComplete();
        //TestThenApply();
        //TestHandler();
        //TestThenAccept();
        //TestThenCompose();
        //TestThenCombine();
        //TestAllOf();
        TestAnyOf();
    }

    public static void TestGet() {
        CompletableFuture<String> uCompletableFuture = CompletableFuture.supplyAsync(() -> {
            ThreadUtils.sleep(500);
            return "Hello world";
        });

        //get为阻塞方法
        //String s = uCompletableFuture.get();
        //如果时间设置的小于500，会抛出异常
        //String s = uCompletableFuture.get(600, TimeUnit.MILLISECONDS);
        //立即获取返回结果，没有获取到返回默认值
        String s = uCompletableFuture.getNow("this is a default value");
        System.out.println(s);
    }

    public static void TestAsync() throws ExecutionException, InterruptedException {
         CompletableFuture<Void> completableFuture=CompletableFuture.runAsync(()->{
             System.out.println("this is a runable");
         });
         CompletableFuture<String> completableFuture1=CompletableFuture.supplyAsync(()->{
             System.out.println("this is a Callable");
             return "Hello world";
         });

         //输出null
        System.out.println(completableFuture.get());
        //输入Hello world
        System.out.println(completableFuture1.get());

    }

    /**
     * 当CompletableFuture的计算结果完成，或者抛出异常的时候，我们可以执行特定的Action。
     * 使用whenComplete：不影响最终的get()方法返回值。
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void TestWhenComplete() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture=CompletableFuture.supplyAsync(()->{
            return "Hello world";
        }).whenComplete((v,e)->{
            v+="!";
            System.out.println(v); //输出Hello world!
            System.out.println(e); //输出null
        });

        //输出 Hello world
        System.out.println(completableFuture.get());

        CompletableFuture<String> completableFuture1=CompletableFuture.supplyAsync(()->{
            int i=10/0;
            return "Hello world 2";
        }).whenComplete((v,e)->{
            v+="!";
            System.out.println(v); //输出 null!
            System.out.println(e); //输出java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
        }).exceptionally((e)->{
            System.out.println(e.getMessage()); //输出java.lang.ArithmeticException: / by zero
            return "Exception";
        });

        //输出 Exception
        System.out.println(completableFuture1.get());
    }

    /**
     * thenApply 是在计算结果后紧接着进行一个action，可以执行多个
     * 会影响到get的返回值
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void TestThenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture=CompletableFuture.supplyAsync(()->{
            return "Hello world";
        }).thenApply((a)->{
            System.out.println(a); //输出Hello world
            return a+="!";
        }).thenApply((a)->{
            System.out.println(a); //输出Hello world!
            return a+="!";
        }).thenApply((a)->{
            System.out.println(a); //输出Hello world!!
            return a+="!";
        });

        //输出Hello world!!!
        System.out.println(completableFuture.get());
    }

    /**
     * handle 相当于WhenComplete和thenApply
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void TestHandler() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture=CompletableFuture.supplyAsync(()->{
            return "Hello world";
        }).handle((v,e)->{
            v+="!";
            System.out.println(e); //输出null
            return v;
        });

        //输出 Hello world!
        System.out.println(completableFuture.get());
    }

    /**
     * thenAccept单纯的去消费结果，没有返回值
     * thenAcceptBoth会多接受一个CompletionStage的返回值
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void TestThenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture= CompletableFuture.supplyAsync(()->
                "Hello world"
        ).thenAccept(x->{
            System.out.println(x); //输出Hello world
        }).thenAcceptAsync(x->{
            System.out.println(x); //上一级没有返回值输出null
        });

        //没有返回值输出 null
        System.out.println(completableFuture.get());

        CompletableFuture<Void> completableFuture1= CompletableFuture.supplyAsync(()->
                "Hello world"
        ).thenAcceptBoth(CompletableFuture.supplyAsync(()->"Hello hpy"),(a,b)->{
            System.out.println(a); //输出Hello world
            System.out.println(b); //输出Hello hpy
        });

        //没有返回值输出 null
        System.out.println(completableFuture1.get());
    }


    /**
     * thenCompose 接受上一级的返回值，并返回一个全新的CompletableFuture
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void TestThenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture= CompletableFuture.supplyAsync(()->{
              return "Hello world";
         }).thenApply((a)->{
            System.out.println(a); //输出 Hello world
            a+="!";
            return a;
        }).thenCompose((a)->{
            final String b=a+"!";
            return CompletableFuture.supplyAsync(()->{
               return b;
            });
        });

        //输出 Hello world!!
        System.out.println(completableFuture.get());
    }

    //thenCombine 和 supplyAsync 不一定哪个先哪个后，是并行执行的。
    public static void TestThenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture= CompletableFuture.supplyAsync(()->{
            System.out.println("Hello world");
            return "Hello world";
        }).thenApply((a)->{
            a+="!";
            return a;
        }).thenCombine(CompletableFuture.supplyAsync(()->{
            System.out.println("Hello hpy");
            return "Hello hpy";
        }).thenApply((a)->{
            a+="!";
            return a;
        }),(a,b)->{
            return a+b;
        });
        //输出Hello world!Hello hpy!
        System.out.println(completableFuture.get());
    }

    /**
     * allOf等全部执行完毕才去执行
     */
    public static void TestAllOf() throws ExecutionException, InterruptedException {

        CompletableFuture<Void> completableFuture= CompletableFuture.allOf(
                CompletableFuture.supplyAsync(()->{
                    ThreadUtils.sleep(2000);
                    System.out.println("Hello world");
                    return "Hello world";
        }),     CompletableFuture.supplyAsync(()->{
                    ThreadUtils.sleep(100);
                    System.out.println("Hello hpy");
                    return "Hello hpy";
        }));

        //等全部执行完毕才去执行 输出null
        System.out.println(completableFuture.get());

    }

    /**
     * anyOf有一个执行完毕才就去执行
     */
    public static void TestAnyOf() throws ExecutionException, InterruptedException {

        CompletableFuture<Object> completableFuture= CompletableFuture.anyOf(
                CompletableFuture.supplyAsync(()->{
                    ThreadUtils.sleep(2000);
                    System.out.println("Hello world");
                    return "Hello world";
                }),     CompletableFuture.supplyAsync(()->{
                    ThreadUtils.sleep(100);
                    System.out.println("Hello hpy");
                    return "Hello hpy";
                }));

        //有一个执行完毕才就去执行 输出Hello hpy
        System.out.println(completableFuture.get());

    }


}


