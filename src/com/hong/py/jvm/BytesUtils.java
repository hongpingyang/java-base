package com.hong.py.jvm;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.jvm
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2020/3/3 11:08
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2020/3/3 11:08
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2020 hongpy Technologies Inc. All Rights Reserved
 **/
public class BytesUtils {

    //
    public static byte[]  int2bytes(int v, int len) {
        byte[] bytes = new byte[len];
        for (int i = 0; i < len; i++) {
            bytes[i]=(byte)(v>>>((len-i-1)*8));
        }
        return bytes;
       /* b[off+3]=(byte)v; //低8位
        b[off+2]=(byte)(v >>> 8); //
        b[off+1]=(byte)(v >>> 16); //
        b[off + 0] = (byte) (v >>> 24); //高8位*/
    }

    public static int bytes2int(byte[] b, int start,int len) {
        int end=start+len;
        int sum=0;
        for (int i = start; i < end; i++) {
            int value=(b[i]& 0xFF);
            sum+= (value<<(--len)*8);
        }
        return sum;
    }

    public static String byte2String(byte[] b, int start, int len) {
        return new String(b, start, len);
    }

    public static byte[] String2Byte(String str) {
        return str.getBytes();
    }

    public static byte[] replaceByte(byte[] oldByte, int start, int oldlen, byte[] replaceByte) {

        byte[] newbyte=new byte[oldByte.length+replaceByte.length-oldlen];
        //oldByte的start前半部分拷贝到
        System.arraycopy(oldByte,0,newbyte,0,start);//start前面
        //start前面到replaceByte长度之间的
        System.arraycopy(replaceByte,0,newbyte,start,replaceByte.length);
        //oldByte的后半部分拷贝到
        System.arraycopy(oldByte,start+oldlen,newbyte,start+replaceByte.length,oldByte.length-start-oldlen);

        return newbyte;
    }

}
