package com.bank.message.controller;


import com.bank.message.service.JmsService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;


/**
 * @author ZHAO
 */
@Api(value = "ActiveMQ相关操作", tags = {"ActiveMQ相关操作"})
@RestController
@Slf4j
@RequestMapping("/jms")
public class Producer {

    @Resource
    JmsService jmsService;


    @ApiOperation(value = "服务器向指定主题【topic_机构号】发送消息，用于向mq发送消息点播或者切换节目")
    @PostMapping("/topic")
    public boolean topic(@RequestBody Map dataMap) {
        return jmsService.sendMsg(dataMap);
    }

}
