package com.bank.icop.service;


import com.bank.icop.vo.OnSiteInspectionTaskVO;

import java.util.List;

/**
 * SOAP调用第三方接口日志 服务类
 *
 * @author zhaozhongyuan
 * @since 2020-06-09
 */
public interface OnSiteInspectionService {


    List<OnSiteInspectionTaskVO> inspectionTaskList(String userId);
}
