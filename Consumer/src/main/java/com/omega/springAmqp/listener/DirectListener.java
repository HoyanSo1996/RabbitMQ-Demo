package com.omega.springAmqp.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Class DirectListener
 *
 * @author KennySo
 * @date 2023/10/1
 */
@Component
public class DirectListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "direct.exchange",
                                 type = ExchangeTypes.DIRECT),
            key = {"blue", "red"})
    )
    public void listenDirectQueue1(String message) {
        System.out.println("direct_consumer_1 receive the message: [" + message + "].");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("direct.queue2"),
            exchange = @Exchange(name = "direct.exchange",
                                 type = ExchangeTypes.DIRECT),
            key = {"yellow", "red"})
    )
    public void listenDirectQueue2(String message) {
        System.out.println("direct_consumer_2 receive the message: [" + message + "].");
    }
}
