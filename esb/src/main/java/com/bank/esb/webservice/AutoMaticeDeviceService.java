package com.bank.esb.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @Author: cq
 * @Date: 2020/7/2
 */
@WebService
public interface AutoMaticeDeviceService {

    @WebMethod
    @WebResult(name = "responseXml")
    String esbService(@WebParam(name = "requestXml") String requestXml);
}
