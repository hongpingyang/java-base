package com.hong.py.jvm;

import com.hong.py.MyClassLoader;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.jvm
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2020/3/3 10:52
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2020/3/3 10:52
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2020 hongpy Technologies Inc. All Rights Reserved
 **/
public class DoHackByteCodeReplace {

    private final int[] CONSTANT_LENGTH={-1,-1,-1,5,5,9,9,3,3,5,5,5,5};

    /**
     * 替换字节码文件
     * @param args
     * @throws IOException
     * @throws NoSuchMethodException
     */
    public static void main(String[] args) throws IOException, NoSuchMethodException {
        System.out.println(new File(".").getAbsolutePath());
        InputStream fileInputStream = new FileInputStream("src/com/hong/py/jvm/HackByteCodeReplaceDemo.class");
        byte[] bytecode = new byte[fileInputStream.available()];
        fileInputStream.read(bytecode);
        fileInputStream.close();
        MyClassLoader2 myClassLoader1 = new MyClassLoader2();
        Class aClassOLD = myClassLoader1.loadByte(bytecode);
        try {
            Method main = aClassOLD.getMethod("main", new Class<?>[]{ String[].class});
            main.invoke(null, new String[]{null});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //HackSystem2 hackSystem2=new HackSystem2();
        //HackSystem2.println();

        DoHackByteCodeReplace replace = new DoHackByteCodeReplace();
        byte[] bytes = replace.ModifyUTF8Content(bytecode, "com/hong/py/jvm/HackSystem", "com/hong/py/jvm/HackSystem2");
        MyClassLoader2 myClassLoader2 = new MyClassLoader2();
        Class aClass = myClassLoader2.loadByte(bytes);
        try {
            Method main = aClass.getMethod("main", new Class<?>[]{ String[].class});
            main.invoke(null, new String[]{null});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * 替换了常量表里的UTF8Content
     * @param bytecode
     * @param oldStr
     * @param newStr
     * @return
     */
    public byte[] ModifyUTF8Content(byte[] bytecode ,String oldStr, String newStr) {
        byte[] newbytecode=new byte[bytecode.length];
        int consontLength=BytesUtils.bytes2int(bytecode,8,2);
        int offest=10;
        for (int i = 0; i < consontLength; i++) {
            int tag = BytesUtils.bytes2int(bytecode, offest, 1); //标志位
            if(tag==1) //CONSTANT_Utf8_info
            {
                int len=BytesUtils.bytes2int(bytecode,offest+1,2);//长度位
                offest+=3;
                String content = BytesUtils.byte2String(bytecode, offest, len);
                if (content.equals(oldStr)) {
                     byte[] newStrbyte=BytesUtils.String2Byte(newStr);
                     byte[] newStrbytelen=BytesUtils.int2bytes(newStr.length(),2);

                     newbytecode=BytesUtils.replaceByte(bytecode,offest-2,2,newStrbytelen);
                     newbytecode=BytesUtils.replaceByte(newbytecode,offest,len,newStrbyte);
                    return  newbytecode;
                } else {
                    offest+=len;
                }
            }
            else
            {
                offest+=CONSTANT_LENGTH[tag];
            }
        }
        return  newbytecode;
    }
}
