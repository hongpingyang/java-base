package com.hong.py;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
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
 * @CreateDate: 2019/9/26 14:30
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/9/26 14:30
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class RabbitRecvTest {

    private final static String QUEUE_NAME = "q_test_01";

    private final static String QUEUE_NAME2 = "test_exchange_01";

    private final static String QUEUE_NAME3 = "test_exchange_02";

    private final static String QUEUE_NAME4 = "test_exchange_04";

    private final static String QUEUE_NAME6 = "test_exchange_06";

    private final static String QUEUE_NAME7 = "rety_test_exchange_07";

    private final static String QUEUE_NAME8 = "failed_test_exchange_08";

    private final static String QUEUE_NAME5 = "dead_test_exchange_04";

    private final static String EXCHANGE_NAME = "test_exchange_fanout";

    private final static String EXCHANGE_NAME2 = "test_exchange_direct";

    private final static String EXCHANGE_NAME3 = "test_exchange_topic";

    private final static String EXCHANGE_NAME4 = "dead_test_exchange_topic";

    private final static String EXCHANGE_NAME5 = "rety_test_exchange_topic";

    private final static String EXCHANGE_NAME6 = "failed_test_exchange_topic";

    public static void main(String[] args) throws IOException, TimeoutException {
         //recvFanoutExchange();
        //recvDirectExchange();
        recvTopicExchange();
    }

    private static void recvFanoutExchange() throws IOException, TimeoutException {
        // 获取到连接以及mq通道
        Connection connection = RabbitMQUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();
        // 声明（创建）队列
        channel.queueDeclare(QUEUE_NAME2,false, false, false, null);
        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME2,EXCHANGE_NAME,"");
        //同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);
        // 定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println(" [exchange] Received '" + message + "'");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //channel.basicAck(envelope.getDeliveryTag(),false); //手动确认
            }
        };
        // 监听队列
        channel.basicConsume(QUEUE_NAME2, true, consumer);

    }

    private static void recvDirectExchange() throws IOException, TimeoutException {
        // 获取到连接以及mq通道
        Connection connection = RabbitMQUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();
        // 声明（创建）队列
        channel.queueDeclare(QUEUE_NAME3,false, false, false, null);

        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME3,EXCHANGE_NAME2,"update");
        channel.queueBind(QUEUE_NAME3,EXCHANGE_NAME2,"delete");

        //同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);
        // 定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println(" [exchange direct] Received '" + message + "'");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //channel.basicAck(envelope.getDeliveryTag(),false); //手动确认
            }
        };
        // 监听队列
        channel.basicConsume(QUEUE_NAME3, true, consumer);

    }

    private static void recvTopicExchange() throws IOException, TimeoutException {
        // 获取到连接以及mq通道
        Connection connection = RabbitMQUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();
        //创建队列参数
        Map<String, Object> queueArgs = new HashMap<String, Object>();
        queueArgs.put("x-dead-letter-exchange", EXCHANGE_NAME4);  //死信队列
        //queueArgs.put("x-dead-letter-exchange-", EXCHANGE_NAME4);
        queueArgs.put("x-message-ttl", 10000);  // 消息超时：让发布的message在队列中可以存活多长时间，以毫秒为单位。
        //queueArgs.put("x-expires", 1000);       // 队列超时：当前的queue在指定的时间内，没有消费者订阅就会被删除，以毫秒为单位。
        //queueArgs.put("x-max-length", 10);     // 队列最大长度：当超过了这个大小的时候，会删除之前最早插入的消息为本次的留出空间。
        //queueArgs.put("x-queue-mode", "lazy");   //延迟加载：queue的信息尽可能的都保存在磁盘上，仅在有消费者订阅的时候才会加载到RAM中。

        // 声明（创建）队列 （队列名字，是否持久化，是否排外，是否自动清理，参数）
        channel.queueDeclare(QUEUE_NAME6,false, false, false, queueArgs);

        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME6,EXCHANGE_NAME3,"dlxquque.*");

        channel.exchangeDeclare(EXCHANGE_NAME5, "topic");

        // 声明（创建）队列 （队列名字，是否持久化，是否排外，是否自动清理，参数）
        channel.queueDeclare(QUEUE_NAME7,false, false, false, null);

        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME7,EXCHANGE_NAME5,"dlxquque.*");

        channel.exchangeDeclare(EXCHANGE_NAME6, "topic");

        // 声明（创建）队列 （队列名字，是否持久化，是否排外，是否自动清理，参数）
        channel.queueDeclare(QUEUE_NAME8,false, false, false, null);

        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME8,EXCHANGE_NAME6,"dlxquque.*");

        //同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);

        // 定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

               RabbitMQUtil.dealMessage(envelope, properties, body, channel);

                //channel.basicReject(envelope.getDeliveryTag(),false);
                //手动确认 true表示要重新入队
                //channel.basicAck(envelope.getDeliveryTag(),true); //手动确认
                //channel.basicRecover()
            }
        };
        // 监听队列

        /**
         * 为队列指定消费者
         * queue: 队列的名称
         * autoAck：
         *   true:RabbitMQ会自动把发送出去的消息置为确认，然后从内存（或磁盘）中删除
         *   false:RabbitMQ会等待消费者地回复确认信号后才从内存（或者磁盘）中移去消息（实际上是先打上删除标记，之后再删除）
         *   如果一直没有收到消费者的确认信号，并且消费此消息的消费者已断开连接，则RabbitMQ会安排该消息重新进入队列，等待投递给下一个消费者，也有可能还是原来那个消费者
         * consumerTag:消费者标签，用来区分多个消费者
         * noLocal:设置为true不能将同一个Connection中生产者发送的消息传送给这个Connection中的消费者
         * exclusive:是否排它
         * arguments:设置消费者其他参数
         * callback：消费者的回调函数，用来处理RabbitMQ推送过来的消息，比如DefaultConsumer，使用时需要重写其中的handleDelivery方法
         *
         *
         * channel.basicConsume(queue, autoAck, consumerTag, noLocal, exclusive, arguments, callback);
         */
        channel.basicConsume(QUEUE_NAME6, true, consumer);

    }


}
