package com.hong.py.pojo;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

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

/**
 * 这里的关键区别是我们如何处理序列化过程。
 * 当类实现java.io.Serializable接口时，JVM完全负责序列化类实例。
 * 在Externalizable的情况下，程序员应该负责整个序列化和反序列化过程
 *
 */
public class User implements Externalizable {

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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
}
