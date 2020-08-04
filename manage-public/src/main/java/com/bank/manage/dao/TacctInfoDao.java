package com.bank.manage.dao;

import com.bank.manage.dos.TacctInfoDO;
import com.bank.manage.vo.TacctInfoVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 帐号及帐号参数
 *
 * @author
 * @date 2020-7-4
 */
public interface TacctInfoDao extends BaseMapper<TacctInfoDO> {

    List<TacctInfoVO> selectPageList(IPage page, @Param("model") TacctInfoDO tacctInfoDO);
}
