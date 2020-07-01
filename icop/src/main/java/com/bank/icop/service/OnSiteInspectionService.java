package com.bank.icop.service;


import java.util.List;
import java.util.Map;

/**
 * SOAP调用第三方接口日志 服务类
 *
 * @author zhaozhongyuan
 * @since 2020-06-09
 */
public interface OnSiteInspectionService {


    List<Map> inspectionTaskList(String userId);
}
