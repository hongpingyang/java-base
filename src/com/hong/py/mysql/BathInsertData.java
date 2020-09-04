package com.hong.py.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Random;

/**
 * author: hongpy
 * create time: 2020-04-18 10:14
 * description:
 * life for code
 *
 * Mysql 百万数据批量插入
 *
 * 执行时间：35489ms
 *
 * 修改 bulk_insert_buffer_size=100M,max_allowed_packet=50M
 *
 * bulk_insert_buffer_size：
 * 用来缓存批量插入数据的时候临时缓存写入数据。
 * mysql会使用这个内存区域来缓存批量结构的数据以帮助批量写入数据文件。
 *
 * com.mysql.jdbc.PacketTooBigException: Packet for query is too large (37667368 > 4194304).
 * You can change this value on the server by setting the max_allowed_packet' variable.
 *
 */
public class BathInsertData {



    public static void main(String[] args) {


        Acount[] acounts = new Acount[1000000];

        for (int i = 0; i < 1000000; i++) {
            Acount acount = new Acount();
            acount.id=i;
            acount.name="测试"+i;
            acount.money = (new Random()).nextInt(10000000);
            acounts[i]=acount;
        }


        BatchInsertData(acounts);
    }


    public static Connection getConnection() {
        // 驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        // URL指向要访问的数据库名
        String url = "jdbc:mysql://192.168.1.141:3306/mybatis?useUnicode=true&amp;characterEncoding=utf8&amp;rewriteBatchedStatements=true&amp;useSSL=false&amp;serverTimezone=UTC&amp;allowPublicKeyRetrieval=true";
        // MySQL配置时的用户名
        String user = "root";
        // MySQL配置时的密码
        String password = "471215";
        Connection conn=null;

        try {
            // 加载驱动程序
            Class.forName(driver);
            // 连续数据库
            conn = DriverManager.getConnection(url, user, password);
        }
        catch (Exception e) {
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();// TODO: handle exception
        }
        return conn;
    }



    /**
     * 批量插入数据
     */
    public static void BatchInsertData(Acount[] acounts)  {


        Connection connection = getConnection();

        String sql="insert into account(id,`name`,money) values";

        //这里必须使用StringBuilder拼接，String效率慢
        StringBuilder vacs = new StringBuilder();
        for (int i = 0; i < acounts.length; i++) {
            vacs.append(" (");
            vacs.append(acounts[i].id);
            vacs.append(", '");
            vacs.append(acounts[i].name);
            vacs.append("', ");
            vacs.append(acounts[i].money);
            vacs.append(" )");
            //vacs+=" ( "+acounts[i].id+", '"+acounts[i].name+"', "+acounts[i].money+" )";
            if (i != acounts.length - 1) {
                //vacs+=",";
                vacs.append(",");
            }

        }

        sql+=vacs;

        long begin = System.currentTimeMillis();

        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            long l = System.currentTimeMillis() - begin;
            System.out.println("执行时间："+l+"ms");
        }
    }


    public static class Acount{
        int id;
        String name;
        double money;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }
    }

}
