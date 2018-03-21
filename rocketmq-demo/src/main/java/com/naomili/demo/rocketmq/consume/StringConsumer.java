package com.naomili.demo.rocketmq.consume;

import com.naomili.rocketmq.spring.starter.annotation.RocketMQMessageListener;
import com.naomili.rocketmq.spring.starter.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "string-topic", consumerGroup = "string_consumer")
public class StringConsumer implements RocketMQListener<String> {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onMessage(String message) {
        log.info("------- StringConsumer received: {}", message);
    }
}