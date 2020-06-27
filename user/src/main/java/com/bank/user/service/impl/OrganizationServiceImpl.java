package com.bank.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.BizException;
import com.bank.core.entity.TokenUserInfo;
import com.bank.user.dao.OrganizationDao;
import com.bank.user.dos.OrganizationDO;
import com.bank.user.dto.OrgDemandDto;
import com.bank.user.dto.OrgListDto;
import com.bank.user.service.OrganizationService;
import com.bank.user.utils.OrgIdUtils;
import com.bank.user.vo.OrgQueryVo;
import com.bank.user.vo.OrgVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Andy
 * @Date: 2020/4/1 16:34
 */
@Service("orgService")
public class OrganizationServiceImpl extends ServiceImpl<OrganizationDao, OrganizationDO> implements OrganizationService {

    @Resource
    private OrganizationDao organizationDao;

    /**
     * 机构表
     */
    @Override
    public void clearnOrgData() {
        organizationDao.clearnOrgData();
    }

    /**
     * 复制零时表中的数据到 机构表中
     */
    @Override
    public void copyData() {
        organizationDao.copyData();
    }


    /**
     * 添加机构信息
     * @param orgVo 机构信息
     * @param tokenUserInfo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addOrg(OrgVo orgVo, TokenUserInfo tokenUserInfo) {
        try{
            OrganizationDO organizationDO=new OrganizationDO();
            BeanUtils.copyProperties(orgVo,organizationDO);
            organizationDao.insert(organizationDO);
            return true;
        }catch (Exception e){
            throw new BizException("添加组织机构失败");
        }
    }

    /**
     * 机构信息
     * @param orgVo 机构信息
     * @param tokenUserInfo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateOrg(OrgVo orgVo, TokenUserInfo tokenUserInfo) {
        try{
            OrganizationDO organizationDO=new OrganizationDO();
            BeanUtils.copyProperties(orgVo,organizationDO);
            organizationDao.updateById(organizationDO);
            return true;
        }catch (Exception e){
            throw new BizException("添加组织机构失败");
        }
    }

    /**
     * 删除组织机构
     * @param orgId 组织机构ID
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteOrg(String orgId) {
        try {
            organizationDao.deleteById(orgId);
            return true;
        }catch (Exception e){
            throw new BizException("删除组织机构失败");
        }
    }

    /**
     * 查询机构列表
     * @param orgQueryVo 查询参数
     * @param currentOrgId 当前用户的机构编号
     * @return
     */
    @Override
    public IPage<OrgListDto> selectOrgPage(OrgQueryVo orgQueryVo, String currentOrgId) {
        //获取用户所在的 机构ID和下级机构ID
        List<OrganizationDO> organizationDOList=this.list();
        List<String> listOrgIds=new ArrayList<>();
        //如果是总行 则 显示所有的机构
        if(this.isHeadOffice(currentOrgId)){
            listOrgIds=OrgIdUtils.returnFidList("0",organizationDOList);
        }else {
            listOrgIds=OrgIdUtils.returnFidList(currentOrgId,organizationDOList);
        }
        Page<OrgListDto> page=new Page<>(orgQueryVo.getPageIndex(),orgQueryVo.getPageSize());
        IPage<OrgListDto> organizationPage=organizationDao.selectPageByCondition(page,listOrgIds,orgQueryVo);
        return organizationPage;
    }

    /**
     * 获取机构详细信息
     * @param orgId 机构编号
     * @return
     */
    @Override
    public OrgListDto getOrgDetailById(String orgId) {
        return organizationDao.getOrgDetailById(orgId);
    }

    /**
     * 获取 对外开放的机构号
     * @return
     */
    @Override
    public List<OrganizationDO> getOpenOrgList() {
        return organizationDao.selectOutsiteList();
    }

    /**
     * 查询是不是总行
     * @param orgId 机构号
     * @return
     */
    @Override
    public boolean isHeadOffice(String orgId) {
        QueryWrapper<OrganizationDO> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("ORG_UNIT_ID",100);
        queryWrapper.eq("ORG_ID",orgId);
        OrganizationDO organizationDO=this.getOne(queryWrapper);
        if(organizationDO==null){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 查找父机构
     * @param tokenUserInfo 当前登录用户
     * @return
     */
    @Override
    public OrgDemandDto getParentOrg(TokenUserInfo tokenUserInfo) {
        String tempOrgId="";
        //如果是总行用户 则返回所有的机构
        if(this.isHeadOffice(tokenUserInfo.getOrgId())){
            tempOrgId="1";
        }else{
            tempOrgId=tokenUserInfo.getOrgId();
        }
        return organizationDao.getParentOrg(tempOrgId);
    }

    /**
     * 查找父机构
     */
    @Override
    public List<OrgDemandDto> getChild(String orgId) {
        return organizationDao.getChild(orgId);
    }

    @Override
    public List<OrganizationDO> getSearchOrgList(String key) {
        QueryWrapper<OrganizationDO> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("ORG_ID,ORG_NAME");
        queryWrapper.like("ORG_ID",key).or().like("ORG_NAME",key);
        return this.list(queryWrapper);
    }


    /**
     * 获取父机构名称
     * @param orgId  机构ID
     * @return
     */
    private String getOrgParentName(String orgId){
        return organizationDao.getParentName(orgId);
    }
}
