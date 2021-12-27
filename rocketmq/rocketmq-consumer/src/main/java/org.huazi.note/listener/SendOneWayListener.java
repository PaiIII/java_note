package org.huazi.note.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 注释
 *
 * @author huazi
 * @date 2021/12/27 15:48
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "sendOneWayListener",topic = "rocketmq-producer",selectorExpression = "tags_send_one_way")
public class SendOneWayListener implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt messageExt) {
        log.info("监听TAGS为：tags_send_one_way 发送过来的message对象为：{}， body为：{}", messageExt.toString(), new String(messageExt.getBody()));
    }
}
