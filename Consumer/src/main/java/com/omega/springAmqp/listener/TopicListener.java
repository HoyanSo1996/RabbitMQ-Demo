package com.omega.springAmqp.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Class TopicListener
 *
 * @author KennySo
 * @date 2023/10/1
 */
@Component
public class TopicListener {

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(name = "topic.queue1"),
            exchange = @Exchange(name = "topic.exchange",
                                 type = ExchangeTypes.TOPIC),
            key = {"china.#"}
    )})
    public void listenTopicQueue1(String message) {
        System.out.println("topic_consumer_1 receive the message: [" + message + "].");
    }

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(name = "topic.queue2"),
            exchange = @Exchange(name = "topic.exchange",
                                 type = ExchangeTypes.TOPIC),
            key = {"japan.#"}
    )})
    public void listenTopicQueue2(String message) {
        System.out.println("topic_consumer_2 receive the message: [" + message + "].");
    }

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(name = "topic.queue3"),
            exchange = @Exchange(name = "topic.exchange",
                    type = ExchangeTypes.TOPIC),
            key = {"#.weather"}
    )})
    public void listenTopicQueue3(String message) {
        System.out.println("topic_consumer_3 receive the message: [" + message + "].");
    }

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(name = "topic.queue4"),
            exchange = @Exchange(name = "topic.exchange",
                    type = ExchangeTypes.TOPIC),
            key = {"#.news"}
    )})
    public void listenTopicQueue4(String message) {
        System.out.println("topic_consumer_4 receive the message: [" + message + "].");
    }
}
