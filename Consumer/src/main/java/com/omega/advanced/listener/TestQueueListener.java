package com.omega.advanced.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Class TestQueueListener
 *
 * @author KennySo
 * @date 2023/10/8
 */
@Component
public class TestQueueListener {

    /**
     * 测试 生产者消息确认机制
     */
    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue("test.queue"),
            exchange = @Exchange(name = "test.exchange",
                                 type = ExchangeTypes.DIRECT),
            key = "test")})
    public void listenTestQueue(String message) {
        System.out.println("test_consumer receive the message: [" + message + "].");
    }

}
