package com.xuecheng.test.rabbitmq;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer01 {

    // 队列名称
    private static final String QUEUE = "helloworld";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = null;
        Channel channel = null;
        try {
            //创建连接
            ConnectionFactory factory = new ConnectionFactory();
            // 设置主机地址
            factory.setHost("127.0.0.1");
            // 设置端口号
            factory.setPort(5672);
            // 设置用户名
            factory.setUsername("guest");
            // 设置密码
            factory.setPassword("guest");
            // 设置虚拟机 一个 mq 服务可以设置多个虚拟机，每个虚拟机就相当于一个独立的 mq
            factory.setVirtualHost("/");

            //建立新的连接
            connection = factory.newConnection();
            // 创建会话通道，生产者和 mq 服务所有通信都在 channel 通道中完成
            channel = connection.createChannel();
            // 声明队列
            // 参数:String queue, boolean durable, boolean exclusive,boolean autoDelete, Map<String, Object> arguments
            /**
             * 参数明细
             * 1.queue 队列名称
             * 2.durable 队列是否持久化 如果持久化，mq 重启之后队列还在
             * 3.exclusive 队列是否独占此连接  队列之允许在该连接中访问，如果连接关闭该队列自动删除,如果此参数设置成 true 可以用于临时队列的创建
             * 4.autoDelete 队列不再使用时是否自动删除此队列
             * 5.arguments 队列参数
             */
            channel.queueDeclare(QUEUE, true, false, false, null);

            // 发送消息
            // 参数：String exchange, String routingKey, BasicProperties props, byte[] body
            /**
             * 1.exchange: 交换机，如果不指定的话将使用 mq 默认的交换机
             * 2.routingKey：路由 key，交换机会根据路由 key 来将消息转发到指定的队列，如果使用默认的交换机，routingKey 设置为队列的名称
             * 3.props：消息包含的属性
             * 4.body：消息体
             */
            String message = "helloWorld,小明" + System.currentTimeMillis();
            channel.basicPublish("", QUEUE, null, message.getBytes());
            System.out.println("send message is :" + message);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(channel != null) {
                channel.close();
            }
            if(connection != null) {
                connection.close();
            }
        }

    }
}
