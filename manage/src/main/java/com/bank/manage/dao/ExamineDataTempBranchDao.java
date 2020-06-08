package com.bank.manage.dao;

import com.bank.manage.dos.ExamineDataTempBranchDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamineDataTempBranchDao extends BaseMapper<ExamineDataTempBranchDO> {
    void delExamineDataBranchByList(@Param("examineDataBranchList") List<Integer> examineDataBranchList);
}
