package com.hong.py.pojo;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: spring-base
 * @Package: com.hong.spring.pojo
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/9/10 20:15
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/9/10 20:15
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class User {

    private String userName;
    private String nickName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
