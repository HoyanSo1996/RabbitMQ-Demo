package com.omega.springAmqp.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Class SimpleQueueListener
 *
 * @author KennySo
 * @date 2023/9/29
 */
@Component
public class SimpleQueueListener {

    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueue(String message) {
        System.out.println("simpleQueue listener receive message: [" + message + "].");
    }
}
