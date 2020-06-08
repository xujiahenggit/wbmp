package com.bank.manage.dao;

import com.bank.manage.dos.CountModuleTempDO;
import com.bank.manage.dos.ExamineDataAdminDO;
import com.bank.manage.dos.ExamineDataTempAdminDO;
import com.bank.manage.dto.StatisticsDTO;
import com.bank.manage.vo.HappyParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface HappyDao {

    List<Map<String, Integer>> HeadStatus(HappyParam param);

    List<ExamineDataAdminDO> checkStatusDetails(HappyParam param);

    /**
     * 按年统计考核分数
     * @param isAdmin
     * @return
     */
    List<StatisticsDTO> checkStatusStatisticsYear(@Param("hasAdmin") boolean isAdmin);

    /**
     * 按年，季度统计考核分数
     * @param isAdmin
     * @return
     */
    List<StatisticsDTO> checkStatusStatisticsQuarter(@Param("hasAdmin") boolean isAdmin);

    List<ExamineDataTempAdminDO> deductStatus(HappyParam param);

    List<Map<String, Integer>> starStatus(HappyParam param);

    List<Map<String, Integer>> serviceLevelStatus(HappyParam param);

    List<CountModuleTempDO> getModuleScore(HappyParam param);
}
