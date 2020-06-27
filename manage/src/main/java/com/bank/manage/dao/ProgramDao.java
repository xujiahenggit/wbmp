package com.bank.manage.dao;

import com.bank.manage.dos.MaterialDO;
import com.bank.manage.dos.ProgramDO;
import com.bank.manage.dos.StyleAreaDO;
import com.bank.manage.dto.MaterialDTO;
import com.bank.manage.dto.Progame.PadProgramDto;
import com.bank.manage.dto.Progame.ProgramPreviewDTO;
import com.bank.manage.dto.ProgramListDTO;
import com.bank.manage.dto.ProgramUpdate.ProgramStyleDto;
import com.bank.manage.dto.ProgramUpdate.ProgramUpdateDto;
import com.bank.manage.vo.ProgramQueryByDeviceVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import javax.swing.*;
import java.util.List;

public interface ProgramDao extends BaseMapper<ProgramDO> {
    /**
     * 节目预览
     * @param programId 节目编号
     * @param deviceId
     * @return
     */
    ProgramPreviewDTO getPreView(@Param(value = "programId") Integer programId,@Param(value = "deviceId") String deviceId);

    /**
     * 获取节目已选择的素材
     * @param programId 节目编号
     * @param deviceId 节目编号
     * @return
     */
    List<MaterialDTO> getMaterialList(@Param(value = "programId") Integer programId, @Param(value = "deviceId") String deviceId);

    /**
     * 通过设备编号 获取节目列表
     * @param programQueryByDeviceVo 查询参数
     * @return
     */
    IPage<ProgramListDTO> selectProgrameList(Page<ProgramListDTO> page, @Param(value = "param") ProgramQueryByDeviceVo programQueryByDeviceVo);

    /**
     * PAD 查询节目详情
     * @param programId 节目编号
     * @param deviceId 设备编号
     * @return
     */
    PadProgramDto selectPadProgram(@Param(value = "programId") Integer programId,@Param(value = "deviceId") String deviceId);

    /**
     * 获取样式
     * @param programId 节目编号
     * @param deviceId 设备编号
     * @return
     */
    ProgramStyleDto getStyle(@Param(value = "programId") Integer programId, @Param(value = "deviceId") String deviceId);

    /**
     * 获取节目详细信息
     * @param programId 节目编号
     * @param deviceId 设备编号
     * @return
     */
    ProgramUpdateDto getProgramInfo(@Param(value = "programId") Integer programId,@Param(value = "deviceId") String deviceId);
}
