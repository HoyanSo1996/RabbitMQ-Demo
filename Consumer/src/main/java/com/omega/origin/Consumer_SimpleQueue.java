package com.omega.origin;

import com.rabbitmq.client.*;

/**
 * Class Consumer_Simple
 *
 * @author KennySu
 * @date 2023/9/28
 */
public class Consumer_SimpleQueue {

    public static void main(String[] args) throws Exception {
        // 1.创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        // 2.设置参数
        factory.setHost("192.168.230.101");  //ip：默认值localhost
        factory.setPort(5672);   //port：默认值5672
        factory.setVirtualHost("/");  //虚拟机,可以在面板自定义一个,默认"/"
        factory.setUsername("guest");
        factory.setPassword("guest");

        // 3.创建连接 connection
        Connection connection = factory.newConnection();
        // 4.创建 channel
        Channel channel = connection.createChannel();

        /*
           5.接收消息
            basicConsume(String queue, boolean autoAck, Consumer callback)
            (1) queue: 队列名称
            (2) autoAck: 是否自动确认收到消息, 只有确认收到了一条消息, 才能接收下一条消息
            (3) consumer: 回调对象. 由于是一个接口, 所以就重写创建它的实现类,只用重写一个方法
         */
        channel.basicConsume("simple", true, new DefaultConsumer(channel) {
            /*
               回调方法, 当接收到消息后, 会自动执行该方法
               handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
               (1) consumerTag：标识
               (2) envelope：获取一些相关的信息, 如交换机、路由key
               (3) properties: 配置信息
               (4) body: 数据
            */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                System.out.println("consumerTag = " + consumerTag);
                System.out.println("exchange = " + envelope.getExchange());
                System.out.println("routingKey = " + envelope.getRoutingKey());
                System.out.println("properties = " + properties);
                System.out.println("body = " + new String(body));
                System.out.println("==============================================");
            }
        });

        // 不用关闭资源, 让其一直监听
    }
}
