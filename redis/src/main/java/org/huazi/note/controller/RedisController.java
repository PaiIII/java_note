package org.huazi.note.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 注释
 *
 * @author huazi
 * @date 2021/12/15 14:55
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/redis")
public class RedisController {

    private final RedisTemplate redisTemplate;

    @GetMapping("/ops/set")
    public void set() {
        redisTemplate.opsForValue().set("test1", 2);
        redisTemplate.opsForValue().set("DEFAULT_CODE_KEY", "AAAAAff4ac1b06c94dd63e7694a");
        //设置时限
        redisTemplate.opsForValue().set("DEFAULT_CODE_KEY", "bfaaf3d4b0ff4ac1b06c94dd63e7694a", 60, TimeUnit.SECONDS);
        //SET 不允许重复
        redisTemplate.opsForSet().add("set_test", 2, 3, 4, "Test1", 2);
        redisTemplate.opsForSet().add("set_test", 6);
        redisTemplate.opsForSet().add("set_test", 7);
        redisTemplate.opsForSet().add("set_test", 6);
    }

    @GetMapping("/ops/get")
    public void get() {
        System.out.println(redisTemplate.opsForValue().get("test1"));
        System.out.println(redisTemplate.opsForValue().get("DEFAULT_CODE_KEY"));
        System.out.println(redisTemplate.opsForSet().members("set_test"));
    }

}
