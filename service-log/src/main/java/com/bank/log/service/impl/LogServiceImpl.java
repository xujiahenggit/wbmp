package com.bank.log.service.impl;

import cn.hutool.core.util.StrUtil;
import com.bank.log.dao.LogDao;
import com.bank.log.dos.LogDO;
import com.bank.log.service.LogService;
import com.bank.log.vo.LogQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/4/1 9:36
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogDao, LogDO> implements LogService {

    @Resource
    private LogDao logDao;

    /**
     * 查询日志
     *
     * @param logQueryVo
     * @return
     */
    @Override
    public IPage<LogDO> SelectLogPage(LogQueryVo logQueryVo) {
        QueryWrapper<LogDO> logDOQueryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(logQueryVo.getOptModual())) {
            //操作模块
            logDOQueryWrapper.eq("OPT_MODUAL", logQueryVo.getOptModual());
        }
        if (StrUtil.isNotBlank(logQueryVo.getOptType())) {
            //操作类型
            logDOQueryWrapper.eq("OPT_TYPE", logQueryVo.getOptType());
        }
        if (StrUtil.isNotBlank(logQueryVo.getOptVersion())) {
            //日志版本
            logDOQueryWrapper.eq("OPT_VERSION", logQueryVo.getOptVersion());
        }
        if (StrUtil.isNotBlank(logQueryVo.getOptUserName())) {
            //操作用户
            logDOQueryWrapper.like("OPT_USER_NAME", logQueryVo.getOptUserName());
        }
        if (StrUtil.isNotBlank(logQueryVo.getStartTime()) && StrUtil.isNotBlank(logQueryVo.getEndTime())) {
            //操作时间
            logDOQueryWrapper.between("OPT_DATE", logQueryVo.getStartTime(), logQueryVo.getEndTime());
        }
        //按时间倒序排序
        logDOQueryWrapper.orderByDesc("OPT_DATE");
        Page<LogDO> logDOPage = new Page<>(logQueryVo.getPageIndex(), logQueryVo.getPageSize());
        return logDao.selectPage(logDOPage, logDOQueryWrapper);
    }

    /**
     * 获取 操作模块列表
     * @return
     */
    @Override
    public List<String> selectOptModuls() {
        return logDao.selectModuleList();
    }

    /**
     * 获取操作类型 列表
     *
     * @return
     */
    @Override
    public List<String> selectOptTypes() {
        return logDao.selectOptTypes();
    }

    /**
     * 获取日志 版本列表
     * @return
     */
    @Override
    public List<String> selectLogVersions() {
        return logDao.selectLogVersions();
    }
}
