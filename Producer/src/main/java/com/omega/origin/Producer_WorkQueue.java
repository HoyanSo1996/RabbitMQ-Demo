package com.omega.origin;

import com.omega.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;

/**
 * Class Producer_WorkQueue
 *
 * @author KennySo
 * @date 2023/9/29
 */
public class Producer_WorkQueue {
    public static void main(String[] args) throws Exception {

        // 1.获取 channel
        Channel channel = RabbitMQUtil.getChannel();

        // 2.声明 队列（在 消费者 或者 生产者 中的一个声明就可以了）
        channel.queueDeclare("work_queue", true, false, false, null);

        // 3.发送消息
        for (int i = 1; i <= 50; i++) {
            String msg = i + " hello, RabbitMQ~~";
            channel.basicPublish("", "work_queue", null, msg.getBytes());
        }

        // 4.关闭资源
        RabbitMQUtil.close(channel);
    }
}
