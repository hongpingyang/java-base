package com.hong.py.localLock;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.localLock
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/10/12 19:34
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/10/12 19:34
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 * 利用本地ConcurrentHashMap做容器和定时任务实现多少时间内不允许重复提交
 **/
public class ResubmitLock {

    private static final ConcurrentHashMap<String, Object> LOCK_CACHE =
            new ConcurrentHashMap<>(200);
    private static final ScheduledThreadPoolExecutor EXECUTOR =
            new ScheduledThreadPoolExecutor(5, new ThreadPoolExecutor.DiscardPolicy());

    private static ResubmitLock instance;
    private  ResubmitLock()
    {

    }

    public static ResubmitLock getInstance() {

        if (instance == null) {
            instance = new ResubmitLock();
        }
        return instance;
    }

    public static String handleKey(String param) {
        return DigestUtils.md5Hex(param == null ? "" : param);
    }

    /**
     * 加锁 putIfAbsent 是原子操作保证线程安全
     *
     * @param key   对应的key
     * @param value
     * @return
     */
    public boolean lock(final String key, Object value) {
        return Objects.isNull(LOCK_CACHE.putIfAbsent(key, value));
    }

    /**
     * 延时释放锁 用以控制短时间内的重复提交
     *
     * @param lock         是否需要解锁
     * @param key          对应的key
     * @param delaySeconds 延时时间
     */
    public void unLock(final boolean lock, final String key, final int delaySeconds) {
        if (lock) {
            EXECUTOR.schedule(() -> {
                LOCK_CACHE.remove(key);
            }, delaySeconds, TimeUnit.SECONDS);
        }
    }

}
