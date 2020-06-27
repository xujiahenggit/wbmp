package com.bank.manage.service.impl;

import com.bank.core.entity.BizException;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.sysConst.NewProcessStatusFile;
import com.bank.core.sysConst.SysStatus;
import com.bank.manage.dao.MonthAttendDao;
import com.bank.manage.dos.MonthAttendDO;
import com.bank.manage.dos.UsherDO;
import com.bank.manage.dto.CheckWorkRejectDto;
import com.bank.manage.dto.MonthAttendDto;
import com.bank.manage.service.MonthAttendService;
import com.bank.manage.service.UsherService;
import com.bank.manage.vo.CheckWorkAttendQueryVo;
import com.bank.manage.vo.MonthAttendPassRejectVo;
import com.bank.manage.vo.MonthAttendQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @Author: Andy
 * @Date: 2020/5/29 16:34
 */
@Service
public class MonthAttendServiceImpl extends ServiceImpl<MonthAttendDao, MonthAttendDO> implements MonthAttendService {


    @Resource
    private MonthAttendDao monthAttendDao;


    @Resource
    private UsherService usherService;

    /**
     *
     * @param monthAttendQueryVo 查询参数
     * @return
     */
    @Override
    public IPage<MonthAttendDO> getWaitList(MonthAttendQueryVo monthAttendQueryVo) {
        try{
            //用手机号获取 行员引导信息
            UsherDO usherDO=usherService.selectUsherByPhone(monthAttendQueryVo.getUserPhone());
            if(usherDO==null){
                throw new BizException("未查询到引导员信息");
            }
            QueryWrapper<MonthAttendDO> queryWrapper=getQueryWrapper(SysStatus.QUERY_TYPE_WAIT,usherDO.getOrgId());
            Page<MonthAttendDO> page=new Page<>(monthAttendQueryVo.getPageIndex(),monthAttendQueryVo.getPageSize());
            return monthAttendDao.selectPage(page,queryWrapper);
        }catch (Exception e){
            if(e instanceof BizException){
                throw new BizException(((BizException) e).getErrorMsg());
            }else {
                throw new BizException("获取已办列表失败");
            }
        }
    }

    /**
     * 已办列表
     *
     * @param monthAttendQueryVo 参数参数
     * @return
     */
    @Override
    public IPage<MonthAttendDO> getAreadyList(MonthAttendQueryVo monthAttendQueryVo) {
        try{
            //用手机号获取 行员引导信息
            UsherDO usherDO=usherService.selectUsherByPhone(monthAttendQueryVo.getUserPhone());
            if(usherDO==null){
                throw new BizException("未查询到引导员信息");
            }
            QueryWrapper<MonthAttendDO> queryWrapper=getQueryWrapper(SysStatus.QUERY_TYPE_AREADY,usherDO.getOrgId());
            Page<MonthAttendDO> page=new Page<>(monthAttendQueryVo.getPageIndex(),monthAttendQueryVo.getPageSize());
            return monthAttendDao.selectPage(page,queryWrapper);
        }catch (Exception e){
           if(e instanceof BizException){
               throw new BizException(((BizException) e).getErrorMsg());
           }else {
               throw new BizException("获取已办列表失败");
           }
        }
    }

    /**
     * 审核通过
     *
     * @param monthAttendPassRejectVo 审核参数
     * @param tokenUserInfo           当前登录用户
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean passMonthAttend(MonthAttendPassRejectVo monthAttendPassRejectVo, TokenUserInfo tokenUserInfo) {
        try{
            MonthAttendDO monthAttendDO=getMonthAttendModel(monthAttendPassRejectVo,tokenUserInfo,NewProcessStatusFile.CHECK_TYPE_PASS);
            this.updateById(monthAttendDO);
            return true;
        }catch (Exception e){
            throw new BizException("审核通过失败！");
        }
    }

    /**
     * 驳回审批
     * @param monthAttendPassRejectVo 审核参数
     * @param tokenUserInfo           当前登录用户
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean rejectMonthAttend(MonthAttendPassRejectVo monthAttendPassRejectVo, TokenUserInfo tokenUserInfo) {
        try{
            MonthAttendDO monthAttendDO=getMonthAttendModel(monthAttendPassRejectVo,tokenUserInfo,NewProcessStatusFile.CHECK_TYPE_REJECT);
            this.updateById(monthAttendDO);
            return true;
        }catch (Exception e){
            throw new BizException("驳回审批失败！");
        }
    }

    /**
     * 获取详细信息
     * @param monthAttendId 月度考勤编号
     * @return
     */
    @Override
    public MonthAttendDto getInfo(Integer monthAttendId) {
        return monthAttendDao.getInfo(monthAttendId);
    }

    /**
     * 月度考勤人数
     * @param date 日期
     * @param processPass 状态
     * @return
     */
    @Override
    public int getMonthAttendNum(String date, String processPass) {
        return monthAttendDao.getMonthAttendNum(date,processPass);
    }

    /**
     * 查询驳回人数 列表
     * @param checkWorkAttendQueryVo 查询参数
     * @param type 类型
     * @return
     */
    @Override
    public IPage<CheckWorkRejectDto> getRejectList(CheckWorkAttendQueryVo checkWorkAttendQueryVo, Integer type) {
        Page<CheckWorkRejectDto> page=new Page<>(checkWorkAttendQueryVo.getPageIndex(),checkWorkAttendQueryVo.getPageSize());
        return monthAttendDao.getRejectList(page,checkWorkAttendQueryVo.getDate(),type);
    }

    /**
     * 构建审核 模型
     * @param monthAttendPassRejectVo 审核参数
     * @param tokenUserInfo 当前登录用户
     * @param ProcessType 审核类型 通过 驳回
     * @return
     */
    private MonthAttendDO getMonthAttendModel(MonthAttendPassRejectVo monthAttendPassRejectVo,TokenUserInfo tokenUserInfo,String ProcessType){
        try{
            MonthAttendDO monthAttendDO=new MonthAttendDO();
            //设置编号
            monthAttendDO.setMonthAttendId(monthAttendPassRejectVo.getMonthAttendId());
            if(NewProcessStatusFile.CHECK_TYPE_PASS.equals(ProcessType)){
                //设置状态为审核通过
                monthAttendDO.setMonthAttendState(NewProcessStatusFile.PROCESS_PASS);
            }else if(NewProcessStatusFile.CHECK_TYPE_REJECT.equals(ProcessType)){
                //设置审核状态为 驳回
                monthAttendDO.setMonthAttendState(NewProcessStatusFile.PROCESS_REJECT);
                //设置审核 驳回理由
                monthAttendDO.setMonthAttendRejectResion(monthAttendPassRejectVo.getMonthAttendRejectResion());
            }
            //设置审批人 工号
            monthAttendDO.setMonthAttendProcessUserId(tokenUserInfo.getUserId());
            //设置审批人 姓名
            monthAttendDO.setMonthAttendProcessUserName(tokenUserInfo.getUserName());
            //设置审批时间
            monthAttendDO.setMonthAttendProcessTime(LocalDateTime.now());
            return monthAttendDO;
        }catch (Exception e){
            throw e;
        }
    }


    /**
     * 构建 查询条件 模型
     * @param queryType 查询类型 待办 已办
     * @param orgId 机构编号
     * @return
     */
    private QueryWrapper<MonthAttendDO> getQueryWrapper(String queryType,String orgId){
        QueryWrapper<MonthAttendDO> queryWrapper=new QueryWrapper<>();
        if(SysStatus.QUERY_TYPE_WAIT.equals(queryType)){
            queryWrapper.eq("MONTH_ATTEND_STATE",NewProcessStatusFile.PROCESS_WAIT);
        }else if(SysStatus.QUERY_TYPE_AREADY.equals(queryType)){
            queryWrapper.and(wrapper -> wrapper.or().eq("MONTH_ATTEND_STATE", NewProcessStatusFile.PROCESS_PASS).or().eq("MONTH_ATTEND_STATE", NewProcessStatusFile.PROCESS_REJECT));
        }
        queryWrapper.eq("MONTH_ATTEND_ORG_ID",orgId);
        queryWrapper.orderByDesc("MONTH_ATTEND_YEAR");
        return queryWrapper;
    }
}
