package com.bank.pad.service;

import com.bank.pad.dos.TopicTypeDO;
import com.bank.pad.vo.TopicTypeVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author zhaozhongyuan
 * @since 2020-04-28
 */
public interface TopicTypeService extends IService<TopicTypeDO> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param topicType
	 * @return
	 */
	IPage<TopicTypeVO> selectTopicTypePage(IPage<TopicTypeVO> page, TopicTypeVO topicType);

}
