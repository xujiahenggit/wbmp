package com.bank.message.service.impl;

import cn.hutool.core.net.NetUtil;
import com.bank.core.utils.RedisUtil;
import com.bank.message.dao.WebsocketConnsDao;
import com.bank.message.dos.WebsocketConnsDO;
import com.bank.message.service.WebsocketConnsService;
import com.bank.message.vo.WebsocketConnsVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 服务实现类
 *
 * @author zhaozhongyuan
 * @since 2020-05-12
 */
@Service
public class WebsocketConnsServiceImpl extends ServiceImpl<WebsocketConnsDao, WebsocketConnsDO> implements WebsocketConnsService {

    @Override
    public IPage<WebsocketConnsVO> selectWebsocketConnsPage(IPage<WebsocketConnsVO> page, WebsocketConnsVO websocketConns) {
        return page.setRecords(baseMapper.selectWebsocketConnsPage(page, websocketConns));
    }

    @Override
    public void SaveGameWebsocketInfo(String sid, Map map) {
        WebsocketConnsDO websocketConnsDO = WebsocketConnsDO.builder()
                .mac(sid)
                .clientId(map.get("ClientId").toString())
                .roleId(Integer.valueOf(map.get("roleId").toString()))
                .serverIp(NetUtil.getLocalhostStr())
                .createTime(LocalDateTime.now()).build();
        saveOrUpdate(websocketConnsDO);
    }

    @Resource
    RedisUtil redisUtil;

    @Override
    public void publishToRedis(Map message) {
        redisUtil.publishMsg(message);
    }

}
