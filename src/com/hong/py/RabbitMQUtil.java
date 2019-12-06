package com.hong.py;

import com.rabbitmq.client.*;
import com.rabbitmq.client.impl.DefaultExceptionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/9/26 10:41
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/9/26 10:41
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class RabbitMQUtil {

    private final static String EXCHANGE_NAME5 = "rety_test_exchange_topic";

    private final static String EXCHANGE_NAME6 = "failed_test_exchange_topic";

    public static Connection getConnection() throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();

        factory.setExceptionHandler(new DefaultExceptionHandler(){
            @Override
            public void handleConfirmListenerException(Channel channel, Throwable exception) {
                System.out.println("=====消息确认发生异常=======");
                exception.printStackTrace();
            }
        });

        //设置服务地址
        factory.setHost("10.20.29.203");
        //端口
        factory.setPort(5672);
        //设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("hpytest");
        factory.setUsername("hpy");
        factory.setPassword("hpy123");
        // 通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;
    }

    public static Long getRetryCount(AMQP.BasicProperties properties) {
        Long retryCount = 0L;
        try {
            Map<String, Object> headers = properties.getHeaders();
            if (headers != null) {
                if (headers.containsKey("count")) {
                    retryCount = (Long) headers.get("count");
               }
        }
        } catch (Exception e)
        {

        }	return retryCount;
    }

    public static void SetRetryCount(AMQP.BasicProperties properties,Long count) {
        try {
            Map<String, Object> headers = properties.getHeaders();
            if (headers != null) {
                if (headers.containsKey("count")) {
                    headers.put("count",count);
                }
            }
            else
            {
                AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
                headers = new HashMap<>();
                headers.put("count",count);
                properties = builder
                        .headers(headers)
                        .build();
            }
        } catch (Exception e)
        {

        }
    }

    /**
     * 消息处理
     * @param envelope
     * @param properties
     * @param body
     * @param channel
     * @throws IOException
     */
    public static void dealMessage(Envelope envelope, AMQP.BasicProperties properties, byte[] body, Channel channel) throws IOException {
        try
        {
            String message = new String(body);
            System.out.println(" [exchange topic] Received '" + message + "'");
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i=10/0;

        } catch (Exception e) {

            Long count= getRetryCount(properties);
            //System.out.println(count);
            //出现异常。要去重试
            if(count<3) {
                System.out.println(" [重试队列] Received '" + count + "'");
                SetRetryCount(properties,++count);
                channel.basicPublish(EXCHANGE_NAME5, envelope.getRoutingKey(), properties, body);
            }
            else
            {
                System.out.println(" [错误队列] Received '" + count + "'");
                channel.basicPublish(EXCHANGE_NAME6, envelope.getRoutingKey(), properties, body);
            }
        }
    }

}
