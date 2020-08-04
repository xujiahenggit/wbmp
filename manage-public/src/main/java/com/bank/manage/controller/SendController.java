package com.bank.manage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bank.auth.base.BaseController;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.utils.HttpUtil;
import com.bank.manage.dos.CommandlogDO;
import com.bank.manage.service.CommandlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/send")
public class SendController extends BaseController {

    @Autowired
    private CommandlogService cs;

    @PostMapping("/tobip")
    public String sendToBip(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        String strRequest = JSON.toJSONString(map);
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
        String response = null;
        try {
            response = HttpUtil.sendPost("www.baidu.com", map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = JSON.parseObject(response);
        Integer responseStatus =
                jsonObject.getString("code").equals("0000") ? 1 : 2;
        CommandlogDO build = CommandlogDO.builder()
                .responseStatus(responseStatus)
                .response(response)
                .request(strRequest)
                .operatorNum(tokenUserInfo.getUserId())
                .operatorName(tokenUserInfo.getUserName())
                .termIp((String) map.get("termIp"))
                .termNum((String) map.get("termNum"))
                .type((String) map.get("type"))
                .name((String) map.get("name"))
                .createdAt(LocalDateTime.now())
                .build();

        boolean save = cs.save(build);
        return response;
    }
}

