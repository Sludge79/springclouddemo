package sludge.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.JedisPool;
import sludge.redis.RedisUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author Giggles
 * @Date 2019-09-26 9:44 AM
 */
@RestController
@RequestMapping("order")
public class OrderHandler {

    private  static final org.slf4j.Logger  log = LoggerFactory.getLogger(OrderHandler.class);


    @Autowired
    private Redisson redisson;


    @Autowired
    private RedisUtil redisUtil;


    @Autowired
    private JedisPool jedisPool;

    @Value("${server.port}")
    private String port;

    @GetMapping("index")
    public String index(){
        return "order端口："+this.port;
    }


    @GetMapping("reduce")
    public String reduce(){
        redisUtil.incrBy("count",-1L);
        System.out.println("剩余"+redisUtil.get("count"));
        return null;
    }

    @GetMapping("get/{key}")
    public String getFromRedis(@PathVariable String key){
        return redisUtil.get(key);
    }

    @GetMapping("setString/{key}/{value}")
    public String setString(@PathVariable("key") String key,@PathVariable("value")String value){
        return redisUtil.set(key,value);
    }

    @PostMapping("myLock/{lockKey}")
    public String myLock(@PathVariable String lockKey){
        lockTest(lockKey);
        return null;
    }

    @PostMapping("setCount")
    public String setCount(){
//        jedisPool.getResource().set("count","10");
        redisUtil.set("count","10");
        return null;
    }

    private String lockTest(String key){
        RLock lock = redisson.getLock(key);
        try{
            lock.tryLock(6, TimeUnit.SECONDS);
            if(Integer.valueOf(redisUtil.get("count"))>0){
                redisUtil.incrBy("count",-1L);
                System.out.println("还剩"+redisUtil.get("count"));
            }else{
                System.out.println("没了啊");
            }
        }catch (InterruptedException e){

        } finally {
            lock.unlock();
        }
        return null;
    }
}
