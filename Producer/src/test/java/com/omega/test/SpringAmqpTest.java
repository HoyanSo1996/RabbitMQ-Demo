package com.omega.test;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Class SpringAmqpTest
 *
 * @author KennySo
 * @date 2023/9/29
 */
@SpringBootTest
public class SpringAmqpTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * send message to simple queue.
     */
    @Test
    public void testSendMsgToSimpleQueue() {
        // 1.队列名称
        String queueName = "simple.queue";
        // 2.消息
        String message = "hello, simple queue!";
        // 3.发送消息
        // (这里queueName所在的参数位是routingKey, 即没有exchange和routingKey时, routingKey --> queue)
        rabbitTemplate.convertAndSend(queueName, message);
    }

    /**
     * send message to work queue.
     */
    @Test
    public void testSendMsgToWorkQueue() {
        String queueName = "work.queue";
        String message = ". hello, work queue!";
        for (int i = 1; i <= 50; i++) {
            rabbitTemplate.convertAndSend(queueName, i + message);
        }
    }

    /**
     * send message to fanout queue
     */
    @Test
    public void testSendMsgToFanoutQueue() {
        String exchangeName = "fanout.exchange";
        String routingKey = "";
        String message = "hello, fanout queue!";
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
