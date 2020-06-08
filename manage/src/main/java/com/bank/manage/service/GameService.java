package com.bank.manage.service;

import com.bank.manage.dos.GameDO;
import com.bank.manage.vo.GameVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 *  服务类
 *
 * @author zhaozhongyuan
 * @since 2020-05-06
 */
public interface GameService extends IService<GameDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param grame
	 * @return
	 */
	IPage<GameVO> selectGamePage(IPage<GameVO> page, GameVO grame);

    List<GameDO> getGameRankByType(String type, int limit);

	List<GameDO> getGameRankInIds(String type,String ids);
}
