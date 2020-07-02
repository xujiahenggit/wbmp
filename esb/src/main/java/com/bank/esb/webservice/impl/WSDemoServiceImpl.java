package com.bank.esb.webservice.impl;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.bank.esb.webservice.WSDemoService;

@Service
@WebService(name = "demoservice", targetNamespace = "http://webservice.wbmp.com")
public class WSDemoServiceImpl implements WSDemoService {

    @Override
    public String sayHello(String userName) {
        return "Hello " + userName;
    }

}
