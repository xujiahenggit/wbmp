package com.bank.message.conf;

import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.BizException;
import com.bank.core.utils.ConfigFileReader;
import com.bank.message.listener.HeartbeatListener;
import com.bank.message.service.WebsocketConnsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket/{sid}")
@Component
@Slf4j
public class WebSocketServer {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //区分连接类型
    private Integer code;

    //接收sid
    private String sid = "";

    /**
     * 连接建立成功调用的方法
     *
     * @param session
     * @param code    1:心跳；2：游戏
     **/
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.session = session;
        webSocketSet.add(this); //加入set中
        addOnlineCount(); //在线数加1
        log.info("有新webSocket连接{}加入", sid);
        this.sid = sid;
        sendMessage("连接成功");
    }


    /**
     * 连接关闭调用的方法
     * <p>
     * 收到客户端消息后调用的方法
     ***/
    @OnClose
    public void onClose() {
        webSocketSet.remove(this); //从set中删除
        subOnlineCount(); //在线数减1
        log.info("有一连接关闭！当前服务器webSocket在线连接数为" + getOnlineCount());
    }

    private static HeartbeatListener heartbeatListener;

    private static WebsocketConnsService websocketConnsService;

    private static ConfigFileReader configFileReader;

    @Autowired
    public void setHeartbeatListener(HeartbeatListener heartbeatListener) {
        WebSocketServer.heartbeatListener = heartbeatListener;
    }

    @Autowired
    public void setWebsocketConnsService(WebsocketConnsService websocketConnsService) {
        WebSocketServer.websocketConnsService = websocketConnsService;
    }

    @Autowired
    public void setYmlProperties(ConfigFileReader configFileReader) {
        WebSocketServer.configFileReader = configFileReader;
    }

    /**
     * 收到客户端消息后调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口" + sid + "的信息:" + message);
        ObjectMapper mapper = new ObjectMapper();
        Map map = null;
        try {
            map = mapper.readValue(message, Map.class);
        } catch (IOException e) {
            String errorMsg = "webSocket收到字符串转map失败。";
            sendInfo(errorMsg, sid);
            throw new BizException(errorMsg);
        }
        Object code = map.get("CODE");
        if (StrUtil.isBlankIfStr(code)) {
            String errorMsg = "code字段不能为空。";
            sendInfo(errorMsg, sid);
            throw new BizException(errorMsg);
        }

        this.code = Integer.valueOf(code.toString());


        if (this.code == 1) {
            //心跳处理：更新在线表，在线状态表信息
            heartbeatListener.handle(map);
        } else if (this.code == 2) {
            Object macObj = map.get("targerSid");
            if (StrUtil.isBlankIfStr(macObj)) {
                sendMessage("参数targerSid不能为空");
            }
            String targetSid = macObj.toString();
            //判断目标是否在此服务器上
            long count = webSocketSet.stream().filter(socket -> socket.sid.equals(targetSid)).count();
            if (count == 0) {
             //转发给redis
                websocketConnsService.publishToRedis(map);
            } else {
                sendInfo(message, targetSid);
            }
        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("webSocket连接发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.info("webSocket向{}发送消息失败，{}", sid, e.getMessage());
        }
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message, @PathParam("sid") String sid) {
        log.info("推送消息到窗口" + sid + "，推送内容:" + message);
        for (WebSocketServer item : webSocketSet) {
            //这里可以设定只推送给这个sid的，为null则全部推送
            if (sid == null) {
                item.sendMessage(message);
            } else if (item.sid.equals(sid)) {
                item.sendMessage(message);
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    public static CopyOnWriteArraySet<WebSocketServer> getWebSocketSet() {
        return webSocketSet;
    }
}
