package com.bank.manage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bank.manage.dos.CountModuleTempDO;
import com.bank.manage.dos.ExamineDataTempAdminDO;
import com.bank.manage.dto.StatisticsDTO;
import com.bank.manage.vo.HappyParam;

@Repository
public interface HappyDao {

    List<Map<String, Object>> HeadStatus(HappyParam param);

    List<Map<String, Object>> checkStatusDetails(HappyParam param);

    /**
     * 按年统计考核分数
     * @param isAdmin
     * @return
     */
    List<StatisticsDTO> checkStatusStatisticsYear(@Param("hasAdmin") boolean isAdmin);

    /**
     * 按年，季度统计考核分数
     * @param isAdmin
     * @param param
     * @return
     */
    List<StatisticsDTO> checkStatusStatisticsQuarter(@Param("hasAdmin") boolean isAdmin, @Param("param") HappyParam param);

    List<ExamineDataTempAdminDO> deductStatus(HappyParam param);

    List<Map<String, Object>> starStatus(HappyParam param);

    List<Map<String, Object>> serviceLevelStatus(HappyParam param);

    List<CountModuleTempDO> getModuleScore(HappyParam param);

    List<String> getOrgIds(@Param("orgids") String orgids);
}
