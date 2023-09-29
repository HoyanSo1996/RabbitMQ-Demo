package com.omega.springAmqp.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Class SimpleQueueConfig
 *
 * @author KennySo
 * @date 2023/9/29
 */
@Configuration
public class SimpleQueueConfig {

    /**
     * 声明 基本消息队列
     * @return queue
     */
    @Bean
    public Queue simpleQueue() {
        return new Queue("simple.queue");
    }


}
