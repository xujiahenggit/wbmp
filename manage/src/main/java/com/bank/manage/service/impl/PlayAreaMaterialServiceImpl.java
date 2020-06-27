package com.bank.manage.service.impl;

import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.enums.ConstantEnum;
import com.bank.core.utils.ConfigFileReader;
import com.bank.core.utils.NetUtil;
import com.bank.manage.dao.MaterialDao;
import com.bank.manage.dao.PlayAreaMaterialDao;
import com.bank.manage.dao.ProgramDao;
import com.bank.manage.dos.MaterialDO;
import com.bank.manage.dos.PlayAreaMaterialDO;
import com.bank.manage.dos.ProgramDO;
import com.bank.manage.dto.DeviceDTO;
import com.bank.manage.dto.MaterialDTO;
import com.bank.manage.dto.PlayAreaMaterialDTO;
import com.bank.manage.dto.ProgramDTO;
import com.bank.manage.service.PlayAreaMaterialService;
import com.bank.manage.vo.PlayAreaMaterialVo;
import com.bank.manage.vo.ProgramVo;
import com.bank.user.dos.OrganizationDO;
import com.bank.user.service.OrganizationService;
import com.bank.user.utils.OrgIdUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PlayAreaMaterialServiceImpl extends ServiceImpl<PlayAreaMaterialDao,PlayAreaMaterialDO> implements PlayAreaMaterialService {

    @Resource
    NetUtil netUtil;
    @Autowired
    private PlayAreaMaterialDao playAreaMaterialDao;

    @Autowired
    private ProgramDao programDao;

    @Autowired
    private MaterialDao materialDao;

    @Resource
    private ConfigFileReader configFileReader;

    @Autowired
    private OrganizationService organizationService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean save(List<DeviceDTO> deviceList, List<PlayAreaMaterialDTO> playAreaMateriaList, ProgramDTO programDTO) {
        Boolean flag =null;
        try {
            log.info("进入节目发布保存：选中的设备信息：【{}】，选中的区域信息：【{}】，填写的节目信息：【{}】",deviceList,playAreaMateriaList,programDTO);
            for (int i = 0; i < deviceList.size(); i++) {
                ProgramDO programDO = ProgramDO.builder()
                        .programName(programDTO.getProgramName())
                        .programType(programDTO.getProgramType())
                        .createdTime(LocalDateTime.now())
                        .createdUser(programDTO.getCreatedUser())
                        .orgId(programDTO.getOrgId())
                        .startTime(programDTO.getStartTime())
                        .endTime(programDTO.getEndTime())
                        .terminalNum(deviceList.get(i).getTerminalNum()).build();
                programDao.insert(programDO);//保存节目信息
                log.info("保存节目信息成功，节目信息：{}",programDO);
                //保存区域中间表
                for (PlayAreaMaterialDTO playAreaMaterialDTO:playAreaMateriaList) {
                    PlayAreaMaterialDO playAreaMaterialDO = PlayAreaMaterialDO.builder()
                            .programId(programDO.getProgramId())
                            .styleNum(playAreaMaterialDTO.getStyleNum())
                            .areaName(playAreaMaterialDTO.getAreaName())
                            .areaNum(playAreaMaterialDTO.getAreaNum())
                            .areaType(playAreaMaterialDTO.getAreaType())
                            .materialId(playAreaMaterialDTO.getMaterialId())
                            .sort(playAreaMaterialDTO.getSort())
                            .playTime(playAreaMaterialDTO.getPlayTime())
                            .type(playAreaMaterialDTO.getType())
                            .terminalNum(deviceList.get(i).getTerminalNum()).build();
                    playAreaMaterialDao.insert(playAreaMaterialDO);
                }
                log.info("保存区域素材中间表成功，数据信息：{}",playAreaMateriaList);
            }
            flag = Boolean.TRUE;
        } catch (Exception e) {
            flag = Boolean.FALSE;
            log.error("节目发布保存失败"+e.getMessage());
            throw new BizException("节目发布失败！");
        }
        return flag;
    }

    @Override
    public IPage<PlayAreaMaterialVo> queryList(PageQueryModel pageQueryModel) {
        Page<PlayAreaMaterialVo> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            }else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        Map<String, Object> queryParam = pageQueryModel.getQueryParam();
        String programName = (String)queryParam.get("programName");
        String orgId = (String) queryParam.get("orgId");
        String deviceType = (String) queryParam.get("deviceType");

        boolean headOffice = organizationService.isHeadOffice(orgId);
        List<String> ids=new ArrayList<>();
        //获取用户所在的 机构ID和下级机构ID
        List<OrganizationDO> organizationDOList=organizationService.list();
        //如果是总行 则 显示所有的机构
        if(headOffice){
            ids=OrgIdUtils.returnFidList("0",organizationDOList);
        }else {
            ids=OrgIdUtils.returnFidList(orgId,organizationDOList);
        }
        IPage<PlayAreaMaterialVo> pageList = playAreaMaterialDao.selectPageListByPageQueryModel(page,programName,ids,deviceType);
        return pageList;
    }

    @Override
    public IPage<MaterialDTO> queryMaterialList(PageQueryModel pageQueryModel) {
        Page<MaterialDO> page = new Page<>(pageQueryModel.getPageIndex(), pageQueryModel.getPageSize());

        if (StringUtils.isNotBlank(pageQueryModel.getSort())) {
            if (StringUtils.equalsIgnoreCase("DESC", pageQueryModel.getOrder())) {
                page.setDesc(pageQueryModel.getSort());
            }
            else {
                page.setAsc(pageQueryModel.getSort());
            }
        }
        Map<String, Object> queryParam = pageQueryModel.getQueryParam();
        String materialName = (String)queryParam.get("materialName");
        String orgId = (String) queryParam.get("orgId");
        String deviceType = (String) queryParam.get("deviceType");
        String catalogId = (String) queryParam.get("catalogId");
        IPage<MaterialDTO> pageList = materialDao.queryListForPlay(page,materialName,orgId,deviceType,catalogId);
        List<MaterialDTO> records = pageList.getRecords();
        if(records != null && records.size()>0){
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
    public List<ProgramVo> queryProgramList() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date());
        String s = format.substring(0, format.lastIndexOf(":"))+":00";
        //String s = "2020-04-20 18:23:00";
        List<ProgramVo> programVoList = playAreaMaterialDao.queryProgramList(s);
        return programVoList;
    }

    /**
     * 查询节目切换
     * @param programId 节目编号
     * @param deviceId 设备编号
     * @return
     */
    @Override
    public List<ProgramVo> queryProgramListByPromId(Integer programId, String deviceId) {
        return playAreaMaterialDao.queryProgramListByPromId(programId,deviceId);
    }
}
