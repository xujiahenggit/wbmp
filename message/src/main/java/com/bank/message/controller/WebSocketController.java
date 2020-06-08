package com.bank.message.controller;

import cn.hutool.core.util.StrUtil;
import com.bank.message.conf.WebSocketServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(value="webSocket相关接口",tags={"webSocket相关接口"})
@RestController
@RequestMapping("/socket")
public class WebSocketController {

    @ApiOperation(value = "服务器向指定客户端推送webSocket消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "客户端id", required = true),
            @ApiImplicitParam(name = "message", value = "数据", required = true),
    })
    @PostMapping("/push/{cid}")
    public void pushToWeb(@PathVariable String cid, String message) {
        if (!StrUtil.isBlankIfStr(cid)){
            WebSocketServer.sendInfo(message, cid);
            log.info("消息成功推送到webSocket连接ID为 "+cid+" 的设备。");
        }else {
            log.info("cid为空，无法推送webSocket消息。");
        }
    }

    @ApiOperation(value = "获取本服务器webSocket的在线连接数")
    @GetMapping("/countOnLine")
    public Object countOnLine() {
        return WebSocketServer.getOnlineCount();
    }
}