package com.bank.esb.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * ClassName: WSDemoService
 *
 * @author Yanwen D. Ding
 *
 * Copyright © 2016 Yusys Technologies Co., Ltd.
 *
 * All Rights Reserved
 *
 * http://www.yusys.com.cn
 *
 * Create Time: 2020/07/02 11:18:33
 */
@WebService
public interface WSDemoService {

    @WebMethod
    String sayHello(@WebParam(name = "userName") String userName);
}
