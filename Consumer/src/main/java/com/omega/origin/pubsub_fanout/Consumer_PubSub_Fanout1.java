package com.omega.origin.pubsub_fanout;

import com.omega.utils.RabbitMQUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * Class Consumer_Fanout
 *
 * @author KennySo
 * @date 2023/9/29
 */
public class Consumer_PubSub_Fanout1 {
    public static void main(String[] args) throws Exception {

        // 1.创建 channel
        Channel channel = RabbitMQUtil.getChannel();

        // 2.接收消息
        channel.basicConsume("fanout_queue_1", true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) {
                System.out.println("获取消息: [" + new String(body) + "].");
            }
        });

        // 不用关闭资源, 让其一直监听
    }
}
