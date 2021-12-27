package org.huazi.note.manager.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.huazi.note.constant.DelayLevel;
import org.huazi.note.manager.entity.Goods;
import org.huazi.note.manager.service.MqService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 注释
 *
 * @author huazi
 * @date 2021/12/24 11:08
 */
@RequestMapping("/send")
@RestController
@RequiredArgsConstructor
@Slf4j
public class SendController {

    private final MqService mqService;

    /**********************************************sendOneWay**********************************************************/
    /**
     * 单向发送
     * 测试路径： http://localhost:8888/rocketmq-producer/send/sendOneWay
     */
    @RequestMapping("/sendOneWay")
    public void sendOneWay() {
        mqService.sendOneWay("tags_send_one_way", "keys_send_one_way", new Goods(1, "商品名称_sendOneWay"));
    }

    /**********************************************异步**********************************************************/

    /**
     * 普通异步
     * 测试路径： http://localhost:8888/rocketmq-producer/send/asyncSend
     */
    @RequestMapping("/asyncSend")
    public void asyncSend() {
        mqService.asyncSend("tags_async_send", "keys_async_send", new Goods(1, "商品名称_asyncSend"), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("发送异步消息成功，sendResult: {}", JSONObject.toJSONString(sendResult));
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("发送异步消息失败", throwable);
            }
        });
    }


    /**
     * 异步延时:
     * 延时10S：DelayLevel.TEN_SECOND.getKey()
     * 测试路径： http://localhost:8888/rocketmq-producer/send/asyncSendDelay
     */
    @RequestMapping("/asyncSendDelay")
    public void asyncSendDelay() {
        mqService.asyncSendDelay("tags_async_send_delay", "keys_async_send_delay", new Goods(1, "商品名称_asyncSendDelay"), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("发送异步延时消息成功，sendResult: {}", JSONObject.toJSONString(sendResult));
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("发送异步延时消息失败", throwable);
            }
        }, 1000, DelayLevel.TEN_SECOND.getKey());
    }

    /**
     * 异步顺序
     * 测试路径： http://localhost:8888/rocketmq-producer/send/asyncSendOrderly
     */
    @RequestMapping("/asyncSendOrderly")
    public void asyncSendOrderly() {
        for (int i = 1; i <= 5; i++) {
            mqService.asyncSendOrderly("tags_async_send_orderly", "keys_async_send_orderly", "该批次统一的hashKey", new Goods(i, String.format("商品名称-%s", i)), new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("发送异步顺序消息成功，sendResult: {}", JSONObject.toJSONString(sendResult));
                }

                @Override
                public void onException(Throwable throwable) {
                    log.error("发送异步顺序消息失败", throwable);
                }
            });
        }
    }

    /**********************************************同步**********************************************************/

    /**
     * 普通同步
     * 测试路径：http://localhost:8888/rocketmq-producer/send/syncSend
     */
    @RequestMapping("/syncSend")
    public void syncSend() {
        Goods goods = new Goods(1, "商品名称_syncSend");
        SendResult sendResult = mqService.syncSend("tags_sync_send", "keys_sync_send", goods);
        log.info("sendResult: {}", JSONObject.toJSONString(sendResult));
    }

    /**
     * 同步延时
     * 延时30S：DelayLevel.THIRTY_SECOND.getKey()
     * 测试路径： http://localhost:8888/rocketmq-producer/send/syncSendDelay
     */
    @RequestMapping("/syncSendDelay")
    public void syncSendDelay() {
        Goods goods = new Goods(1, "商品名称_syncSendDelay");
        SendResult sendResult = mqService.syncSendDelay("tags_sync_send_delay", "keys_sync_send_delay", goods, 1000, DelayLevel.THIRTY_SECOND.getKey());
        log.info("sendResult: {}", JSONObject.toJSONString(sendResult));
    }

    /**
     * syncSend方式：同步顺序发送
     * 测试路径： http://localhost:8888/rocketmq-producer/send/syncSendOrderly
     */
    @RequestMapping("/syncSendOrderly")
    public void syncSendOrderly() {
        for (int i = 1; i <= 5; i++) {
            Goods goods = new Goods(i, String.format("商品名称-%s", i));
            SendResult sendResult = mqService.syncSendOrderly("tags_sync_send_orderly", "keys_sync_send_orderly", "该批次统一的hashKey", goods);
            log.info("sendResult: {}", JSONObject.toJSONString(sendResult));
        }
    }

    /**
     * syncSend方式：批量发送
     * 测试路径： http://localhost:8888/rocketmq-producer/send/syncSendBatch
     */
    @RequestMapping("/syncSendBatch")
    public void syncSendBatch() {
        List<Object> goodsList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Goods goods = new Goods(i, String.format("商品名称-%s", i + "批量"));
            goodsList.add(goods);
        }
        SendResult sendResult = mqService.syncSendBatch("tags_sync_send_batch", "keys_sync_send_batch", goodsList);
        log.info("sendResult: {}", JSONObject.toJSONString(sendResult));
    }
}
