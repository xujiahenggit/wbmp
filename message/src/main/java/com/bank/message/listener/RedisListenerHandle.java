package com.bank.message.listener;

import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.BizException;
import com.bank.message.conf.WebSocketServer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class RedisListenerHandle {

    public void receiveMessage(String message){
        System.out.println("接收消息："+message);
        ObjectMapper mapper = new ObjectMapper();
        Map map = null;
        try {
            map = mapper.readValue(message, Map.class);
        } catch (IOException e) {
            String errorMsg = "webSocket收到字符串转map失败。";
            throw new BizException(errorMsg);
        }
        Object sid = map.get("sid");
        if (StrUtil.isBlankIfStr(sid)){
            throw new BizException("sid：消息发送者id不能为空");
        }
        String sidStr = sid.toString();

        Object targerSid = map.get("targerSid");
        if (StrUtil.isBlankIfStr(targerSid)){
            WebSocketServer.sendInfo("targerSid：消息接收者id不能为空",sidStr);
            return;
        }
        WebSocketServer.sendInfo(message,targerSid.toString());


    }

}
