package com.bank.manage.util;

import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.sysConst.NewProcessStatusFile;
import com.bank.manage.dos.NewProcessDO;
import com.bank.manage.dos.NewProcessHistoryDO;
import com.bank.manage.vo.NewProcessQueryVo;
import com.bank.user.utils.OrgIdUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @Author: Andy
 * @Date: 2020/4/22 10:19
 */
public class NewProcessUtil {
    /**
     * 流程历史 model
     *
     * @param processId    流程ID
     * @param opetateType  操作类型
     * @param userInfo     操作用户信息
     * @param rejectResion 驳回理由
     * @return
     */
    public static NewProcessHistoryDO getNewProcessModel(Integer processId, String opetateType, TokenUserInfo userInfo, String rejectResion) {
        NewProcessHistoryDO newProcessHistoryDO = NewProcessHistoryDO.builder()
                .processId(processId)
                .operatorId(userInfo.getUserId())
                .operatorName(userInfo.getUserName())
                .orgId(userInfo.getOrgId())
                .operateType(opetateType)
                .rejectReason(rejectResion)
                .createTime(LocalDateTime.now())
                .build();
        return newProcessHistoryDO;
    }


    /**
     * 组建 审核列表查询条件
     *
     * @param newProcessQueryVo 查询参数
     * @param queryType         查询类型
     * @param listOrgIds        当前用户组织机构号 及 下级机构号
     * @param tokenUserInfo     当前登录用户信息
     * @return
     */
    public static QueryWrapper<NewProcessDO> getQueryWrapper(NewProcessQueryVo newProcessQueryVo, String queryType, List<String> listOrgIds, TokenUserInfo tokenUserInfo) {
        QueryWrapper<NewProcessDO> queryWrapper = new QueryWrapper<>();
        //机构号
        queryWrapper.in("ORG_ID", listOrgIds);
        // 审核人不能是自己
        queryWrapper.ne("CREATOR_ID", tokenUserInfo.getUserId());
        if (NewProcessStatusFile.QUERY_TYPE_WAIT.equals(queryType)) {
            //待审核
            queryWrapper.eq("STATUS", NewProcessStatusFile.PROCESS_WAIT);
        }
        if (NewProcessStatusFile.QUERY_TYPE_ALREADY.equals(queryType)) {
            //已审核 或者 已驳回
            queryWrapper.and(wapper -> wapper.eq("STATUS", NewProcessStatusFile.PROCESS_PASS).or().eq("STATUS", NewProcessStatusFile.PROCESS_REJECT));
        }
        if (StrUtil.isNotBlank(newProcessQueryVo.getCreatorName())) {
            //提交人
            queryWrapper.eq("CREATOR_NAME", newProcessQueryVo.getCreatorName());
        }
        if (StrUtil.isNotBlank(newProcessQueryVo.getTradingModule())) {
            //类型
            queryWrapper.eq("TRADING_MODULE", newProcessQueryVo.getTradingModule());
        }
        //按创建时间倒序排序
        queryWrapper.orderByDesc("CREATE_TIME");

        return queryWrapper;
    }

}
