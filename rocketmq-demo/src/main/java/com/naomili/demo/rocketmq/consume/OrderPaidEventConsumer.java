package com.naomili.demo.rocketmq.consume;

import com.naomili.rocketmq.spring.starter.annotation.RocketMQMessageListener;
import com.naomili.rocketmq.spring.starter.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "order-paid-topic", consumerGroup = "order-paid-consumer")
public class OrderPaidEventConsumer implements RocketMQListener<OrderPaidEvent> {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onMessage(OrderPaidEvent orderPaidEvent) {
        log.info("------- OrderPaidEventConsumer received: {}", orderPaidEvent);
    }
}