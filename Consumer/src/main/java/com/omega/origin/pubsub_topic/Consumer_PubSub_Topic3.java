package com.omega.origin.pubsub_topic;

import com.omega.utils.RabbitMQUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * Class Consumer_PubSub_Topic1
 *
 * @author KennySo
 * @date 2023/9/29
 */
public class Consumer_PubSub_Topic3 {
    public static void main(String[] args) throws Exception {

        // 1.获取 channel
        Channel channel = RabbitMQUtil.getChannel();

        // 2.接收消息
        channel.basicConsume("topic_queue_3", true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                System.out.println("接收 " + (new String(body)) + ", 存储到数据库." );
            }
        });

        // 不用关闭资源, 让其一直监听
    }
}
