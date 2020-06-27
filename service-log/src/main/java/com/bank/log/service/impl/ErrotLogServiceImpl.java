package com.bank.log.service.impl;

import cn.hutool.core.util.StrUtil;
import com.bank.log.dao.ErrorLogDao;
import com.bank.log.dos.ErrorLogDO;
import com.bank.log.service.ErrotLogService;
import com.bank.log.vo.ErrLogVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/1 10:40
 */
@Service
public class ErrotLogServiceImpl extends ServiceImpl<ErrorLogDao, ErrorLogDO> implements ErrotLogService {

    @Resource
    private ErrorLogDao errorLogDao;

    /**
     * 错误日志 多维度查询
     * @param errLogVo
     * @return
     */
    @Override
    public IPage<ErrorLogDO> SelectErrLogPage(ErrLogVo errLogVo) {
        QueryWrapper<ErrorLogDO> queryWrapper=new QueryWrapper<>();
        if(StrUtil.isNotBlank(errLogVo.getOptUserName())){
            //操作用户
            queryWrapper.like("OPT_USER_NAME",errLogVo.getOptUserName());
        }
        if(StrUtil.isNotBlank(errLogVo.getOptVersion())){
            //日志版本
            queryWrapper.eq("ERR_VERSION",errLogVo.getOptVersion());
        }
        if(StrUtil.isNotBlank(errLogVo.getStartTime()) && StrUtil.isNotBlank(errLogVo.getEndTime())){
            //操作时间
            queryWrapper.between("ERR_DATE",errLogVo.getStartTime(),errLogVo.getEndTime());
        }
        //按操作时间倒序排序
        queryWrapper.orderByDesc("ERR_DATE");
        Page<ErrorLogDO> page=new Page<>(errLogVo.getPageIndex(),errLogVo.getPageSize());
        return errorLogDao.selectPage(page,queryWrapper);
    }

    /**
     * 获取错误日志版本列表
     * @return
     */
    @Override
    public List<String> getErrVersion() {
        return errorLogDao.getErrVersion();
    }
}
