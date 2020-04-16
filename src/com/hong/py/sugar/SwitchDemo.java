package com.hong.py.sugar;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.sugar
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2020/4/15 10:38
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
public class SwitchDemo {

    /**
     * switch支持String，本质是先 hashcode (int类型)比较,因为不同字符串hashcode可能相同，
     * 故里面还会用equals来判断是否相同。
     * @param args
     */
    public static void main(String[] args) {

        String str1 = "world";
        System.out.println(str1.hashCode());
        String str2 = new String("world");
        System.out.println(str2.hashCode());
        String str3 = "hello";
        System.out.println(str3.hashCode());

        SwitchDemo demo = new SwitchDemo();
        demo.testSwitch(str1);
        demo.testSwitch(str2);
        demo.testSwitch(str3);


    }

    public void testSwitch(String str) {

        switch (str) {
            case "hello":
                System.out.println("hello");
                break;

            case "world":
                System.out.println("world");
                break;

            default:
                System.out.println("default");
                break;
        }
    }
}
