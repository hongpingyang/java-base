package com.hong.py.localLock;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.localLock
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/10/12 19:58
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/10/12 19:58
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/

@Aspect
public class ResubmitDataAspect {

    private final static Object PRESENT = new Object();

    @Around("@annotation(com.hong.py.localLock.Resubmit)")
    public Object handleResubmit(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        //获取注解信息
        Resubmit annotation = method.getAnnotation(Resubmit.class);
        int delaySeconds = annotation.delaySeconds();
        Object[] pointArgs = joinPoint.getArgs();
        String key = "";

        //获取第一个参数
        Object firstParam = pointArgs[0];
        //生成加密参数 使用了content_MD5的加密方式
        key = ResubmitLock.handleKey(firstParam.toString());

        //执行锁
        boolean lock = false;
        try {
            //设置解锁key
            lock = ResubmitLock.getInstance().lock(key, PRESENT);
            if (lock) {
                //放行
                return joinPoint.proceed();
            } else {
                System.out.println("重複提交了");
                //响应重复提交异常
                return null;
            }
        } finally {
            //设置解锁key和解锁时间
            ResubmitLock.getInstance().unLock(lock, key, delaySeconds);
        }
    }
}
