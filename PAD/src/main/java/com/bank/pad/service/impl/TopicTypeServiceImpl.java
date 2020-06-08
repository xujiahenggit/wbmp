package com.bank.pad.service.impl;

import com.bank.pad.dos.TopicTypeDO;
import com.bank.pad.vo.TopicTypeVO;
import com.bank.pad.dao.TopicTypeDao;
import com.bank.pad.service.TopicTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务实现类
 *
 * @author zhaozhongyuan
 * @since 2020-04-28
 */
@Service
public class TopicTypeServiceImpl extends ServiceImpl<TopicTypeDao, TopicTypeDO> implements TopicTypeService {

	@Override
	public IPage<TopicTypeVO> selectTopicTypePage(IPage<TopicTypeVO> page, TopicTypeVO topicType) {
		return page.setRecords(baseMapper.selectTopicTypePage(page, topicType));
	}

}
