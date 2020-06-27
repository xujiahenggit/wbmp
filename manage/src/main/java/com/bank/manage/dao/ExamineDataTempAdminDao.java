package com.bank.manage.dao;

import com.bank.manage.dos.ExamineDataTempAdminDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamineDataTempAdminDao extends BaseMapper<ExamineDataTempAdminDO> {
    void delExamineDataTempAdminByList(@Param("examineDataAdminList") List<Integer> examineDataAdminList);
}
