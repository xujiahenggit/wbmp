package com.bank.core.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Value("${spring.redis.expireTime}")
    private int expireTime;//默认过期时间

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    //============================String=============================
    /**
     * 普通缓存获取
     * @param key 键
     * @return 值
     */
    public Object get(String key){
        return key==null?null:redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key,Object value) {
        return set(key, value, expireTime);
    }

    /**
     * 更新过期时间
     * @param key 键
     * @param minutes 分钟
     * @return true成功 false失败
     */
    public boolean expire(String key,long minutes) {
       return redisTemplate.expire(key,minutes,TimeUnit.MINUTES);

    }

    /**
     * 更新过期时间
     * @param key 键
     * @param minutes 分钟
     * @return true成功 false失败
     */
    public boolean expire(String key) {
       return expire(key,expireTime);

    }

    /**
     * 更新过期时间
     * @param key 键
     * @return true存在 false不存在
     */
    public boolean hasKey(String key) {
       return redisTemplate.hasKey(key);

    }

    /**
     * 普通缓存放入并设置时间
     * @param key 键
     * @param value 值
     * @param time 时间(分钟) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key,Object value,long time){
        try {
            if(time>0){
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.MINUTES);
            }else{
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void publishMsg(Object msg){
        redisTemplate.convertAndSend("webSocketShare",msg);
    }
}
