package com.imooc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class redisTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @GetMapping("set")
    public String set(@RequestParam String key, @RequestParam String value){
        redisTemplate.opsForValue().set(key,value);
        return "ok";

    }
    @GetMapping("get")
    public String get(@RequestParam String key){
        return (String) redisTemplate.opsForValue().get(key);
    }
    @GetMapping("del")
    public String del(@RequestParam String key){
        redisTemplate.delete(key);
        return "del";
    }

}
