package com.bank.manage.service.impl;

import com.bank.core.entity.BizException;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.sysConst.NewProcessStatusFile;
import com.bank.core.sysConst.SysStatus;
import com.bank.manage.dao.PartorlProcessDao;
import com.bank.manage.dos.PartorlProcessDO;
import com.bank.manage.service.PartorlProcessService;
import com.bank.manage.vo.PartorlProcessQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/27 21:06
 */
@Service
public class PartorlProcessServiceImpl extends ServiceImpl<PartorlProcessDao, PartorlProcessDO> implements PartorlProcessService {

    @Resource
    private PartorlProcessDao partorlProcessDao;


    /**
     * 待处理 列表
     * @param partorlProcessQueryVo 查询参数
     * @param tokenUserInfo 当前登录用户
     * @return
     */
    @Override
    public IPage<PartorlProcessDO> getWaitList(PartorlProcessQueryVo partorlProcessQueryVo, TokenUserInfo tokenUserInfo) {
       try{
           QueryWrapper<PartorlProcessDO> queryWrapper=getQueryModel(partorlProcessQueryVo,tokenUserInfo,SysStatus.QUERY_TYPE_WAIT);
           Page<PartorlProcessDO> page=new Page<>(partorlProcessQueryVo.getPageIndex(),partorlProcessQueryVo.getPageSize());
           return partorlProcessDao.selectPage(page,queryWrapper);
       }catch (Exception e){
           throw new BizException("获取待处理列表失败！");
       }
    }

    /**
     * 已处理列表
     * @param partorlProcessQueryVo 查询参数
     * @param tokenUserInfo 当前登录用户
     * @return
     */
    @Override
    public IPage<PartorlProcessDO> getAreadyList(PartorlProcessQueryVo partorlProcessQueryVo, TokenUserInfo tokenUserInfo) {
        try{
            QueryWrapper<PartorlProcessDO> queryWrapper=getQueryModel(partorlProcessQueryVo,tokenUserInfo,SysStatus.QUERY_TYPE_WAIT);
            Page<PartorlProcessDO> page=new Page<>(partorlProcessQueryVo.getPageIndex(),partorlProcessQueryVo.getPageSize());
            return partorlProcessDao.selectPage(page,queryWrapper);
        }catch (Exception e){
            throw new BizException("获取已处理列表失败！");
        }
    }

    /**
     * 构建查询模型
     * @param partorlProcessQueryVo 查询参数
     * @param tokenUserInfo 当前登录用户
     * @param queryType 查询类型  10：未办；20：已办；
     * @return
     */
    private QueryWrapper<PartorlProcessDO> getQueryModel(PartorlProcessQueryVo partorlProcessQueryVo,TokenUserInfo tokenUserInfo,String queryType){
        QueryWrapper<PartorlProcessDO> queryWrapper=new QueryWrapper<>();
        if(SysStatus.QUERY_TYPE_WAIT.equals(queryType)){
            queryWrapper.eq("PARTORL_PROCESS_STATE", NewProcessStatusFile.PROCESS_WAIT);
        }else if(SysStatus.QUERY_TYPE_AREADY.equals(queryType)){
            queryWrapper.eq("PARTORL_PROCESS_STATE",NewProcessStatusFile.PROCESS_PASS);
        }
        queryWrapper.eq("PARTORL_PROCESS_ORG_ID ",tokenUserInfo.getOrgId());
        queryWrapper.orderByDesc("PARTORL_PROCESS_DATE");
        return queryWrapper;
    }
}
