package com.omega.origin;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Class Producer_SimpleQueue
 *
 * @author KennySu
 * @date 2023/9/28
 */
public class Producer_SimpleQueue {

    public static void main(String[] args) throws Exception  {
        // 1.创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        // 2.设置参数
        factory.setHost("192.168.230.101");  // ip：默认值localhost
        factory.setPort(5672);   // port：默认值5672
        factory.setVirtualHost("/");  //虚拟机,可以在面板自定义一个,默认"/"
        factory.setUsername("guest");
        factory.setPassword("guest");

        // 3.创建连接 connection
        Connection connection = factory.newConnection();

        // 4.创建 channel
        Channel channel = connection.createChannel();

        /*
           5.声明队列 (队列只用在全局声明一次就可以了, 在生产者和消费者都可以)
            queueDeclare(String queue, boolean durable, boolean exclusive,
                         boolean autoDelete, Map<String, Object> arguments)
            (1) queue: 队列名称. 如果队列名不存在,则会创建该队列; 如果存在,不创建新的
            (2) durable: 是否持久化, 当mq重启后, 队列是否还存在.
            (3) exclusive: 是否独占, 只能有一个消费者监听这队列.
            (4) autoDelete: 是否自动删除. 当没有Consumer连接时, 自动删除掉队列.
            (5) argument: 参数. 负责设置队列的高级配置, 如过期时间, 死信交换机等..
         */
        channel.queueDeclare("simple", true, false, false, null);

        /*
           6.发送消息
            basicPublish(String exchange, String routingKey,
                         BasicProperties props, byte[] body)
            (1) exchange: 交换机名称. 简单模式没有用到交换机, 默认写 ""
            (2) routingKey: 路由名称, 由于没有多个队列, 就指定上面定义的 simple
            (3) props: 配置信息
            (4) body: 要发送的消息
         */
        String msg = "hello, RabbitMQ~~";
        channel.basicPublish("", "simple", null, msg.getBytes());

        // 7.关闭资源
        channel.close();
        connection.close();
    }
}