package com.bank.icop.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.BizException;
import com.bank.icop.service.OnSiteInspectionService;
import com.bank.icop.util.SoapUtil;
import com.bank.icop.vo.OnSiteInspectionTaskVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<OnSiteInspectionTaskVO> inspectionTaskList(String userId) {
        Map<String, Object> parmMap = new HashMap<>();
        parmMap.put("userNo",userId);//4095
        Map report = null;
        try {
            report = SoapUtil.sendReport("FXYJ11001","812",parmMap);
        } catch (Exception e) {
            throw new BizException("获取运营检查任务列表报错！"+e.getMessage());
        }

        Object status = report.get("status");
        if (StrUtil.isBlankIfStr(status)){
            throw new BizException("获取运营检查任务列表接口返回状态码为空！");
        }else {
            if (!status.equals("1")){
                throw new BizException("返回状态  -1:参数为空 ,  0:用户不存在；该接口返回状态码为："+status+"！");
            }
        }

        List<OnSiteInspectionTaskVO> list = new ArrayList<>();
        if(CollectionUtil.isNotEmpty(report)){
            List<Map> data = (List<Map>) report.get("List");
            data.stream()
                    .forEach(e->list.add(new OnSiteInspectionTaskVO(
                            e.get("TASKPK"),
                            e.get("TASKYEAR"),
                            e.get("TASKNAME"),
                            e.get("TASKSTARTDATE"),
                            e.get("TASKENDDATE"),
                            e.get("NUMBER")
                            )));
        }
        return list;
    }


}
