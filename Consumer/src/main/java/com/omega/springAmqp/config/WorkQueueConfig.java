package com.omega.springAmqp.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class WorkQueueConfig
 *
 * @author KennySo
 * @date 2023/10/1
 */
@Configuration
public class WorkQueueConfig {

    /**
     * 声明 工作队列
     * @return work queue
     */
    @Bean
    public Queue workQueue() {
        return new Queue("work.queue");
    }
}
