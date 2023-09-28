package com.omega.origin.work_queue;

import com.omega.utils.RabbitMQUtil;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * Class Consumer_WorkQueue
 *
 * @author KennySo
 * @date 2023/9/29
 */
public class Consumer_WorkQueue_Prefetch2 {
    public static void main(String[] args) throws Exception {

        // 1.获取 channel
        Channel channel = RabbitMQUtil.getChannel();

        // 第一步：设置预取值 prefetch
        channel.basicQos(1);

        // 2.接收消息
        channel.basicConsume("work_queue", false, new DefaultConsumer(channel) {
            @Override             // 第二步：闭自动确认 autoAck
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) {
                System.out.println("message = " + new String(body));
                try {
                    // 处理业务...
                    // consumer1 设置为200ms， consumer2 设置为1000ms
                    Thread.sleep(1000);

                    // 第三步：设置手动确认
                    channel.basicAck(envelope.getDeliveryTag(), false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // 不用关闭资源, 让其一直监听
    }
}
