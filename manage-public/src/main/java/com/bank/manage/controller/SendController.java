package com.bank.manage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bank.auth.base.BaseController;
import com.bank.core.entity.TokenUserInfo;
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
    public JSONObject sendToBip(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        String strRequest = JSON.toJSONString(map);
        TokenUserInfo tokenUserInfo = getCurrentUserInfo(request);
//        String response = null;
//        try {
//            response = HttpUtil.sendPost("www.baidu.com", map);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        JSONObject jsonObject = JSON.parseObject(response);
//        Integer responseStatus =
//                jsonObject.getString("code").equals("0000") ? 1 : 2;
        CommandlogDO build = CommandlogDO.builder()
//                .responseStatus(responseStatus)
//                .response(response)
                .responseStatus(0)
                .response("test")
                .request(strRequest)
                .operatorNum(tokenUserInfo.getUserId())
                .operatorName(tokenUserInfo.getUserName())
                .termIp((String) map.get("termIp"))
                .termNum((String) map.get("termNum"))
                .type((String) map.get("type"))
                .name((String) map.get("name"))
                .pendStatus(0)
                .createdAt(LocalDateTime.now())
                .build();

        cs.save(build);
//        return JSON.parseObject(response);
        return getStr();
    }

    private JSONObject getStr() {
        String s;
//         s = "{\"processID\":\"test\",\"processName\":\"测试\",\"processUri\":\"http://www.baidu.com\",\"cpuRate\":\"99%\",\"memoryUsage\":\"99%\",\"memoryName\":\"硬盘\",\"total\":\"100\",\"usd\":\"99\",\"surplus\":\"1\"}";
        s = "{\"list\":[{\"funcModel\":\"测试1\",\"version\":\"1.0\",\"filename\":\"qwe\"},{\"funcModel\":\"测试2\",\"version\":\"1.2\",\"filename\":\"asd\"},{\"funcModel\":\"测试2\",\"version\":\"1.3\",\"filename\":\"zxc\"}]}";
        JSONObject jsonObject = JSON.parseObject(s);
        return jsonObject;
    }
}

