package com.bank.core.utils;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 流水码生成工具
 * ClassName: SerialNumberUtil
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/07/21 15:48:08
 */
@Component
@Slf4j
public class SerialNumberUtil {

    @Resource
    ConfigFileReader configFileReader;

    @Resource
    NetUtil netUtil;

    @Resource
    private RedisTemplate<String, String> stringRedisTemplate;

    private String icopSerialNumber = "ICOP_SERIAL_NUMBER";

    //网点综合管理平台渠道号
    private String channelId = "812";

    private String initValue = "0";

    //最大流水号
    private long maxSerialNumber = 99999999;

    public SerialNumberUtil(RedisTemplate<String, String> stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 获取ICOP请求流水码
     * @return
     */
    public String getICOPSerialNumber() {
        BoundValueOperations<String, String> boundValueOperations = stringRedisTemplate.boundValueOps(icopSerialNumber);
        boundValueOperations.setIfAbsent(initValue);

        List<Object> results = stringRedisTemplate.executePipelined(new RedisCallback<List<Object>>() {

            @Override
            public List<Object> doInRedis(RedisConnection connection) throws DataAccessException {
                connection.incr(boundValueOperations.getKey().getBytes());
                connection.time();
                return null;
            }
        });

        // 自增后值
        long value = (Long) results.get(0);
        //达到最大序列号时
        if (value == maxSerialNumber) {
            //置0
            boundValueOperations.set(initValue);
        }
        // 服务器时间
        Date now = new Date((Long) results.get(1));

        StringBuffer sb = new StringBuffer();
        //日期
        sb.append(DateUtil.format(now, DatePattern.PURE_DATE_PATTERN));
        //渠道号
        sb.append(channelId);

        //节点号
        String node = "91";
        //uat环境使用92作为节点
        if (configFileReader.getSpringProfile().equals("uat")) {
            node = "92";
        }
        //生产环境使用ip地址匹配
        //192.168.4.150 --> 91
        //192.168.4.151 --> 92
        //192.168.4.152 --> 93 灰度
        else if (configFileReader.getSpringProfile().equals("pro")) {
            String proIp = netUtil.getProIp();
            if (proIp.equals("192.168.4.151")) {
                node = "92";
            }
            else if (proIp.equals("192.168.4.152")) {
                node = "93";
            }
        }
        sb.append(node);

        //序列号
        sb.append(new DecimalFormat("00000000").format(value));

        String serialNumber = sb.toString();
        //log.info("ICOP接口请求序列号：{}", serialNumber);
        return serialNumber;
    }
}
