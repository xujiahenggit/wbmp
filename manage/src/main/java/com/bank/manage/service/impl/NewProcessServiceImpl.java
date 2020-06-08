package com.bank.manage.service.impl;

import cn.hutool.core.util.StrUtil;
import com.bank.core.entity.BizException;
import com.bank.core.entity.PageQueryModel;
import com.bank.core.entity.TokenUserInfo;
import com.bank.core.enums.ConstantEnum;
import com.bank.core.sysConst.ConstFile;
import com.bank.core.sysConst.NewProcessStatusFile;
import com.bank.manage.dao.NewProcessDao;
import com.bank.manage.dos.NewProcessDO;
import com.bank.manage.dos.NewProcessHistoryDO;
import com.bank.manage.dto.NewProcessDTO;
import com.bank.manage.dto.NewProcessInfoDto;
import com.bank.manage.service.MaterialService;
import com.bank.manage.service.NewProcessHistoryService;
import com.bank.manage.service.NewProcessService;
import com.bank.manage.util.NewProcessUtil;
import com.bank.manage.vo.NewProcessPassVo;
import com.bank.manage.vo.NewProcessQueryVo;
import com.bank.manage.vo.NewProcessRejectVo;
import com.bank.user.dao.UserDao;
import com.bank.user.dos.OrganizationDO;
import com.bank.user.dos.UserDO;
import com.bank.user.service.OrganizationService;
import com.bank.user.utils.OrgIdUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class NewProcessServiceImpl extends ServiceImpl<NewProcessDao,NewProcessDO> implements NewProcessService {
    @Resource
    private NewProcessDao newProcessDao;

    @Resource
    private OrganizationService organizationService;

    @Resource
    private NewProcessHistoryService newProcessHistoryService;

    @Resource
    @Lazy
    private MaterialService materialService;
    /**
     * 待审核 流程列表
     *
     * @param newProcessQueryVo 查询参数
     * @param tokenUserInfo 当前登录用户信息
     * @return
     */
    @Override
    public IPage<NewProcessDO> getWaitProcessList(NewProcessQueryVo newProcessQueryVo,TokenUserInfo tokenUserInfo) {
        //获取用户所在的 机构ID和下级机构ID
        List<OrganizationDO> organizationDOList=organizationService.list();
        List<String> listOrgIds=OrgIdUtils.returnFidList(tokenUserInfo.getOrgId(),organizationDOList);
        //待审核列表
        QueryWrapper<NewProcessDO> queryWrapper= NewProcessUtil.getQueryWrapper(newProcessQueryVo,NewProcessStatusFile.QUERY_TYPE_WAIT,listOrgIds,tokenUserInfo);
        Page<NewProcessDO> newProcessDOPage=new Page<>(newProcessQueryVo.getPageIndex(),newProcessQueryVo.getPageSize());
        return newProcessDao.selectPage(newProcessDOPage,queryWrapper);
    }

    /**
     * 获取首页审核待办数量
     * @param tokenUserInfo 当前用户机构号
     * @return
     */
    @Override
    public Integer getWaitProcessNum(TokenUserInfo tokenUserInfo) {
        //获取用户所在的 机构ID和下级机构ID
        List<OrganizationDO> organizationDOList=organizationService.list();
        List<String> listOrgIds=OrgIdUtils.returnFidList(tokenUserInfo.getOrgId(),organizationDOList);
        QueryWrapper<NewProcessDO> queryWrapper=new QueryWrapper<>();
        // 审核人不能是自己
        queryWrapper.ne("CREATOR_ID",tokenUserInfo.getUserId());
        //机构号
        queryWrapper.in("ORG_ID",listOrgIds);
        //待审核
        queryWrapper.eq("STATUS", NewProcessStatusFile.PROCESS_WAIT);
        Integer num=newProcessDao.selectCount(queryWrapper);
        log.info("待办审核数为："+num);
        return num;
    }

    /**
     * 查询 已办理的审核机构号
     * @param newProcessQueryVo 查询参数
     * @param tokenUserInfo 当前用户所登录的用户信息
     * @return
     */
    @Override
    public IPage<NewProcessDO> getPassProcessList(NewProcessQueryVo newProcessQueryVo, TokenUserInfo tokenUserInfo) {
        //获取用户所在的 机构ID和下级机构ID
        List<OrganizationDO> organizationDOList=organizationService.list();
        List<String> listOrgIds=OrgIdUtils.returnFidList(tokenUserInfo.getOrgId(),organizationDOList);
        //已审核列表
        QueryWrapper<NewProcessDO> queryWrapper= NewProcessUtil.getQueryWrapper(newProcessQueryVo,NewProcessStatusFile.QUERY_TYPE_ALREADY,listOrgIds,tokenUserInfo);
        Page<NewProcessDO> newProcessDOPage=new Page<>(newProcessQueryVo.getPageIndex(),newProcessQueryVo.getPageSize());
        return newProcessDao.selectPage(newProcessDOPage,queryWrapper);
    }

    /**
     * 审核通过
     *  先决条件 判断是否已经审核通过
     * 1.修改状态为 审核通过
     * 2.修改 素材强制状态
     * 2.添加 审核历史表
     * @param newProcessPassVo 流程编号
     * @param tokenUserInfo 当前登录的用户信息
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean passPorcess(NewProcessPassVo newProcessPassVo, TokenUserInfo tokenUserInfo) {
        try{
            //判断是否已经审核
            boolean flag=isAlreadyOpreaed(newProcessPassVo.getProcessId(),NewProcessStatusFile.CHECK_TYPE_PASS);
            if(flag){
                log.info("流程编号："+newProcessPassVo.getProcessId()+"已审核，无需重复审核");
                throw new BizException("该流程已经审核,无需重复操作！");
            }
            NewProcessDO passPorcess=newProcessDao.selectById(newProcessPassVo.getProcessId());
            if(passPorcess!=null && tokenUserInfo.getUserId().equals(passPorcess.getCreatorId())){
                throw new BizException("不能审核自己提交的流程！");
            }
            //1.修改状态 为审核通过
            NewProcessDO newProcess= NewProcessDO.builder()
                    //编号
                    .processId(newProcessPassVo.getProcessId())
                    //状态
                    .status(NewProcessStatusFile.PROCESS_PASS).build();
            newProcessDao.updateById(newProcess);
            //2.如果是素材审批 则需要修改 强制状态
            NewProcessDO processType=newProcessDao.selectById(newProcessPassVo.getProcessId());
            if(ConstantEnum.TRADE_TYPE_SCWJ.getType().equals(processType.getTradingType())){
                materialService.updateForcePlayState(newProcessPassVo.getListMaterial());
            }
            //3. 添加 流程历史
            NewProcessHistoryDO newProcessHistoryDO=NewProcessUtil.getNewProcessModel(newProcessPassVo.getProcessId(),NewProcessStatusFile.OPERATE_TYPE_PASS,tokenUserInfo,null);

            newProcessHistoryService.save(newProcessHistoryDO);
            return true;
        }catch (Exception e){
            if(e instanceof BizException){
                throw new BizException(((BizException) e).getErrorMsg());
            }else{
                throw new BizException("审核失败");
            }
        }
    }

    /**
     * 驳回审批
     * 1.修改审批状态
     * 2.添加审批 历史记录
     * @param newProcessRejectVo 参数
     * @param tokenUserInfo 当前登录用户信息
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean rejectProcess(NewProcessRejectVo newProcessRejectVo,TokenUserInfo tokenUserInfo) {
        try{
            //判断是否已经驳回
            boolean flag=isAlreadyOpreaed(newProcessRejectVo.getProcessId(),NewProcessStatusFile.CHECK_TYPE_REVOKE);
            if(flag){
                log.info("流程编号："+newProcessRejectVo.getProcessId()+"已驳回，无需重复驳回");
                throw new BizException("该流程已经驳回,无需重复操作！");
            }
            NewProcessDO passPorcess=newProcessDao.selectById(newProcessRejectVo.getProcessId());
            if(passPorcess!=null && tokenUserInfo.getUserId().equals(passPorcess.getCreatorId())){
                throw new BizException("不能审核自己提交的流程！");
            }
            //1.修改状态 为驳回
            NewProcessDO newProcess= NewProcessDO.builder()
                    //编号
                    .processId(newProcessRejectVo.getProcessId())
                    //状态
                    .status(NewProcessStatusFile.PROCESS_REJECT).build();
            newProcessDao.updateById(newProcess);
            //2. 添加 流程历史
            NewProcessHistoryDO newProcessHistoryDO=NewProcessUtil.getNewProcessModel(newProcessRejectVo.getProcessId(),NewProcessStatusFile.PROCESS_REJECT,tokenUserInfo,newProcessRejectVo.getRejectReason());

            newProcessHistoryService.save(newProcessHistoryDO);
            return true;
        }catch (Exception e){
           if(e instanceof BizException){
               throw new BizException(((BizException) e).getErrorMsg());
           }else {
               throw new BizException("驳回审批失败");
           }
        }
    }

    /**
     * 创建流程主体
     * 1.创建流程
     * 2.创建流程历史
     * @param newProcessDO 流程主体
     * @param tokenUserInfo 操作用户信息
     * @return
     */
    @Override
    public boolean createProcess(NewProcessDO newProcessDO,TokenUserInfo tokenUserInfo) {
        try{
            //1.创建流程
            newProcessDao.insert(newProcessDO);
            //2.创建流程历史
            NewProcessHistoryDO newProcessHistoryDO=NewProcessUtil.getNewProcessModel(newProcessDO.getProcessId(),NewProcessStatusFile.OPERATE_TYPE_CREATE,tokenUserInfo,null);
            newProcessHistoryService.save(newProcessHistoryDO);
            return true;
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 获取审批 类型列表
     * @return
     */
    @Override
    public List<String> listProcessModual() {
        return newProcessDao.listProcessModual();
    }

    /**
     * 获取流程详细信息
     * @param processId 流程编号
     * @return
     */
    @Override
    public NewProcessInfoDto getProcessInfo(Integer processId) {
        return newProcessDao.getProcessInfo(processId);
    }

    /**
     * 我的申请列表
     * @param newProcessQueryVo 查询参数
     * @param tokenUserInfo 当前登录用户信息
     * @return
     */
    @Override
    public IPage<NewProcessDO> getSelfList(NewProcessQueryVo newProcessQueryVo, TokenUserInfo tokenUserInfo) {
        QueryWrapper<NewProcessDO> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("CREATOR_ID",tokenUserInfo.getUserId());
        queryWrapper.orderByDesc("CREATE_TIME");
        Page<NewProcessDO> newProcessDOPage=new Page<>(newProcessQueryVo.getPageIndex(),newProcessQueryVo.getPageSize());
        return newProcessDao.selectPage(newProcessDOPage,queryWrapper);
    }

    /**
     * 撤销审核
     * 1.修改流程状态
     * 2.插入记录
     * @param processId 流程编号
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean revokeProcess(Integer processId,TokenUserInfo tokenUserInfo) {
        try{
            NewProcessDO newProcess=newProcessDao.selectById(processId);
            if(!newProcess.getCreatorId().equals(tokenUserInfo.getUserId())){
                log.info("流程编号："+processId+"已撤销，无需重复撤销");
                throw new BizException("撤回失败,撤回需本人操作！");
            }
            //判断是否已经撤销
            boolean flagRevoke=isAlreadyOpreaed(processId,NewProcessStatusFile.CHECK_TYPE_REVOKE);
            if(flagRevoke){
                log.info("流程编号："+processId+"已撤销，无需重复撤销");
                throw new BizException("该流程已经撤销,无需重复操作！");
            }
            //判断是否审核通过
            boolean flagPass=isAlreadyOpreaed(processId,NewProcessStatusFile.CHECK_TYPE_PASS);
            if(flagPass){
                log.info("流程编号："+processId+"已通过，不能撤销");
                throw new BizException("该流程已经审核通过,不能撤销！");
            }
            //判断是否驳回
            boolean flagReject=isAlreadyOpreaed(processId,NewProcessStatusFile.CHECK_TYPE_REJECT);
            if(flagReject){
                log.info("流程编号："+processId+"已驳回，不能撤销");
                throw new BizException("该流程已经已驳回,不能撤销！");
            }
            NewProcessDO newProcessDO=NewProcessDO.builder()
                    .processId(processId)
                    .status(NewProcessStatusFile.PROCESS_REVOKE).build();
            newProcessDao.updateById(newProcessDO);
            //2.保存记录
            NewProcessHistoryDO newProcessHistoryDO=NewProcessUtil.getNewProcessModel(processId,NewProcessStatusFile.OPERATE_TYPE_REVOKE,tokenUserInfo,null);
            newProcessHistoryService.save(newProcessHistoryDO);
            return true;
        }catch (Exception e){
            if(e instanceof BizException){
                throw new BizException(((BizException) e).getErrorMsg());
            }else{
                throw new BizException("流程撤销失败");
            }
        }
    }

    /**
     * 获取未办结 信息
     * @param newProcessQueryVo 查询条件
     * @param tokenUserInfo 当前登录用户信息
     * @return
     */
    @Override
    public IPage<NewProcessDO> getMyProcess(NewProcessQueryVo newProcessQueryVo, TokenUserInfo tokenUserInfo) {
        QueryWrapper<NewProcessDO> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("CREATOR_ID",tokenUserInfo.getUserId());
        queryWrapper.eq("STATUS",NewProcessStatusFile.PROCESS_WAIT);
        queryWrapper.eq("ACTIVE", "1");
        queryWrapper.orderByDesc("CREATE_TIME");
        Page<NewProcessDO> newProcessDOPage=new Page<>(newProcessQueryVo.getPageIndex(),newProcessQueryVo.getPageSize());
        return newProcessDao.selectPage(newProcessDOPage,queryWrapper);
    }

    /**
     * 判断流程是否审核完毕
     * @param processId 流程编号
     * @return 已操作过：true 未通过：false
     */
    private boolean isAlreadyOpreaed(Integer processId,String type){
        NewProcessDO newProcessDO=newProcessDao.selectById(processId);
        if(NewProcessStatusFile.CHECK_TYPE_PASS.equals(type)){
            //已通过
            if(StrUtil.isNotBlank(newProcessDO.getStatus()) && NewProcessStatusFile.PROCESS_PASS.equals(newProcessDO.getStatus())){
                return true;
            }
        }else if(NewProcessStatusFile.CHECK_TYPE_REJECT.equals(type)){
            //已驳回
            if(StrUtil.isNotBlank(newProcessDO.getStatus()) && NewProcessStatusFile.PROCESS_REJECT.equals(newProcessDO.getStatus())){
                return true;
            }
        }else if(NewProcessStatusFile.CHECK_TYPE_REVOKE.equals(type)){
            //已撤回
            if(StrUtil.isNotBlank(newProcessDO.getStatus()) && NewProcessStatusFile.PROCESS_REVOKE.equals(newProcessDO.getStatus())){
                return true;
            }
        }
        return false;
    }
}
