package com.hong.py.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

public class ListenableFutureTask<V> extends FutureTask<V>
        implements ListenableFuture<V> {

    private final ExecutionList executionList = new ExecutionList();

    //这个是执行生成client
    public static <V> ListenableFutureTask<V> create(Callable<V> callable) {
        return new ListenableFutureTask<V>(callable);
    }

    //这个是执行生成client
    public static <V> ListenableFutureTask<V> create(
            Runnable runnable, V result) {
        return new ListenableFutureTask<V>(runnable, result);
    }

    //这个是执行生成client
    ListenableFutureTask(Callable<V> callable) {
        super(callable);
    }
    //这个是执行生成client
    ListenableFutureTask(Runnable runnable, V result) {
        super(runnable, result);
    }


    //添加监听和线程执行Executor
    @Override
    public void addListener(Runnable listener, Executor exec) {
        executionList.add(listener, exec);
    }

    //添加监听
    @Override
    public void addListener(Runnable listener) {
        executionList.add(listener, null);
    }

    /**
     * 执行listener监听
     */
    @Override
    protected void done() {
        executionList.execute();
    }
}