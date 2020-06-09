package com.bank.user.service.impl;

import com.bank.user.dao.NfrtOrgDao;
import com.bank.user.dos.NfrtOrgDO;
import com.bank.user.dto.OrgNftDto;
import com.bank.user.service.NfrtOrgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/6/3 15:13
 */
@Service
public class NfrtOrgServiceImpl extends ServiceImpl<NfrtOrgDao, NfrtOrgDO> implements NfrtOrgService {

    @Resource
    private NfrtOrgDao nfrtOrgDao;

    /**
     * 清空核心机构表
     */
    @Override
    public void clearnNfrt() {
        nfrtOrgDao.clearnNfrt();
    }

    /**
     * 复制中间表的数据到 核心机构表中
     */
    @Override
    public void copyTempData() {
        nfrtOrgDao.copyTempData();
    }

    /**
     * 获取分支行列表
     * @return
     */
    @Override
    public List<OrgNftDto> getNftOrgList() {
        return nfrtOrgDao.getNftOrgList();
    }

    /**
     * 获取网点类别
     *
     * @param orgId 分支行核心机构号
     * @return
     */
    @Override
    public List<OrgNftDto> getOutletsList(String orgId) {
        return nfrtOrgDao.getOutletsList(orgId);
    }
}
