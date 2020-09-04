package com.hong.py.pojo;

/**
 * author: hongpy
 * create time: 2020-06-09 10:48
 * description:
 * life for code
 */
public class Person implements IPerson {

    //性别
    protected String sex;

    //身份证号
    private String CardId;

    //区域
    public String region;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCardId() {
        return CardId;
    }

    public void setCardId(String cardId) {
        CardId = cardId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
