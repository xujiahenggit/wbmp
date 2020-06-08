package com.bank.manage.service;

import com.bank.core.entity.PageQueryModel;
import com.bank.manage.dos.PlayAreaMaterialDO;
import com.bank.manage.dto.DeviceDTO;
import com.bank.manage.dto.MaterialDTO;
import com.bank.manage.dto.PlayAreaMaterialDTO;
import com.bank.manage.dto.ProgramDTO;
import com.bank.manage.vo.PlayAreaMaterialVo;
import com.bank.manage.vo.ProgramVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface PlayAreaMaterialService extends IService<PlayAreaMaterialDO> {

    /**
     * 节目发布保存
     * @param deviceList
     * @param playAreaMateriaList
     * @param programDTO
     * @return
     */
    Boolean save(List<DeviceDTO> deviceList, List<PlayAreaMaterialDTO> playAreaMateriaList, ProgramDTO programDTO);

    /**
     * 节目发布信息查询
     * @param pageQueryModel
     * @return
     */
    IPage<PlayAreaMaterialVo> queryList(PageQueryModel pageQueryModel);

    /**
     * 节目发布素材信息查询
     * @param pageQueryModel
     * @return
     */
    IPage<MaterialDTO> queryMaterialList(PageQueryModel pageQueryModel);

    List<ProgramVo> queryProgramList();

    /**
     * 查询 节目切换
     * @param programId 节目编号
     * @param deviceId 设备编号
     * @return
     */
    List<ProgramVo> queryProgramListByPromId(Integer programId, String deviceId);
}

