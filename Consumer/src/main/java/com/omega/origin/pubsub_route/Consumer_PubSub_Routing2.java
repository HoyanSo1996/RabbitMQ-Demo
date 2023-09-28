package com.omega.origin.pubsub_route;

import com.omega.utils.RabbitMQUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * Class Consumer_PubSub_Route1
 *
 * @author KennySo
 * @date 2023/9/29
 */
public class Consumer_PubSub_Routing2 {
    public static void main(String[] args) throws Exception {

        // 1.创建 channel
        Channel channel = RabbitMQUtil.getChannel();

        // 声明 队列（在 消费者 或者 生产者 中的一个声明就可以了）
        // channel.queueDeclare("fanout_queue_1", true, false, false, null);

        // 2.接收消息
        channel.basicConsume("direct_queue_2", true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                System.out.println("接收 " + (new String(body)) + ", 存储到数据库." );
            }
        });

        // 不用关闭资源, 让其一直监听
    }
}
