package com.bank.manage.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bank.manage.dos.*;
import com.bank.manage.dto.AcceptDTO;
import com.bank.manage.entity.Result;
import com.bank.manage.service.*;
import com.bank.message.conf.WebSocketServer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/accept")
public class AcceptController {
    @Autowired
    private TermStatusService tss;

    @Autowired
    private SvcStatuslogService ss;

    @Autowired
    private DcStatusService ds;

    @Autowired
    private DcStatuslogService dls;

    @Autowired
    private TransService ts;

    @PostMapping("/post")
    public Result post(@RequestBody String str) {
        Result resultBody;
        JSONObject jsonObject = JSONObject.parseObject(str);
        String code = jsonObject.getString("code");
        switch (code) {
            case "moduleState":
                resultBody = setModuleState(jsonObject);
                if (StringUtils.isNotBlank(jsonObject.getString("SVCSTATUS"))) {
                    setServiceState(jsonObject);
                }
                break;
            case "serviceState":
                resultBody = setServiceState(jsonObject);
                break;
            case "tradeLog":
                resultBody = setTradeLog(jsonObject);
                break;
            default:
                resultBody = new Result("9999", "交易码识别错误");
                break;
        }
        if (resultBody.getRetmsg() == null || resultBody.getRetmsg().equals("")) {
            resultBody.setRetmsg("交易成功");
        }
        return resultBody;
    }

    private Result setModuleState(JSONObject jsonObject) {
        String strtermnum = jsonObject.getString("STRTERMNUM");
        String loop = jsonObject.getString("LOOP");

        List<AcceptDTO> acceptDTOS = JSONArray.parseArray(loop, AcceptDTO.class);
        for (AcceptDTO acceptDTO : acceptDTOS) {
            DcStatusDO dcStatusDO = DcStatusDO.builder()
                    .strTermNum(strtermnum)
                    .ihdwStatus(acceptDTO.getIHDWSTATUS())
                    .strVMName(acceptDTO.getSTRVMNAME())
                    .strVMDetailedStatus(acceptDTO.getSTRVMDETAILEDSTATUS())
                    .strVMDetailedStatus2(acceptDTO.getSTRVMDETAILEDSTATUS2())
                    .strVMDetailedStatus3(acceptDTO.getSTRVMDETAILEDSTATUS3())
                    .build();
            LocalDateTime now = ds.getNow(dcStatusDO);

            DcStatuslogDO dcStatuslogDO = DcStatuslogDO.builder()
                    .strtermnum(strtermnum)
                    .strvmname(acceptDTO.getSTRVMNAME())
                    .strhdwstatus(acceptDTO.getIHDWSTATUS() + "")
//                    .modelName(acceptDTO.getModelName())
                    .modelstatusdesc(acceptDTO.getModelStatusDesc())
                    .dtbegin(now)
                    .build();
            dls.log(dcStatuslogDO);
        }
        return new Result("0000", "交易成功");
    }

    private Result setServiceState(JSONObject jsonObject) {
        TermStatusDO termStatusDO = TermStatusDO.builder()
                .svcstatus(jsonObject.getInteger("SVCSTATUS"))
                .strtermnum(jsonObject.getString("STRTERMNUM"))
                .build();
        LocalDateTime now = tss.getNow(termStatusDO);
        SvcStatuslogDO statuslogDO = SvcStatuslogDO.builder()
                .strtermnum(termStatusDO.getStrtermnum())
                .svcstatus(termStatusDO.getSvcstatus())
                .dtbegin(now).build();
        ss.setSvc(statuslogDO);
        return new Result("0000", "交易成功");
    }

    //(jsonObject.getString("transTime"))
    private Result setTradeLog(JSONObject jsonObject) {
        TransDO transDO = new TransDO();
        if (StringUtils.isNotBlank(jsonObject.getString("deviceId"))) {
            transDO.setDeviceId(jsonObject.getString("deviceId"));
        }
        if (StringUtils.isNotBlank(jsonObject.getString("deviceClass"))) {
            transDO.setDeviceClass(jsonObject.getString("deviceClass"));
        }
        if (StringUtils.isNotBlank(jsonObject.getString("deviceType"))) {
            transDO.setDeviceType(jsonObject.getString("deviceType"));
        }
        if (StringUtils.isNotBlank(jsonObject.getString("transSource"))) {
            transDO.setTransSource(jsonObject.getString("transSource"));
        }
        if (StringUtils.isNotBlank(jsonObject.getString("transType"))) {
            transDO.setTransType(jsonObject.getString("transType"));
        }
        if (StringUtils.isNotBlank(jsonObject.getString("transCode"))) {
            transDO.setTransCode(jsonObject.getString("transCode"));
        }
        if (StringUtils.isNotBlank(jsonObject.getString("hostTsn"))) {
            transDO.setHostTsn(jsonObject.getString("hostTsn"));
        }
        if (StringUtils.isNotBlank(jsonObject.getString("transOrgno"))) {
            transDO.setTransOrgno(jsonObject.getString("transOrgno"));
        }
        if (StringUtils.isNotBlank(jsonObject.getString("ownerOrgno"))) {
            transDO.setOwnerOrgno(jsonObject.getString("ownerOrgno"));
        }
        if (StringUtils.isNotBlank(jsonObject.getString("STRTERMNUM"))) {
            transDO.setTermNum(jsonObject.getString("STRTERMNUM"));
        }
        if (StringUtils.isNotBlank(jsonObject.getString("innerTermNum"))) {
            transDO.setInnerTermNum(jsonObject.getString("innerTermNum"));
        }
        if (StringUtils.isNotBlank(jsonObject.getString("targetAccount"))) {
            transDO.setTargetAccount(jsonObject.getString("targetAccount"));
        }
        if (StringUtils.isNotBlank(jsonObject.getString("tellernum"))) {
            transDO.setTellernum(jsonObject.getString("tellernum"));
        }
        if (StringUtils.isNotBlank(jsonObject.getString("strbanknum"))) {
            transDO.setStrbanknum(jsonObject.getString("strbanknum"));
        }
        if (StringUtils.isNotBlank(jsonObject.getString("strbranchnum"))) {
            transDO.setStrbranchnum(jsonObject.getString("strbranchnum"));
        }
        if (StringUtils.isNotBlank(jsonObject.getString("strsubbranchnum"))) {
            transDO.setStrsubbranchnum(jsonObject.getString("strsubbranchnum"));
        }
        if (StringUtils.isNotBlank(jsonObject.getString("strssbnum"))) {
            transDO.setStrssbnum(jsonObject.getString("strssbnum"));
        }
        if (StringUtils.isNotBlank(jsonObject.getString("transName"))) {
            transDO.setTransName(jsonObject.getString("transName"));
        }
        if (StringUtils.isNotBlank(jsonObject.getString("account"))) {
            transDO.setAccount(jsonObject.getString("account"));
        }
        if (StringUtils.isNotBlank(jsonObject.getString("accountType"))) {
            transDO.setAccountType(jsonObject.getString("accountType"));
        }
        if (StringUtils.isNotBlank(jsonObject.getString("returnCode"))) {
            transDO.setReturnCode(jsonObject.getString("returnCode"));
        }
        if (StringUtils.isNotBlank(jsonObject.getString("returnDesc"))) {
            transDO.setReturnDesc(jsonObject.getString("returnDesc"));
        }
        if (StringUtils.isNotBlank(jsonObject.getString("transTime"))) {
            transDO.setTransTime(LocalDateTime.parse(jsonObject.getString("transTime")
                    , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        if (StringUtils.isNotBlank(jsonObject.getString("transStatus"))) {
            transDO.setTransStatus(jsonObject.getString("transStatus"));
        }

        //使用websocket推送给前端
        try {
            String s = JSONObject.toJSONString(transDO);
            WebSocketServer.sendInfo(s, "10086");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //入库
        boolean save = ts.save(transDO);
        //返回成功
        return new Result("0000", "交易成功");
    }
}
