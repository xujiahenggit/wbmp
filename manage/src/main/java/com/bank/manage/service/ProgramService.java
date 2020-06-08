package com.bank.manage.service;

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
import com.bank.manage.vo.ProgramQueryVo;
import com.bank.manage.vo.ProgramUpdateVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ProgramService extends IService<ProgramDO> {
    /**
     * 节目预览
     * @param programId 节目编号
     * @param deviceId
     * @return
     */
    ProgramPreviewDTO getPreView(Integer programId, String deviceId);

    /**
     * 通过节目 编号获取 已选择的素材列表
     * @param programId 节目编号
     * @param deviceId 设备编号
     * @return
     */
    List<MaterialDTO> getMaterialList(Integer programId, String deviceId);

    /**
     * 通过 设备编号 获取节目列表
     * @param programQueryByDeviceVo 查询参数
     * @return
     */
    IPage<ProgramListDTO> getProgramList(ProgramQueryByDeviceVo programQueryByDeviceVo);

    /**
     * 修改节目
     * @param programUpdateVo 节目参数
     * @return
     */
    Boolean updatePrograme(ProgramUpdateVo programUpdateVo);

    /**
     * 切换节目
     * @param programId 节目编号
     * @param deviceId 设备编号
     * @return
     */
    Boolean changeProgram(Integer programId, String deviceId);

    /**
     * 查询可用节目列表
     * @param programQueryVo 查询参数
     * @return
     */
    IPage<ProgramDO> selectUseProgramList(ProgramQueryVo programQueryVo);

    /**
     * PAD 查询节目详情
     * @param programId 节目编号
     * @param deviceId 设备编号
     * @return
     */
    PadProgramDto selectPadProgram(Integer programId, String deviceId);

    /**
     * 获取 节目样式
     * @param programId 节目编号
     * @param deviceId 设备编号
     * @return
     */
    ProgramStyleDto getStyle(Integer programId, String deviceId);

    /**
     * 获取节目详细信息
     * @param programId 节目编号
     * @param deviceId 设备编号
     * @return
     */
    ProgramUpdateDto getProgramInfo(Integer programId, String deviceId);
}
