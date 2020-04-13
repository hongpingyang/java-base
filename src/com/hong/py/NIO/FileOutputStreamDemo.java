package com.hong.py.NIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.NIO
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2020/4/1 17:04
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2020/4/1 17:04
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2020 hongpy Technologies Inc. All Rights Reserved
 **/
public class FileOutputStreamDemo {



    public void test04() throws IOException {

        byte[] bytes = {12,21,34,11,21};

        FileOutputStream fileOutputStream = new FileOutputStream(new File("").getAbsolutePath()+"/io/test.txt");
        // 写入二进制文件，直接打开会出现乱码
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

}
