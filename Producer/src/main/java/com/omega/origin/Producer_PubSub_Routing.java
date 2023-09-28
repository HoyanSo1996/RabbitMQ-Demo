package com.omega.origin;

import com.omega.utils.RabbitMQUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

/**
 * Class Produer_PubSub_Direct
 *
 * @author KennySo
 * @date 2023/9/29
 */
public class Producer_PubSub_Routing {
    public static void main(String[] args) throws Exception {

        // 1.创建 channel
        Channel channel = RabbitMQUtil.getChannel();

        // 2.声明 exchange
        String exchangeName = "direct_exchange";
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT, true, false, false, null);

        // 3.声明 多条队列
        String queue1Name = "direct_queue_1";
        String queue2Name = "direct_queue_2";
        channel.queueDeclare(queue1Name, true, false, false, null);
        channel.queueDeclare(queue2Name, true, false, false, null);

        // 4.绑定交换机, 并且绑定 routingKey
        //   绑定第一条队列
        channel.queueBind(queue1Name, exchangeName, "red");
        channel.queueBind(queue1Name, exchangeName, "blue");
        //   绑定第二条队列
        channel.queueBind(queue2Name, exchangeName, "red");
        channel.queueBind(queue2Name, exchangeName, "yellow");

        // 5.发送消息 (要指定 routingKey)
        String redMsg = "[红色警报]: xxxxx";
        String blueMsg = "[蓝色警报]: xxxxx";
        String yellowMsg = "[黄色警报]: xxxxx";
        channel.basicPublish(exchangeName, "red", null, redMsg.getBytes());
        channel.basicPublish(exchangeName, "blue", null, blueMsg.getBytes());
        channel.basicPublish(exchangeName, "yellow", null, yellowMsg.getBytes());

        // 6.释放资源
        RabbitMQUtil.close(channel);
    }
}
