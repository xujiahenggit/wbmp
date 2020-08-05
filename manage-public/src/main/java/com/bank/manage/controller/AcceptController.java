package com.bank.manage.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bank.manage.dos.*;
import com.bank.manage.dto.AcceptDTO;
import com.bank.manage.entity.Result;
import com.bank.manage.service.*;
import com.bank.message.conf.WebSocketServer;
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
        if (code.equals("moduleState")) {
            resultBody = setModuleState(jsonObject);
        } else if (code.equals("serviceState")) {
            resultBody = setServiceState(jsonObject);
        } else if (code.equals("tradeLog")) {
            resultBody = setTradeLog(jsonObject);
        } else {
            resultBody = new Result("9999", "交易码识别错误");
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
                    .strTermNum(strtermnum)
                    .strVMName(acceptDTO.getSTRVMNAME())
                    .strhdwstatus(acceptDTO.getIHDWSTATUS() + "")
                    .modelName(acceptDTO.getModelName())
                    .modelStatusDesc(acceptDTO.getModelStatusDesc())
                    .dtBegin(now)
                    .build();
            dls.log(dcStatuslogDO);
        }
        return new Result("0000", "测试");
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
        return new Result("0000", "");
    }

    //(jsonObject.getString("transTime"))
    private Result setTradeLog(JSONObject jsonObject) {
        TransDO transDO = TransDO.builder()
                .transTime(LocalDateTime.parse(jsonObject.getString("transTime")
                        , DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .transStatus(jsonObject.getString("transStatus"))
                .returnDesc(jsonObject.getString("returnDesc"))
                .returnCode(jsonObject.getString("returnCode"))
                .accountType(jsonObject.getString("accountType"))
                .account(jsonObject.getString("account"))
                .amount(Float.parseFloat(jsonObject.getString("amount")))
                .transName(jsonObject.getString("transName"))
                .strssbnum(jsonObject.getString("strssbnum"))
                .strsubbranchnum(jsonObject.getString("strsubbranchnum"))
                .strbranchnum(jsonObject.getString("strbranchnum"))
                .strbanknum(jsonObject.getString("strbanknum"))
                .tellernum(jsonObject.getString("tellernum"))
                .targetAccount(jsonObject.getString("targetAccount"))
                .innerTermNum(jsonObject.getString("innerTermNum"))
                .termNum(jsonObject.getString("STRTERMNUM"))
                .ownerOrgno(jsonObject.getString("ownerOrgno"))
                .transOrgno(jsonObject.getString("transOrgno"))
                .hostTsn(jsonObject.getString("hostTsn"))
                .transCode(jsonObject.getString("transCode"))
                .transType(jsonObject.getString("transType"))
                .transSource(jsonObject.getString("transSource"))
                .deviceType(jsonObject.getString("deviceType"))
                .deviceClass(jsonObject.getString("deviceClass"))
                .deviceId(jsonObject.getString("deviceId")).build();

        String s = JSONObject.toJSONString(transDO);
        WebSocketServer.sendInfo(s, "");

        boolean save = ts.save(transDO);
        return new Result("0000", "");
    }
}
