package com.hong.py.localLock;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.localLock
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/10/12 20:21
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/10/12 20:21
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class SubmitMethod {

    @Resubmit(delaySeconds = 10)
    public String submit(String name) {
        System.out.println("提交了:"+name);
        return "提交了:"+name;
    }
}
