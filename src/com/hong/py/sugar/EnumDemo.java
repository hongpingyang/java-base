package com.hong.py.sugar;

import com.hong.py.pojo.Student;

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
        System.out.println(spring.name+":"+spring.value);
    }


    /**
     * enum默认是实现了ENUM的子类，所以不能继续实现其它的类，可以实现接口。
     */
    public enum SeflEnumDemo  {

    SPRING("春天",1),

    SUMMER("夏天",2),

    AUTUMN("秋天",3),

    WINTER("冬天",4);


    private String name;
    private int value;


    SeflEnumDemo(String name,int value) {
        this.name=name;
        this.value = value;
    }

    public String getName() {
        return name;
    }


    public int getValue() {
        return this.value;
    }
  }
}
