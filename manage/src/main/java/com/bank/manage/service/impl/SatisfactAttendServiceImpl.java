package com.bank.manage.service.impl;

import com.bank.core.entity.BizException;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.sysConst.NewProcessStatusFile;
import com.bank.core.sysConst.SysStatus;
import com.bank.manage.dao.SatisfactAttendDao;
import com.bank.manage.dos.*;
import com.bank.manage.dto.*;
import com.bank.manage.service.*;
import com.bank.manage.vo.CheckWorkAttendQueryVo;
import com.bank.manage.vo.SatisfactAttendQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Andy
 * @Date: 2020/5/29 21:18
 */
@Service
public class SatisfactAttendServiceImpl extends ServiceImpl<SatisfactAttendDao, SatisfactAttendDO> implements SatisfactAttendService {

    @Resource
    private SatisfactAttendDao satisfactAttendDao;


    @Resource
    private SatisfactAssessmentService satisfactAssessmentService;

    @Resource
    private SatisfactAttendItemService satisfactAttendItemService;

    @Resource
    private MonthAttendService monthAttendService;

    @Resource
    private MonthAttendItemService monthAttendItemService;

    @Resource
    private WorkSuppleService workSuppleService;

    /**
     * 待办列表
     *
     * @param satisfactAttendQueryVo 查询参数
     * @param tokenUserInfo          当前登录的用户
     * @return
     */
    @Override
    public IPage<SatisfactAttendDto> getWaitList(SatisfactAttendQueryVo satisfactAttendQueryVo, TokenUserInfo tokenUserInfo) {
        try {
            return getList(satisfactAttendQueryVo, tokenUserInfo.getOrgId(), SysStatus.QUERY_TYPE_WAIT);
        } catch (Exception e) {
            throw new BizException("获取待办列表失败！");
        }
    }

    /**
     * 已办列表
     *
     * @param satisfactAttendQueryVo 查询参数
     * @param tokenUserInfo          当前登录用户
     * @return
     */
    @Override
    public IPage<SatisfactAttendDto> getAreadyList(SatisfactAttendQueryVo satisfactAttendQueryVo, TokenUserInfo tokenUserInfo) {
        try {
            return getList(satisfactAttendQueryVo, tokenUserInfo.getOrgId(), SysStatus.QUERY_TYPE_AREADY);
        } catch (Exception e) {
            throw new BizException("获取已办列表失败！");
        }
    }

    /**
     * 获取满意度考核详细信息
     * 1.是否已经提交过
     * 如果已经提交过 直接获取提交过的内容
     * 如果未提交过 获取新的考核指标
     *
     * @param satisfactAttendId 满意度考核编号
     * @return
     */
    @Override
    public SatisfactAssessmentActDto SelectSatisfactInfo(Integer satisfactAttendId) {
        SatisfactAttendDO satisfactAttendDO = satisfactAttendDao.selectById(satisfactAttendId);
        if (satisfactAttendDO != null && NewProcessStatusFile.PROCESS_PASS.equals(satisfactAttendDO.getSatisfactAttendSubmitState())) {
            return satisfactAttendDao.SelectSatisfactInfo(satisfactAttendId);
        } else {
            //重新构建新的考核表单内容
            SatisfactAssessmentActDto satisfactAssessmentActDto = satisfactAttendDao.SelectSatisfactBasicInfo(satisfactAttendId);
            //一级指标列表
            List<SatisfactAssessmentDto> listAssessment = new ArrayList<>();
            //查询一级考核指标
            QueryWrapper<SatisfactAssessmentDO> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("SATISFACT_ASSESSMENT_TOPID ", 0);
            List<SatisfactAssessmentDO> list = satisfactAssessmentService.list(queryWrapper);
            if (list.size() > 0) {
                for (SatisfactAssessmentDO item : list) {
                    SatisfactAssessmentDto satisfactAssessmentDto = new SatisfactAssessmentDto();
                    //一级指标编号
                    satisfactAssessmentDto.setSatisfactAssessmentId(item.getSatisfactAssessmentId());
                    //一级指标描述
                    satisfactAssessmentDto.setSatisfactAssessmentDisc(item.getSatisfactAssessmentDisc());
                    //一级指标分值
                    satisfactAssessmentDto.setSatisfactAssessmentScore(item.getSatisfactAssessmentScore());
                    //实际得分
                    satisfactAssessmentDto.setSatisfactAssessmentTrueScore(item.getSatisfactAssessmentScore());
                    //内容
                    satisfactAssessmentDto.setSatisfactAssessmentContent(item.getSatisfactAssessmentContent());
                    //考核标准
                    satisfactAssessmentDto.setSatisfactAssessmentStandart(item.getSatisfactAssessmentStandart());
                    listAssessment.add(satisfactAssessmentDto);
                }
                //查询二级指标列表
                if (listAssessment.size() > 0) {
                    for (SatisfactAssessmentDto item : listAssessment) {
                        //二级指标列表
                        List<SatisfactSecondAssessmentDto> listSecondAssement = new ArrayList<>();
                        QueryWrapper<SatisfactAssessmentDO> queryWrapperSecond = new QueryWrapper<>();
                        queryWrapperSecond.eq("SATISFACT_ASSESSMENT_TOPID", item.getSatisfactAssessmentId());
                        List<SatisfactAssessmentDO> SecondAssessmentList = satisfactAssessmentService.list(queryWrapperSecond);
                        if (SecondAssessmentList.size() > 0) {
                            for (SatisfactAssessmentDO secondAssessment : SecondAssessmentList) {
                                SatisfactSecondAssessmentDto satisfactSecondAssessmentDto = new SatisfactSecondAssessmentDto();
                                //设置编号
                                satisfactSecondAssessmentDto.setSecondsatisfactAssessmentId(secondAssessment.getSatisfactAssessmentId());
                                //二级考核指标描述
                                satisfactSecondAssessmentDto.setSecondsatisfactAssessmentDisc(secondAssessment.getSatisfactAssessmentDisc());
                                //二级考核指标分值
                                satisfactSecondAssessmentDto.setSecondsatisfactAssessmentScore(secondAssessment.getSatisfactAssessmentScore());
                                //实际分值
                                satisfactSecondAssessmentDto.setSecondSatisfactTrueScore(secondAssessment.getSatisfactAssessmentScore());
                                //考核内容
                                satisfactSecondAssessmentDto.setSecondsatisfactAssessmentContent(secondAssessment.getSatisfactAssessmentContent());
                                //考核标准
                                satisfactSecondAssessmentDto.setSecondsatisfactAssessmentStandart(secondAssessment.getSatisfactAssessmentStandart());
                                listSecondAssement.add(satisfactSecondAssessmentDto);
                            }
                            item.setSecondAssessmentList(listSecondAssement);
                        }
                    }
                }
                satisfactAssessmentActDto.setListAssessment(listAssessment);
            }
            return satisfactAssessmentActDto;
        }
    }

    /**
     * 满意度考核提交
     * 1.修改满意度状态为 已提交,提交满意度数据(将数据保存到 满意度月度考核从表 T_SATISFACT_ATTEND_ITEM中)
     * 2.判断是否是最后 一条满意度数据
     * @param satisfactAssessmentActDto 满意度考核提交参数
     * @param tokenUserInfo 当前登录用户
     * @return
     */
    @Override
    public boolean submit(SatisfactAssessmentActDto satisfactAssessmentActDto, TokenUserInfo tokenUserInfo) {
        try {
            //修改满意度状态
            SatisfactAttendDO satisfactAttendDO=getSatisfactAttendModel(satisfactAssessmentActDto,tokenUserInfo);
            this.updateById(satisfactAttendDO);
            //提交满意度数据
            List<SatisfactAttendItemDO> list = getSatisfactAttendItemModelList(satisfactAssessmentActDto);
            satisfactAttendItemService.saveBatch(list);
            SatisfactAttendDO satisfactAttendDOQuery=this.getById(satisfactAssessmentActDto.getSatisfactAttendId());
            //2.判断是否是最后一条满意度
            List<SatisfactAttendDO> satisfactattentRejectList=satisfactAttendDao.getSatisfactAttendRejectSize(tokenUserInfo.getOrgId(),satisfactAttendDOQuery.getSatisfactAttendYear().toString());

            List<SatisfactAttendDO> satisfactattentPassList=satisfactAttendDao.getSatisfactAttendPassSize(tokenUserInfo.getOrgId(),satisfactAttendDOQuery.getSatisfactAttendYear().toString());
            //需要 项月度考勤审核列表推送 审核流程
            if(satisfactattentRejectList.size()==0){
                MonthAttendDO monthAttendDO=new MonthAttendDO();
                //设置考勤月份
                monthAttendDO.setMonthAttendYear(satisfactAttendDOQuery.getSatisfactAttendYear());
                //设置机构号
                monthAttendDO.setMonthAttendOrgId(tokenUserInfo.getOrgId());
                //设置机构名称
                monthAttendDO.setMonthAttendOrgName(tokenUserInfo.getOrgName());
                monthAttendService.save(monthAttendDO);
                List<MonthAttendItemDO> listMonthItem=new ArrayList<>();
                for (SatisfactAttendDO item:satisfactattentPassList){
                    MonthAttendItemDO monthAttendItemDO=new MonthAttendItemDO();
                    //主表ID
                    monthAttendItemDO.setMonthAttendId(monthAttendDO.getMonthAttendId());
                    //引导员ID
                    monthAttendItemDO.setUsherId(item.getUsherId());
                    //满意度考核得分
                    monthAttendItemDO.setMonthAttendScore(item.getSatisfactAttendScore());
                    //应出勤天数
                    monthAttendItemDO.setMonthAttendMustDays(10);
                    //实际出勤天数
                    monthAttendItemDO.setMonthAttendRealDays(10);
                    //工作日加班时长
                    float workWorkLength=workSuppleService.getRestWorkLenghth(item.getUsherId(),item.getSatisfactAttendYear(),SysStatus.WORK_TYPE_WORK);
                    monthAttendItemDO.setMonthAttendWorkLength(workWorkLength);
                    //休日加班时长
                    float restWorkLength=workSuppleService.getRestWorkLenghth(item.getUsherId(),item.getSatisfactAttendYear(),SysStatus.WORK_TYPE_REST);
                    monthAttendItemDO.setMonthAttendRestLenght(restWorkLength);
                    listMonthItem.add(monthAttendItemDO);
                }
                monthAttendItemService.saveBatch(listMonthItem);
            }
            return true;
        } catch (Exception e) {
            throw new BizException("满意度考核提交失败");
        }
    }


    /**
     * 获取满意度 人数
     * @param date 日期
     * @param processPass 状态
     * @return
     */
    @Override
    public int getatisfactAttendNum(String date, String processPass) {
        return satisfactAttendDao.getatisfactAttendNum(date,processPass);
    }

    /**
     * 月满意度 未完成人数列表
     * @param checkWorkAttendQueryVo 查询参数
     * @return
     */
    @Override
    public IPage<CheckWorkRejectDto> getSasifactWaitPerple(CheckWorkAttendQueryVo checkWorkAttendQueryVo) {
        Page<CheckWorkRejectDto> page=new Page<>(checkWorkAttendQueryVo.getPageIndex(),checkWorkAttendQueryVo.getPageSize());
        return satisfactAttendDao.getSasifactWaitPerple(page,checkWorkAttendQueryVo.getDate());
    }

    /**
     * 构建 审核满意度 模型
     * @param satisfactAssessmentActDto 满意度参数
     * @param tokenUserInfo 当前登录用户
     * @return
     */
    private SatisfactAttendDO getSatisfactAttendModel(SatisfactAssessmentActDto satisfactAssessmentActDto, TokenUserInfo tokenUserInfo){
        SatisfactAttendDO satisfactAttendDO=new SatisfactAttendDO();
        //设置编号
        satisfactAttendDO.setSatisfactAttendId(satisfactAssessmentActDto.getSatisfactAttendId());
        //设置状态为 已审核通过
        satisfactAttendDO.setSatisfactAttendSubmitState(NewProcessStatusFile.PROCESS_PASS);
        //设置考核实际得分
        satisfactAttendDO.setSatisfactAttendScore(satisfactAssessmentActDto.getSatisfactAttendScore());
        //设置提交人
        satisfactAttendDO.setSatisfactAttendSubmitUserid(tokenUserInfo.getUserId());
        //设置提交人姓名
        satisfactAttendDO.setSatisfactAttendSubmitUsername(tokenUserInfo.getUserName());
        //设置提交时间
        satisfactAttendDO.setSatisfactAttendSubmitTime(LocalDateTime.now());
        //设置主管领导评语
        satisfactAttendDO.setSatisfactAttendRemark(satisfactAssessmentActDto.getSatisfactAttendRemark());
        return satisfactAttendDO;
    }

    /**
     * 构建 满意度从表 列表模型
     * @param satisfactAssessmentActDto 保存参数
     * @return
     */
    private List<SatisfactAttendItemDO> getSatisfactAttendItemModelList(SatisfactAssessmentActDto satisfactAssessmentActDto) {
        try {
            List<SatisfactAttendItemDO> list = new ArrayList<>();
            for (SatisfactAssessmentDto item : satisfactAssessmentActDto.getListAssessment()) {
                for (SatisfactSecondAssessmentDto secondAssessmentDto : item.getSecondAssessmentList()) {
                    SatisfactAttendItemDO satisfactAttendItemDO = new SatisfactAttendItemDO();
                    //设置满意度主表编号
                    satisfactAttendItemDO.setSatisfactAttendId(satisfactAssessmentActDto.getSatisfactAttendId());
                    //设置一级指标ID
                    satisfactAttendItemDO.setSatisfactAssessmentFirstId(item.getSatisfactAssessmentId());
                    //设置二级指标ID
                    satisfactAttendItemDO.setSatisfactAssessmentSecondId(secondAssessmentDto.getSecondsatisfactAssessmentId());
                    //设置一级指标实际得分
                    satisfactAttendItemDO.setSatisfactAssessmentFirstCore(item.getSatisfactAssessmentTrueScore());
                    //设置二级指标实际得分
                    satisfactAttendItemDO.setSatisfactAssessmentSecondScore(secondAssessmentDto.getSecondSatisfactTrueScore());
                    //设置备注
                    satisfactAttendItemDO.setSatisfactAttendItemRemark(secondAssessmentDto.getSatisfactAttendItemRemark());
                    list.add(satisfactAttendItemDO);
                }
            }
            return list;
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * 按不同的类型 查询 列表
     *
     * @param satisfactAttendQueryVo 查询参数
     * @param orgId                  机构号
     * @param queryType              查询类别 待办 已办
     * @return
     */
    private IPage<SatisfactAttendDto> getList(SatisfactAttendQueryVo satisfactAttendQueryVo, String orgId, String queryType) {
        try {
            Page<SatisfactAttendDto> page = new Page<>(satisfactAttendQueryVo.getPageIndex(), satisfactAttendQueryVo.getPageSize());
            return satisfactAttendDao.getList(page, orgId, queryType);
        } catch (Exception e) {
            throw e;
        }
    }



}
