package com.hong.py.RabbitMq;

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
 * @CreateDate: 2019/9/26 10:31
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/9/26 10:31
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class RabbitMqTest {

    private final static String QUEUE_NAME = "q_test_01";

    private final static String QUEUE_NAME2 = "test_exchange_01";

    private final static String EXCHANGE_NAME = "test_exchange_fanout";

    private final static String EXCHANGE_NAME2 = "test_exchange_direct";

    private final static String EXCHANGE_NAME3 = "test_exchange_topic";

    //死信交换机
    private final static String EXCHANGE_NAME4 = "dead_test_exchange_topic";
    //重试队列
    private final static String EXCHANGE_NAME5 = "rety_test_exchange_topic";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        //默认情形下消费者获得相同的数量的消息。不考虑每个消息执行的任务的时长，
        // 有可能导致一个队列里执行的任务很耗时很忙，一个队列很闲。
        //  Qos 的 prefetchCount 参数就是用来限制这批未确认消息数量的。
        // 设为1时，队列只有在收到消费者发回的上一条消息 ack 确认后，
        // 才会向该消费者发送下一条消息。
        // prefetchCount 的默认值为0，即没有限制，队列会将所有消息尽快发给消费者。


        //设置队列的arguments
        /*    x-message-ttl(消息过期时间)、
              x-max-length(最大积压消息个数)、
              x-dead-letter-exchange(消息过期后投递的exchange)
              x-dead-letter-routing-key(消息过期后按照指定的routingkey重新发送)、
              x-max-priority(队列优先级，值越大优先级超高，优先级高的消息具备优先被消费的特权)
              x-expires(控制队列如果在多长时间未使用则会被删除，毫秒为单位)、
              x-max-length-bytes


          durable： 是否持久化, 队列的声明默认是存放到内存中的，
          如果rabbitmq重启会丢失，如果想重启之后还存在就要使队列持久化，
          保存到Erlang自带的Mnesia数据库中，当rabbitmq重启之后会读取该数据库
      */


        /**
         * exchange：交换器名称，如果设置为空字符串，则消息会被发送到RabbitMQ默认的交换器中。
         * routingKey：指定路由键，交换器根据路由键将消息存储到相应的队列之中
         * mandatory：为true则当exchange找不到相应的queue时，会调用basic.return方法将消息返还给生产者，否则丢弃
         * immediate：为true则当exchange将消息route到所有queue(s)发现没有consumer时，不会将消息插入队列，会调用basic.return方法将消息返还给生产者
         * props：消息为持久化  —— MessageProperties.PERSISTENT_TEXT_PLAIN
         * body：msg字节
         * channel.basicPublish(ex_log, "", true, true, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
         */


        /**
         * 实现重试机制
         * 一般来说RabbitMQ有个方法channel.basicNack()能够让消息回到队列中，
         * 这样可以实现重试。但是这样没有明确重试次数，
         * 如果当前的消息一直重试的话，则后面的消息就会堆积起来，
         * 导致后面的消息无法消费。这是一个致命的缺点。
         * 因此这就需要设置重试次数来解决这种问题。
         * 下面提供几种解决方案。
         * 1.使用redis或者mongo等第三方存储当前重试次数。
         * 2.在header中添加重试次数，并且使用channel.basicPublish() 方法重新将消息发送出去后将重试次数加1。
         * 3.使用spring-rabbit中自带的retry功能。
         *
         *
         * 实现延迟发送消息机制
         *
         * 设置队列的x-message-ttl(消息过期时间，就是要延迟的时间)
         * 设置队列的死信队列
         * 队列的消息不接收处理，让消息过期，到死信队列处理。
         *
         */

        /**
         *
         * ConnectionFactory factory = new ConnectionFactory();
         * factory.setExceptionHandler(new DefaultExceptionHandler(){
         * 必须添加自定义异常处理，默认的handleConfirmListenerException 一旦消息处理出现异常，会关闭连接导致不可用。
         *
         */

        /*recv1();
        recv2();
        sendWorker();*/

        //sendFanoutExchange();

        //sendDirectExchange();

        sendTopicExchange();
    }

    /**
     * Worker模式 ，分发消息到每个消费者。 不需要交换机
     */
    private static void sendWorker() throws IOException, TimeoutException, InterruptedException {
        // 获取到连接以及mq通道
        Connection connection = RabbitMQUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();
        // 声明（创建）队列
        //第一个参数是队列名称，
        //第二个参数是是否持久化
        //第三个参数是否自动删除。
        channel.queueDeclare(QUEUE_NAME,false, false, false, null);
        for (int i = 0; i < 20; i++) {

            String message = "Hello World! "+i;
            //默认的交换机
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
        //关闭通道和连接
        channel.close();
        connection.close();
    }

    private static void recv1() throws IOException, TimeoutException {
        // 获取到连接以及mq通道
        Connection connection = RabbitMQUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();
        // 声明（创建）队列
        channel.queueDeclare(QUEUE_NAME,false, false, false, null);

        //同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);
         // 定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println(" [x1] Received '" + message + "'");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //channel.basicAck(envelope.getDeliveryTag(),false); //手动确认
                //channel.basicNack();
            }
        };
        // 监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer); //true 表示自动确认，
                                                             //false表示手动确认。必须在消息消费后发送确认消息。

        /*消息的确认模式
        消费者从队列中获取消息，服务端如何知道消息已经被消费呢？

        模式1：自动确认
        只要消息从队列中获取，无论消费者获取到消息后是否成功消息，都认为是消息已经成功消费。
        模式2：手动确认
        消费者从队列中获取消息后，服务器会将该消息标记为不可用状态，等待消费者的反馈，如果消费者一直没有反馈，那么该消息将一直处于不可用状态。
        */
    }

    private static void recv2() throws IOException, TimeoutException {
        // 获取到连接以及mq通道
        Connection connection = RabbitMQUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();
        // 声明（创建）队列
        channel.queueDeclare(QUEUE_NAME,false, false, false, null);
        //同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);
        // 定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body);
                System.out.println(" [x2] Received '" + message + "'");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        // 监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer); //true 表示自动确认，
    }

    /**
     * 订阅发布模式
     * 注意：消息发送到没有队列绑定的交换机时，消息将丢失，
     * 因为，交换机没有存储消息的能力，消息只能存在在队列中
     */
    private static void sendFanoutExchange() throws IOException, TimeoutException, InterruptedException {
        // 获取到连接以及mq通道
        Connection connection = RabbitMQUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();

        // 声明exchange
        /**
         * type:交换器的类型，如Direct Topic Headers Fanout
         * Direct – 处理路由键。需要将一个队列绑定到交换机上，要求该消息与一个特定的路由键完全匹配。
         * Topic - 将路由键和某模式进行匹配。此时队列需要绑定要一个模式上。符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词。
         * Fanout - 不处理路由键。消息都会被转发到与该交换机绑定的所有队列上。（Fanout交换机转发消息是最快的）。
         * Headers -通过发送的request message中的header进行匹配，其中匹配规则（x-match）又分为all和any，all代表必须所有的键值对匹配，any代表只要有一个键值对匹配即可。headers exchange的默认匹配规则（x-match）是any。
         *
         *channel.exchangeDelare(EXCHANGE_NAME,
         */
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        for (int i = 0; i < 20; i++) {
            Thread.sleep(2000);
            // 消息内容
            String message = "Hello World! "+i;
            channel.basicPublish( EXCHANGE_NAME,"", null, message.getBytes());
            System.out.println(" [exchange fanout] Sent '" + message + "'");
        }
        //关闭通道和连接
        channel.close();
        connection.close();
    }


    /**
     * 路由模式
     */
    private static void sendDirectExchange() throws IOException, TimeoutException, InterruptedException {
        // 获取到连接以及mq通道
        Connection connection = RabbitMQUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();

        //direct交换机类型 需要将一个队列绑定到交换机上，要求该消息与一个特定的路由键完全匹配。
        channel.exchangeDeclare(EXCHANGE_NAME2, "direct");

        for (int i = 0; i < 20; i++) {
            Thread.sleep(2000);
            // 消息内容
            String message = "Hello World! "+i;
            //delete 是direct  路由键
            channel.basicPublish( EXCHANGE_NAME2,"delete", null, message.getBytes());
            System.out.println(" [exchange direct delete] Sent '" + message + "'");

            channel.basicPublish( EXCHANGE_NAME2,"update", null, message.getBytes());
            System.out.println(" [exchange direct update] Sent '" + message + "'");
        }
        //关闭通道和连接
        channel.close();
        connection.close();
    }

    /**
     * 主题模式 （通配符模式）
     */
    private static void sendTopicExchange() throws IOException, TimeoutException, InterruptedException {
// 获取到连接以及mq通道
        Connection connection = RabbitMQUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();

        //创建死信交换机
        channel.exchangeDeclare(EXCHANGE_NAME4, "fanout");

        channel.exchangeDeclare(EXCHANGE_NAME3, "topic");

        //可以添加监听处理
        channel.addReturnListener(new ReturnCallback() {
            @Override
            public void handle(Return aReturn) {

            }
        });

        for (int i = 0; i < 10; i++) {
            Thread.sleep(2000);
            // 消息内容
            String message = "Hello World! "+i;

            //设置headers，设置count初始值
            AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
            Map<String, Object> headers = new HashMap<>();
            headers.put("count",0);
            AMQP.BasicProperties basicProperties = builder
                    .headers(headers)
                    .deliveryMode(2)//2設置消息持久化
                    .build();
            //delete 是direct  路由键
            //channel.txSelect();
            //channel.basic
            channel.basicPublish( EXCHANGE_NAME3,"dlxquque.123", basicProperties, message.getBytes());
            System.out.println(" [exchange topic hongpy] Sent '" + message + "'");
            //channel.txCommit();
            //channel.basicPublish( EXCHANGE_NAME3,"anhui.t", null, message.getBytes());
            //System.out.println(" [exchange topic anhui] Sent '" + message + "'");
        }

        channel.confirmSelect();
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long l, boolean b) throws IOException {

            }

            @Override
            public void handleNack(long l, boolean b) throws IOException {

            }
        });

        //channel.addr

        //关闭通道和连接
        channel.close();
        connection.close();
    }

}
