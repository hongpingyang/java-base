package com.hong.py.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.concurrent
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/11/26 10:29
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/11/26 10:29
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public interface ListenableFuture<V> extends Future<V> {

    //添加Runnable、Executor
    void addListener(Runnable listener, Executor executor);

    void addListener(Runnable listener);
}
