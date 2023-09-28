package com.omega.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Class RabbitMQUtil
 *
 * @author KennySu
 * @date 2023/9/28
 */
public class RabbitMQUtil {

    // 1.创建连接工厂
    private static final ConnectionFactory factory = new ConnectionFactory();

    static {
        // 2.设置参数
        factory.setHost("192.168.163.101");  //ip：默认值localhost
        factory.setPort(5672);   //port：默认值5672
        factory.setVirtualHost("/");  //虚拟机,可以在面板自定义一个,默认"/"
        factory.setUsername("guest");
        factory.setPassword("guest");
    }

    // 3.创建连接
    public static Connection connection;

    static {
        try {
            connection = factory.newConnection();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取channel
     * @return channel
     */
    public static Channel getChannel() throws IOException {
        return connection.createChannel();
    }

    /**
     * 关闭资源
     * @param channel channel
     */
    public static void close(Channel channel) {
        try {
            if (channel != null) {
                channel.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
