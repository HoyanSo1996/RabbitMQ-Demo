package com.omega.origin;

import com.omega.utils.RabbitMQUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

public class Producer_PubSub_Topics {
    public static void main(String[] args) throws Exception {

        // 1.获取 channel
        Channel channel = RabbitMQUtil.getChannel();

        // 2.声明 交换机
        String exchangeName = "topic_exchange";
        channel.exchangeDeclare(
                exchangeName, BuiltinExchangeType.TOPIC, true, false, false, null);

        // 3.声明 多条队列
        String queue1Name = "topic_queue_1";
        String queue2Name = "topic_queue_2";
        String queue3Name = "topic_queue_3";
        String queue4Name = "topic_queue_4";
        channel.queueDeclare(queue1Name, true, false, false, null);
        channel.queueDeclare(queue2Name, true, false, false, null);
        channel.queueDeclare(queue3Name, true, false, false, null);
        channel.queueDeclare(queue4Name, true, false, false, null);

        // 4.绑定交换机, 并且绑定 routingKey
        //  需求：声明4条队列, 并以 china, japan, weather, news 将消息进行分组
        channel.queueBind(queue1Name, exchangeName, "china.#");
        channel.queueBind(queue2Name, exchangeName, "japan.#");
        channel.queueBind(queue3Name, exchangeName, "#.weather");
        channel.queueBind(queue4Name, exchangeName, "#.news");

        // 5.发送消息 (要指定 routingKey)
        String chinaMsg = "[中国新闻]: xxxxx";
        String japaneseMsg = "[日本新闻]: xxxxx";
        channel.basicPublish(exchangeName, "china.news", null, chinaMsg.getBytes());
        channel.basicPublish(exchangeName, "japan.news", null, japaneseMsg.getBytes());

        // 6.释放资源
        RabbitMQUtil.close(channel);
    }
}