package com.xuecheng.test.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class Consumer02_subscribe_sms {

    // 声明队列名称
    private static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    // 声明交换机名称
    private static final String EXCHANGE_FANOUT_INFORM = "exchange_fanout_inform";

    public static void main(String[] args) {

        // 创建一个与 MQ 的连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");


        Connection connection = null;
        Channel channel = null;
        try {
            // 创建连接
            connection = connectionFactory.newConnection();
            // 创建与交换机的通道，一个通道相当于一次会话
            channel = connection.createChannel();
            // 声明交换机
            channel.exchangeDeclare(EXCHANGE_FANOUT_INFORM, BuiltinExchangeType.FANOUT);
            // 声明队列
            channel.queueDeclare(QUEUE_INFORM_SMS, true, false, false, null);
            // 交换机与队列绑定
            channel.queueBind(QUEUE_INFORM_SMS, EXCHANGE_FANOUT_INFORM, "");
            // 定义消费消息方法
            DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    // 获取交换机
                    String exchange = envelope.getExchange();
                    // 获取路由 key
                    String routingKey = envelope.getRoutingKey();
                    // 获取消息 ID
                    long deliveryTag = envelope.getDeliveryTag();
                    // 获取消息内容
                    String message = new String(body, StandardCharsets.UTF_8);
                    System.out.println("receive message is : " + message);
                }
            };
            // 消费消息
            channel.basicConsume(QUEUE_INFORM_SMS, defaultConsumer);
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
