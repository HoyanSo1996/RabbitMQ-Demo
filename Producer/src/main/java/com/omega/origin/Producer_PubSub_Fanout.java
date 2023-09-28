package com.omega.origin;

import com.omega.utils.RabbitMQUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

/**
 * Class Producer_Fanout
 *
 * @author KennySo
 * @date 2023/9/29
 */
public class Producer_PubSub_Fanout {
    public static void main(String[] args) throws Exception {

        // 1.获取 channel
        Channel channel = RabbitMQUtil.getChannel();

        /*
           2.声明 exchange
            exchangeDeclare(String exchange, BuiltinExchangeType type, boolean durable,
                            boolean autoDelete, boolean internal, Map<String, Object> arguments)
            (1) exchange: 交换机名字. 不存在则新建, 存在则声明失败.
            (2) type: 交换机类型. a.FANOUT(广播)  b.DIRECT(定向)  c.TOPIC(通配符方式) d.HEADS(参数匹配)
            (3) durable: 是否持久化.
            (4) autoDelete：自动删除.
            (5) internal: 供rabbitmq内部开发使用, 一般为 false.
            (6) argument: 参数.
         */
        String exchangeName = "fanout_exchange";
        channel.exchangeDeclare(
                exchangeName, BuiltinExchangeType.FANOUT, true, false, false, null);

        // 2.声明 多条队列
        String queue1Name = "fanout_queue_1";
        String queue2Name = "fanout_queue_2";
        channel.queueDeclare(queue1Name, true, false, false, null);
        channel.queueDeclare(queue2Name, true, false, false, null);

        /*
           3.绑定交换机
            queueBind(String queue, String exchange, String routingKey)
            (1) queue：队列名称
            (2) exchange：交换机名称
            (3) routingKey: 路由方式. 由于是fanout形式, 每个queue都能拿到一份, 所以默认为 "".
         */
        channel.queueBind(queue1Name, exchangeName, "");
        channel.queueBind(queue2Name, exchangeName, "");

        // 4.发送消息
        String msg = "fanout message";
        channel.basicPublish(exchangeName, "", null, msg.getBytes());

        // 5.释放资源
        RabbitMQUtil.close(channel);
    }
}
