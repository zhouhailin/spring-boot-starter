package com.naomili.demo.rocketmq.produce;

import com.naomili.rocketmq.spring.starter.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Component
@EnableScheduling
public class ProduceDemo {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Scheduled(initialDelay = 5 * 1000, fixedDelay = 2 * 1000)
    public void run() throws Exception {
        System.out.println("====================run====================");
        // send string
        rocketMQTemplate.convertAndSend("string-topic", "Hello, World!");

        // send string with spring Message
        rocketMQTemplate.send("string-topic", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());

        // send user-defined object
        rocketMQTemplate.convertAndSend("order-paid-topic", new OrderPaidEvent("T_001", new BigDecimal("88.00")));

        // send message with special tag
        rocketMQTemplate.convertAndSend("message-ext-topic:tag0", "I'm from tag0"); //not be consume
        rocketMQTemplate.convertAndSend("message-ext-topic:tag1", "I'm from tag1");
    }
}
