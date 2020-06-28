package com.bank.user.service.impl;

import com.bank.core.entity.TokenUserInfo;
import com.bank.core.sysConst.SysStatus;
import com.bank.user.dao.NfrtOrgDao;
import com.bank.user.dos.NfrtOrgDO;
import com.bank.user.dos.OrganizationDO;
import com.bank.user.dto.OrgNftDto;
import com.bank.user.service.NfrtOrgService;
import com.bank.user.service.OrganizationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/6/3 15:13
 */
@Service
public class NfrtOrgServiceImpl extends ServiceImpl<NfrtOrgDao, NfrtOrgDO> implements NfrtOrgService {

    @Resource
    private NfrtOrgDao nfrtOrgDao;


    @Resource
    private OrganizationService organizationService;

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
     * 正常获取+村镇
     * @return
     */
    @Override
    public List<OrgNftDto> getNftOrgList() {
        //获取正常支行列表
        List<OrgNftDto> listNomal=nfrtOrgDao.getNftOrgList();
        //获取 村镇支行
        List<OrgNftDto> listC=nfrtOrgDao.getContryOrgList();

        listNomal.addAll(listC);
        return listNomal;
    }

    /**
     * 获取网点类别
     *
     * @param orgId 分支行核心机构号
     * @param type 类型
     * @return
     */
    @Override
    public List<OrgNftDto> getOutletsList(String orgId,String type) {
        List<OrgNftDto> listOrg=new ArrayList<>();
        if(SysStatus.ORG_TYPE_NOMAL.equals(type)){
            listOrg=nfrtOrgDao.getOutletsList(orgId);
        }else if(SysStatus.ORG_TYPE_CZ.equals(type)){
            listOrg=nfrtOrgDao.getContryOutsitList(orgId+"03");
        }
        return listOrg;
    }

    /**
     * 获取所有的 网点列表
     * @return
     */
    @Override
    public List<OrgNftDto> getAllOutletsList(){
        return nfrtOrgDao.getAllOutletList();
    }

    /**
     * 获取分行下面的所有的 网点的机构编号列表
     * @param branchNo 分行编号
     * @return
     */
    @Override
    public List<String> getSubOutSiteList(String branchNo) {
        return nfrtOrgDao.getSubOutSiteList(branchNo);
    }


    /**
     * 获取当前登录用户 所在的分行
     * @param tokenUserInfo 当前登录用户
     * @return
     */
    @Override
    public List<OrgNftDto> getOrgListByUser(TokenUserInfo tokenUserInfo) {
        //判断用户是否是总行
        boolean flag=organizationService.isHeadOffice(tokenUserInfo.getOrgId());
        if(flag){
            return nfrtOrgDao.getNftOrgList();
        }
        //如果不是总行 则查询他所在的机构
        //查询当前用户 所在的机构号
        OrganizationDO organizationDO=organizationService.getById(tokenUserInfo.getOrgId());
        return nfrtOrgDao.getOrgListByUser(organizationDO.getOrgDepartId());
    }

    /**
     * 查询用户所在的网点列表
     * @param orgId 分行编号
     * @param type 类型
     * @param tokenUserInfo 当前登录用户
     * @return
     */
    @Override
    public List<OrgNftDto> getOutlegetOutSitListByUsertsList(String orgId, String type, TokenUserInfo tokenUserInfo) {
        List<OrgNftDto> listOrg=new ArrayList<>();

        OrganizationDO organizationDO=organizationService.getById(tokenUserInfo.getOrgId());
        //判断用户是否是总行
        boolean flag=organizationService.isHeadOffice(tokenUserInfo.getOrgId());
        if(flag){
            if(SysStatus.ORG_TYPE_NOMAL.equals(type)){
                listOrg=nfrtOrgDao.getOutletsList(orgId);
            }else if(SysStatus.ORG_TYPE_CZ.equals(type)){
                listOrg=nfrtOrgDao.getContryOutsitList(orgId+"03");
            }
            return listOrg;
        }else{
            listOrg=nfrtOrgDao.getOutlegetOutSitListByUsertsList(organizationDO.getOrgId(),orgId);
        }
        return listOrg;
    }
}
