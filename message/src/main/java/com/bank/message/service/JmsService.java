package com.bank.message.service;

import java.util.Map;

public interface JmsService {
    boolean sendMsg(Map dataMap);
}
