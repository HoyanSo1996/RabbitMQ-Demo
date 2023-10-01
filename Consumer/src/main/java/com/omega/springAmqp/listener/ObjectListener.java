package com.omega.springAmqp.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Class ObjectListener
 *
 * @author KennySo
 * @date 2023/10/2
 */
@Component
public class ObjectListener {

    @RabbitListener(queues = "object.queue")
    public void listenObjectQueue(Map<String, Object> message) {
        System.out.println("object_consumer receive the message: " + message + ".");
    }
}
