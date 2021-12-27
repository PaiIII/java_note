package org.huazi.note.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 设置消费模式consumeMode = ConsumeMode.ORDERLY，默认情况下是并发消费模式（ConsumeMode.CONCURRENTLY）。
 *
 * @author huazi
 * @date 2021/12/24 14:22
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "asyncSendOrderlyListener", topic = "rocketmq-producer", selectorExpression = "tags_async_send_orderly",consumeMode = ConsumeMode.ORDERLY)
public class AsyncSendOrderlyListener implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt messageExt) {
        log.info("监听TAGS为：tags_async_send_orderly 发送过来的message对象为：{}， body为：{}", messageExt.toString(), new String(messageExt.getBody()));
    }

}
