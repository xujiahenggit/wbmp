package com.bank.core.utils;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class NetUtil {

    @Resource
    ConfigFileReader configFileReader;

    public String getUrlSuffix(String contextPath) {
        String ip = cn.hutool.core.net.NetUtil.getLocalhostStr();
        if (configFileReader.getSpringProfile().equals("dev")){
            ip = configFileReader.getApplicationIp();
        }
        return "http://"+ip+":"+configFileReader.getApplicationPort()+contextPath;
    }

    public String getUrlSuffix() {
        String ip = cn.hutool.core.net.NetUtil.getLocalhostStr();
        if (configFileReader.getSpringProfile().equals("dev")){
            ip = configFileReader.getApplicationIp();
        }
        return "http://"+ip+":"+configFileReader.getApplicationPort()+configFileReader.getApplicationContextPath();
    }

}
