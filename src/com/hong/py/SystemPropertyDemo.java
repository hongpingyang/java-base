package com.hong.py;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/8/22 16:35
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/8/22 16:35
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class SystemPropertyDemo {
    public static void main(String[] args) {
        String log_file = System.getProperty("user.dir") ;
        System.out.println(log_file);
        System.getProperties().list(System.out);
    }
}
