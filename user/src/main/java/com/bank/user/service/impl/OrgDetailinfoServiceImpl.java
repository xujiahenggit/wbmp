package com.bank.user.service.impl;

import com.bank.core.entity.BizException;
import com.bank.user.dao.OrgDetailinfoDao;
import com.bank.user.dos.OrgDetailinfoDO;
import com.bank.user.dto.OrgDetailDto;
import com.bank.user.service.OrgDetailinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Andy
 * @Date: 2020/5/7 16:05
 */
@Service
public class OrgDetailinfoServiceImpl extends ServiceImpl<OrgDetailinfoDao, OrgDetailinfoDO> implements OrgDetailinfoService {
    @Resource
    private OrgDetailinfoDao orgDetailinfoDao;
    @Override
    public boolean updateOrgDetail(OrgDetailinfoDO orgDetailinfoDO) {
        try{
            OrgDetailinfoDO detailinfoDO=orgDetailinfoDao.selectById(orgDetailinfoDO.getOrgId());
            if(detailinfoDO!=null){
                orgDetailinfoDao.updateById(orgDetailinfoDO);
            }else{
                orgDetailinfoDao.insert(orgDetailinfoDO);
            }
            return true;
        }catch (Exception e){
            throw new BizException("更新机构信息失败");
        }
    }

    /**
     * 获取机构详细信息
     * @param orgId 机构编号
     * @return
     */
    @Override
    public OrgDetailDto getInfoByOrgId(String orgId) {
        return orgDetailinfoDao.getOrgInfoByOrgId(orgId);
    }
}
