package com.omega.advanced;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * Class RabbitMQConfig
 *
 * @author KennySo
 * @date 2023/10/8
 */
@Slf4j
@Configuration
public class RabbitMQConfig implements ApplicationContextAware {

    /**
     * 每个RabbitTemplate只能配置一个ReturnCallback, 因此需要在项目启动过程中配置.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 1.获取RabbitTemplate
        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);

        // 2.设置ReturnCallback
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {

            // 2.1 打印日志  (通过设置错误 routingKey 进行测试)
            log.error("消息投递到queue失败, 响应码: {}, 原因: {}, 交换机: {}, 路由key: {}, 消息: {}",
                    replyCode, replyText, exchange, routingKey, new String(message.getBody()));

            // 2.2 重发消息 Todo
        });
    }
}
