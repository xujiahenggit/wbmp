package com.bank.core.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class NetUtil {

    @Resource
    ConfigFileReader configFileReader;

    public String getUrlSuffix(String contextPath) {
        String ip = "";
        if (configFileReader.getSpringProfile().equals("dev")) {
            ip = configFileReader.getApplicationIp();
        }
        //生产环境与sit环境使用域名
        else if (StringUtils.indexOf("sit,pro", configFileReader.getSpringProfile()) > -1) {
            return "http://" + configFileReader.getApplicationIp() + "/" + contextPath;
        }
        else {
            ip = cn.hutool.core.net.NetUtil.getLocalhostStr();
        }
        return "http://" + ip + ":" + configFileReader.getApplicationPort() + contextPath;
    }

    public String getUrlSuffix() {
        String ip = "";
        if (configFileReader.getSpringProfile().equals("dev")) {
            ip = configFileReader.getApplicationIp();
        }
        //生产环境与sit环境使用域名
        else if (StringUtils.indexOf("sit,pro", configFileReader.getSpringProfile()) > -1) {
            return "http://" + configFileReader.getApplicationIp() + "/" + configFileReader.getApplicationContextPath();
        }
        else {
            ip = cn.hutool.core.net.NetUtil.getLocalhostStr();
        }
        return "http://" + ip + ":" + configFileReader.getApplicationPort() + configFileReader.getApplicationContextPath();
    }

    private String getProIp() {
        String ip = "";
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (inetAddress.isLoopbackAddress()) {//回路地址，如127.0.0.1
                        continue;
                    }
                    else if (inetAddress.isLinkLocalAddress()) {//169.254.x.x
                        continue;
                    }
                    else {
                        if (StringUtils.startsWith(inetAddress.toString(), "192.168.4")) {
                            ip = inetAddress.toString();
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            System.out.println("获取ip地址失败！");
        }
        return ip == "" ? "192.168.4.150" : ip;
    }

}
