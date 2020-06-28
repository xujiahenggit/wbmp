package com.bank.manage.service;

import com.bank.core.entity.TokenUserInfo;
import com.bank.manage.dos.PartorlRecordDO;
import com.bank.manage.dto.PartorlRecordDto;
import com.bank.manage.dto.PartorlRecordHeadDto;
import com.bank.manage.excel.partorl.PartorlExcelEntity;
import com.bank.manage.vo.PartorlRecordQueryVo;
import com.bank.manage.vo.partorlRecord.PartorlRecordVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author: Andy
 * @Date: 2020/5/12 15:09
 */
public interface PartorlRecordService extends IService<PartorlRecordDO> {
    /**
     * 分页查询 巡查记录列表
     * @param partorlRecordQueryVo 查询参数
     * @return
     */
    IPage<PartorlRecordDto> getPageRecord(PartorlRecordQueryVo partorlRecordQueryVo, TokenUserInfo tokenUserInfo);

    /**
     * 保存巡查内容信息
     * @param partorlRecordVo 巡查内容信息
     * @param tokenUserInfo 当前用户信息
     * @return
     */
    boolean saveRecord(PartorlRecordVo partorlRecordVo, TokenUserInfo tokenUserInfo);

    /**
     * 提交巡查内容信息
     * @param partorlRecordVo 巡查内容信息
     * @param tokenUserInfo 当前登录用户信息
     * @return
     */
    boolean submitPartorlRecord(PartorlRecordVo partorlRecordVo, TokenUserInfo tokenUserInfo);

    /**
     * 用ID 获取巡查记录信息
     * @param recordId 巡查记录ID
     * @return
     */
    PartorlExcelEntity getRecordById(Integer recordId);

    /**
     * 用ID 获取巡查内容填报头
     * @param processId 待办编号
     * @param tokenUserInfo 当前登录用户
     * @return
     */
    PartorlRecordHeadDto getHeadInfo(Integer processId, TokenUserInfo tokenUserInfo);
}
