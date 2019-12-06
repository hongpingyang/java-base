package com.hong.py;


import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.net.URLCodec;

import java.io.UnsupportedEncodingException;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/9/9 15:55
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/9/9 15:55
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class CommonsCodecTest {

    public static void main(String[] args) throws UnsupportedEncodingException, DecoderException {

        System.out.println("===============base64======================");
        Base64 base64 = new Base64();
        String s = base64.encodeToString("测试22222222222".getBytes());
        System.out.println(s);
        String s1 = new String(base64.decode(s));
        System.out.println(s1);


        System.out.println("===============MD5======================");
        String result = DigestUtils.md5Hex("测试");
        System.out.println(result);


        System.out.println("===============URLCodec======================");
        URLCodec codec = new URLCodec();
        String ss = codec.encode("测试", "utf-8");
        System.out.println(ss);

        String ss1 = codec.decode(ss, "utf-8");
        System.out.println(ss1);


    }
}
