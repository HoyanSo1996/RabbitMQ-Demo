package com.omega.springAmqp.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class FanoutConfig
 *
 * @author KennySo
 * @date 2023/10/1
 */
@Configuration
public class FanoutConfig {

    /**
     * 声明 交换机
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout.exchange");
    }

    /**
     * 声明 第一条队列
     */
    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanout.queue1");
    }

    /**
     * 声明 第二条队列
     */
    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanout.queue2");
    }

    /**
     * 绑定 第一条队列和交换机
     */
    @Bean
    public Binding bindingQueue1(Queue fanoutQueue1, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }

    /**
     * 绑定 第二条队列和交换机
     */
    @Bean
    public Binding bindingQueue2(Queue fanoutQueue2, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }
}
