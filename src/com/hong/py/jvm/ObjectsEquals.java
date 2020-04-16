package com.hong.py.jvm;

import java.util.Objects;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.jvm
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2020/4/15 14:40
 * @Version: 1.0
 * ////////////////////////////////////////////////////////////////////
 * //                          _ooOoo_                               //
 * //                         o8888888o                              //
 * //                         88" . "88                              //
 * //                         (| ^_^ |)                              //
 * //                         O\  =  /O                              //
 * //                      ____/`---'\____                           //
 * //                    .'  \\|     |//  `.                         //
 * //                   /  \\|||  :  |||//  \                        //
 * //                  /  _||||| -:- |||||-  \                       //
 * //                  |   | \\\  -  /// |   |                       //
 * //                  | \_|  ''\---/''  |   |                       //
 * //                  \  .-\__  `-`  ___/-. /                       //
 * //                ___`. .'  /--.--\  `. . ___                     //
 * //              ."" '<  `.___\_<|>_/___.'  >'"".                  //
 * //            | | :  `- \`.;`\ _ /`;.`/ - ` : | |                 //
 * //            \  \ `-.   \_ __\ /__ _/   .-` /  /                 //
 * //      ========`-.____`-.___\_____/___.-`____.-'========         //
 * //                           `=---='                              //
 * //      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //
 * //            佛祖保佑       永不宕机     永无BUG                  //
 * ////////////////////////////////////////////////////////////////////
 * <p>
 * Copyright © 2020 hongpy Technologies Inc. All Rights Reserved
 **/
public class ObjectsEquals {

    public static void main(String[] args) {

        int a=12345;
        short b=(short)12345;
        int c=12345;

        Integer d=12345;
        Integer e=12345;

        Object f=Short.valueOf((short)12345);
        Object g=Integer.valueOf(12345);


        System.out.println(a==b);//true
        System.out.println(a==c);//true
        System.out.println(d==e);//false

        //因为 Objects.equals 如果两个参数包装类型不一致，就是false
        // 一个的包装数据类型 Short，另一个的包装数据类型 Integer，所以最终的比较结果必然是false
        System.out.println(Objects.equals(a,b));//false

        System.out.println(g==f);  //false
    }


}
