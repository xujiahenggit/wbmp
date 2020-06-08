package com.bank.manage.service.impl;

import com.bank.manage.dos.GameDO;
import com.bank.manage.vo.GameVO;
import com.bank.manage.dao.GameDao;
import com.bank.manage.service.GameService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import java.util.List;

/**
 *  服务实现类
 *
 * @author zhaozhongyuan
 * @since 2020-05-06
 */
@Service
public class GameServiceImpl extends ServiceImpl<GameDao, GameDO> implements GameService {


	@Resource
	private GameDao gameDao;

	@Override
	public IPage<GameVO> selectGamePage(IPage<GameVO> page, GameVO grame) {
		return page.setRecords(baseMapper.selectGamePage(page, grame));
	}

	@Override
	public List<GameDO> getGameRankByType(String type, int limit) {
		return gameDao.getGameRankByType(type,limit);
	}

	@Override
	public List<GameDO> getGameRankInIds(String type, String ids) {
		return gameDao.getGameRankInIds(type,ids);
	}

}
