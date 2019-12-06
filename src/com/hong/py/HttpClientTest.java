package com.hong.py;

import com.alibaba.fastjson.JSON;
import com.hong.py.pojo.User;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/9/10 19:47
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/9/10 19:47
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class HttpClientTest {
    public static void main(String[] args) {
//        TestHttpClientDoGetOne();
//        TestHttpClientDoGetOneWhitParams();
        TestHttpClientDoPostOne();
        TestHttpClientDoPostOneWithParams();
        TestHttpClientDoPostOneWithParamsEntity();

    }

    public static   void TestHttpClientDoGetOne() {

       CloseableHttpClient httpClient = HttpClientBuilder.create().build();
       HttpGet httpGet = new HttpGet("http://localhost:8889/HttpClient/doGetOne");
       CloseableHttpResponse response=null;

       try {
           response = httpClient.execute(httpGet);
           HttpEntity entity = response.getEntity();
           System.out.println("响应状态为"+response.getStatusLine());
           if (entity != null) {
               System.out.println(entity.toString());
               System.out.println(entity.getContentLength());
           }

       } catch (IOException e) {
           e.printStackTrace();
       }finally {
           try {
           if (httpClient != null) {

                   httpClient.close();
               }
               if (response != null) {

                   response.close();
               }
            }
           catch (IOException e) {
               e.printStackTrace();
           }
       }
   }


    public static   void TestHttpClientDoGetOneWhitParams() {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("http://localhost:8889/HttpClient/doGetOneWithParams?name=zhansan&age=24");
        CloseableHttpResponse response=null;

        try {
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            System.out.println("响应状态为"+response.getStatusLine());
            if (entity != null) {
                System.out.println(entity.toString());
                System.out.println(entity.getContentLength());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (httpClient != null) {

                    httpClient.close();
                }
                if (response != null) {

                    response.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static   void TestHttpClientDoPostOne() {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://localhost:8889/HttpClient/doPostOne");
        CloseableHttpResponse response=null;

        try {
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            System.out.println("响应状态为"+response.getStatusLine());
            if (entity != null) {
                System.out.println(entity.toString());
                System.out.println(entity.getContentLength());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (httpClient != null) {

                    httpClient.close();
                }
                if (response != null) {

                    response.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static   void TestHttpClientDoPostOneWithParams() {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://localhost:8889/HttpClient/doPostOneWithParams?name=zhansan&age=24");
        CloseableHttpResponse response=null;

        httpPost.setHeader("Content-Type", "application/json;charset=utf-8");

        try {
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            System.out.println("响应状态为"+response.getStatusLine());
            if (entity != null) {
                System.out.println(entity.toString());
                System.out.println(entity.getContentLength());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (httpClient != null) {

                    httpClient.close();
                }
                if (response != null) {

                    response.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static   void TestHttpClientDoPostOneWithParamsEntity() {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://localhost:8889/HttpClient/doPostOneWithParamsEntity?name=zhansan&age=24");
        User user=new User();
        user.setUserName("zhansanfeng");
        user.setNickName("zsf");
        String jsonString = JSON.toJSONString(user);

        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        entity.setContentType("application/json;charset=UTF-8");
        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);

        CloseableHttpResponse response=null;

        //httpPost.setHeader("Content-Type", "application/json;charset=utf-8");

        try {
            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为"+response.getStatusLine());
            if (entity != null) {
                System.out.println(responseEntity.toString());
                System.out.println(responseEntity.getContentLength());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (httpClient != null) {

                    httpClient.close();
                }
                if (response != null) {

                    response.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
