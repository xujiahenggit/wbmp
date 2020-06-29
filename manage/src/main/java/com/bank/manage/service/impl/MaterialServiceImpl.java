package com.bank.manage.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.enums.ConstantEnum;
import com.bank.core.sysConst.NewProcessStatusFile;
import com.bank.core.utils.ConfigFileReader;
import com.bank.core.utils.NetUtil;
import com.bank.core.utils.StringSplitUtil;
import com.bank.manage.dao.CatalogMaterialDao;
import com.bank.manage.dao.MaterialDao;
import com.bank.manage.dao.PlayAreaMaterialDao;
import com.bank.manage.dos.CatalogMaterialDO;
import com.bank.manage.dos.MaterialDO;
import com.bank.manage.dos.NewProcessDO;
import com.bank.manage.dos.PlayAreaMaterialDO;
import com.bank.manage.dto.MaterialDTO;
import com.bank.manage.service.MaterialService;
import com.bank.manage.service.NewProcessService;
import com.bank.manage.vo.ForcePlayVo;
import com.bank.manage.vo.MaterialVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MaterialServiceImpl extends ServiceImpl<MaterialDao, MaterialDO> implements MaterialService {

    @Resource
    private MaterialDao materialDao;

    @Resource
    @Lazy
    private NewProcessService newProcessService;

    @Resource
    private ConfigFileReader configFileReader;

    @Resource
    private CatalogMaterialDao catalogMaterialDao;

    @Autowired
    private PlayAreaMaterialDao playAreaMaterialDao;

    @Resource
    NetUtil netUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertMaterial(List<MaterialDTO> materialDTO, String catalogId, TokenUserInfo tokenUserInfo) {
        log.info("接收到的数据：{}，catalogId:{}", materialDTO, catalogId);
        try {
            for (MaterialDTO material : materialDTO) {
                if (!"03".equals(material.getMaterialType())) {//非文字素材
                    String materialPath = material.getMaterialPath();
                    String splitMaterialPath = StringSplitUtil.splitMaterialPath(materialPath, netUtil.getUrlSuffix(""));
                    MaterialDO materialDO = MaterialDO.builder()
                            .materialName(material.getMaterialName())
                            .materialType(material.getMaterialType())
                            .materialFormat(material.getMaterialFormat())
                            .materialSpec(material.getMaterialSpec())
                            .materialPath(splitMaterialPath)
                            .materialSize(StringSplitUtil.getMaterialSize(splitMaterialPath))
                            .orgId(tokenUserInfo.getOrgId())
                            .createdUser(tokenUserInfo.getUserId())
                            .createdTime(LocalDateTime.now())
                            .expirTime(material.getExpirTime())
                            .deviceType(material.getDeviceType())
                            .orgName(material.getOrgName()).build();
                    log.info("插入素材表信息：{}", materialDO);
                    materialDao.insert(materialDO);

                    NewProcessDO newProcessDO = NewProcessDO.builder()
                            .status(NewProcessStatusFile.PROCESS_WAIT)
                            .active("1")
                            .creatorId(tokenUserInfo.getUserId())
                            .creatorName(tokenUserInfo.getUserName())
                            .createTime(LocalDateTime.now())
                            .orgId(tokenUserInfo.getOrgId())
                            .tradingId(String.valueOf(materialDO.getMaterialId()))
                            .tradingName("素材新增")
                            .tradingModule("素材管理")
                            .tradingType(ConstantEnum.TRADE_TYPE_SCWJ.getType()).build();
                    log.info("插入流程表信息：{}", newProcessDO);
                    newProcessService.createProcess(newProcessDO, tokenUserInfo);

                    CatalogMaterialDO catalogMaterialDO = CatalogMaterialDO.builder()
                            .catalogId(Integer.parseInt(catalogId))
                            .materialId(materialDO.getMaterialId()).build();
                    log.info("插入素材目录中间表信息：{}", catalogMaterialDO);
                    catalogMaterialDao.insert(catalogMaterialDO);
                }
                else {
                    MaterialDO materialDO = MaterialDO.builder()
                            .materialName(material.getMaterialName())
                            .materialType(material.getMaterialType())
                            .materialFormat(material.getMaterialFormat())
                            .orgId(tokenUserInfo.getOrgId())
                            .orgName(material.getOrgName())
                            .createdUser(tokenUserInfo.getUserId())
                            .createdTime(LocalDateTime.now())
                            .expirTime(material.getExpirTime())
                            .deviceType(material.getDeviceType())
                            .text(material.getText()).build();
                    log.info("插入素材表信息：{}", materialDO);
                    materialDao.insert(materialDO);

                    NewProcessDO newProcessDO = NewProcessDO.builder()
                            .status(NewProcessStatusFile.PROCESS_WAIT)
                            .active("1")
                            .tradingId(String.valueOf(materialDO.getMaterialId()))
                            .tradingName("素材新增")
                            .tradingModule("素材管理")
                            .tradingType(ConstantEnum.TRADE_TYPE_SCWJ.getType())
                            .creatorId(tokenUserInfo.getUserId())
                            .creatorName(tokenUserInfo.getUserName())
                            .createTime(LocalDateTime.now())
                            .orgId(tokenUserInfo.getOrgId()).build();
                    log.info("插入流程表信息：{}", newProcessDO);
                    newProcessService.createProcess(newProcessDO, tokenUserInfo);

                    CatalogMaterialDO catalogMaterialDO = CatalogMaterialDO.builder()
                            .catalogId(Integer.parseInt(catalogId))
                            .materialId(materialDO.getMaterialId()).build();
                    log.info("插入素材目录中间表信息：{}", catalogMaterialDO);
                    catalogMaterialDao.insert(catalogMaterialDO);
                }
            }
            return Boolean.TRUE;
        }
        catch (Exception e) {
            throw new BizException("素材保存失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateMaterial(MaterialDTO materialDTO, TokenUserInfo tokenUserInfo) {
        Boolean flag = null;

        QueryWrapper<NewProcessDO> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("TRADING_ID", materialDTO.getMaterialId())
                .eq("TRADING_TYPE", "T_MATERIAL");
        NewProcessDO newProcessDO = newProcessService.getOne(queryWrapper1);

        String status = newProcessDO.getStatus();
        if ("30".equals(status) || "40".equals(status)) {
            throw new BizException("素材已被驳回或撤销，请勿修改！");
        }

        MaterialDO materialDO = MaterialDO.builder()
                .materialId(materialDTO.getMaterialId())
                .materialName(materialDTO.getMaterialName())
                .updateUser(tokenUserInfo.getUserId())
                .updateTime(LocalDateTime.now())
                .expirTime(materialDTO.getExpirTime())
                .deviceType(materialDTO.getDeviceType())
                .forcePlay(materialDTO.getForcePlay()).build();
        log.info("素材修改信息：{}", materialDO);
        try {
            materialDao.updateById(materialDO);
            flag = true;
        }
        catch (Exception e) {
            flag = false;
            throw new BizException("素材更新失败");
        }
        return flag;
    }

    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Boolean deleteMaterial(Integer id) {
        Boolean flag = null;
        QueryWrapper<PlayAreaMaterialDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("MATERIAL_ID", id);
        Integer i = playAreaMaterialDao.selectCount(queryWrapper);
        if (i > 0) {
            throw new BizException("该素材正在被使用，请勿删除");
        }
        QueryWrapper<NewProcessDO> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("TRADING_ID", id)
                .eq("TRADING_TYPE", "T_MATERIAL");
        NewProcessDO newProcessDO = newProcessService.getOne(queryWrapper1);

        String status = newProcessDO.getStatus();
        if ("10".equals(status) || "20".equals(status)) {
            throw new BizException("素材在审核过程中，请勿删除！");
        }
        try {
            NewProcessDO newProcessDO1 = NewProcessDO.builder()
                    .active("0")
                    .processId(newProcessDO.getProcessId())
                    .build();
            newProcessService.updateById(newProcessDO1);

            //materialDao.deleteById(id);
            flag = true;
        }
        catch (Exception e) {
            flag = false;
            log.error("素材删除失败：" + e.getMessage());
            throw new BizException("素材删除失败！");
        }
        return flag;
    }

    @Override
    public MaterialDO queryMaterialById(Integer id) {
        MaterialDO materialDO = materialDao.selectById(id);
        if (materialDO == null) {
            throw new BizException("查无此记录");
        }
        materialDO.setMaterialPath(netUtil.getUrlSuffix("") + materialDO.getMaterialPath());
        return materialDO;
    }

    /**
     * 修改素材强制状态
     *
     * @param list 素材列表
     * @return
     */
    @Override
    public boolean updateForcePlayState(List<ForcePlayVo> list) {
        try {
            if (list.size() > 0) {
                for (ForcePlayVo forcePlayVo : list) {
                    MaterialDO materialDO = MaterialDO.builder()
                            //素材编号
                            .materialId(forcePlayVo.getMaterialId())
                            //强制状态
                            .forcePlay(forcePlayVo.getForcePlay()).build();
                    materialDao.updateById(materialDO);
                }
            }
            return true;
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public IPage<MaterialVo> queryMaterialList(PageQueryModel pageQueryModel) {
        Page<MaterialVo> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

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
        String catalogId = (String) queryParam.get("catalogId");
        String createdUser = (String) queryParam.get("createdUser");
        String orgId = (String) queryParam.get("orgId");
        String deviceType = (String) queryParam.get("deviceType");
        String forcePlay = (String) queryParam.get("forcePlay");
        String materialName = (String) queryParam.get("materialName");
        IPage<MaterialVo> pageList = materialDao.selectPageListByCatalogType(page, catalogId, createdUser, orgId,
                deviceType, forcePlay, materialName);
        List<MaterialVo> records = pageList.getRecords();
        if (records != null && records.size() > 0) {
            for (int i = 0; i < records.size(); i++) {
                String materialType = records.get(i).getMaterialType();
                if (!ConstantEnum.MATERIAL_TYPE_TEXT.getType().equals(materialType)) {//非文字素材
                    records.get(i).setMaterialPath(netUtil.getUrlSuffix("") + records.get(i).getMaterialPath());
                }
            }
        }
        return pageList;
    }

    @Override
    public List<MaterialVo> queryAppMaterialList() {
        List<MaterialVo> records = materialDao.queryListByCatalogId("6");
        if (records != null && records.size() > 0) {
            for (int i = 0; i < records.size(); i++) {
                String materialType = records.get(i).getMaterialType();
                if (!ConstantEnum.MATERIAL_TYPE_TEXT.getType().equals(materialType)) {//非文字素材
                    records.get(i).setMaterialPath(netUtil.getUrlSuffix("") + records.get(i).getMaterialPath());
                }
            }
        }
        return records;
    }
}
