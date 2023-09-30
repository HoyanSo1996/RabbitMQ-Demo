package com.omega.springAmqp.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Class FanoutListener
 *
 * @author KennySo
 * @date 2023/10/1
 */
@Component
public class FanoutListener {

    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String message) {
        System.out.println("fanout_consumer_1 receive the message: [" + message + "].");
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String message) {
        System.out.println("fanout_consumer_2 receive the message: [" + message + "].");
    }
}
