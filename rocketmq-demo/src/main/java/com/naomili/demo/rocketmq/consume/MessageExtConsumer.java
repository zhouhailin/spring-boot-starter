package com.naomili.demo.rocketmq.consume;

import com.naomili.rocketmq.spring.starter.annotation.RocketMQMessageListener;
import com.naomili.rocketmq.spring.starter.core.RocketMQListener;
import com.naomili.rocketmq.spring.starter.core.RocketMQPushConsumerLifecycleListener;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.UtilAll;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "message-ext-topic", selectorExpress = "tag1", consumerGroup = "${spring.application.name}-message-ext-consumer")
public class MessageExtConsumer implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onMessage(MessageExt message) {
        log.info("------- MessageExtConsumer received message, msgId:{}, body:{} ", message.getMsgId(), new String(message.getBody()));
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        // set consumer consume message from now
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);
        consumer.setConsumeTimestamp(UtilAll.timeMillisToHumanString3(System.currentTimeMillis()));
    }
}