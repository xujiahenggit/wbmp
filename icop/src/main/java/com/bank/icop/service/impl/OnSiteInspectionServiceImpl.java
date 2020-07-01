package com.bank.icop.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.BizException;
import com.bank.icop.service.OnSiteInspectionService;
import com.bank.icop.util.SoapUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SOAP调用第三方接口 现场检查实现类
 *
 * @author zhaozhongyuan
 * @since 2020-06-09
 */
@Service
public class OnSiteInspectionServiceImpl implements OnSiteInspectionService {


    @Override
    public List<Map> inspectionTaskList(String userId) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo", userId);//4095
        List<Map> data = getIcopTagList(parmMap,
                "FXYJ11001",
                "获取运营检查任务列表",
                1,
                "返回状态  -1:参数为空 ,  0:用户不存在");
//        List<OnSiteInspectionTaskVO> list = new ArrayList<>();
//        data.stream()
//                .forEach(e -> list.add(new OnSiteInspectionTaskVO(
//                        e.get("TASKPK"),
//                        e.get("TASKYEAR"),
//                        e.get("TASKNAME"),
//                        e.get("TASKSTARTDATE"),
//                        e.get("TASKENDDATE"),
//                        e.get("NUMBER")
//                )));
        return data;
    }


    private List<Map> getIcopTagData(Map<String, Object> parmMap, String serviceCode, String serviceName, Integer rightCode, String errMsg, String tag) {
        Map report = getReport(parmMap, serviceCode, serviceName, rightCode, errMsg);
        Object list = report.get(tag);
        if (list != null) {
            List<Map> tagData = null;
            try {
                tagData = (List<Map>) list;
            } catch (Exception e) {
                throw new BizException(tag + "标签数据转List<Map>出错，" + e.getMessage());
            }
            return tagData;
        } else {
            throw new BizException("返回值不包含" + tag + "标签！");
        }
    }


    private List<Map> getIcopTagList(Map<String, Object> parmMap, String serviceCode, String serviceName, Integer rightCode, String errMsg) {
        return getIcopTagData(parmMap, serviceCode, serviceName, rightCode, errMsg, "List");
    }

    private Map getReport(Map<String, Object> parmMap, String serviceCode, String serviceName, Integer rightCode, String errMsg) {
        Map report = null;
        try {
            report = SoapUtil.sendReport(serviceCode, "812", parmMap);
        } catch (Exception e) {
            throw new BizException(serviceName + "报错！" + e.getMessage());
        }
        if (CollectionUtil.isEmpty(report)) {
            throw new BizException("返回报文为空！");
        }
        checkStatus(serviceName, rightCode, errMsg, report);
        return report;
    }

    private void checkStatus(String serviceName, Integer rightCode, String errMsg, Map report) {
        Object status = report.get("status");
        if (StrUtil.isBlankIfStr(status)) {
            throw new BizException(serviceName + "接口返回状态码为空！");
        } else {
            if (!status.equals(String.valueOf(rightCode))) {
                throw new BizException(errMsg + "；该接口返回状态码为：" + status + "！");
            }
        }
    }


}
