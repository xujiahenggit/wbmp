package com.bank.manage.service.impl;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dao.StatusLogDao;
import com.bank.manage.dos.StatusLogDO;
import com.bank.manage.service.StatusLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StatusLogServiceImpl extends ServiceImpl<StatusLogDao,StatusLogDO> implements StatusLogService {

    @Autowired
    private StatusLogDao statusLogDao;

    @Override
    public IPage<StatusLogDO> queryStatusLog(PageQueryModel pageQueryModel) {

        Page<StatusLogDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            }
            else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        //条件查询
        Map<String, Object> queryParam = pageQueryModel.getQueryParam();
        String terminalNum = (String) queryParam.get("terminalNum");
        String startTime = (String) queryParam.get("startTime");
        String endTime = (String) queryParam.get("endTime");

        QueryWrapper<StatusLogDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(terminalNum), "TERMINAL_NUM", terminalNum);
        if(StringUtils.isNotBlank(startTime) && StringUtils.isNotBlank(endTime)){
            queryWrapper.between("CREATE_TIME",startTime,endTime);
        }

        return statusLogDao.selectPage(page,queryWrapper);
    }
}
