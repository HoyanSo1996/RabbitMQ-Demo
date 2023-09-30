package com.omega.springAmqp.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Class WorkQueueListener
 *
 * @author KennySo
 * @date 2023/10/1
 */
@Component
public class WorkQueueListener {

    @RabbitListener(queues = "work.queue")
    public void listenWorkQueue1(String message) throws InterruptedException {
        System.out.println("listener 1 receive the message: [" + message + "].");
        Thread.sleep(200);
    }

    @RabbitListener(queues = "work.queue")
    public void listenWorkQueue2(String message) throws InterruptedException {
        System.err.println("listener 2 receive the message: [" + message + "].");
        Thread.sleep(1000);
    }
}
