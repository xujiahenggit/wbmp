package com.bank.message.service;

import com.bank.message.dos.WebsocketConnsDO;
import com.bank.message.vo.WebsocketConnsVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.Map;

/**
 *  服务类
 *
 * @author zhaozhongyuan
 * @since 2020-05-12
 */
public interface WebsocketConnsService extends IService<WebsocketConnsDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param websocketConns
	 * @return
	 */
	IPage<WebsocketConnsVO> selectWebsocketConnsPage(IPage<WebsocketConnsVO> page, WebsocketConnsVO websocketConns);

    void SaveGameWebsocketInfo(String sid, Map map);

	void publishToRedis(Map message);
}
