package com.hong.py.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.concurrent
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/11/26 10:56
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/11/26 10:56
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class NameThreadFactory implements ThreadFactory {


    protected static final AtomicInteger POOL_SEQ = new AtomicInteger(1);

    protected final AtomicInteger mThreadNum = new AtomicInteger(1);

    protected final String mPrefix;

    protected final boolean mDaemon;

    protected final ThreadGroup mGroup;

    public NameThreadFactory() {
        this("pool-" + POOL_SEQ.getAndIncrement(), false);
    }

    public NameThreadFactory(String prefix) {
        this(prefix, false);
    }

    public NameThreadFactory(String prefix, boolean daemon) {
        mPrefix = prefix + "-thread-";
        mDaemon = daemon;
        SecurityManager s = System.getSecurityManager();
        mGroup = (s == null) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
    }

    @Override
    public Thread newThread(Runnable runnable) {
        String name=(mPrefix+mThreadNum.getAndIncrement());
        Thread thread = new Thread(mGroup, runnable, name, 0);
        thread.setDaemon(mDaemon);
        return thread;
    }


}
