package com.hong.py.sugar;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.sugar
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2020/4/15 12:05
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

public class EnumDemo {


    public static void main(String[] args) {
        SeflEnumDemo spring = SeflEnumDemo.SPRING;
        System.out.println(spring.value);
    }




    public enum SeflEnumDemo{

    SPRING(1),

    SUMMER(2),

    AUTUMN(3),

    WINTER(4);

    private int value;

        SeflEnumDemo(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
  }
}
