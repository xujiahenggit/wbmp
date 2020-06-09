package com.bank.manage.dao;

import com.bank.manage.dos.PlayAreaMaterialDO;
import com.bank.manage.vo.PlayAreaMaterialVo;
import com.bank.manage.vo.ProgramVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PlayAreaMaterialDao extends BaseMapper<PlayAreaMaterialDO> {
    IPage<PlayAreaMaterialVo> selectPageListByPageQueryModel(Page page, @Param("programName") String programName,
                                             @Param("ids") List<String> ids, @Param("deviceType") String deviceType);

    List<ProgramVo> queryProgramList(@Param("s") String s);

    /**
     * 查询节目预览
     * @param programId
     * @param deviceId
     * @return
     */
    List<ProgramVo> queryProgramListByPromId(@Param(value = "programId") Integer programId,@Param(value = "deviceId") String deviceId);
}
