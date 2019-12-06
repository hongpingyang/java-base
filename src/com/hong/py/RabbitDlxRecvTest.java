package com.hong.py;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/9/26 17:12
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/9/26 17:12
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class RabbitDlxRecvTest {

    private final static String QUEUE_NAME5 = "dead_test_exchange_04";

    private final static String EXCHANGE_NAME4 = "dead_test_exchange_topic";

    public static void main(String[] args) throws IOException, TimeoutException {
        recvDlxTopicExchange();
    }

    private static void recvDlxTopicExchange() throws IOException, TimeoutException {
        // 获取到连接以及mq通道
        Connection connection = RabbitMQUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();

        //声明（创建）死信队列
        channel.queueDeclare(QUEUE_NAME5,false, false, false, null);

        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME5,EXCHANGE_NAME4,"");


        //同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);

        // 定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println(" [exchange topic] Received '" + message + "'");

                //channel.basicAck(envelope.getDeliveryTag(),false); //手动确认
            }
        };
        // 监听队列
        channel.basicConsume(QUEUE_NAME5, true, consumer);

    }
}
