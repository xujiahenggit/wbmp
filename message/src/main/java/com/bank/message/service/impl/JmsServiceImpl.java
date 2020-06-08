package com.bank.message.service.impl;

import com.bank.core.entity.BizException;
import com.bank.message.service.JmsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Topic;
import java.util.Map;

/**
 * @author ZHAO
 */
@Slf4j
@Service
public class JmsServiceImpl implements JmsService {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Override
    public boolean sendMsg(Map dataMap) {
        Object orgId = dataMap.get("orgId");
        if (orgId == null) {
            throw new BizException("orgId不能为空");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String topicName = "topic_" + orgId;
        Topic topic = () -> topicName;
        try {
            String jsonMsg = objectMapper.writeValueAsString(dataMap);
            jmsMessagingTemplate.convertAndSend(topic, jsonMsg);
        } catch (MessagingException e) {
            log.info("消息发送到ActiveMQ时遇到错误，发送失败。");
            return false;
        }catch (JsonProcessingException e) {
            log.info("map转json出错");
            return false;
        }
        return true;
    }
}
