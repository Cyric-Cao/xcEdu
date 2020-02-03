package com.xuecheng.test.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * RabbitMQ 入门测试程序
 */
public class Consumer01 {

    private static final String QUEUE = "helloworld";

    public static void main(String[] args) {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置 MQ 服务器
        connectionFactory.setHost("127.0.0.1");
        // 设置端口号
        connectionFactory.setPort(5672);

        Connection connection = null;
        try {
            // 创建新的连接
            connection = connectionFactory.newConnection();
            // 创建通道 channel
            Channel channel = connection.createChannel();
            // 声明队列
            channel.queueDeclare(QUEUE, true, false, false, null);
            // 定义消费方法
            DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
                /**
                 * 消费者接受消息调用此方法
                 *
                 * @param consumerTag 消费者的标签，在 channel.basicConsume() 去指定
                 * @param envelope    消息包的内容，可以从中获取消息的 id，消息的 routingKey,交换机，消息和重传标志（收到消息失败后是否需要重新发送）
                 * @param properties  消息属性
                 * @param body        消息内容
                 * @throws IOException
                 */
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    // 交换机
                    String exchange = envelope.getExchange();
                    System.out.println(exchange);
                    // 路由 Key
                    String routingKey = envelope.getRoutingKey();
                    System.out.println(routingKey);
                    // 消息的 ID 标识
                    long deliveryTag = envelope.getDeliveryTag();
                    System.out.println(deliveryTag);
                    // 消息内容
                    String message = new String(body, "UTF-8");
                    System.out.println("receive message is : " + message);
                }
            };
            // 监听队列
            /**
             * 参数：String queue, boolean autoAck, Consumer callback
             * queue：队列名称
             * autoAck：是否自动回复，设为 true 表示消息接收到会自动向 MQ 回复接收到的消息，MQ 接收到回复会删除消息，设为 false 则需要进行手动编码的回复
             */
            channel.basicConsume(QUEUE, true, defaultConsumer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
